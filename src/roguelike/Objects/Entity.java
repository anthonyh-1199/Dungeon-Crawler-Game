/*
 * Abstract class that encapsulates non-playable characters, both hostile and friendly
 */
package roguelike.Objects;

import roguelike.Board;

/**
 *
 * @author Anthony
 */
public abstract class Entity extends GameObject{
    
    int health, xposition, yposition, speed;
    Board gameboard;
    
}
