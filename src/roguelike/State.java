/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import roguelike.Objects.Entity;

/**
 *
 * @author Anthony
 */
public abstract class State {
    
    public abstract void EnterState(Entity e);

    public abstract void Update();
    
    public abstract void CheckTransitions();
    
}
