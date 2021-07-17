/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.objects.entities.goblin;

import java.awt.Color;
import roguelike.Board;
import roguelike.objects.entities.ParentEntity;
import roguelike.objects.ParentGameObject;
import roguelike.State;
import roguelike.StateMachine;

/**
 * @author Anthony
 */
public class EntityGoblin extends ParentEntity implements StateMachine {
    
    //Initialize variables
    State STATE_IDLE = new GoblinStateIdle();
    State STATE_CHASE= new GoblinStateChase();
    State STATE_DEAD = new GoblinStateDead();
    State currentState;

    //Constructor
    public EntityGoblin(int x, int y, Board b) {
        
        //Call parent constructor
        super(x, y, b);

        //Board attributes
        objectSymbol = 'g';
        isSolid = true;
        objectName = "goblin";
        objectType = "enemy";
        isOpaque = false;
        objectColor = new Color(36,191,32);
        
        //Combat attributes
        this.armorClass = 12;
        this.hitPoints = 7;
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
    public void takeDamage(ParentGameObject source, int damageRoll, int hitRoll) {
        
        if (hitRoll < armorClass) {
            
            gameboard.getCamera().AddMessage("The goblin dodges the attack!");
            
            return;
            
        }
        
        gameboard.getCamera().AddMessage("The goblin is hit with your   weapon!");
        
        this.hitPoints -= damageRoll;
        
        //Check if object is dead
        if (hitPoints <= 0) {

            changeState(STATE_DEAD);
            
        }
        
    }
    
    @Override
    public void update() {
        
        currentState.update();
        
    }

}
