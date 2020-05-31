import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The game's menu.
 * This is a class that has a menu and that has methods related to that menu
 * It is used to create buffered images
 */
public class Menu {

    /**
     * Variable that contains the images of the menu
     * It contains in each of the z slots a different id menu
     * It contains in each of the y slots a different menu image
     * and in each of the x spots a different state of the image: "normal" - "hover" - "pressed"
     */
    static private Image [][][] menuImages = new Image[4][][];
    static final private int width = 300, height = 130;
    static private boolean started = false;
    static private int id = 0;
    /**
     * It indicates the current selected part of the menu
     * I has the index of the selected element
     */
    public static int selected = 0;
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
    static private void startMenu() {
        Menu.menuImages[0] = new Image[4][2];
        // Start
        Menu.menuImages[0][0][0] = new Image("Images/StartNorml.png");
        Menu.menuImages[0][0][1] = new Image("Images/StartHover.png");
        // Settings
        Menu.menuImages[0][1][0] = new Image("Images/SettingsNorml.png");
        Menu.menuImages[0][1][1] = new Image("Images/SettingsHover.png");
        // Extra
        Menu.menuImages[0][2][0] = new Image("Images/StartNorml.png");
        Menu.menuImages[0][2][1] = new Image("Images/StartHover.png");
        // Exit
        Menu.menuImages[0][3][0] = new Image("Images/StartNorml.png");
        Menu.menuImages[0][3][1] = new Image("Images/StartHover.png");

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
    static public void printMenuStart(GraphicsContext g, int id, int widthScreen, int heightScreen) {
        Menu.startI();
        int menuLength = Menu.menuImages[id].length;
        int yStd = (heightScreen/(Menu.menuImages[id].length*2));
        int xStd = yStd*(300/130);
        int x = (widthScreen/2) - (xStd/2);
        for (int i = 0; i < Menu.menuImages[id].length; i++) {
            g.drawImage(Menu.menuImages[id][i][(Menu.selected == i) ? (1) : (0)], x, yStd * (1 + (i*1.5)), xStd, yStd);
        }
        Menu.id = id;
    }

    static private void startI() {
        if(!Menu.started) { startMenu();}
    }

    static public void selectHigherOne() { Menu.selected--; if(Menu.selected < 0) Menu.selected = Menu.menuImages[Menu.id].length-1; }
    static public void selectLowerOne() { Menu.selected++; if(Menu.selected > Menu.menuImages[Menu.id].length-1) Menu.selected = 0; }

    static public void pressed( Tetris te) { pressedI( te); }

    private static void pressedI(Tetris te) {
        switch (Menu.id){
            // Start Menu
            case 0:
                switch(Menu.selected){
                    // Start button
                    case 0:
                        Tetris.printable = new String("Start");
                        break;
                    // Settings button
                    case 1:
                        Tetris.printable = new String("Settings");
                        break;
                    // Extras button
                    case 2:
                        Tetris.printable = new String("Extras");
                        break;
                    // Exit button
                    case 3:
                        Tetris.printable = new String("Exit");
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
                System.err.println("Not avaible menu option id, " + Menu.id);
        }
    }

    static public int getId() { return Menu.id;};
}
