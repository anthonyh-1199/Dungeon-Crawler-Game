/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.Objects;

import java.util.Random;
import roguelike.Board;

/**
 *
 * @author Anthony
 */
public class EntitySheep extends Entity{
    
    //Constructor
    public EntitySheep(int xposition, int yposition, Board gameboard, int health){
        
        //Add self to board
        gameboard.SetSquare(xposition, yposition, this);
        
        //Set variables
        this.xposition = xposition;
        this.yposition = yposition;
        this.gameboard = gameboard;
        this.health = health;
        this.symbol = 'S';
        this.isSolid = true;
        this.speed = 1;
        
        //Add self to actionQueue
        gameboard.AddObjectToList(this);
    }
    
    public void Move(Player p){
        
        int xgoal = xposition;
        int ygoal = yposition;
        
        //Get square in random direction
        Random r = new Random();
        int randomDirection = r.nextInt((4) + 1);
        
        switch (randomDirection){
            case 0:
                ygoal--;
                break;
            case 1:
                ygoal++;
                break;
            case 2:
                xgoal++;
                break;
            case 3:
                xgoal--;
                break;
            default:
                break;
        }
        
        //If space is empty, move into it
        if (gameboard.CheckIfSquareIsEmpty(xgoal, ygoal)) {

            //Remove current position in board
            gameboard.SetSquare(xposition, yposition, null);
            
            //Update positional variables
            xposition = xgoal;
            yposition = ygoal;

            //Set to new position in board
            gameboard.SetSquare(xgoal, ygoal, this);
            
        }
        
    }

    public void Die(){
        
        //Remove self from board
        gameboard.SetSquare(xposition, yposition, null);
        
        //Remove self from actionList
        gameboard.RemoveObjectFromList(this);
        
    }
    
    @Override
    public void Update() {

        //Check if object is dead
        if (health <= 0){

            Die();
            return;
            
        }
        
        //Else, move
        for (int i = 0; i < speed; i++){
            Move(gameboard.GetPlayer());
        }

    }
    
}