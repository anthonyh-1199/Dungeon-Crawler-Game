
package roguelike.items.food;

import roguelike.Board;
import roguelike.items.ParentItem;
import roguelike.objects.entities.ParentEntity;

/**
 * @author Anthony
 */
public abstract class ParentFood extends ParentItem {
    
    int foodHealthAmount;

    //Constructor for when the weapon is on the game board
    public ParentFood(int x, int y, Board b) {
        
        super(x, y, b);
        
        itemType = "food";
        
        Initialize();
        
    }
    
    //Constructor for when the weapon is in player's inventory
    public ParentFood() {
        
        this.itemType = "food";
        
        Initialize();
        
    }
    
    //Helper method for setting the item's attributes
    protected abstract void Initialize();
    
    //Class methods
    
    //Applies effects for when the item is consumed
    public void consume(ParentEntity parent) {};

    @Override
    public void update() {}
    
}
