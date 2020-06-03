package it.vareare.tetris.content.control;

import it.vareare.tetris.content.animations.PerformanceViewer;
import it.vareare.tetris.content.general.Content;
import it.vareare.tetris.content.general.Menu_M;
import it.vareare.tetris.content.general.Settings;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import it.vareare.tetris.content.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

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
            case ESCAPE:
            case BACK_SPACE:
                Menu_M.setCurrentSelection(Menu_M.getMenu_m()[Menu_M.getCurrentMenu()].length - 1);
                Menu_M.handleSelected();
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
                Settings.defaultSettings();
                Settings.defaultGameSettings();
                Settings.saveSettings();
                Content.updateSettings();
                break;
            case G:
                Content.log = ("Changed input to game");
                Content.changeInput(Game.getInstance(), null);
                Menu_M.standardAnimation.stop();
                break;
        }
    }

    public static EventHandler<MouseEvent> mouseEventInMenuHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            double clickY = event.getY();
            double clickX = event.getX();
            Font font = Menu_M.getFontTable();
            Content.log = "Mouse clicked in cord: (" + clickX + "," + clickY + ")";
            //if()
            // Content.g.fillRect(Content.width*0.2, Content.height*0.46-height, width, height);
            // width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth("EXIT", Content.g.getFont());
            // height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Content.g.getFont()).getLineHeight();
        }
    };
}
