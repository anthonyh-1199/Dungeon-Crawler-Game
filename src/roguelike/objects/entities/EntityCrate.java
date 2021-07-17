/*

 */
package roguelike.objects.entities;

import roguelike.objects.entities.Entity;
import roguelike.Board;
import roguelike.objects.ParentGameObject;

/**
 * @author Anthony
 */
public class EntityCrate extends Entity{
    
    //Constructor
    public EntityCrate(int x, int y, Board b, int h){
        
        //Call parent constructor
        super(x, y, b);
        
        //Set variables
        hitPoints = h;
        symbol = 'C';
        isSolid = true;
        speed = 0;
        color = color.WHITE;
        name = "Crate";
        isOpaque = true;
        this.armorClass = 0;
        
        //Add self to actionQueue
        gameboard.addObjectToList(this);
        
    }
    
    @Override
    public void takeDamage(ParentGameObject source, int damageRoll, int hitRoll) {
        
        this.hitPoints -= damageRoll;
        
    }
    
    @Override
    public void update() {

        //Check if object is dead
        if (hitPoints <= 0){

            deleteSelf();
            return;
            
        }

    }
    
}