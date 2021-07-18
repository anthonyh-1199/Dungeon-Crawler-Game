
package roguelike.items.weapons;

import java.awt.Color;
import roguelike.Board;

/**
 * @author Anthony
 */
public class WeaponShortbow extends ParentWeapon {
    
    //Constructor for when item is on the board
    public WeaponShortbow(int x, int y, Board b) {
        
        super(x, y, b);

    }
    
    //Constructor for when item is in player's inventory
    public WeaponShortbow() {

        super();
        
    }
    
    @Override
    protected void Initialize() {
        
        //Item attributes
        itemName = "Shortbow";

        //Board state attributes
        isSolid = false;
        objectSymbol = ')';
        objectColor = new Color(170,85,0);
        
        //Status effect attributes
        weaponDamageBase = 5;
        weaponDamageModifier = 1;
        weaponRange = 8;
        
    }

    @Override
    public void onHitEffect() {};

    @Override
    public void update() {}
    
}
