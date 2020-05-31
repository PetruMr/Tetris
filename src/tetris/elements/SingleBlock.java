package tetris.elements;

import javafx.scene.canvas.GraphicsContext;

/**
 * This class represents a single block of Tetris. A block is something that's either composed by an image or a Polygon.
 * They are used to print the grid of Tetris. They are (By definition) square, with lighter parts in the top left and
 * darker parts in the bottom left, although they could also be different.
 * They will always occupy a square space on the grind.
 */
public interface SingleBlock {

    /**
     * This is the main method that the classes that implement this interface have to have.
     * By calling this method, the class that implements this interface should print the block to the screen on the
     * given coordinates (which represent the top left corner of the block to be printed).
     * The block's dimension in the x axys and the y axis set beforehand, to ease printing speed.
     * @param g     The graphics context of the scene.
     * @param posx  The x coordinate of the top left corner of the block
     * @param posy  The y coordinate of the top left corner of the block
     * @throws IllegalArgumentException If GraphicsContext is null, if posx or posy are "bad" or if the dimensions weren't set beforehand.
     */
    abstract public void printBlock(GraphicsContext g, int posx, int posy) throws IllegalArgumentException;

    /**
     * With this method the block of dimension will be set to a certain amount. This is an abstract method that will
     * need implementation.
     * @param dimx  The x dimensions
     * @param dimy  The y dimensions
     */
    abstract public void setDimensions(int dimx, int dimy);

}
