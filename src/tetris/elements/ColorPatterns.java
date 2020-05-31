package tetris.elements;

import javafx.scene.paint.Color;

public class ColorPatterns {

    /**
     * The amount of patterns of color that are possible
     * If it is higher there are more patterns that can be created
     */
    public static final int numOfPatterns = 10;

    /**
     * The various patterns that are possible.
     * Its a matrix defined by [] the number of patterns
     * and
     * [] the various blocks and the background color with their colors, in this order:
     *  {
     *     0 - Reversed L block:
     *      //
     *      // // //
     *
     *     1 - L block:
     *            //
     *      // // //
     *
     *     2 - O block:
     *      // //
     *      // //
     *
     *     3 - Z block:
     *         // //
     *      // //
     *
     *     4 - T block:
     *         //
     *      // // //
     *
     *     5 - Reversed Z block:
     *      // //
     *         // //
     *
     *     6 - I block:
     *      // // // //
     *
     *     7 - Background color.
     *  }
     *  These are also known as "ids" for the blocks (from 0-6)
     */
    private static Color[][] colorPattern = new Color[ColorPatterns.numOfPatterns][8];

    public static void generateColors() {
        /**
         * Standard Tetris colors
         */
        colorPattern[0][0] = Color.rgb(100, 240, 255, 1 );
        colorPattern[0][1] = Color.rgb(255, 180, 90, 1  );
        colorPattern[0][2] = Color.rgb(255, 250, 40, 1  );
        colorPattern[0][3] = Color.rgb(140, 255, 110, 1 );
        colorPattern[0][4] = Color.rgb(205, 110, 255, 1 );
        colorPattern[0][5] = Color.rgb(255, 45, 55, 1   );
        colorPattern[0][6] = Color.rgb(35, 255, 180, 1  );
        colorPattern[0][7] = Color.rgb(45,45,45, 1      );
    }
    /**
     * Returns the color of a certain patter and of a certain id ( of a particular block )
     * @param numberOfPattern   The number of a pattern
     * @param id                The id of the block
     * @return                  The color of a block of a certain pattern
     */
    public static Color getColorAtId( int numberOfPattern, int id ) {
        return new Color(colorPattern[numberOfPattern][id].getRed(),colorPattern[numberOfPattern][id].getGreen(), colorPattern[numberOfPattern][id].getBlue(), 1);
    }

    /**
     * Returns a brither shade of the color of a certain pattern and of a certain id
     * @param numberOfPattern           The number of the patter
     * @param id                        The id of the block
     * @param howMuchLighter            How much lighter the block should be, basically how much more of rgb there should be
     * @return                          The color but in a lighter tone
     * @throws IllegalArgumentException If "howMuchLighter" is negative
     */
    public static Color getLighterColorAtId( int numberOfPattern, int id, int howMuchLighter)
            throws IllegalArgumentException {
        if(howMuchLighter < 0 )
                throw new IllegalArgumentException("The index of how much darker the image has to be is negative (" + howMuchLighter + ")");
        return Color.rgb(
                (int)((colorPattern[numberOfPattern][id].getRed()*255   + howMuchLighter > 255)?(255):(colorPattern[numberOfPattern][id].getRed()*255   + howMuchLighter)),
                (int)((colorPattern[numberOfPattern][id].getGreen()*255 + howMuchLighter > 255)?(255):(colorPattern[numberOfPattern][id].getGreen()*255 + howMuchLighter)),
                (int)((colorPattern[numberOfPattern][id].getBlue()*255  + howMuchLighter > 255)?(255):(colorPattern[numberOfPattern][id].getBlue()*255  + howMuchLighter)),
                1);
    }

    /**
     * Returns a brither shade of the color of a certain pattern and of a certain id
     * If r g or b is darker than 0 then it is set to 0
     * @param numberOfPattern           The number of the patter
     * @param id                        The id of the block
     * @param howMuchDarker             How much darker the block should be, basically how much more of rgb there should be
     * @return                          The color but in a lighter tone
     * @throws IllegalArgumentException If "howMuchDarker" is negative
     */
    public static Color getDarkerColorAtId( int numberOfPattern, int id, int howMuchDarker)
            throws IllegalArgumentException {
        if(howMuchDarker < 0 )
            throw new IllegalArgumentException("The index of how much darker the image has to be is negative (" + howMuchDarker + ")");
        return Color.rgb(
                (int)((colorPattern[numberOfPattern][id].getRed()*255   - howMuchDarker < 0)?(0):(colorPattern[numberOfPattern][id].getRed()*255   - howMuchDarker)),
                (int)((colorPattern[numberOfPattern][id].getGreen()*255 - howMuchDarker < 0)?(0):(colorPattern[numberOfPattern][id].getGreen()*255 - howMuchDarker)),
                (int)((colorPattern[numberOfPattern][id].getBlue()*255  - howMuchDarker < 0)?(0):(colorPattern[numberOfPattern][id].getBlue()*255  - howMuchDarker)),
                1);
    }


}
