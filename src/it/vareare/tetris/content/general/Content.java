package it.vareare.tetris.content.general;
import it.vareare.tetris.content.control.Game;
import it.vareare.tetris.content.control.Menu;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.Serializable;

public class Content implements Serializable {

    /* -- UTILS -- */

    transient static public EventHandler<KeyEvent> inputType;
    transient static public String log;

    transient static public Stage stage;
    transient static public Canvas canvas;
    transient static public GraphicsContext g;
    transient static public Scene scene;

    transient static public LinearGradient gradient = new LinearGradient(0,0,3000,0,false, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0,Color.RED),new Stop(1,Color.BLUE)});

    /* -- SETTINGS -- */

    transient static public boolean fullscreen = false;

    transient static public int [][] resolutions = new int[][]{
            {
                854, 480
            },
            {
                1280, 720
            },
            {
                1920, 1080
            },
            {
                1920, 1080
            },
            {
                3840, 2160
            }
    };

    transient static public       int difficulty = 15;
    transient static public final int DIFFICULTY_MAX = 30;
    transient static public final int DIFFICULTY_MIN = 0;
    transient static public       int style = 0;
    transient static public final int STYLE_MIN = 0;
    transient static public final int STYLE_MAX = 2;
    transient static public       int mode = 0;
    transient static public final int MODE_MIN = 0;
    transient static public final int MODE_MAX = 0;
    transient static public       int volume = 0;
    transient static public final int VOLUME_MIN = 0;
    transient static public final int VOLUME_MAX = 100;
    transient static public       int quality = 0;
    transient static public final int QUALITY_MIN = 0;
    transient static public final int QUALITY_MAX = 3;
    transient static public       int resolution = 1;
    transient static public final int RESOLUTION_MIN = 0;
    transient static public final int RESOLUTION_MAX = resolutions.length-1;

    /* STANDARD ONES */
    static public int       s_difficulty = 15;
    static public int       s_style = 0;
    static public int       s_mode = 0;
    static public int       s_volume = 0;
    static public int       s_quality = 0;
    static public int       s_resolution = 1;
    static public boolean   s_fullscreen = false;

    /* EFFECTIVE ONES */
    static public int e_difficulty = 15;
    static public int e_style = 0;
    static public int e_mode = 0;
    static public int e_volume = 0;
    static public int e_quality = 0;
    static public int e_resolution = 1;
    static public boolean e_fullscreen = false;

    /* WIDTH AND HEIGHT OF THE WINDOW */

    transient static public int width = resolutions[e_resolution][0];
    transient static public int height = resolutions[e_resolution][1];


    /*TODO
    *  ?DECIDERE SE I CAMBI DELLE IMPOSTAZIONI VENGONO FATTI IN MODO ATTIVO O IN MODO PASSIVO
    *  ---Cioè se i cambi che faccio attraverso il cambiamento di un impostazione al cambiamento di come funziona tutto se viene
    *  fatto in un modo attivo in base all'input, che quindi attiva un interpretazione da parte del computer, oppure se viene
    *  fatto in modo passivo, che quindi prende quello che deve fare in base a cosa è cambiato quando lo deve fare.
    *  ---L'ipotesi si può quindi dividere tra, per esempio, rendere i cambiamenti effettivi quando si salva/inizia una partita
    *  oppure rendere i cambiamenti effettivi mano mano che vengono cambiati.
    * */
    static private int changeGeneral(int subject, int amount, int MIN, int MAX) {
        if(subject + amount >= MIN && subject + amount <= MAX) {
            subject += amount;
        } else {
            if(subject + amount < MIN) {
                subject = MIN;
            } else {
                subject = MAX;
            }
        }
        System.out.println(subject);
        return subject;
    }

    static public void changeDifficulty(int amount) { Content.difficulty = Content.changeGeneral(Content.difficulty, amount, Content.DIFFICULTY_MIN, Content.DIFFICULTY_MAX); }
    static public void changeStyle(int amount)      { Content.style = Content.changeGeneral(Content.style, amount, Content.STYLE_MIN, Content.STYLE_MAX); }
    static public void changeMode(int amount)       { Content.mode = Content.changeGeneral(Content.mode, amount, Content.MODE_MIN, Content.MODE_MAX); }
    static public void changeVolume(int amount)     { Content.volume = Content.changeGeneral(Content.volume, amount, Content.VOLUME_MIN, Content.VOLUME_MAX); }
    static public void changeQuality(int amount)    { Content.quality = Content.changeGeneral(Content.quality, amount, Content.QUALITY_MIN, Content.QUALITY_MAX); }
    static public void changeResolution(int amount) { Content.resolution = Content.changeGeneral(Content.resolution, amount, Content.RESOLUTION_MIN, Content.RESOLUTION_MAX); }
    static public void changeFullscreen()           { Content.fullscreen = !Content.fullscreen;}


    /*TODO*/
    static public void saveSettings() {
        e_difficulty = difficulty;
        e_style = style;
        e_mode = mode;
        e_volume = volume;
        e_quality = quality;
        e_resolution = resolution;
        e_fullscreen = fullscreen;
    }

    static public void updateSettings() {
        /* Impostazione della risoluzione */
        if(!e_fullscreen) {
            Content.width = resolutions[e_resolution][0];
            Content.height = resolutions[e_resolution][1];
            Content.stage.setWidth(Content.width);
            Content.stage.setHeight(Content.height);
        }

        /* Impostazione del fullscreen */
        Content.stage.setFullScreen(e_fullscreen);

        /*
         * TODO
         *  -------------------------------------
         *  PER GLI ALTRI SETTINGS LA STESSA COSA
         *  -------------------------------------
         */
    }

    static public void resetSettings() {
        difficulty  = e_difficulty;
        style       = e_style;
        mode        = e_mode;
        volume      = e_volume;
        quality     = e_quality;
        resolution  = e_resolution;
        fullscreen  = e_fullscreen;
    }

    static public void defaultSettings() {
        volume = s_volume;
        quality = s_quality;
        resolution = s_resolution;
        fullscreen = s_fullscreen;
    }

    public static void defaultGameSettings() {
        difficulty = s_difficulty;
        style = s_style;
        mode = s_mode;
    }

    /* USEFUL STRUFF LIKE INIT AND SO ON */

    static public AnimationTimer standardAnimation = new AnimationTimer() {
        @Override
        public void handle(long now) {
            Content.width  = (int) Content.stage.getWidth();
            Content.height = (int) Content.stage.getHeight();
            Content.canvas.setWidth(width);
            Content.canvas.setHeight(height);

            g.setFill(new LinearGradient(0,
                    0,
                    0,
                    height,
                    false,
                    CycleMethod.NO_CYCLE,
                    new Stop[]{ new Stop(0,Color.rgb(0x61, 0x43, 0x85)),new Stop(1,Color.rgb(0x51, 0x63, 0x95))}
                    ));
            g.fillRect(0,0, Content.width, Content.height);
        }
    };

    static public void init(Stage stage) {

        /*
        * TODO
        * Fare un controllo per cercare il file di setting serializzato e leggerlo
        * se non c'è fare la normale init
        *
        * se c'è allora caricare alcune cose dalle impostazioni
        *
        * */

        Content.stage = stage;
        Content.stage.setWidth(Content.width);
        Content.stage.setHeight(Content.height);

        /* Canvas and other stuff of the window */
        Content.canvas = new Canvas(Content.width, Content.height);

        Content.g = Content.canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        Content.scene = new Scene(root);

        Content.stage.setTitle("Tetris");
        Content.stage.setScene(scene);
        Content.stage.show();
        Content.stage.requestFocus();
        Content.stage.setResizable(false);

        Content.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        Content.inputType = Menu.getInstance();
        Content.scene.setOnKeyPressed(Content.inputType);

        standardAnimation.start();
    }

    public static void changeInput(EventHandler<KeyEvent> instance) {
        Content.scene.setOnKeyPressed(instance);
    }
}