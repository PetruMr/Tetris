package tetris.controls;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import tetris.elements.ColorPattern;
import tetris.elements.Particle;
import tetris.elements.SingleBlockPLY;
import tetris.general.Content;
import tetris.scenes.MainMenu;

public class Controls{

    static public Particle p = new Particle(1, 500, 0.5, 0, 20, 150, 150, 1, 5, Color.GREEN, 5, 2000, 1, 3, 2, Content.g);

    static int grd = 40;
    final static public EventHandler<KeyEvent> menu = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch(event.getCode()) {
                case UP:
                case W:
                    MainMenu.selectNext();
                    break;
                case DOWN:
                case S:
                    MainMenu.selectPrev();
                    break;
                case SPACE:
                case ENTER:
                    MainMenu.press();
                    break;
                case G:
                    int dir = 270;
                    int angD = 260;
                    double spd = 0.1;
                    int numFor1 = 1000;
                    p = new Particle(numFor1, spd, 1, dir, angD, Content.width/2-30,
                            Content.height/2, 10, 1, Color.DARKCYAN, 1, 20000,
                            0.6, 2, 1, Content.g);
                    Particle a = new Particle(numFor1, spd, 1, dir, angD, Content.width/2-10,
                            Content.height/2, 10, 1, Color.DARKGREEN, 1, 20000,
                            0.6, 2, 1, Content.g);
                    Particle c = new Particle(numFor1, spd, 1, dir, angD, Content.width/2+10,
                            Content.height/2, 10, 1, Color.DARKMAGENTA, 1, 20000,
                            0.6, 2, 1, Content.g);
                    Particle d = new Particle(numFor1, spd, 1, dir, angD, Content.width/2+30,
                            Content.height/2, 10, 1, Color.DARKORANGE, 1, 20000,
                            0.6, 2, 1, Content.g);
                    Particle b = new Particle(numFor1, spd, 1, dir, angD, Content.width/2-50,
                            Content.height/2, 10, 1, Color.DARKRED, 1, 20000,
                            0.6, 2, 1, Content.g);
                    Particle e = new Particle(numFor1, spd, 1, dir, angD, Content.width/2-70,
                            Content.height/2, 10, 1, Color.DARKVIOLET, 1, 20000,
                            0.6, 2, 1, Content.g);
                    Particle f = new Particle(numFor1, spd, 1, dir, angD, Content.width/2+50,
                            Content.height/2, 10, 1, Color.DARKSALMON, 1, 20000,
                            0.6, 2, 1, Content.g);
                    Particle g = new Particle(numFor1, spd, 1, dir, angD, Content.width/2+70,
                            Content.height/2, 10, 1, Color.DARKTURQUOISE, 1, 20000,
                            0.6, 2, 1, Content.g);
                    d.animation();
                    p.animation();
                    a.animation();
                    c.animation();
                    b.animation();
                    e.animation();
                    f.animation();
                    g.animation();
                //case BACK_SLASH:
                //    debugMode();
                //    break;
            }
        }
    };

    static public EventHandler<KeyEvent> game = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {

        }
    };

    static public EventHandler<KeyEvent> debug = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch(event.getCode()){
                case DIGIT1:
                    debugAnimation1.start();
                    debugAnimation2.stop();
                    debugAnimation3.stop();
                    break;
                case DIGIT2:
                    debugAnimation1.stop();
                    debugAnimation2.start();
                    debugAnimation3.stop();
                    break;
                case DIGIT3:
                    debugAnimation1.stop();
                    debugAnimation2.stop();
                    debugAnimation3.start();
                    break;
                case SPACE:
                    Controls.numberOfThings+= 100;
                    break;
                case BACK_SPACE:
                    Controls.numberOfThings-=100;
                    break;
            }
        }
    };

    static public int numberOfThings = 0;
    static public AnimationTimer debugAnimation1 = new AnimationTimer() {
        @Override
        public void handle(long now) {
            for(int i = 0; i < Controls.numberOfThings; i++) {
                Content.g.setFill(ColorPattern.getColorAtId(0,i%6));
                Content.g.fillRect((int)(300 + (i/10)), (int)(150 + (i/10)), 50, 50);
            }
        }
    };

    static public Image image = new Image("Images/SelectingArrow.bmp");
    static public AnimationTimer debugAnimation2 = new AnimationTimer() {
        @Override
        public void handle(long now) {
            for(int i = 0; i < Controls.numberOfThings; i++) {
                Content.g.drawImage(Controls.image, (int)(300 + (i/10)), (int)(150 + (i/10)), 50, 50);
            }
        }
    };

    static public SingleBlockPLY block = new SingleBlockPLY(50,50,0,1);
    static public AnimationTimer debugAnimation3 = new AnimationTimer() {
        @Override
        public void handle(long now) {
            for(int i = 0; i < Controls.numberOfThings; i++) {
                block.printBlock(Content.g,(int)(300 + (i/10)), (int)(150 + (i/10)));
            }
        }
    };

}
