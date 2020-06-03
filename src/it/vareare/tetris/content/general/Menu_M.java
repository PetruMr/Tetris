package it.vareare.tetris.content.general;

import com.sun.javafx.tk.FontMetrics;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import it.vareare.tetris.content.other.*;
import javafx.stage.WindowEvent;
import tetris.elements.Particle;

import java.awt.*;

public class Menu_M {

    static private String [][] menu_m = new String[][] {
            {
                "PLAY", "SETTINGS", "EXIT"
            },
            {
                "START", "DIFFICULTY", "STYLE", "MODE", "SAVE GAME SETTINGS","UNDO CHANGES", "DEFAULT GAME SETTINGS", "BACK"
            },
            {
                "RESOLUTION", "FULLSCREEN", "VOLUME", "QUALITY", "SAVE SETTINGS", "UNDO CHANGES", "DEFAULT SETTINGS", "BACK"
            },
            {
                "RESUME", "SETTINGS", "LEAVE"
            }
    };

    static private String[] menuTitles_m = new String[] {
            "TETRIS",
            "PLAY",
            "SETTINGS",
            "PAUSE"
    };

    static private int MAIN_MENU_ID = 0;
    static private int PLAY_MENU_ID = 1;
    static private int SETTINGS_MENU_ID = 2;
    static private int GAME_MENU_ID = 3;

    static private int currentMenu = 0;
    static private int currentSelection = 0;

    static public void setCurrentMenu(int index) {
        if(index >= 0 && index < menu_m.length) {
            currentMenu = index;
            setCurrentSelection(0);
        }
        return;
    }

    static public void setCurrentSelection(int index) {
        if(index >= 0 && index < menu_m[currentMenu].length){
            currentSelection = index;
        }
        return;
    }

    static public int getCurrentMenu() {
        return currentMenu;
    }

    static public int getCurrentSelection() {
        return currentSelection;
    }

    static public String [][] getMenu_m() {
        return menu_m.clone();
    }

    static public void increaseCurrentSelection() {
        if(currentSelection+1 == menu_m[currentMenu].length) {
            currentSelection = 0;
        } else {
            currentSelection += 1;
        }
    }

    static public void decreaseCurrentSelection() {
        if(currentSelection == 0) {
            currentSelection = menu_m[currentMenu].length-1;
        } else {
            currentSelection -= 1;
        }
    }

    static private int getXForMenuPiece() {
        return (int)(Content.width*0.2);
    }

    static private int getYForMenuPiece( int index ) {
        return (int)((Content.height*(0.4 + (0.06*index))));
    }

    static public void startAnimation() {
        standardAnimation.start();
    }

    static public void handleSelected() {
        Content.log = menu_m[currentMenu][currentSelection];
        switch(menu_m[currentMenu][currentSelection]) {
            case "PLAY":
                setCurrentMenu(PLAY_MENU_ID);
                break;
            case "SETTINGS":
                setCurrentMenu(SETTINGS_MENU_ID);
                break;
            case "EXIT":
                callExit();
                break;
            case "BACK":
            case "LEAVE":
                setCurrentMenu(MAIN_MENU_ID);
                break;
            case "SAVE GAME SETTINGS":
            case "SAVE SETTINGS":
                Content.saveSettings();
                Content.updateSettings();
                break;
            case "UNDO CHANGES":
                Content.resetSettings();
                break;
            case "DEFAULT SETTINGS":
                Content.defaultSettings();
                break;
            case "DEFAULT GAME SETTINGS":
                Content.defaultGameSettings();
                break;
            default:
                changeSettingsWithHandleSelected();
        }
    }

    static private void changeSettingsWithHandleSelected() {
        Menu_M.handleIncreaseSelected();
    }

    public static void handleIncreaseSelected() {
        Content.log = menu_m[currentMenu][currentSelection] + " increased";
        switch(menu_m[currentMenu][currentSelection]) {
            case "DIFFICULTY":
                Content.changeDifficulty(1);
                break;
            case "STYLE":
                Content.changeStyle(1);
                break;
            case "MODE":
                Content.changeMode(1);
                break;
            case "RESOLUTION":
                Content.changeResolution(1);
                break;
            case "FULLSCREEN":
                Content.changeFullscreen();
                break;
            case "VOLUME":
                Content.changeVolume(1);
                break;
            case "QUALITY":
                Content.changeQuality(1);
                break;
            default:
                Content.log = menu_m[currentMenu][currentSelection] + " couldn't be increased";
                break;
        }
    }

    public static void handleDecreaseSelected() {
        Content.log = menu_m[currentMenu][currentSelection] + " decreased";
        switch(menu_m[currentMenu][currentSelection]) {
            case "DIFFICULTY":
                Content.changeDifficulty(-1);
                break;
            case "STYLE":
                Content.changeStyle(-1);
                break;
            case "MODE":
                Content.changeMode(-1);
                break;
            case "RESOLUTION":
                Content.changeResolution(-1);
                break;
            case "FULLSCREEN":
                Content.changeFullscreen();
                break;
            case "VOLUME":
                Content.changeVolume(-1);
                break;
            case "QUALITY":
                Content.changeQuality(-1);
                break;
            default:
                Content.log = menu_m[currentMenu][currentSelection] + " couldn't be decreased";
                break;
        }
    }

    static private void callExit() {
        Content.stage.fireEvent(new WindowEvent(Content.stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    static public AnimationTimer standardAnimation = new AnimationTimer() {
        @Override
        public void handle(long now) {
            Content.g.setFont(Font.font("Monospace",  FontWeight.BOLD, FontPosture.REGULAR,Content.width*0.1));
            Content.g.setFill(Color.WHITE);

            Content.g.fillText(menuTitles_m[currentMenu], Content.width*0.135,Content.height*0.3);

            Content.g.setFont(Font.font("Sans-serif", Content.width*0.02));
            Content.g.setFill(Color.WHITE);

            for(int i = 0; i < menu_m[currentMenu].length; i++) {
                Content.g.fillText(menu_m[currentMenu][i], Content.width*0.2,getYForMenuPiece(i));
            }
            illuminateCurrentMenu();
        }
    };

    static public void illuminateCurrentMenu() {
        Content.g.setFont(Font.font("Sans-serif", Content.width*0.02));
        new Particle(20, 0.5, 1, 180, 180, (int)(Content.width*0.18),
                (int)(getYForMenuPiece(currentSelection) - (int)(com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Content.g.getFont()).getLineHeight()/2)),
                (int)(com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Content.g.getFont()).getLineHeight()/4),
                (int)(com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Content.g.getFont()).getLineHeight()/4),
                Color.WHITE, 0.2, 50,
                0.6, 10, 1, Content.g).animation();
    }


    // Content.g.setFont(Font.font("Sans-serif", Content.width*0.02));
    // float width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth("PLAY", Content.g.getFont());
    // float height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Content.g.getFont()).getLineHeight();
    // Content.g.setFill(Color.DARKBLUE);
    // Content.g.fillRect(Content.width*0.2, Content.height*0.4-height, width, height);
    // width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth("SETTINGS", Content.g.getFont());
    // height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Content.g.getFont()).getLineHeight();
    // Content.g.setFill(Color.DARKBLUE);
    // Content.g.fillRect(Content.width*0.2, Content.height*0.46-height, width, height);
    // width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth("EXIT", Content.g.getFont());
    // height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Content.g.getFont()).getLineHeight();
    // Content.g.setFill(Color.DARKBLUE);
    // Content.g.fillRect(Content.width*0.2, Content.height*0.52-height, width, height);
}
