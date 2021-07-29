
package roguelike.items.food;

import java.awt.Color;
import roguelike.Board;
import roguelike.objects.entities.ParentEntity;

/**
 * @author Anthony
 */
public class FoodMeat extends ParentFood {

    //Constructor for when the weapon is on the game board
    public FoodMeat(int x, int y, Board b) {
        
        super(x, y, b);

    }
    
    //Constructor for when the weapon is in player's inventory
    public FoodMeat() {
        
        super();
        
    }
    
    @Override
    protected void Initialize() {
        
        //Item attributes
        itemName = "Meat";

        //Board state attributes
        isSolid = false;
        objectSymbol = 'm';
        objectColor = new Color(209, 20, 20);
        
        //Status effect attributes
        foodHealthAmount = 3;
        
    }

    @Override
    public void consume(ParentEntity parent) {
    
        parent.hitPoints += foodHealthAmount;
        
        if (parent.hitPoints > parent.maxHitPoints) {
            
            parent.hitPoints = parent.maxHitPoints;
            
        }
        
    }

    @Override
    public void update() {}
    
}
