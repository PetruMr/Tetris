package tetris.scenes;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tetris.controls.Controls;
import tetris.general.Content;

public class PerformanceViewer {


    public static PerformanceTracker tracker = PerformanceTracker.getSceneTracker(Content.scene);
    public static AnimationTimer a = new AnimationTimer() {
        @Override
        public void handle(long now) {

            Content.g.setFont(Font.font(10));
            Content.g.setFill(Color.WHITE);
            Content.g.fillText("FPS_acc: "+ (int)tracker.getAverageFPS(), 10, 20);
            tracker.resetAverageFPS();
            Content.g.fillText("FPS: " + (int)tracker.getInstantFPS(), 10, 35);
            Content.g.fillText("CM: " + MainMenu.printable, 10, 50);
            Content.g.fillText("NumberOfThings: " + Controls.numberOfThings, 10, 65);
        }
    };
}
