
package roguelike.Items;

import roguelike.Board;

/**
 * @author Anthony
 */
public abstract class ParentWeapon extends ParentItem {
    
    int weaponDamage, weaponRange;

    public ParentWeapon(int x, int y, Board b) {
        super(x, y, b);
    }
    
    //Constructor for when item is in player's inventory
    public ParentWeapon() {}
    
    //Class methods
    
    abstract int GetDamage();
    
    abstract void OnHitEffect();

    @Override
    public void Update() {}
    
}
