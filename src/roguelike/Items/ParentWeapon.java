
package roguelike.Items;

import roguelike.Board;

/**
 * @author Anthony
 */
public abstract class ParentWeapon extends ParentItem {
    
    int weaponDamage, weaponRange;

    public ParentWeapon(int x, int y, Board b) {
        
        super(x, y, b);
        
        this.itemType = "weapon";
        
    }
    
    //Constructor for when item is in player's inventory
    public ParentWeapon() {
        
        this.itemType = "weapon";
    }
    
    
    //Class methods
    
    public abstract int getDamage();
    
    public abstract int getAccuracy(int accuracyModifier);
    
    public abstract void onHitEffect();

    @Override
    public void update() {}
    
}
