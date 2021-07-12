/*
 */
package roguelike.Objects.Goblin;

import roguelike.Objects.Entity;
import roguelike.State;

/**
 * @author Anthony
 */
public class GoblinStateDead extends State {

    EntityGoblin parent;

    @Override
    public void EnterState(Entity e) {
        
        parent = (EntityGoblin) e;
        
        //Remove self from board
        e.gameboard.SetSquare(e.xposition, e.yposition, null);
        
        //Remove self from actionList
        e.gameboard.RemoveObjectFromList(e);
        
    }

    @Override
    public void Update() {}

    @Override
    public void CheckTransitions() {}
    
}
