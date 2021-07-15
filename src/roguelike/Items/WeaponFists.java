
package roguelike.Items;

import java.awt.Color;
import java.util.Random;
import roguelike.Board;

/**
 * @author Anthony
 */
public class WeaponFists extends ParentWeapon {
    
    //Constructor for when item is on the board
    public WeaponFists(int x, int y, Board b) {
        
        super(x, y, b);
        
        //Item attributes
        itemName = "Fists";
        itemType = "Weapon";
        
        //Board state attributes
        isSolid = false;
        symbol = 'd';
        color = new Color(255,228,231);
        
        //Status effect attributes
        weaponDamage = 2;
        weaponRange = 1;

    }
    
    //Constructor for when item is in player's inventory
    public WeaponFists() {

        //Item attributes
        itemName = "Fists";
        itemType = "Weapon";
        
        //Board state attributes
        isSolid = false;
        symbol = 'd';
        color = new Color(255,228,231);
        
        //Status effect attributes
        weaponDamage = 2;
        weaponRange = 1;
        
    }
    
    @Override
    public int getAccuracy(int accuracyModifier) {
        
        Random r = new Random();
        
        return (r.nextInt(20) + 1) + accuracyModifier;
        
    }

    //Returns a random number representing the damage done by an attack
    @Override
    public int getDamage() {
        
        Random r = new Random();
        
        return (r.nextInt(2) + 1);
        
    }
    
    @Override
    public void onHitEffect() {};

    @Override
    public void update() {};
    
}
