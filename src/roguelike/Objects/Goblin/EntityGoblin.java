/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.Objects.Goblin;

import java.awt.Color;
import roguelike.Board;
import roguelike.Objects.Entity;
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
        
        //Set variables
        this.hitPoints = h;
        this.symbol = 'g';
        this.isSolid = true;
        this.speed = 1;
        this.name = "Goblin";
        isOpaque = false;
        color = new Color(36,191,32);
        
        //Add self to actionQueue
        gameboard.AddObjectToList(this);
        
        Start();
    }
    
    /* State machine methods */
    
    @Override
    public void Start() {
        ChangeState(STATE_IDLE);
    } 
    
    @Override
    public void ChangeState(State state) {
        currentState = state;
        currentState.EnterState(this);
    }
    
    /* Class methods */

    @Override
    public void Update() {
        currentState.Update();
    }
    
}
