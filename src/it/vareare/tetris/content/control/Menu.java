package it.vareare.tetris.content.control;

import it.vareare.tetris.content.animations.PerformanceViewer;
import it.vareare.tetris.content.general.Content;
import it.vareare.tetris.content.general.Menu_M;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import it.vareare.tetris.content.control.*;

public class Menu implements EventHandler<KeyEvent> {

    private static Menu instance = new Menu();
    public static Menu getInstance() {
        return instance;
    }

    @Override
    public void handle(KeyEvent event) {
        switch(event.getCode()) {
            case UP:
            case W:
                Menu_M.decreaseCurrentSelection();
                Content.log = ("Go up");
                break;
            case DOWN:
            case S:
                Menu_M.increaseCurrentSelection();
                Content.log = ("Go down");
                break;
            case SPACE:
            case ENTER:
                Menu_M.handleSelected();
                break;
            case RIGHT:
            case D:
                Menu_M.handleIncreaseSelected();
                break;
            case LEFT:
            case A:
                Menu_M.handleDecreaseSelected();
                break;
            case BACK_SLASH:
                if(!PerformanceViewer.getInstance().isRunning())
                    PerformanceViewer.getInstance().start();
                else
                    PerformanceViewer.getInstance().stop();
                Content.log = ("Debug");
                break;
            case F1:
                /* Nel caso ci siano problemi con i settings attuali, F1 "ripara" il gioco*/
                Content.defaultSettings();
                Content.defaultGameSettings();
                Content.saveSettings();
                Content.updateSettings();
                break;
            //case G:
            //    Content.log = ("Changed input to game");
            //    Content.changeInput(Game.getInstance());
            //    break;
        }
    }
}
