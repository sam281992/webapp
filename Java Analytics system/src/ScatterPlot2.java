

import java.awt.RenderingHints;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.ui.ApplicationFrame;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;

public class ScatterPlot2 extends ApplicationFrame {
	public ScatterPlot2(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	private float[][] data1 = new float[2][10000];
	
	 public ChartPanel ScatterPlot21(Instances data) throws Exception {

	        //super(title);
	        populateData(data);
	        
	        final NumberAxis domainAxis = new NumberAxis("X");
	        domainAxis.setRange(2.5,4.5);
	        //domainAxis.setAutoRangeMinimumSize(3.00);
	        final NumberAxis rangeAxis = new NumberAxis("Y");
	        rangeAxis.setRange(50000,110000);
	       // rangeAxis.setAutoRangeMinimumSize(55000.00);
	        final FastScatterPlot plot = new FastScatterPlot(this.data1, domainAxis, rangeAxis);
	        final JFreeChart chart = new JFreeChart(" Scatter Plot", plot);


	        // force aliasing of the rendered content..
	        chart.getRenderingHints().put
	            (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        final ChartPanel panel = new ChartPanel(chart, true);
	        panel.setPreferredSize(new java.awt.Dimension(600, 400));
	        panel.setMinimumDrawHeight(10);
	        panel.setMaximumDrawHeight(2000);
	        panel.setMinimumDrawWidth(20);
	        panel.setMaximumDrawWidth(2000);
	        return panel;
//	        setContentPane(panel);

	 }
	 public void populateData(Instances data) throws Exception {
    	
    	data.setClassIndex(data.numAttributes()-1);
    	//build model
    	LinearRegression model = new LinearRegression();
    	model.buildClassifier(data); //the last instance with missing
    
    	//classify the last instance
    	for (int i = 0; i < data.size(); i++) {

    	Instance gre = data.get(i);
    	String s[] = (gre.toString().split(","));
    	float gpaScore = Float.parseFloat(s[0]);
    	float greScore = Float.parseFloat(s[1]);
        
            this.data1[0][i] = gpaScore;
            this.data1[1][i] = greScore;
        }

    }

}
