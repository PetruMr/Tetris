package tetris.elements;

import javafx.scene.image.Image;

import java.util.LinkedList;

/**
 * MenuPart is a class that represents a menu. It's connected to other menus trough nodes. Each menu has its own even
 * listener from which it decides what to do.
 */
public class MenuPart {

    LinkedList<Image> menu = new LinkedList<Image>();

}
