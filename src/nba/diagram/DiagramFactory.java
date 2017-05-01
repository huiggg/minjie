package nba.diagram;

public class DiagramFactory {
    
    public static BarDiagram createBarDiagram(double[] values, String[] keys) {
        BarDiagram bd = new BarDiagram(values, keys);
        return bd;
    }
    
    public static LineDiagram createLineDiagram(double[] values, String[] keys) {
        LineDiagram ld = new LineDiagram(values, keys);
        return ld;
    }
    
    public static PieDiagram createPieDiagram(double[] values, String[] keys) {
        PieDiagram pd = new PieDiagram(values, keys);
        return pd;
    }
    
//    public static void main(String[] args) {
//        double[] dataset = { 12, 3, 5, 7, 9, 4, 2 };
//        String[] fruits = { "apple", "banana", "lemon", "strawberry", "watermelon", "peer", "cherry" };
//        BarDiagram bd = DiagramFactory.createBarDiagram(dataset, fruits);
//        bd.draw("hello", "world");
//        LineDiagram ld = DiagramFactory.createLineDiagram(dataset, fruits);
//        ld.draw("hello", "world");
//        PieDiagram pd = DiagramFactory.createPieDiagram(dataset, fruits);
//        pd.draw();
//    }
}
