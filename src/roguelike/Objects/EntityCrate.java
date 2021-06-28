/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.Objects;

import roguelike.Board;

/**
 *
 * @author Anthony
 */
public class EntityCrate extends Entity{
    
    //Constructor
    public EntityCrate(int xposition, int yposition, Board gameboard, int health){
        
        //Add self to board
        gameboard.SetSquare(xposition, yposition, this);
        
        //Set variables
        this.xposition = xposition;
        this.yposition = yposition;
        this.gameboard = gameboard;
        this.health = health;
        this.symbol = 'C';
        this.isSolid = true;
        this.speed = 0;
        
        //Add self to actionQueue
        gameboard.AddObjectToList(this);
    }

    public void Die(){
        
        //Remove self from board
        gameboard.SetSquare(xposition, yposition, null);
        
        //Remove self from actionList
        gameboard.RemoveObjectFromList(this);
        
        //TO-DO: Add items drops
        
    }
    
    @Override
    public void Update() {

        //Check if object is dead
        if (health <= 0){

            Die();
            return;
            
        }

    }
    
}