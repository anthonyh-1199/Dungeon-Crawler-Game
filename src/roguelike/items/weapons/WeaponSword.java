
package roguelike.items.weapons;

import java.awt.Color;
import roguelike.Board;

/**
 * @author Anthony
 */
public class WeaponSword extends ParentWeapon {
    
    //Constructor for when item is on the board
    public WeaponSword(int x, int y, Board b) {
        
        super(x, y, b);

    }
    
    //Constructor for when item is in player's inventory
    public WeaponSword() {

        super();
        
    }
    
    @Override
    protected void Initialize() {
        
        //Item attributes
        itemName = "Sword";

        //Board state attributes
        isSolid = false;
        symbol = 's';
        color = new Color(255,228,231);
        
        //Status effect attributes
        weaponDamageBase = 6;
        weaponDamageModifier = 2;
        weaponRange = 1;
        
    }

    @Override
    public void onHitEffect() {};

    @Override
    public void update() {}
    
}
