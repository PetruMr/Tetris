package tetris.elements;

import javafx.scene.canvas.GraphicsContext;

/**
 *  * DESCRIPTION:
 *
 * Implementation of SingleBlock that uses a a few Polygons to be defined.
 * This should be the faster version of the SingleBlock.
 *
 * Changing the dimensions of the SingleBlockPLY takes a bit of computing, so its not really that suggested.
 * All SingleBlockPLY should be generated at the beginning and kept saved in memory for later use, than being
 * dynamically generated.
 *
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *  * AUTHOR:   MARINCAS PETRU MARCEL;
 *  * DATE:     23/03/2020 - CREATION
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */
public class SingleBlockPLY implements SingleBlock {

    /*//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////--------------------------------------SingleBlockPLY  ATTRIBUTES--------------------------------------///////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    /** The polygon's normal part, it's the "background" of the block, it has the standard color */
    private PM_Polygon single;
    /** The polygon's lighter part, it's the "shine" of the block, it has the lighter color */
    private PM_Polygon lights;
    /** The polygon's darker part, it's the "shadow" of the block, it has the darker color */
    private PM_Polygon shadows;

    /**
     * Creates a SingleBlockPLY based on the color pattern of the class "ColorPatterns" and its ID.
     * @param dimx          The x dimensions of the block
     * @param dimy          The y dimensions of the block
     * @param colorPattern  The ColorPattern used
     * @param id            The id of the ColorPattern used
     */
    public SingleBlockPLY (int dimx, int dimy, int colorPattern, int id) {

        /*
         * The polygon is generated, with the various points beign assigned anti-clockwise and the color pattern
         * gotten from the colorPattern class. The opacity is always gonna be one.
         */
        this.single = new PM_Polygon(new double[]{0, dimx, dimx, 0},
                                     new double[]{0, 0, dimy, dimy},
                                     ColorPattern.getColorAtId(colorPattern, id), 1);


        /*
         * Now we create the shadows and the lights of the polygons, this is made by darkening and lighting the
         * rgb of the Color. The lighter and darker part are 1/5 of the block.
         */

        this.lights  = new PM_Polygon(new double[]{0, dimx, (double)dimx/5 * 4, (double)dimx/5, (double)dimx/5,     0           },
                                      new double[]{0, 0,    (double)dimy/5,     (double)dimy/5, (double)dimy/5 * 4, (double)dimy},
                                      ColorPattern.getLighterColorAtId(colorPattern, id, 70), 1);


        this.shadows = new PM_Polygon(new double[]{(double)dimx, (double)dimx, 0,            (double)dimx/5,     (double)dimx/5 * 4, (double)dimx/5 * 4},
                                      new double[]{0,            (double)dimy, (double)dimy, (double)dimy/5 * 4, (double)dimy/5 * 4, (double)dimy/5    },
                                      ColorPattern.getDarkerColorAtId(colorPattern, id, 40), 1);
    }

    @Override
    public void printBlock(GraphicsContext g, int posx, int posy) throws IllegalArgumentException {
        g.setFill(this.single.noCopyGetColor());
        g.fillPolygon(this.single.getShiftedXPoints(posx), this.single.getShiftedYPoints(posy), this.single.getNPoints());

        g.setFill(this.lights.noCopyGetColor());
        g.fillPolygon(this.lights.getShiftedXPoints(posx), this.lights.getShiftedYPoints(posy), this.lights.getNPoints());

        g.setFill(this.shadows.noCopyGetColor());
        g.fillPolygon(this.shadows.getShiftedXPoints(posx), this.shadows.getShiftedYPoints(posy), this.shadows.getNPoints());
    }

    @Override
    public void setDimensions(int dimx, int dimy) {

        /* Basically a constructor */

        /*
         * The polygon is generated, with the various points beign assigned anti-clockwise and the color pattern
         * gotten from the colorPattern class. The opacity is always gonna be one.
         */
        this.single = new PM_Polygon(new double[]{0, dimx, dimx, 0},
                                     new double[]{0, 0, dimy, dimy},
                                     this.single.noCopyGetColor(), 1);


        /*
         * Now we create the shadows and the lights of the polygons, this is made by darkening and lighting the
         * rgb of the Color. The lighter and darker part are 1/5 of the block.
         */

        this.lights  = new PM_Polygon(new double[]{0, dimx, (double)dimx/5 * 4, (double)dimx/5, (double)dimx/5,     0           },
                                      new double[]{0, 0,    (double)dimy/5,     (double)dimy/5, (double)dimy/5 * 4, (double)dimy},
                                      this.lights.noCopyGetColor(), 1);


        this.shadows = new PM_Polygon(new double[]{(double)dimx, (double)dimx, 0,            (double)dimx/5,     (double)dimx/5 * 4, (double)dimx/5 * 4},
                                      new double[]{0,            (double)dimy, (double)dimy, (double)dimy/5 * 4, (double)dimy/5 * 4, (double)dimy/5    },
                                      this.shadows.noCopyGetColor(), 1);
    }
}
