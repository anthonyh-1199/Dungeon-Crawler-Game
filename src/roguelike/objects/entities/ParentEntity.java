/*
 * Abstract class that encapsulates non-playable characters, both hostile and friendly
 */
package roguelike.objects.entities;

import roguelike.Board;
import roguelike.objects.ParentGameObject;

/**
 * @author Anthony
 */
public abstract class ParentEntity extends ParentGameObject{
    
    public int hitPoints, speed, armorClass;

    public ParentEntity(int x, int y, Board b) {
        
        super(x, y, b);
        
    }

    public void takeDamage(ParentGameObject source, int damageRoll, int hitRoll) {};
    
}
