/*
 * Abstract class that encapsulates non-playable characters, both hostile and friendly
 */
package roguelike.Objects;

import roguelike.Board;

/**
 * @author Anthony
 */
public abstract class Entity extends GameObject{
    
    public int hitPoints, speed, armorClass;

    public Entity(int x, int y, Board b) {
        super(x, y, b);
    }
    
    public void takeDamage(GameObject source, int damageRoll, int hitRoll) {};
    
}
