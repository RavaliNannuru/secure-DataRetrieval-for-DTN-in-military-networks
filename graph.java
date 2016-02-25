package secure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart.
 */
public class graph extends ApplicationFrame 
{
    public graph(int val1,int val2) 
	{

        super("title");
        CategoryDataset dataset = createDataset(val1,val2);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(500, 400));
        setContentPane(chartPanel);

    }

    /**
     * Returns a sample dataset.
     * 
     * @return The dataset.
     */
    private static CategoryDataset createDataset(int val1,int val2) 
	{
	 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
	try
	{		
        // row keys...
        String series1 = "BSW";
        String series2 = "CP-ABE";

		String startTag = "<html><b><font size='2' color='black'>";
		String endTag = "</font></b></html>";
		
        // create the dataset...
      
		dataset.addValue((double)val1, series1, series1);
		dataset.addValue((double)val2, series2, series2);
		
	} 
	   catch (Exception e) 
	   {		
		e.printStackTrace();
	   }
        return dataset;
       
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) 
	{       
        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
            "Space Cost",         // chart title
            "Algorithms",         // domain axis label
            "Size",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, new Color(0, 0, 64)
        );
        GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, new Color(0, 64, 0)
        );
        GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, new Color(64, 0, 0)
        );
		
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) 
	{
        graph demo = new graph(10,13);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}