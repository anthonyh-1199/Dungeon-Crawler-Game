/*
 */
package roguelike.objects.entities.goblin;

import roguelike.objects.entities.Entity;
import roguelike.State;

/**
 * @author Anthony
 */
public class GoblinStateDead extends State {

    EntityGoblin parent;

    @Override
    public void enterState(Entity e) {
        
        parent = (EntityGoblin) e;
        
        parent.gameboard.getCamera().AddMessage("The goblin's been slain!");
        
        parent.deleteSelf();
        
    }

    @Override
    public void update() {}

    @Override
    public void checkTransitions() {}
    
}
