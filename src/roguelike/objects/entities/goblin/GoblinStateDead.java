/*
 */
package roguelike.objects.entities.goblin;

import roguelike.objects.entities.ParentEntity;
import roguelike.State;

/**
 * @author Anthony
 */
public class GoblinStateDead extends State {

    EntityGoblin parent;

    @Override
    public void enterState(ParentEntity e) {
        
        parent = (EntityGoblin) e;
        
        parent.gameboard.getCamera().addMessage("The goblin's been slain!");
        
        parent.deleteSelf();
        
    }

    @Override
    public void update() {}

    @Override
    public void checkTransitions() {}
    
}
