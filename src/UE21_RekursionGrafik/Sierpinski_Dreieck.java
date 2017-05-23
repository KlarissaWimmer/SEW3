package UE21_RekursionGrafik;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Point2D;

/**
 * Created by Klarissa on 03.04.17.
 */
public class Sierpinski_Dreieck extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Sierpinski-Dreieck");

        Canvas canvas = new Canvas(600, 600);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(new BorderPane(canvas)));
        primaryStage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        double size = 580;
        double height = (Math.sqrt(3)*size/2)/2;
        double width = size/2;
        Point2D p1 = new Point2D(width, 10);
        Point2D p2 =  new Point2D(width*2, height*2);
        Point2D p3 =  new Point2D(10,height*2);
        drawSierpinsikiTriangle(gc,10, p1, p2, p3);

    }


    public static void drawSierpinsikiTriangle(GraphicsContext gc, double sizemin, Point2D point1, Point2D point2, Point2D point3){
    gc.setStroke(Color.CHOCOLATE);
    if(point2.distance(point3) > sizemin) {
        double[] x = {point1.getX(), point2.getX(), point3.getX()};
        double[] y = {point1.getY(), point2.getY(), point3.getY()};
        gc.strokePolygon(x, y, 3);

        gc.strokeLine(point3.getX(),point3.getY(), point2.getX(), point2.getY());
        gc.strokeLine(point1.getX(),point1.getY(), point2.getX(), point2.getY());
        gc.strokeLine(point3.getX(),point3.getY(), point1.getX(), point1.getY());

        drawSierpinsikiTriangle(gc,10, point3, point2.midpoint(point3), point1.midpoint(point3));
        drawSierpinsikiTriangle(gc,10, point2, point3.midpoint(point2), point1.midpoint(point2));
        drawSierpinsikiTriangle(gc,10, point1, point2.midpoint(point1), point3.midpoint(point1));

    }
    }
}
