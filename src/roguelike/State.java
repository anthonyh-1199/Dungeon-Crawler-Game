/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import roguelike.objects.entities.ParentEntity;

/**
 *
 * @author Anthony
 */
public abstract class State {
    
    public abstract void enterState(ParentEntity e);

    public abstract void update();
    
    public abstract void checkTransitions();
    
}
