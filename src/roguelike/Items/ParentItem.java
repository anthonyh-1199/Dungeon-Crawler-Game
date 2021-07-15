
package roguelike.Items;

import roguelike.Board;
import roguelike.Objects.GameObject;

/**
 * @author Anthony
 */
public abstract class ParentItem extends GameObject{
    
    public String itemName; //Name of item shown in inventory
    public String itemType; //Name of item's subgroup (potion, armor, weapon, etc)

    //Constructor for when item is on the board
    public ParentItem(int x, int y, Board b) {
        super(x, y, b);
        isOpaque = false;
    }
    
    //Constructor for when item is in player's inventory
    public ParentItem() {}
    
}
