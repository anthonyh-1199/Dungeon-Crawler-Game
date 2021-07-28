/*
 */
package roguelike.objects.entities.goblin;

import roguelike.objects.entities.ParentEntity;
import roguelike.State;
import roguelike.items.food.FoodMeat;

/**
 * @author Anthony
 */
public class GoblinStateDead extends State {

    EntityGoblin parent;

    @Override
    public void enterState(ParentEntity e) {
        
        parent = (EntityGoblin) e;
        
        parent.gameboard.getCamera().addMessage("The goblin's been slain!");
        
        new FoodMeat(parent.xposition, parent.yposition, parent.gameboard);
        
        parent.deleteSelf();
        
    }

    @Override
    public void update() {}

    @Override
    public void checkTransitions() {}
    
}
