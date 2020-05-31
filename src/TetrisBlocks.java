import tetris.elements.ColorPatterns;

/**
 * Tetris game's coment.
 */
public class TetrisBlocks {

    /* -- TYPES OF BLOCKS -- */
    private Block [] blocks = new Block[7];

    /**
     * Initializes the block to become of a certain dimension and have a certain color pattern that is inside of blocks
     * @param dimx              The x dimension of the blocks
     * @param dimy              The y dimension of the blocks
     * @param colorPattern      The color pattern of the blocks
     */
    public void initializeBlocks( int dimx, int dimy, int colorPattern ) {
        // To make sure
        ColorPatterns.generateColors();
        /*
        Initialization template
        blocks[0-6] = new Block(
                (new boolean[][]{
                        {false, false, false, false},
                        {false, false, false, false},
                        {false, false, false, false},
                        {false, false, false, false}
                }),
                dimx,
                dimy,
                colorPattern,
                0-6
        );*/

        /*
        *   Reversed L Block:
        *      //
        *      // // //
        */
        blocks[0] = new Block(
                (new boolean[][]{
                        {false, false, false, false},
                        {true,  false, false, false},
                        {true,  true,  true,  false},
                        {false, false, false, false}
                }),
                dimx,
                dimy,
                colorPattern,
                0
        );

        /*
         *  L Block:
         *            //
         *      // // //
         */
       /* blocks[1] = new Block(
                (new boolean[][]{
                        {false, false, false, false},
                        {false, false, true,  false},
                        {true,  true,  true,  false},
                        {false, false, false, false}
                }),
                dimx,
                dimy,
                colorPattern,
                1
        );*/
        blocks[1] = new Block(
                (new boolean[][]{
                        {false, false, true},
                        {true, true, true},
                        {false, false, false},
                }),
                dimx,
                dimy,
                colorPattern,
                1
        );

        /*
         *   O Block:
         *      // //
         *      // //
         */
        blocks[2] = new Block(
                (new boolean[][]{
                        {false, false, false, false},
                        {true,  true,  false, false},
                        {true,  true,  false, false},
                        {false, false, false, false}
                }),
                dimx,
                dimy,
                colorPattern,
                2
        );

        /*
         *   Z Block:
         *         // //
         *      // //
         */
        blocks[3] = new Block(
                (new boolean[][]{
                        {false, false, false, false},
                        {false, true,  true,  false},
                        {true,  true,  false, false},
                        {false, false, false, false}
                }),
                dimx,
                dimy,
                colorPattern,
                3
        );

        /*
         *   T Block:
         *      //
         *      // // //
         */
        blocks[4] = new Block(
                (new boolean[][]{
                        {false, false, false, false},
                        {false, true,  false, false},
                        {true,  true,  true,  false},
                        {false, false, false, false}
                }),
                dimx,
                dimy,
                colorPattern,
                4
        );

        /*
         *   Reversed Z Block:
         *      // //
         *         // //
         */
        blocks[5] = new Block(
                (new boolean[][]{
                        {false, false, false, false},
                        {true,  true,  false, false},
                        {false, true,  true,  false},
                        {false, false, false, false}
                }),
                dimx,
                dimy,
                colorPattern,
                5
        );

        /*
         *   I Block:
         *      // // // //
         */
        blocks[6] = new Block(
                (new boolean[][]{
                        {false, false, false, false},
                        {true,  true,  true,  true },
                        {false, false, false, false},
                        {false, false, false, false}
                }),
                dimx,
                dimy,
                colorPattern,
                6
        );
    }

    public Block getBlock( int index ) {
        return this.blocks[index];
    }
}
