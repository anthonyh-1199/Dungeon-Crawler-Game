
package roguelike.items.weapons;

import java.awt.Color;
import roguelike.Board;

/**
 * @author Anthony
 */
public class WeaponFists extends ParentWeapon {
    
    //Constructor for when item is on the board
    public WeaponFists(int x, int y, Board b) {
        
        super(x, y, b);

    }
    
    //Constructor for when item is in player's inventory
    public WeaponFists() {

        super();
        
    }
    
    @Override
    protected void Initialize() {
        
        //Item attributes
        itemName = "Fists";

        //Board state attributes
        isSolid = false;
        objectSymbol = '@';
        objectColor = new Color(255,228,231);
        
        //Status effect attributes
        weaponDamageBase = 2;
        weaponDamageModifier = 0;
        weaponRange = 1;
        
    }

    @Override
    public void onHitEffect() {};

    @Override
    public void update() {};
    
}
