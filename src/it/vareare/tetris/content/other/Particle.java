package it.vareare.tetris.content.other;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tetris.controls.Controls;

import java.util.SplittableRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * A class that makes a particle animation based on randomly generated numbers.
 */
public class Particle {

    private AnimationTimer particleAnimation;
    private long animationStart;
    private GraphicsContext g;
    private AnimationTimer getTime = new AnimationTimer() {
        @Override
        public void handle(long now) {
            animationStart = now;
            stop();
        }
    };

    private SingleParticle [] particles;

    /**
     * Generates a new particle animation based on random values
     * @param numOfParticles    The number of particles
     * @param spd               The speed of each particle
     * @param spdChange         The percentage of speed change of the particles (-1 means still, 1 means double)
     * @param dir               The general direction of the particles in degrees (0 is right, 90 is up, etc.)
     * @param dirDelta          The the "width/2" of the cone of particles
     * @param posX              The X coordinate of the particles
     * @param posY              The Y coordinate of the particles
     * @param posXDelta         How much the x coordinate can change
     * @param posYDelta         How much the y coordinate can change
     * @param color             The original color
     * @param colorChange       How much the color brightness can change (1 is nothing, 2 means double);
     * @param lifeTime          How long is the lifetime of a particle in milliseconds
     * @param lifeTimeChange    How much can the lifetime of each particle alter in percentage (0 means by nothing)
     * @param size              The size of the particle
     * @param sizeDelta         How much can a particle be bigger or smaller
     */
    public Particle(int numOfParticles, double spd, double spdChange, double dir, double dirDelta, int posX, int posY,
                    int posXDelta, int posYDelta, Color color, double colorChange, long lifeTime, double lifeTimeChange,
                    int size, int sizeDelta, GraphicsContext g) {
        this.g = g;

        /* The casual generator for all particles */
        SplittableRandom generator = new SplittableRandom(System.nanoTime());

        /* The various speeds*/
        DoubleStream speeds = generator.doubles(numOfParticles, spd-(spd*(spdChange)), spd+(spd*spdChange));

        /* The various directions */
        double dx = Math.cos(Math.toRadians(dir));
        double dy = Math.sin(Math.toRadians(dir));

        double dxDelta = dx-Math.cos(Math.toRadians(dir-dirDelta));
        double dyDelta = dy-Math.sin(Math.toRadians(dir-dirDelta));

        double maxAng = Math.max(dir-dirDelta, dir+dirDelta),
                minAng = Math.min(dir-dirDelta, dir+dirDelta);

        DoubleStream T_dxDoubles = generator.doubles(numOfParticles, minAng, maxAng);

        double [] tx = T_dxDoubles.toArray();
        double [] ty = new double[tx.length];
        System.arraycopy(tx, 0, ty, 0, tx.length);

        for(int i = 0; i < numOfParticles; i++) {
            tx[i] = Math.cos(Math.toRadians(tx[i]));
            ty[i] = Math.sin(Math.toRadians(ty[i]));
        }

        /* The various starting positions */
        IntStream posXs = generator.ints(numOfParticles, posX-posXDelta, posX+posXDelta);
        IntStream posYs = generator.ints(numOfParticles, posY-posYDelta, posY+posYDelta);

        /* The various lifetimes */
        lifeTime *= 1000000.00;
        LongStream lifeTimes = generator.longs(numOfParticles, (long)(lifeTime-(lifeTime*lifeTimeChange)), (long)(lifeTime+(lifeTime*lifeTimeChange)));

        /* The various brightnesses */
        DoubleStream colorChanges = generator.doubles(numOfParticles, 1-colorChange, 1+colorChange);

        /* The various sizes*/
        IntStream sizes = generator.ints(numOfParticles, size-sizeDelta, size+sizeDelta);

        /* The only things i need */
        int [] A_startPositionX = posXs.toArray();
        int [] A_startPositionY = posYs.toArray();
        double [] A_speeds = speeds.toArray();
        double [] A_dx = tx;
        double [] A_dy = ty;
        Color [] A_Colors = new Color[numOfParticles];
        double [] A_colorChange = colorChanges.toArray();
        for (int i = 0 ; i < numOfParticles; i++) {
            A_Colors[i] = color.deriveColor(0, 1, A_colorChange[i], 1);
        }
        long [] A_lifetime = lifeTimes.toArray();
        int [] A_size = sizes.toArray();



        /* I create the particle reference variables */
        this.particles = new SingleParticle[numOfParticles];

        for(int i = 0; i < numOfParticles; i++) {
            this.particles[i] = new SingleParticle
                            ( this, A_startPositionX[i], A_startPositionY[i], A_speeds[i], A_dx[i], A_dy[i],
                            A_Colors[i],A_lifetime[i],A_size[i]);
        }
    }

    public void animation() {
        getTime.start();
        for(SingleParticle s : particles)
            s.startAnimation();
    }

    public long getAnimationStart() {
        return animationStart;
    }

    public GraphicsContext getG() {
        return g;
    }

}

class SingleParticle {
    public int startPositionX;
    public int startPositionY;
    public double dx;
    public double dy;
    public Color color;
    public long lifeTime;
    public int size;

    public Particle parent;

    public AnimationTimer a = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if((SingleParticle.this.lifeTime + SingleParticle.this.parent.getAnimationStart()) - now < 0) {
                this.stop();
                /*
                 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                 *  WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP
                 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                 */
                Controls.numberOfThings--;
            } else {
                SingleParticle.this.parent.getG().setFill(SingleParticle.this.color);
                SingleParticle.this.parent.getG().fillRect(
                        (SingleParticle.this.startPositionX+(float)SingleParticle.this.size/2) + (SingleParticle.this.dx*(0.0000001*(now-SingleParticle.this.parent.getAnimationStart()))),
                        (SingleParticle.this.startPositionY+(float)SingleParticle.this.size/2) + (SingleParticle.this.dy*(0.0000001*(now-SingleParticle.this.parent.getAnimationStart()))),
                        SingleParticle.this.size,
                        SingleParticle.this.size
                );
            }
        }
    };

    public SingleParticle(Particle parent, int startPositionX, int startPositionY, double spd, double dx, double dy, Color color, long lifeTime, int size) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.dx = dx*spd;
        this.dy = dy*spd;
        this.color = color;
        this.lifeTime = lifeTime;
        this.size = size;
        this.parent = parent;
    }

    public void startAnimation() {
        a.start();
/*
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *  WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP - WIP
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */
        Controls.numberOfThings++;
    }
}
