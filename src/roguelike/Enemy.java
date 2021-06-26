/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

/**
 *
 * @author Anthony
 */
public class Enemy extends GameObject{
    
    int health, xposition, yposition;
    Board gameboard;
    char symbol = 'S';
    boolean isSolid = true;
    
    //Constructor
    public Enemy(int xposition, int yposition, Board gameboard, int health){
        
        //Add self to board
        gameboard.SetObjectAtSquare(xposition, yposition, this);
        
        //Set variables
        this.xposition = xposition;
        this.yposition = yposition;
        this.gameboard = gameboard;
        this.health = health;
        
        //Add self to actionQueue
        gameboard.AddObjectToList(this);
    }
    
    public void Move(Player p){
        int xplayer = p.xposition;
        int yplayer = p.yposition;
        
        int xgoal = xposition;
        int ygoal = yposition;
        
        if (xplayer > xposition + 1){
            xgoal++;
        } 
        else 
        if (xplayer < xposition - 1){
            xgoal--;
        }
        else 
        if (yplayer > yposition + 1){
            ygoal++;
        }
        else 
        if (yplayer < yposition - 1){
            ygoal--;
        }
        
        //If space is empty, move into it
        if ((gameboard.GetObjectAtSquare(xgoal, ygoal)) == null) {

            //Remove current position in board
            gameboard.SetObjectAtSquare(xposition, yposition, null);
            
            //Update positional variables
            xposition = xgoal;
            yposition = ygoal;

            //Set to new position in board
            gameboard.SetObjectAtSquare(xgoal, ygoal, this);
            
        }
        
    }

    public void Die(){
        
        //Remove self from board
        gameboard.SetObjectAtSquare(xposition, yposition, null);
        
        //Remove self from actionList
        gameboard.RemoveObjectFromList(this);
        
    }
    
    @Override
    public boolean IsSolid(){
        return isSolid;
    }
    
    @Override
    public char GetSymbol(){
        return symbol;
    }

    @Override
    public void Update() {

        //Check if object is dead
        if (health <= 0){

            Die();
            return;
            
        }
        
        //Else, move
        Move(gameboard.GetPlayer());

    }
    
}
