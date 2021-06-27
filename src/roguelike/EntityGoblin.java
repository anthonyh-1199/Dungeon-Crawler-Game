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
public class EntityGoblin extends Entity{

    //Constructor
    public EntityGoblin(int xposition, int yposition, Board gameboard, int health){
        
        //Add self to board
        gameboard.SetObjectAtSquare(xposition, yposition, this);
        
        //Set variables
        this.xposition = xposition;
        this.yposition = yposition;
        this.gameboard = gameboard;
        this.health = health;
        this.symbol = 'Z';
        this.isSolid = true;
        this.speed = 1;
        
        //Add self to actionQueue
        gameboard.AddObjectToList(this);
    }
    
    public void Move(Player p){
        int xplayer = p.xposition;
        int yplayer = p.yposition;
        
        int xgoal = xposition;
        int ygoal = yposition;
        
        if ((xplayer > xposition) && ((gameboard.GetObjectAtSquare(xposition + 1, yposition)) == null)) {
            xgoal++;
        } 
        else 
        if ((xplayer < xposition) && ((gameboard.GetObjectAtSquare(xposition - 1, yposition)) == null)) {
            xgoal--;
        }
        else 
        if ((yplayer > yposition) && ((gameboard.GetObjectAtSquare(xposition, yposition + 1)) == null)) {
            ygoal++;
        }
        else 
        if ((yplayer < yposition) && ((gameboard.GetObjectAtSquare(xposition, yposition - 1)) == null)) {
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
