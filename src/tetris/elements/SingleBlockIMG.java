package tetris.elements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SingleBlockIMG implements SingleBlock {
    private Image image = new Image("Images/StartNorml.png");

    private int dimx, dimy;

    public SingleBlockIMG(int dimx, int dimy){
        this.dimx = dimx;
        this.dimy = dimy;
    }

    @Override
    public void printBlock(GraphicsContext g, int posx, int posy) throws IllegalArgumentException {
        g.drawImage(this.image,(double)posx,(double)posy,(double)this.dimx,(double)this.dimy);
    }

    @Override
    public void setDimensions(int dimx, int dimy) {
        this.dimx = dimx;
        this.dimy = dimy;
    }
}
