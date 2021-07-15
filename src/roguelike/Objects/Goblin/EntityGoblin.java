/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.Objects.Goblin;

import java.awt.Color;
import roguelike.Board;
import roguelike.Objects.Entity;
import roguelike.Objects.GameObject;
import roguelike.State;
import roguelike.StateMachine;

/**
 * @author Anthony
 */
public class EntityGoblin extends Entity implements StateMachine {
    
    //Initialize variables
    State STATE_IDLE = new GoblinStateIdle();
    State STATE_CHASE= new GoblinStateChase();
    State STATE_DEAD = new GoblinStateDead();
    State currentState;

    //Constructor
    public EntityGoblin(int x, int y, Board b, int h) {
        
        //Call parent constructor
        super(x, y, b);

        //Board attributes
        this.symbol = 'g';
        this.isSolid = true;
        this.name = "Goblin";
        isOpaque = false;
        color = new Color(36,191,32);
        
        //Combat attributes
        this.armorClass = 12;
        this.hitPoints = h;
        this.speed = 1;
        
        //Add self to actionQueue
        gameboard.addObjectToList(this);
        
        start();
        
    }
    
    /* State machine methods */
    
    @Override
    public void start() {
        
        changeState(STATE_IDLE);
        
    } 
    
    @Override
    public void changeState(State state) {
        
        currentState = state;
        currentState.enterState(this);
        
    }
    
    /* Class methods */
    
    @Override
    public void takeDamage(GameObject source, int damageRoll, int hitRoll) {
        
        if (hitRoll < armorClass) {
            
            gameboard.getCamera().AddMessage("The goblin dodges the    attack!");
            
            return;
            
        }
        
        gameboard.getCamera().AddMessage("The goblin is hit with   your weapon!");
        
        this.hitPoints -= damageRoll;
        
    }
    
    @Override
    public void update() {
        
        currentState.update();
        
    }

}
