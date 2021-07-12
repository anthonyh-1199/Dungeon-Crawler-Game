/*
 * Abstract class that encapsulates non-playable characters, both hostile and friendly
 */
package roguelike.Objects;

import roguelike.Board;

/**
 * @author Anthony
 */
public abstract class Entity extends GameObject{
    
    public int health, speed;

    public Entity(int x, int y, Board b) {
        super(x, y, b);
    }
    
}
