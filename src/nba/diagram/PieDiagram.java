package nba.diagram;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

public class PieDiagram {
    private static JFreeChart pieChart;
    private static DefaultPieDataset dataset;
    
    public PieDiagram() { }

    // 画图前要构造变量 DefaultPieDataset 的值
    public PieDiagram(double[] values, String[] keys) {
        if (values.length != keys.length) {
            System.out.println("Error! Length of each paramter is not equal!");
            System.exit(0);
        }
        
        dataset = new DefaultPieDataset();
        
        for (int i = 0; i < values.length; ++i) {
            dataset.setValue(keys[i], values[i]);
        }

    }

    public void draw() {
        pieChart = ChartFactory.createPieChart("Pie Chart", dataset);

        ChartFrame frame = new ChartFrame("饼图 ", pieChart, true);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        double[] dataset = { 12, 3, 5, 7, 9, 4, 2 };
//        String[] fruits = { "apple", "banana", "lemon", "strawberry", "watermelon", "peer", "cherry" };
//        PieDiagram b = new PieDiagram(dataset, fruits);
//        b.draw();
//    }

}
