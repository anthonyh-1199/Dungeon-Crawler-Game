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
    public EntityCrate(int x, int y, Board b, int h){
        
        //Call parent constructor
        super(x, y, b);
        
        //Set variables
        this.health = h;
        this.symbol = 'C';
        this.isSolid = true;
        this.speed = 0;
        this.color = color.WHITE;
        
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