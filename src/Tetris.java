import tetris.elements.ColorPattern;
import tetris.elements.SingleBlock;
import tetris.elements.SingleBlockIMG;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// For performance
import com.sun.javafx.perf.PerformanceTracker;

/*
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *  WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */



/**
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * TETRIS - Main class
 * developed with jfx
 * @author Marincas Petru Marcel
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * /////////////////////////////////////////// Diary of work done //////////////////////////////////////////////////
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * Date: -  17/01/2020 - Version 0.0:
 *                              + Starting of the project. Laying down various classes that i will use.
 *                     - Work time:
 *                              + Not uniform 2-3 hours.
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *       -  26/01/2020 - Version 0.1:
 *                              + All blocks. They are printable with the specific position
 *                              + Each block has a light and a dark part
 *                              + Lighter and darker colors
 *                              + Method to print the blocks
 *                              + Rotation of the blocks
 *                              + No bugs. Yet.
 *                              + Can rotate and move with input and it works well and fast with the blocks.
 *                              + Deciding on the menus, which are planned to be images
 *                      - Work time:
 *                              + Not uniform 3-5 hours.
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *       -  23/03/2020 - Version 0.1:
 *                              + Created a Package named Elements where i plan to contain all tetris.elements.elements, like the menu
 *                                and the various block classes.
 *                              + I "benchmarked" Image vs Polygon type of blocks and Image wins by a lot :
 *                                  Number of polygons - FPS with Image - FPS with PLY block
 *                                  200   - 60 - 60
 *                                  400   - 60 - 50
 *                                  1000  - 60 - 30
 *                                  3000  - 60 - 25
 *                                  3400  - 1  - 25 // Cap for Image
 *                                  10000 - ?? - 10
 *                              + Although Image is faster at printing, its a bit slower at loading, needing a few
 *                                seconds to load 10000 Images, as compared to the very fast PLY loading.
 *                      - Work time:
 *                              + From 04:06 AM to 05:45 AM
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *      - 28/03/2020 - Version 0.1.1:
 *                              + Created packages to better view the work done (controls, elements, scenes)
 *                              + Decided to make a resolution option. All things in the game are images that are
 *                                  loaded and if they are of the current resolution they are used.
 *                              + The game will have full screen or not full screen mode. It won't be resizable
 *                              + I found out that Image automatically upscales the image to its prefference
 *                                  If i keep the image at the size the bitmap/png is i can keep it with a good aspect
 *                                  Because of this, i will create various png textures to apply on various resolutions
 *                                  I run benchamarks for Images/Polygons/Rectangles and 1 and 2 did basicalyl the same
 *                                  whilst polygons consumed a lot of CPU to run.
 *                                  Because of this i chose to use Images with various resolutions instead of making a
 *                                  personal pixel art reader and writer for my images. It's way too time consuming and i
 *                                  fear that the performance may still be behind the image because of the various
 *                                  multiplications and such that need to be done to place the rectangles that compose
 *                                  something.
 *                       - Work time:
 *                              + From 15:30 to 19:15
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *      - 29/03/2020 - Version 0.1.1:
 *                              + Working on particles
 *                      - Work time:
 *                              + From 19:20 to 21:40 // from 23:30 - 1:30
 *
 *
 */
public class Tetris extends Application {
    public Tetris t = this;

    public TetrisBlocks blocks = new TetrisBlocks();
    public int rot = 0;
    public int x = 0;
    public AnimationTimer menuScreen, gameTimer;
    public EventHandler<KeyEvent> menuASD,menuQWE, menuControls;
    static public String printable = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Block and menu initialization */
        this.blocks.initializeBlocks(20,20,0);

        /* Canvas and other stuff of the window */
        Canvas canvas = new Canvas(1000, 700);

        GraphicsContext g = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Tetris");
        primaryStage.setScene(scene);
        primaryStage.show();

        /* ------------------------------------------------------------------------------------------------------ */
        /* ------------------------------------------------ INPUT ----------------------------------------------- */
        /* ------------------------------------------------------------------------------------------------------ */

        final int[] i = {1};
        menuControls = new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.W) {
                    Menu.selectHigherOne();
                }
                if(event.getCode() == KeyCode.S) {
                    Menu.selectLowerOne();
                }
                if(event.getCode() == KeyCode.ENTER) {
                    Menu.pressed(t);
                    i[0]+=1000;
                }
            }
        };

        menuASD = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCharacter());
                if(event.getCode() == KeyCode.UP) {
                    rot++;
                }
                if(event.getCode() == KeyCode.DOWN) {
                    rot--;
                }
                if(event.getCode() == KeyCode.RIGHT) {
                    x+=40;
                }
                if(event.getCode() == KeyCode.LEFT) {
                    x-=40;
                }
                if(event.getCode() == KeyCode.SPACE) {
                    primaryStage.close();
                }
                if(event.getCode() == KeyCode.G) {
                    menuScreen.stop();
                    gameTimer.start();
                    scene.setOnKeyPressed(menuQWE);
                }
                if(event.getCode() == KeyCode.W) {
                    Menu.selected++;
                }
                if(event.getCode() == KeyCode.S) {
                    Menu.selected--;
                }
            }
        };

        menuQWE = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCharacter());
                if(event.getCode() == KeyCode.UP) {
                    rot++;
                }
                if(event.getCode() == KeyCode.DOWN) {
                    rot--;
                }
                if(event.getCode() == KeyCode.RIGHT) {
                    x+=40;
                }
                if(event.getCode() == KeyCode.LEFT) {
                    x-=40;
                }
                if(event.getCode() == KeyCode.SPACE) {
                    primaryStage.close();
                }
                if(event.getCode() == KeyCode.G) {
                    menuScreen.start();
                    gameTimer.stop();
                    scene.setOnKeyPressed(menuASD);
                }
                if(event.getCode() == KeyCode.W) {
                    Menu.selected++;
                }
                if(event.getCode() == KeyCode.S) {
                    Menu.selected--;
                }
            }
        };



        scene.setOnKeyPressed(menuControls);

        /* ------------------------------------------------------------------------------------------------------ */
        /* --------------------------------------------- DRAW HANDLER ------------------------------------------- */
        /* ------------------------------------------------------------------------------------------------------ */
        //SingleBlock[] printin = new SingleBlock[10000];
        //for(int k = 0; k < 10000; k++) {
        //    printin[k] = new SingleBlockIMG(50, 50);
        //}
        menuScreen = new AnimationTimer(){
            @Override
            public void handle(long now) {
                /* Pre-emptive measures to help the window work */

                // To make sure even if resized its still fine and it can print as it should
                int width = (int)primaryStage.getWidth();
                int height = (int)primaryStage.getHeight();
                canvas.setWidth(width);
                canvas.setHeight(height);
           //     // Filling the background, standard struff
                g.setFill(Color.rgb(39,39,39));
                g.fillRect(0,0, width, height);
//
           //     /* Now, the window stuff */
           //     //blocks.getBlock(0).printBlock(g, 40, 50, 0);
           //     //blocks.getBlock(1).printBlock(g, 100, 50, 0);
           //     //blocks.getBlock(2).printBlock(g, 160, 50, 0);
           //     //blocks.getBlock(3).printBlock(g, 220, 50, 0);
           //     //blocks.getBlock(4).printBlock(g, 40, 120, 0);
           //     //blocks.getBlock(5).printBlock(g, 100, 120, 0);
           //     //blocks.getBlock(6).printBlock(g, 160, 120, 0);
           //     //blocks.getBlock(1).printBlock(g, x+300, 150, rot);
                Menu.printMenuStart(g, 0, width, height);
                for(int j = 0; j < i[0]; j++) {
                    g.setFill(ColorPattern.getColorAtId(0,j%6));
                    g.fillRect((int)(x + 300 + (j/10)), (int)(150 + (j/10)), 50, 50);
                            //g, x + 300 + (j), 150 + j);
                }
            }
        };

        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                /* Pre-emptive measures to help the window work */

                // To make sure even if resized its still fine and it can print as it should
                int width = (int)primaryStage.getWidth();
                int height = (int)primaryStage.getHeight();
                canvas.setWidth(width);
                canvas.setHeight(height);
                // Filling the background, standard struff
                g.setFill(Color.BLACK);
                g.fillRect(0,0, width, height);

                /* Now, the window stuff */
                //blocks.getBlock(1).printBlock(g, x+300, 150, rot);
                Menu.printMenuStart(g, 0, width, height);
            }
        };

        menuScreen.start();

        PerformanceTracker tracker = PerformanceTracker.getSceneTracker(scene);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                g.setFont(Font.font(10));
                g.setFill(Color.WHITE);
                g.fillText("FPS_acc: "+ (int)tracker.getAverageFPS(), 10, 20);
                tracker.resetAverageFPS();
                g.fillText("FPS: " + (int)tracker.getInstantFPS(), 10, 35);
                g.fillText("CM: " + Menu.getId(), 10, 50);
                g.fillText("PRN: " + Tetris.printable, 10, 65);
                g.fillText("I: " + i[0], 10, 80);
            }
        }.start();

        final Image prova = new Image("Images/StartHover.png");
        final long[] last = {0};
        final double[] height = {0};
        final double[] mHeight = {130};
        int spdXS = 600;
        final double[] divX = {(double)spdXS / 1000000000};
        System.out.println(divX[0]);
        final boolean[] side = {true};
        final double[] actualHeight = {150};

        new AnimationTimer() {
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
                g.drawImage(prova, 150, actualHeight[0] - (height[0]/2), 300,height[0]);
                g.setFont(Font.font(10));
                g.fillText("HEIGHT: " + height[0], 10, 80);
                g.fillText("VARYING: " + thing, 10, 95);
                g.fillText("DIFFTIME: " + diffTime, 10, 110);

                g.setFont(Font.font(300));
                g.fillText(">", 100, 600);
            }
        };

    }
}
