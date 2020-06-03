import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import tetris.controls.Controls;
import tetris.scenes.MainMenu;
import tetris.general.Content;
import tetris.scenes.PerformanceViewer;

/**
 * Test class
 */
public class Test extends Application {

    @Override
    public void init() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Content.init(primaryStage);

        Content.scene.setOnKeyPressed(Controls.debug);
        MainMenu.g = Content.g;

        MainMenu.a.start();
        //MainMenu.arrowTimer.start();
        PerformanceViewer.a.start();


    }
}
