package UE22_Threads;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


/**
 * UE22_Threads
 * Created by Klarissa Wimmer
 * am Montag den 24.April.2017
 */

public class Pferderennen extends Application{
    public static void main(String[] args) {
            Application.launch(args);
        }

       private static final double SLIDER_MAX_VALUE = 50.0;
       public static TextArea textArea = new TextArea();
        int i = 0;
    @Override
    public void start(Stage stage) {
        stage.setTitle("Pferderennen");
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 550, 400);
        stage.setScene(scene);
        borderPane.setPadding(new Insets(10));
        Label label = new Label("Pferderennen - mit Threads");
        Button start = new Button("Start");
        Button plus = new Button("+");
        Button minus = new Button("-");
        List<ProgressBar> bar = new ArrayList<>();
        bar.add(new ProgressBar(0));

        textArea.setEditable(false);
        bar.get(i).setMaxWidth(Double.MAX_VALUE);
        bar.get(i).setMinWidth(380);

        VBox vbox2 = new VBox();
        vbox2.getChildren().addAll(label, bar.get(i));
        vbox2.setPadding(new Insets(0,20,50,0));
        vbox2.setAlignment(Pos.TOP_LEFT);

        EventHandler<ActionEvent> mehr = e -> {
            bar.add(new ProgressBar(0));
            i++;
            vbox2.getChildren().addAll(bar.get(i));
            bar.get(i).setMinWidth(380);
            textArea.appendText("Adding "+ bar.size() + " Bars \n");
        };
        EventHandler<ActionEvent> weniger = e -> {
            if(bar.size() > 1) {
                vbox2.getChildren().remove(bar.get(i));
                bar.remove(i);
                i--;
                textArea.appendText("Removing to "+ bar.size() + " Bars \n");
            }
        };

        EventHandler<ActionEvent> starten = (ActionEvent e) -> {
            textArea.clear();
            if(1 == bar.size()) textArea.appendText(bar.size() + " Pferd ist im Rennen \n \nErgebnis: \n");
            else textArea.appendText(bar.size() + " Pferde sind im Rennen \n \nErgebnis: \n");

            List<Pferd> pferde = new ArrayList<>();
                    for( int i = 0; i < bar.size(); i++) {
                        bar.get(i).setProgress(0);
                        pferde.add(new Pferd(bar.get(i),i+1));
                        pferde.get(i).start();
                    }
        };

        start.setOnAction(starten);
        plus.setOnAction(mehr);
        minus.setOnAction(weniger);

        VBox vbox1 = new VBox();
        vbox1.getChildren().addAll(plus, minus);
        vbox1.setPadding(new Insets(10,20,20,0));
        vbox1.setSpacing(5);
        vbox1.setAlignment(Pos.TOP_LEFT);

        VBox vbox3 = new VBox();
        vbox3.getChildren().addAll(start);
        vbox3.setPadding(new Insets(10,0,10,0));
        vbox3.setSpacing(5);
        vbox3.setAlignment(Pos.TOP_LEFT);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0,0,10,0));
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().addAll(vbox1, vbox2, vbox3);

        borderPane.setBottom(textArea);
        borderPane.setTop(hbox);

        stage.show();
    }

    public class Pferd extends Thread {
        ProgressBar progress = new ProgressBar();
        int pause;
        double schritt;
        int num;

        public Pferd(ProgressBar p, int number) {
            progress = p;
            num = number;
        }

        public void run() {
            while(1 >= progress.getProgress()) {
                pause = (int) (Math.random() * 1000);
                schritt = Math.random()/20;
                progress.setProgress(progress.getProgress() + schritt);
                try {
                    sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(()-> textArea.appendText("Horse/Thread-" + num + " ist im Ziel \n"));
        }
    }
}

