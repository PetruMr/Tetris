package tetris.elements;

import javafx.scene.paint.Color;

/**
 * Each pattern is named:
 * CP_*Number of pattern*_*Number of block*
 *
 * The number of blocks is 7.
 * They are usually referred to in this order:ù
 *
 *      *     1 - Reversed L block:
 *      *      //
 *      *      // // //
 *      *
 *      *     2 - L block:
 *      *            //
 *      *      // // //
 *      *
 *      *     3 - O block:
 *      *      // //
 *      *      // //
 *      *
 *      *     4 - Z block:
 *      *         // //
 *      *      // //
 *      *
 *      *     5 - T block:
 *      *         //
 *      *      // // //
 *      *
 *      *     6 - Reversed Z block:
 *      *      // //
 *      *         // //
 *      *
 *      *     7 - I block:
 *      *      // // // //
 *
 * The 8th number of a pattern defines background.
 * Other numbers from then on might have different users, check the comment.
 */

public enum ColorPattern {

    CP_1_1  (100, 240, 255  ),
    CP_1_2  (255, 180, 90   ),
    CP_1_3  (255, 250, 40   ),
    CP_1_4  (140, 255, 110  ),
    CP_1_5  (205, 110, 255  ),
    CP_1_6  (255, 45,  55   ),
    CP_1_7  (35,  255, 180  ),
    CP_1_8  (45,  45,  45   );

    private int r, g, b;
    private Color color;
    ColorPattern(int r, int g, int b) {
        this.color = Color.rgb(r,g,b,1);
    }

    static private final Color[][] colors = new Color[][]{
            new Color[]{
                    CP_1_1.getColor(),
                    CP_1_2.getColor(),
                    CP_1_3.getColor(),
                    CP_1_4.getColor(),
                    CP_1_5.getColor(),
                    CP_1_6.getColor(),
                    CP_1_7.getColor(),
                    CP_1_8.getColor()
            }
    };

    private Color getColor() {
        return this.color;
    }

    /**
     * Returns the color of a certain patter and of a certain id ( of a particular block )
     * @param numberOfPattern   The number of a pattern
     * @param id                The id of the block
     * @return                  The color of a block of a certain pattern
     */
    public static Color getColorAtId( int numberOfPattern, int id ) {
        return new Color(colors[numberOfPattern][id].getRed(),colors[numberOfPattern][id].getGreen(), colors[numberOfPattern][id].getBlue(), 1);
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
                (int)((colors[numberOfPattern][id].getRed()*255   + howMuchLighter > 255)?(255):(colors[numberOfPattern][id].getRed()*255   + howMuchLighter)),
                (int)((colors[numberOfPattern][id].getGreen()*255 + howMuchLighter > 255)?(255):(colors[numberOfPattern][id].getGreen()*255 + howMuchLighter)),
                (int)((colors[numberOfPattern][id].getBlue()*255  + howMuchLighter > 255)?(255):(colors[numberOfPattern][id].getBlue()*255  + howMuchLighter)),
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
                (int)((colors[numberOfPattern][id].getRed()*255   - howMuchDarker < 0)?(0):(colors[numberOfPattern][id].getRed()*255   - howMuchDarker)),
                (int)((colors[numberOfPattern][id].getGreen()*255 - howMuchDarker < 0)?(0):(colors[numberOfPattern][id].getGreen()*255 - howMuchDarker)),
                (int)((colors[numberOfPattern][id].getBlue()*255  - howMuchDarker < 0)?(0):(colors[numberOfPattern][id].getBlue()*255  - howMuchDarker)),
                1);
    }

}
