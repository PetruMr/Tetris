import tetris.elements.ColorPatterns;
import tetris.elements.PM_Polygon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A block speciefies a tetris block made up of multiple polygons
 */
public class Block {

    /* -- Polygon its composed of -- */
    private PM_Polygon single;      // The single block
    private PM_Polygon lights;      // The lights of the single block
    private PM_Polygon shadows;     // The shadows of the single block

    private int numOfBlocks = 0;
    private int dimx;
    private int dimy;
    private int colorPattern;
    private int id;
    // The order is rotation,x, y
    private boolean [][][] composition;

    // The actual polygons to be printed. They are an array to accommodate various rotations
    private PM_Polygon [][] truePolygon;
    private PM_Polygon[][] trueLightsPolygon;
    private PM_Polygon [][] trueShadowsPolygon;

    /**
     * Creates one of the blocks that are used in tetris
     * It does a lot of things, like creating a "buffered" block, that is a complete version of the block,
     * assigning all parameters like the color and such.
     * @param composition   The composition of the block, a 4x4 matrix of booleans that makes up the block
     * @param dimx          The width of the block
     * @param dimy          The height of the block
     * @param colorPattern  The color pattern to be used by the block
     * @param id            The id of the block, see "Color patterns" to view the ids
     */
    public Block(boolean[][] composition, int dimx, int dimy, int colorPattern, int id) {
        // I create the one with no rotation, from which i base the ones that are rotated
        this.composition = new boolean[4][composition.length][composition[0].length];

        // Rotation zero
        for(int i = 0; i < composition.length; i++) {
            for(int j = 0; j < composition[i].length; j++) {
                this.composition[0][i][j] = composition[i][j];
                if(this.composition[0][i][j]) {
                    this.numOfBlocks++;
                }
            }
        }

        truePolygon        = new PM_Polygon[4][numOfBlocks];
        trueLightsPolygon  = new PM_Polygon[4][numOfBlocks];
        trueShadowsPolygon = new PM_Polygon[4][numOfBlocks];

        // Rotation two
        for( int i = 0; i < this.composition[0].length; i++ ){
            for(int j = 0; j < this.composition[0][i].length; j++){
                this.composition[1][i][j] = this.composition[0][(this.composition[0][i].length-1) - j][i];
            }
        }

        // Rotation one
        for( int i = 0; i < this.composition[0].length; i++ ){
            for(int j = 0; j < this.composition[0][i].length; j++){
                this.composition[2][i][j] = this.composition[0][(this.composition[0].length-1) - i][(this.composition[0][i].length-1)-j];
            }
        }

        // Rotation three
        for( int i = 0; i < this.composition[0].length; i++ ){
            for(int j = 0; j < this.composition[0][i].length; j++){
                this.composition[3][i][j] = this.composition[0][j][(this.composition[0].length-1) - i];
            }
        }

        /* ------------------------------------------------------------------------------------- */
        /* ---------------------------- THE SETTING OF THE POLYGONS ---------------------------- */
        /* ------------------------------------------------------------------------------------- */

        this.dimx = dimx;
        this.dimy = dimy;
        this.colorPattern = colorPattern;
        this.id = id;

        /*
         * The polygon is generated, with the various points beign assigned anti-clockwise and the color pattern
         * gotten from the colorPattern class. The opacity is always gonna be one.
         */
        this.single = new PM_Polygon(new double[]{0, dimx, dimx, 0},
                                     new double[]{0, 0, dimy, dimy},
                                     ColorPatterns.getColorAtId(colorPattern, id), 1);


        /*
         * Now we create the shadows and the lights of the polygons, this is made by darkening and lighting the
         * rgb of the Color. The lighter and darker part are 1/5 of the block.
         */

        this.lights  = new PM_Polygon(new double[]{0, dimx, (double)dimx/5 * 4, (double)dimx/5, (double)dimx/5,     0           },
                                      new double[]{0, 0,    (double)dimy/5,     (double)dimy/5, (double)dimy/5 * 4, (double)dimy},
                                      ColorPatterns.getLighterColorAtId(colorPattern, id, 70), 1);


        this.shadows = new PM_Polygon(new double[]{(double)dimx, (double)dimx, 0,            (double)dimx/5,     (double)dimx/5 * 4, (double)dimx/5 * 4},
                                      new double[]{0,            (double)dimy, (double)dimy, (double)dimy/5 * 4, (double)dimy/5 * 4, (double)dimy/5    },
                                      ColorPatterns.getDarkerColorAtId(colorPattern, id, 40), 1);

        /*
         * And now i build the actual blocks, both the normal part and the rotations.
         * The rotations start with the normal definition and the they are just read in different ways.
         */

        /*for( int i = 0; i < 4; i++) {
            for ( int j = 0; j < 4; j++ ) {
                System.out.print(this.composition[0][i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");*/

        // Normal block
        for(int k = 0; k < this.composition.length; k++ ) {
            int num = 0;
            for( int i = 0; i < this.composition[k].length; i++ ) {
                for( int j = 0; j < this.composition[k][i].length; j++) {
                    if(this.composition[k][i][j]) {
                        this.truePolygon[k][num] =
                                new PM_Polygon(this.single.getShiftedXPoints(dimx*j), this.single.getShiftedYPoints(dimy*i), this.single.noCopyGetColor(), 1);
                        this.trueLightsPolygon[k][num] =
                                new PM_Polygon(this.lights.getShiftedXPoints(dimx*j), this.lights.getShiftedYPoints(dimy*i), this.lights.noCopyGetColor(), 1);
                        this.trueShadowsPolygon[k][num] =
                                new PM_Polygon(this.shadows.getShiftedXPoints(dimx*j), this.shadows.getShiftedYPoints(dimy*i), this.shadows.noCopyGetColor(), 1);
                        num++;
                    }
                }
            }
        }
        /*double [] xPointsOfTrue = new double[numOfBlocks * this.single.getNPoints()];
        double [] yPointsOfTrue = new double[numOfBlocks * this.single.getNPoints()];
        for(int k = 0; k < this.composition.length; k++ ) {
            for( int i = 0; i < this.composition[k].length; i++ ) {
                for( int j = 0; j < this.composition[k][i].length; j++) {
                    if(this.composition[k][i][j]) {
                        System.arraycopy(this.single.getShiftedXPoints(dimx * j), 0, xPointsOfTrue, (i * (this.composition[k][i].length - 1)) + j, this.single.getNPoints());
                        System.arraycopy(this.single.getShiftedYPoints(dimy * i), 0, yPointsOfTrue, (i * (this.composition[k][i].length - 1)) + j, this.single.getNPoints());
                    }
                }
            }
            this.truePolygon[k] = new Elements.PM_Polygon(xPointsOfTrue, yPointsOfTrue, this.single.getColor(), 1);
            xPointsOfTrue = new double[numOfBlocks * this.single.getNPoints()];
            yPointsOfTrue = new double[numOfBlocks * this.single.getNPoints()];
        }

        // Light part of the block
        xPointsOfTrue = new double[numOfBlocks * this.lights.getNPoints()];
        yPointsOfTrue = new double[numOfBlocks * this.lights.getNPoints()];
        for(int k = 0; k < this.composition.length; k++ ) {
            for( int i = 0; i < this.composition[k].length; i++ ) {
                for( int j = 0; j < this.composition[k][i].length; j++) {
                    if(this.composition[k][i][j]) {
                        System.arraycopy(this.lights.getShiftedXPoints(dimx * j), 0, xPointsOfTrue, (i*(this.composition[k][i].length-1)) + j, this.lights.getNPoints());
                        System.arraycopy(this.lights.getShiftedYPoints(dimy * i), 0, yPointsOfTrue, (i*(this.composition[k][i].length-1)) + j, this.lights.getNPoints());
                    }
                }
            }
            this.trueLightsPolygon[k] = new Elements.PM_Polygon(xPointsOfTrue, yPointsOfTrue, this.lights.getColor(), 1);
            xPointsOfTrue = new double[numOfBlocks * this.lights.getNPoints()];
            yPointsOfTrue = new double[numOfBlocks * this.lights.getNPoints()];
        }

        // Dark part of the block
        xPointsOfTrue = new double[numOfBlocks * this.shadows.getNPoints()];
        yPointsOfTrue = new double[numOfBlocks * this.shadows.getNPoints()];
        for(int k = 0; k < this.composition.length; k++ ) {
            for( int i = 0; i < this.composition[k].length; i++ ) {
                for( int j = 0; j < this.composition[k][i].length; j++) {
                    if(this.composition[k][i][j]) {
                        System.arraycopy(this.shadows.getShiftedXPoints(dimx * j), 0, xPointsOfTrue, (i*(this.composition[k][i].length-1)) + j, this.shadows.getNPoints());
                        System.arraycopy(this.shadows.getShiftedYPoints(dimy * i), 0, yPointsOfTrue, (i*(this.composition[k][i].length-1)) + j, this.shadows.getNPoints());
                    }
                }
            }
            this.trueShadowsPolygon[k] = new Elements.PM_Polygon(xPointsOfTrue, yPointsOfTrue, this.shadows.getColor(), 1);
            xPointsOfTrue = new double[numOfBlocks * this.shadows.getNPoints()];
            yPointsOfTrue = new double[numOfBlocks * this.shadows.getNPoints()];
        }*/
    }

    /**
     * Returns the number of points of the normal block
     * @return  The number of points of the normal block
     */
    public int getNPoints() {
        return single.getNPoints();
    }

    /**
     * Returns the number of points of the light block
     * @return  The number of points of the light block
     */
    public int getLightNPoints() {
        return lights.getNPoints();
    }

    /**
     * Returns the number of points of the shadow block
     * @return  The number of points of the shadow blocks
     */
    public int getShadowNPoints() {
        return shadows.getNPoints();
    }

    /**
     * Returns the x of a block at a certain rotation
     * @param x         The x of the part in the leftmost block
     * @param rotation  Is the rotation of the block, anti-clockwise
     * @return          The x coords of the block to be printed
     */
    public double[] getXPoints(int x, int rotation) {
        double[] xPoints = new double[this.numOfBlocks*4];
        //System.arraycopy(this.truePolygon[rotation%4].getShiftedXPoints(x), 0, xPoints, 0, this.numOfBlocks*4);
        return xPoints;
    }

    /**
     * Returns the y of a block at a certain rotation
     * @param y         The y of the part in the bottommost block (this is done to facilitate usage)
     * @param rotation  Is the rotation of the block, anti-clockwise
     * @return          The y coords of the block to be printed
     */
    public double[] getYPoints(int y, int rotation) {
        double[] yPoints = new double[this.numOfBlocks*4];
        //System.arraycopy(this.truePolygon[rotation%4].getShiftedYPoints(y/*-(this.composition.length*this.dimy)*/), 0, yPoints, 0, this.numOfBlocks*4);
        return yPoints;
    }

    /**
     * Returns the x points of a certain rotation of the lighter side of the block
     * @param x         The x of the part in the leftmost block
     * @param rotation  Is the rotation of the block, anti-clockwise
     * @return          The x coords of the light part of the block to be printed
     */
    public double[] getLightXPoints(int x, int rotation) {
        double[] xPoints = new double[6 * this.numOfBlocks];
        //System.arraycopy(this.trueLightsPolygon[rotation%4].getShiftedXPoints(x), 0, xPoints, 0, 6 * this.numOfBlocks);
        return xPoints;
    }

    /**
     * Returns the x points of a certain rotation of the lighter side of the block
     * @param y         The y of the part in the bottommost block (this is done to facilitate usage)
     * @param rotation  Is the rotation of the block, anti-clockwise
     * @return          The y coords of the light part of the block to be printed
     */
    public double[] getLightYPoints(int y, int rotation) {
        double[] yPoints = new double[6 * this.numOfBlocks];
        //System.arraycopy(this.trueLightsPolygon[rotation%4].getShiftedYPoints(y), 0, yPoints, 0, 6 * this.numOfBlocks);
        return yPoints;
    }

    /**
     * Returns the x points of a certain rotation of the darker side of the block
     * @param x         The x of the part in the leftmost block
     * @param rotation  Is the rotation of the block, anti-clockwise
     * @return          The x coords of the light part of the block to be printed
     */
    public double[] getShadowXPoints(int x, int rotation) {
        double[] xPoints = new double[6 * this.numOfBlocks];
        //System.arraycopy(this.trueShadowsPolygon[rotation%4].getShiftedXPoints(x), 0, xPoints, 0, 6 * this.numOfBlocks);
        return xPoints;
    }

    /**
     * Returns the x points of a certain rotation of the darker side of the block
     * @param y         The y of the part in the bottommost block (this is done to facilitate usage)
     * @param rotation  Is the rotation of the block, anti-clockwise
     * @return          The y coords of the darker part of the block to be printed
     */
    public double[] getShadowYPoints(int y, int rotation) {
        double[] yPoints = new double[6 * this.numOfBlocks];
        //System.arraycopy(this.trueShadowsPolygon[rotation%4].getShiftedYPoints(y), 0, yPoints, 0, 6 * this.numOfBlocks);
        return yPoints;
    }

    /**
     * Get the color of the block
     * It is done without copy to make it faster. Do not modify the returnvalue from outside
     * @return  The colors of the block
     */
    public Color getColor() {
        return this.single.noCopyGetColor();
    }

    /**
     * Get the color of the light part of the block
     * It is done without copy to make it faster. Do not modify the returnvalue from outside
     * @return  The color of the light part of the block
     */
    public Color getLightsColor() {
        return this.lights.noCopyGetColor();
    }

    /**
     * Get the color of the darker party of the block
     * It is done without copy to make it faster. Do not modify the returnvalue from outside
     * @return  The color of the dark part of the block
     */
    public Color getShadowsColor() {
        return this.shadows.noCopyGetColor();
    }


    public void printBlock(GraphicsContext g, int posx, int posy, int rotation) {
        /* This is done to be able to receive any rotation.
            If positive, it just does the mod,
            if negative, it turns it positive and then does the mod
        */
        int nm = (rotation<0)?(((((-rotation/4) + 1)*4)+rotation) % 4):(rotation%4);
        for(int i = 0 ; i < this.numOfBlocks; i++ ) {
            g.setFill(this.getColor());
            g.fillPolygon(this.truePolygon[nm][i].getShiftedXPoints(posx), this.truePolygon[nm][i].getShiftedYPoints(posy), this.getNPoints());
            g.setFill(this.getLightsColor());
            g.fillPolygon(this.trueLightsPolygon[nm][i].getShiftedXPoints(posx), this.trueLightsPolygon[nm][i].getShiftedYPoints(posy), this.getLightNPoints());
            g.setFill(this.getShadowsColor());
            g.fillPolygon(this.trueShadowsPolygon[nm][i].getShiftedXPoints(posx), this.trueShadowsPolygon[nm][i].getShiftedYPoints(posy), this.getShadowNPoints());
        }
    }
}
