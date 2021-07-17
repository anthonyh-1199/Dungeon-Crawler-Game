
package roguelike.items.weapons;

import java.awt.Color;
import roguelike.Board;

/**
 * @author Anthony
 */
public class WeaponDagger extends ParentWeapon {
    
    //Constructor for when item is on the board
    public WeaponDagger(int x, int y, Board b) {
        
        super(x, y, b);

    }
    
    //Constructor for when item is in player's inventory
    public WeaponDagger() {
        
        super();
        
    }
    
    @Override
    protected void Initialize() {
        
        //Item attributes
        itemName = "Dagger";

        //Board state attributes
        isSolid = false;
        objectSymbol = 'd';
        objectColor = new Color(255,228,231);
        
        //Status effect attributes
        weaponDamageBase = 3;
        weaponDamageModifier = 1;
        weaponRange = 1;
        
    }

    @Override
    public void onHitEffect() {};

    @Override
    public void update() {}
    
}
