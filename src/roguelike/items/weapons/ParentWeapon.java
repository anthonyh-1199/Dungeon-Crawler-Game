
package roguelike.items.weapons;

import java.util.Random;
import roguelike.Board;
import roguelike.items.ParentItem;

/**
 * @author Anthony
 */
public abstract class ParentWeapon extends ParentItem {
    
    int weaponDamageBase, weaponDamageModifier, weaponRange;

    //Constructor for when the weapon is on the game board
    public ParentWeapon(int x, int y, Board b) {
        
        super(x, y, b);
        
        itemType = "weapon";
        
        objectType = "item";
        
        Initialize();
        
    }
    
    //Constructor for when the weapon is in player's inventory
    public ParentWeapon() {
        
        this.itemType = "weapon";
        
        Initialize();
        
    }
    
    //Helper method for setting the item's attributes
    protected abstract void Initialize();
    
    //Class methods
    
    //Returns a random number representing the damage done by an attack
    public int getDamage() {
        
        Random r = new Random();

        return (r.nextInt(weaponDamageBase) + 1) + weaponDamageModifier;
        
    }
    
    public int getAccuracy(int accuracyModifier) {
        
        Random r = new Random();

        return (r.nextInt(20) + 1) + accuracyModifier;
        
    }
    
    public int getRange() {
        
        return weaponRange;
        
    }
    
    public abstract void onHitEffect();

    @Override
    public void update() {}
    
}
