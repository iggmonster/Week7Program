import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Week7Program extends Application {

    private Stage stage = null;

    public void start(Stage stage) {

        boolean end = false;
        //initialize variables for scene
        int sceneX = 0;
        int sceneY = 0;
//scanner
        Scanner filesc = new Scanner(System.in);
        System.out.println("Input file name:");
        String filename = filesc.nextLine();

//group
        Group root = new Group( );

//file reading
        String line = "";
        String splitBy = " ";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null)
            {
                if (end == true){
                    break;
                }
                String[] linearray = line.split(splitBy);
                //System.out.println(Arrays.toString(linearray));

                String command = linearray[0];

//processing commands
                switch (command){
                    case "SIZE":
                        sceneX = Integer.parseInt(linearray[1]);
                        sceneY = Integer.parseInt(linearray[2]);
                        break;
                    case "LINE":
                        //System.out.println((Double.parseDouble(linearray[1]) + Double.parseDouble(linearray[2]) + Double.parseDouble(linearray[3]) + Double.parseDouble(linearray[4])));
                        root.getChildren().add(makeLine(Double.parseDouble(linearray[1]), Double.parseDouble((linearray[2])), Double.parseDouble(linearray[3]), Double.parseDouble(linearray[4])));
                        break;
                    case "CIRCLE":
                        root.getChildren().add(makeCircle(Double.parseDouble(linearray[1]), Double.parseDouble(linearray[2]), Double.parseDouble(linearray[3])));
                        break;
                    case "RECTANGLE":
                        root.getChildren().add(makeRectangle(Double.parseDouble(linearray[1]), Double.parseDouble(linearray[2]), Double.parseDouble(linearray[3]), Double.parseDouble(linearray[4])));
                        break;
                    case "TEXT":
                        String[] textarray = new String[linearray.length - 3];
                        for (int i = 3; i < linearray.length; i++){
                            textarray[i-3] = linearray[i];
                        }

                        String text = Arrays.toString(textarray)
                                .replace("[", "")
                                        .replace("]", "")
                                                .replace(", ", " ");
                        System.out.println(text);

                        root.getChildren().add(makeText(Double.parseDouble(linearray[1]), Double.parseDouble(linearray[2]), text));
                        break;
                    case "END":
                        end = true;
                        break;
                    default:
                        if (Arrays.toString(linearray).contains("//")){
                            break;
                    }
                        else if (command.equals("")){
                            break;
                        }
                        else {
                            System.out.println("Error reading line: " + Arrays.toString(linearray));
                        }
                        break;
                }

            }
        }
        catch (IOException e)
        {

        }

//make scene
        Scene scene = new Scene( root, sceneX, sceneY );
        scene.setFill(Color.BLACK);

        stage.setTitle(filename);
        stage.setScene(scene);
        stage.show();
    }

    private Line makeLine(double x1, double y1, double x2, double y2){
        Line line = new Line( x1, y1, x2, y2 );
        line.setStroke(Color.rgb(127, 244, 16));
        line.setStrokeWidth(1);
        return line;
    }
    private Circle makeCircle(double x, double y, double radius){
        Circle circle = new Circle( x, y, radius );
        circle.setFill(Color.BLACK);
        circle.setStroke(Color.rgb(127, 244, 16));
        circle.setStrokeWidth(1);
        return circle;
    }
    private Rectangle makeRectangle(double x, double y, double width, double height){
        Rectangle rect = new Rectangle( x, y, width, height );
        rect.setFill(Color.BLACK);
        rect.setStroke(Color.rgb(127, 244, 16));
        rect.setStrokeWidth(1);
        return rect;
    }
    private Text makeText(double x, double y, String string){
        Text text = new Text(x, y, string);
        text.setStroke(Color.rgb(127, 244, 16));
        return text;
    }

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) throws Exception{
        launch(args);
    }

}