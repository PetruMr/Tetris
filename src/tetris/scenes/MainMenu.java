package tetris.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import tetris.general.Content;

public class MainMenu {







    static public GraphicsContext g;

    /**
     * Variable that contains the images of the menu
     * It contains in each of the z slots a different id menu
     * It contains in each of the y slots a different menu image
     * and in each of the x spots a different state of the image: "normal" - "hover" - "pressed"
     */
    static private Image[][][] menuImages = new Image[4][][];
    static final private int width = 300, height = 130;
    static private boolean started = false;
    static private int id = 0;
    /**
     * It indicates the current selected part of the menu
     * I has the index of the selected element
     */
    static private int selected = 0;
    /**
     * Creates a singleton of the menu type of object
     *  This is especially made for Tetris so the menus are preset
     *  Id 0 stands for the standard menu, which is the menu comprehending:
     *      -Start
     *      -Settings
     *      -Extra
     *      -Exit
     *  Id 1 stands for the settings menu, which is the menu comprehending:
     *      -Difficulty
     *      -Controls
     *      -Show performance
     *  Id 2 stands for the extras menu, which is the menu comprehending:
     *      -Credits
     *  Id 3 Stands for the in-game menu, which is the menu comprehending:
     *      -Controls
     *      -How to play
     *      -Exit
     */
    static public void startMenu() {
        MainMenu.menuImages[0] = new Image[4][2];
        // Start
        MainMenu.menuImages[0][0][0] = new Image("Images/StartNorml.png");
        MainMenu.menuImages[0][0][1] = new Image("Images/StartHover.png");
        // Settings
        MainMenu.menuImages[0][1][0] = new Image("Images/SettingsNorml.png");
        MainMenu.menuImages[0][1][1] = new Image("Images/SettingsHover.png");
        // Extra
        MainMenu.menuImages[0][2][0] = new Image("Images/StartNorml.png");
        MainMenu.menuImages[0][2][1] = new Image("Images/StartHover.png");
        // Exit
        MainMenu.menuImages[0][3][0] = new Image("Images/StartNorml.png");
        MainMenu.menuImages[0][3][1] = new Image("Images/StartHover.png");

        started = true;
    }

    /**
     * Prints the menu on the screen.
     * Id's are:
     *  0 - Standard menu
     *  1 - Settings menu
     *  2 - Extras menu
     *  3 - In-Game menu
     * @param g     The graphics context where to print the objects
     * @param id    The id of the current menu to print.
     */
    static public void printMenu(GraphicsContext g, int id, int widthScreen, int heightScreen) {
        MainMenu.startI();
        int menuLength = MainMenu.menuImages[id].length;
        int yStd = (heightScreen/(MainMenu.menuImages[id].length*2));
        int xStd = yStd*(300/130);
        int x = (widthScreen/2) - (xStd/2);
        for (int i = 0; i < MainMenu.menuImages[id].length; i++) {
            g.drawImage(MainMenu.menuImages[id][i][(MainMenu.selected == i) ? (1) : (0)], x, yStd * (1 + (i*1.5)), xStd, yStd);
        }
        MainMenu.id = id;
    }

    static private void startI() {
        if(!MainMenu.started) { startMenu();}
    }

    static public void selectNext() {
        MainMenu.selected--;
        if(MainMenu.selected < 0)
            MainMenu.selected = MainMenu.menuImages[MainMenu.id].length-1;
    }

    static public void selectPrev() {
        MainMenu.selected++;
        if(MainMenu.selected > MainMenu.menuImages[MainMenu.id].length-1)
            MainMenu.selected = 0;
    }

    static public String printable;

    public static void press() {
        switch (MainMenu.id){
            // Start Menu
            case 0:
                switch(MainMenu.selected){
                    // Start button
                    case 0:
                        MainMenu.printable = new String("Start");
                        break;
                    // Settings button
                    case 1:
                        MainMenu.printable = new String("Settings");
                        break;
                    // Extras button
                    case 2:
                        MainMenu.printable = new String("Extras");
                        break;
                    // Exit button
                    case 3:
                        MainMenu.printable = new String("Exit");
                        break;
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.err.println("Not avaible menu option id, " + MainMenu.id);
        }
    }

    static public int getId() { return MainMenu.id;};

    static public final AnimationTimer a = new AnimationTimer() {
        @Override
        public void handle(long now) {
            // To make sure even if resized its still fine and it can print as it should
            //printMenu(g, 0, Content.width, Content.height);
            //g.setFill(Color.BLACK);
            //g.fillText(MainMenu.printable, 10, 20);
        }
    };

    static public final Image selectingArrow= new Image("Images/SelectingArrow.bmp", 12, 12, true, true);

    static public final AnimationTimer arrowTimer = new AnimationTimer() {

        final Image prova = new Image("Images/SelectingArrow.png");
        final long[] last = {0};
        final double[] height = {0};
        final double[] mHeight = {150};
        int spdXS = 600;
        final double[] divX = {(double)spdXS / 1000000000};
        final boolean[] side = {true};
        final double[] actualHeight = {150};

        @Override
        public void handle(long now) {
            double diffTime = now - last[0];
            double thing = divX[0] * diffTime;
            last[0] = now;

            if (side[0]) {
                height[0] += thing;
            } else {
                height[0] -= thing;
            }

            if(height[0] + thing >= mHeight[0] && side[0]) {
                height[0] = mHeight[0];
                side[0] = false;
            } else if (height[0] - thing <= -mHeight[0] && !side[0]) {
                height[0] = -mHeight[0];
                side[0] = true;
            }
            //System.out.println(height[0] + " " + thing + " " + diffTime);
            g.drawImage(prova, 150, actualHeight[0] - (height[0]/2), 150,height[0]);
            g.setFont(Font.font(10));
            g.fillText("HEIGHT: " + height[0], 10, 80);
            g.fillText("VARYING: " + thing, 10, 95);
            g.fillText("DIFFTIME: " + diffTime, 10, 110);

            g.setFont(Font.font(300));
            g.fillText(">", 100, 600);
        }
    };
}
