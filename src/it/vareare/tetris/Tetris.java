package it.vareare.tetris;

import it.vareare.tetris.content.general.Content;
import it.vareare.tetris.content.general.Menu_M;
import it.vareare.tetris.content.other.Particle;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Tetris extends Application {

    @Override
    public void init() {

    }

    @Override
    public void start(Stage prStage) throws InterruptedException {
        Content.init(prStage);
        Menu_M.startAnimation();
    }

    public static void main(String[] args) {
        Tetris.launch(args);
    }
}
