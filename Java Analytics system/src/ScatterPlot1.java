

import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;

import org.jfree.chart.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.ApplicationFrame;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class ScatterPlot1 extends ApplicationFrame {

	public ScatterPlot1(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	private float[][] data1 = new float[2][10000];
	private ChartPanel panel;
	 
	public ChartPanel ScatterPlot12(Instances data) throws Exception {

	       // super(title);
	        populateData(data);
	        
	        NumberAxis domainAxis = new NumberAxis("X");
	        //domainAxis.setAutoRangeIncludesZero(false);
	        domainAxis.setRange(2.0,4.5);
	        NumberAxis rangeAxis = new NumberAxis("Y");
	        rangeAxis.setRange(50000,110000);
	        //rangeAxis.setAutoRangeMinimumSize(55000);
	        final FastScatterPlot plot = new FastScatterPlot(this.data1, domainAxis, rangeAxis);
	        plot.zoom(50.0);
	        final JFreeChart chart = new JFreeChart("Scatter Plot(Predicted)", plot);
	        
	        
	        // force aliasing of the rendered content..
	        chart.getRenderingHints().put
	            (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        
	        panel = new ChartPanel(chart, true);
	        panel.setPreferredSize(new java.awt.Dimension(600, 400));
	        panel.setMinimumDrawHeight(10);
	        //panel.setMaximumDrawHeight(2000);
	        panel.setMinimumDrawWidth(20);
	        //panel.setMaximumDrawWidth(2000);
	        //Rectangle2D dataArea = this.panel.getScreenDataArea();
	        panel.setDomainZoomable(true);
	        panel.setRangeZoomable(true);
	        return panel;
	        //setContentPane(panel);

	 }
	 public void populateData(Instances data) throws Exception {
    	
    	data.setClassIndex(data.numAttributes()-1);
    	//build model
    	LinearRegression model = new LinearRegression();
    	model.buildClassifier(data); //the last instance with missing
    	//System.out.println(model);
    	//classify the last instance
    	for (int i = 0; i < data.size(); i++) {
    	//System.out.println(data.size());
    	Instance gre = data.get(i);
    	
    	String s[] = (gre.toString().split(","));
    	float gpaScore = Float.parseFloat(s[0]);
    	//float greScore = Float.parseFloat(s[1]);
    	float score = (float) model.classifyInstance(gre);
        //    final float x = (float) i + 1000;
            this.data1[0][i] = gpaScore;
            this.data1[1][i] = score;
        }

    }

}
