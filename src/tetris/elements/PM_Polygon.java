package tetris.elements;
/*
 * AUTHOR:   MARINCAS PETRU MARCEL;
 * DATE:     15/12/2019 - CREATION
 * USAGE:    A polygon type of shown object, which is defined to be used by the "fillPolygon" and "strokePolygon"
 *           methods in the handle() of DrawingThread. It also has a color.
 *
 *           It is a basic geometry object, meant to be used as a sprite, but that will be added later.
 *
 * */

//To specify the colors we use are from javafx and not from awt
import javafx.scene.paint.Color;

//import java.awt.*;

public class PM_Polygon {
    /* -- ATTRIBUTES -- */
    private double []   xPoints;
    private double []   yPoints;
    private int         nPoints;
    private Color       color;
    private double      opacity;

    /**
     * Constructor of the ShownObject type of Polygon
     * @param xPoints Requires an array of x coordinates for points
     * @param yPoints Requires an array of y coordinates for points
     * @param color   Requires a color made with the method "Color.rgb(int red, int green, int blue)"
     * @param opacity Is the opacity and has to be between
     */
    public PM_Polygon(double [] xPoints, double [] yPoints, Color color, double opacity) {
        if  (   xPoints.length == yPoints.length    &&
                xPoints.length > 2                  &&
                yPoints.length > 2
        )
        {
            this.nPoints = xPoints.length;
            this.xPoints = new double[this.nPoints];
            this.yPoints = new double[this.nPoints];
            for(int i = 0; i < nPoints; i++){
                this.xPoints[i] = xPoints[i];
                this.yPoints[i] = yPoints[i];
            }

            if  (   color != null   &&
                    opacity >= 0     &&
                    opacity <= 1     ) {
                this.color = new Color ( color.getRed(), color.getGreen(), color.getBlue(), opacity );
                this.opacity = opacity;
            } else {
                this.color = Color.BLACK;
                this.opacity = 1.0;
            }
        }
    }

    public PM_Polygon(PM_Polygon polygon ) {
        this.nPoints = polygon.getXPoints().length;
        this.xPoints = new double[this.nPoints];
        this.xPoints = polygon.getXPoints();
        this.yPoints = new double[this.nPoints];
        this.yPoints = polygon.getYPoints();
        this.color = polygon.getColor();
        this.opacity = polygon.getOpacity();
    }

    /**
     * Method to change the current position of the points of this Polygon.
     * @param changeX The amount of x change in the polygon
     * @param changeY The amount of y change in the polygon
     */
    public void changePosition ( double changeX, double changeY ) {
        for ( int i = 0; i < this.nPoints; i++ ) {
            this.xPoints[i] += changeX;
            this.yPoints[i] += changeY;
        }
    }

    /**
     * Method that changes the current color with the new color. The new color wont affect the opacity
     * @param newColor new color created with the method "Color.rgb(int red, int green, int blue)"
     */
    public void setColor ( Color newColor ) {
        if( newColor != null ) {
            this.color = new Color( newColor.getRed(), newColor.getGreen(), newColor.getBlue(), this.opacity);
        }
    }

    /**
     * Method that sets the opacity of the current color
     * @param opacity The opacity of the current color, has to be in the 0.0 - 1.0 range
     */
    public void setOpacity ( double opacity ) {
        if( opacity >= 0 && opacity <= 1) {
            this.opacity = opacity;
            this.color = new Color( this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.opacity);
        }
    }

    /**
     * Method that returns the xPoints
     * @return an array of doubles of xPoints
     */
    public double [] getXPoints() {
        double [] xPoints = new double[this.nPoints];
        for ( int i = 0; i < this.nPoints; i++) {
            xPoints[i] = this.xPoints[i];
        }
        return xPoints;
    }

    /**
     * Method that returns the yPoints
     * @return an array of doubles of yPoints
     */
    public double [] getYPoints() {
        double [] yPoints = new double[this.nPoints];
        for ( int i = 0; i < this.nPoints; i++) {
            yPoints[i] = this.yPoints[i];
        }
        return yPoints;
    }

    /**
     * Returns the x points shifted by and amount
     * @param shiftAmount   The amount of shift of the X points
     * @return              The x points of the polygon shifted by and amount
     */
    public double[] getShiftedXPoints( int shiftAmount ) {
        double [] xPoints = new double[this.nPoints];
        for ( int i = 0; i < this.nPoints; i++) {
            xPoints[i] = this.xPoints[i] + shiftAmount;
        }
        return xPoints;
    }

    /**
     * Returns the y points shifted by and amount
     * @param shiftAmount   The amount of shift of the X points
     * @return              The x points of the polygon shifted by and amount
     */
    public double[] getShiftedYPoints( int shiftAmount ) {
        double [] yPoints = new double[this.nPoints];
        for ( int i = 0; i < this.nPoints; i++) {
            yPoints[i] = this.yPoints[i] + shiftAmount;
        }
        return yPoints;
    }

    public Color getColor() {
        return new Color ( this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.opacity );
    }

    public Color noCopyGetColor() {
        return this.color;
    }

    public double getOpacity() {
        return this.opacity;
    }

    public int getNPoints() {
        return this.nPoints;
    }


}