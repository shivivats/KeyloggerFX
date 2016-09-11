import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Shivi on 19-Aug-16.
 */
public class Main extends Application {

    Stage window;
    Scene scene;
    //FileWriter fw;
    FileWriter keyboardLog;
    BufferedWriter keyboardWriter;
    //FileWriter mouseLog, mouseWheelLog;
    KeyboardListener kl;
    //MouseListener ml;
    //MouseWheelListener mwl;
    boolean hookRegistered = false;
    boolean writerInit = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("KeyloggerFX");

        // TURNING LOGGER OFF TO REMOVE ANNOYING MESSAGES
        // Get the logger for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

        try {
            // true tells us to append the data to the file.
            //fw = new FileWriter("test" + ".txt", true);

            keyboardLog = new FileWriter("keyboard_log.txt", true);
            keyboardWriter = new BufferedWriter(keyboardLog);
            //mouseLog = new FileWriter("mouse_log.txt",true);
            //mouseWheelLog = new FileWriter("mouse_wheel_log.txt", true);

            kl = new KeyboardListener();
            //ml = new MouseListener();
            //mwl = new MouseWheelListener();
            GlobalScreen.registerNativeHook();
            hookRegistered = true;
        } catch (NativeHookException ne) {
            System.out.println("There was a problem registering the native hook.");
            System.out.println(ne.getMessage());
            hookRegistered = false;
            System.exit(1);
        } catch (Exception e) {
            // exception for bufferedwriter
        }

        scene = new Scene(createHomeScreen(), 600, 400);
        window.setScene(scene);
        window.show();
    }

    private GridPane createHomeScreen() {
        GridPane grid = new GridPane();
        // grid pane
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(8);

        // label for displaying message
        Label firstMessage = new Label("Welcome to KeyloggerFX");
        GridPane.setConstraints(firstMessage, 0, 0);

        Label secondMessage = new Label("This is a Keylogger made using JNativeHook library");
        GridPane.setConstraints(secondMessage, 0, 1);

        Label thirdMessage = new Label("Click on a button below to start logging keyboard/mouse sessions");
        GridPane.setConstraints(thirdMessage, 0, 2);

        // buttons for starting keyboard/mouse logging
        // open a new window that says keyboard logging is now in action. press esc to close logging
        // then log everything onto console/file

        /*
        Button all = new Button("Start All");
        all.setOnAction(e -> {
            try {
                keyboardLog.write(getDateStamp());
                //mouseLog.write(getDateStamp());
                //mouseWheelLog.write(getDateStamp());

                kl.setFw(keyboardLog);
                //ml.setFw(mouseLog);
                //mwl.setFw(mouseWheelLog);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("Keyboard listener started");
            GlobalScreen.addNativeKeyListener(kl);
        });
        GridPane.setConstraints(all, 0, 3);*/

        Button keyboard = new Button("Start keyboard listener");
        keyboard.setOnAction(e -> {
            try {
                keyboardWriter.newLine();
                keyboardWriter.write(getDateStamp());
                keyboardWriter.newLine();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                kl.setFw(keyboardWriter);
                writerInit = true;
            } catch (Exception e1) {
                e1.printStackTrace();
                writerInit = false;
            }
            System.out.println("Keyboard listener started");
            GlobalScreen.addNativeKeyListener(kl);
        });
        GridPane.setConstraints(keyboard, 0, 4);

        /*
        Button mouse = new Button("Start mouse listener");
        mouse.setOnAction(e -> {
            System.out.println("Mouse listener started");
            MouseListener ml = new MouseListener();
            GlobalScreen.addNativeMouseListener(ml);
            GlobalScreen.addNativeMouseMotionListener(ml);
        });
        GridPane.setConstraints(mouse, 0, 5);*/

        /*
        Button mouseWheel = new Button("Start mouse wheel listener");
        mouseWheel.setOnAction(e -> {
            System.out.println("Mouse wheel listener started");
            GlobalScreen.addNativeMouseWheelListener(new MouseWheelListener());
        });
        GridPane.setConstraints(mouseWheel, 0, 6);
        */

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            if (hookRegistered == true) {
                try {
                    GlobalScreen.unregisterNativeHook();
                } catch (NativeHookException e1) {
                    System.out.println("There was an error in unregistering the native hook");
                    e1.printStackTrace();
                }
            }
            if (writerInit == true) {
                try {
                    kl.closeFw();
                    //ml.closeFw();
                    //mwl.closeFw();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                keyboardLog.close();
                //mouseLog.close();
                //mouseWheelLog.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            window.close();
        });
        GridPane.setConstraints(closeButton, 0, 7);

        grid.getChildren().addAll(firstMessage, secondMessage, thirdMessage, keyboard, closeButton);
        return grid;
    }

    public String getDateStamp() {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();
        String dateStamp = "[" + df.format(date) + "]";
        return dateStamp;
    }
}
