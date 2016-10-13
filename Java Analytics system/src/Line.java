

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class Line extends JFrame implements ChartMouseListener{
	
	
	private LinearRegression model;
	private Instances data;
	private JFreeChart chart;
	private Crosshair xCrosshair;
	private Crosshair yCrosshair;
	private ChartPanel chartPanel;
	
	public Line() throws Exception {
		super("XY Line Chart Example with JFreechart");
	}

 
    public JPanel createChartPanel(Instances data,File f, JTextField textField, File header ) throws Exception {
    	   String chartTitle = "Expected vs Predicted";
    	    String xAxisLabel = "X";
    	    String yAxisLabel = "Y";
    	    
    	    XYDataset dataset = createDataset(data,f, textField, header);
    	 
    	     chart = ChartFactory.createXYLineChart(chartTitle,
    	            xAxisLabel, yAxisLabel, dataset);
    	    this.chartPanel = new ChartPanel(chart);
    	    this.chartPanel.addChartMouseListener(this);
    	    XYPlot xyPlot = (XYPlot) chart.getPlot();
    	    NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
    	    domain.setRange(2.50, 4.50);
            domain.setTickUnit(new NumberTickUnit(0.1));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
            range.setRange(50000, 110000);
            range.setTickUnit(new NumberTickUnit(10000));
            CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
            this.xCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
            this.xCrosshair.setLabelVisible(true);
            this.yCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0f));
            this.yCrosshair.setLabelVisible(true);
            crosshairOverlay.addDomainCrosshair(xCrosshair);
            crosshairOverlay.addRangeCrosshair(yCrosshair);
            chartPanel.addOverlay(crosshairOverlay);
    	    return this.chartPanel;
    }

	public XYDataset createDataset(Instances data, File f, JTextField textField, File header) throws Exception
	{
		
		
		XYSeriesCollection dataset = new XYSeriesCollection();
	    XYSeries series1 = new XYSeries("Expected");
	    XYSeries series2 = new XYSeries("Predicted");
		
	    data.setClassIndex(data.numAttributes()-1);
	    
    	//build model
    	model = new LinearRegression();
    	model.buildClassifier(data); //the last instance with missing
    	
    	
    	//classify the last instance
    	for (int i = 0; i < data.size(); i++) {
    	//
    	Instance salaryData = data.get(i);
    	
    	String s[] = (salaryData.toString().split(","));
    	float greScore = Float.parseFloat(s[0]);
    	float salary = Float.parseFloat(s[1]);
    	
    	series1.add(greScore,salary);
    	double score = model.classifyInstance(salaryData);
    	
    	series2.add(greScore,score);
    	score = (double)Math.round(score*100)/100;
      
	}
    	dataset.addSeries(series1);
	    dataset.addSeries(series2);
	    
	    Instance Coordinate1 = data.get(10);
    	Instance Coordinate2 = data.get(1000);
    	double score = model.classifyInstance(Coordinate1);
    	double score1 = model.classifyInstance(Coordinate2);
    	String s[] = Coordinate1.toString().split(",");
    	String s1[] = Coordinate2.toString().split(",");
    	float gpaScore = Float.parseFloat(s[0]);
    	float gpaScore1 = Float.parseFloat(s1[0]);
    	
    	
    	textField.setText("Equation: y= "+ ((score1-score)/(gpaScore1-gpaScore))+"x "+"+ "+((score1-score)/(gpaScore1-gpaScore)*score));
	    
	    
		Instance1 i1 = new Instance1();
		Instances data1 = i1.performLinearRegression(f);
		BufferedReader br = new BufferedReader(new FileReader(header));
		String line1 = br.readLine();
		Instances combine = new Instances(new BufferedReader(new FileReader(f))); 
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/anmoljain/Desktop/TeamProject/JavaProj 3.0/src/reproducedFile.csv"));
		writer.write(line1+"\n");
		for (int i = 0; i < data1.size(); i++) {
			Instance trialSalaryData = data1.get(i);
			//String line2 = br.readLine();
			Instance combineInst = combine.get(i);
			double score2 = model.classifyInstance(trialSalaryData);
			score1 = (double)Math.round(score2*100)/100;
			String score3 = String.valueOf(score2);
	    	String rep = (combineInst.toString().replace(combineInst.toString().substring(combineInst.toString().lastIndexOf(',')+1,combineInst.toString().length()), score3))+"\n";
	    	writer.write(rep);
	    	
		}
		writer.flush();
    	writer.close();
    	
	    
		return dataset;
	}
	
	public void writePredictedValue(Instances data) throws Exception
	{
	}
	public void getLineEquation(Instances data) throws Exception
	{
		
	}


	@Override
	public void chartMouseClicked(ChartMouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void chartMouseMoved(ChartMouseEvent event) {
		Rectangle2D dataArea = this.chartPanel.getScreenDataArea();
        JFreeChart chart = event.getChart();
        XYPlot plot = (XYPlot) chart.getPlot();
        ValueAxis xAxis = plot.getDomainAxis();
        double x = xAxis.java2DToValue(event.getTrigger().getX(), dataArea, 
                RectangleEdge.BOTTOM);
        double y = DatasetUtilities.findYValue(plot.getDataset(), 0, x);
        this.xCrosshair.setValue(x);
        this.yCrosshair.setValue(y);
		
	}	    
}	
