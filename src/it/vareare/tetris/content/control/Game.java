package it.vareare.tetris.content.control;

import it.vareare.tetris.content.animations.PerformanceViewer;
import it.vareare.tetris.content.general.Content;
import it.vareare.tetris.content.general.Menu_M;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Game implements EventHandler<KeyEvent> {

    private static Game instance = new Game();
    public static Game getInstance() {
        return instance;
    }

    @Override
    public void handle(KeyEvent event) {
        switch(event.getCode()) {
            case UP:
            case W:
                Content.log = ("Move up");
                break;
            case DOWN:
            case S:
                Content.log = ("Move down");
                break;
            case LEFT:
            case A:
                Content.log = ("Move left");
                break;
            case RIGHT:
            case D:
                Content.log = ("Move right");
                break;
            case SPACE:
            case ENTER:
                Content.log = ("Drop down");
                break;
            case ESCAPE:
            case P:
                Content.log = ("Pause");
            case G:
                Content.log = ("Changed input to menu");
                Content.changeInput(Menu.getInstance(), null);
                Menu_M.startAnimation();
                break;
            case BACK_SLASH:
                if(!PerformanceViewer.getInstance().isRunning())
                    PerformanceViewer.getInstance().start();
                else
                    PerformanceViewer.getInstance().stop();
                Content.log = ("Debug");
                break;
        }
    }
}