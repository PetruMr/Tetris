package tetris.general;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tetris.elements.ColorPattern;

public class Content {

    static public Stage stage;
    static public Canvas canvas;
    static public GraphicsContext g;
    static public Scene scene;

    static public int width;
    static public int height;

    static public Color backgroundColor = ColorPattern.getColorAtId(0,7);

    static public AnimationTimer standardAnimation = new AnimationTimer() {
        @Override
        public void handle(long now) {
            Content.width  = (int) Content.stage.getWidth();
            Content.height = (int)Content.stage.getHeight();
            Content.canvas.setWidth(width);
            Content.canvas.setHeight(height);

            g.setFill(Content.backgroundColor);
            g.fillRect(0,0, Content.width, Content.height);
        }
    };

    static public void init( Stage stage) {
        Content.stage = stage;

        /* Canvas and other stuff of the window */
        Content.canvas = new Canvas(1000, 700);

        Content.g = Content.canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        Content.scene = new Scene(root);

        Content.stage.setTitle("Tetris");
        Content.stage.setScene(scene);
        Content.stage.show();

        standardAnimation.start();
    }
}
