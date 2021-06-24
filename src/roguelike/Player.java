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
public class Player {
    
    int health, xposition, yposition;
    char character = '@';
    Board gameboard;
    
    public Player(int health, int xposition, int yposition, Board gameboard){
        this.health = health;
        this.xposition = xposition;
        this.yposition = yposition;
        this.gameboard = gameboard;
    }
    
    public void MoveUp(){
        //Check if space is empty
        if ((gameboard.GetObjectAtSquare(xposition, yposition - 1)).isSolid == false){
            
            //Move player upwards
            yposition--;
            
        }
        
        //If not, perform contextual behavior based on object in square
    }
    
    public void MoveDown(){
        //Check if space is empty
        if ((gameboard.GetObjectAtSquare(xposition, yposition + 1)).isSolid == false){
            
            //Move player upwards
            yposition++;
            
        }
        
        //If not, perform contextual behavior based on object in square
    }
}
