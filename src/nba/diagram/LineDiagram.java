package nba.diagram;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public class LineDiagram {
    private static String xAxis = "";
    private static JFreeChart lineChart;
    private static DefaultCategoryDataset dataset;
    
    public LineDiagram() { }

    // 画图前要构造变量 DefaultCategoryDataset 的值
    public LineDiagram(double[] values, String[] keys) {
        if (values.length != keys.length) {
            System.out.println("Error! Length of each paramter is not equal!");
            System.exit(0);
        }
        
        dataset = new DefaultCategoryDataset();
        
        for (int i = 0; i < values.length; ++i) {
            dataset.addValue(values[i], xAxis, keys[i]);
        }

    }

    // 画图横坐标默认"category", 纵坐标默认”value“
    public void draw() {
        lineChart = ChartFactory.createLineChart("Bar Chart", "category", "value", dataset);

        ChartFrame frame = new ChartFrame("折线图 ", lineChart, true);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }
    
    // 画图自定义类别类型（横坐标）和值类型（纵坐标）
    public void draw(String categoryAxisLabel, String valueAxisLabel) {
        lineChart = ChartFactory.createLineChart("Line Chart", categoryAxisLabel, valueAxisLabel, dataset);

        ChartFrame frame = new ChartFrame("折线图 ", lineChart, true);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        double[] dataset = { 12, 3, 5, 7, 9, 4, 2 };
//        String[] fruits = { "apple", "banana", "lemon", "strawberry", "watermelon", "peer", "cherry" };
//        LineDiagram b = new LineDiagram(dataset, fruits);
//        b.draw();
//    }

}
