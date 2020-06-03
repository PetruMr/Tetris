package it.vareare.tetris.content.animations;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import it.vareare.tetris.content.general.Content;

public class PerformanceViewer extends AnimationTimer{

    private static boolean running = false;

    public static PerformanceTracker tracker = PerformanceTracker.getSceneTracker(Content.scene);
    public static PerformanceViewer instance = new PerformanceViewer();
    public PerformanceViewer() { super(); }
    public static PerformanceViewer getInstance() { return PerformanceViewer.instance;}

    @Override
    public void handle(long now) {
        Content.g.setFont(Font.font(20));
        Content.g.setFill(Color.WHITE);
        Content.g.fillText("FPS_acc: "+ (int)tracker.getAverageFPS(), 5, 20);
        tracker.resetAverageFPS();
        Content.g.fillText("FPS: " + (int)tracker.getInstantFPS(), 5, 40);
        Content.g.fillText("LOG: " + Content.log, 5, 60);
    }

    @Override
    public void start() {
        super.start();
        PerformanceViewer.running = true;
    }

    @Override
    public void stop() {
        super.stop();
        PerformanceViewer.running = false;
    }

    public static boolean isRunning() { return PerformanceViewer.running; }
}
