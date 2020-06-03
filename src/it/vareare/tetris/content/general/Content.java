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

    /* WIDTH AND HEIGHT OF THE WINDOW */

    transient static public int width = Settings.resolutions[Settings.e_resolution][0];
    transient static public int height = Settings.resolutions[Settings.e_resolution][1];


    /*TODO
    *  ?DECIDERE SE I CAMBI DELLE IMPOSTAZIONI VENGONO FATTI IN MODO ATTIVO O IN MODO PASSIVO
    *  ---Cioè se i cambi che faccio attraverso il cambiamento di un impostazione al cambiamento di come funziona tutto se viene
    *  fatto in un modo attivo in base all'input, che quindi attiva un interpretazione da parte del computer, oppure se viene
    *  fatto in modo passivo, che quindi prende quello che deve fare in base a cosa è cambiato quando lo deve fare.
    *  ---L'ipotesi si può quindi dividere tra, per esempio, rendere i cambiamenti effettivi quando si salva/inizia una partita
    *  oppure rendere i cambiamenti effettivi mano mano che vengono cambiati.
    * */


    /*TODO*/

    static public void updateSettings() {
        /* Impostazione della risoluzione */
        if(!Settings.e_fullscreen) {
            Content.width = Settings.resolutions[Settings.e_resolution][0];
            Content.height = Settings.resolutions[Settings.e_resolution][1];
            Content.stage.setWidth(Content.width);
            Content.stage.setHeight(Content.height);
            Content.stage.centerOnScreen();
            Content.stage.centerOnScreen();
        }

        /* Impostazione del fullscreen */
        Content.stage.setFullScreen(Settings.e_fullscreen);

        /*
         * TODO
         *  -------------------------------------
         *  PER GLI ALTRI SETTINGS LA STESSA COSA
         *  -------------------------------------
         */
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