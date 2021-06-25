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
public class Player extends GameObject{
    
    int health, xposition, yposition;
    char symbol = '@';
    Board gameboard;
    
    public Player(int health, int xposition, int yposition, Board gameboard){
        this.health = health;
        this.xposition = xposition;
        this.yposition = yposition;
        this.gameboard = gameboard;
        
        gameboard.SetObjectAtSquare(xposition, yposition, this);
    }
    
    public void MoveUp(){
        //Check if space is empty
        if ((gameboard.GetObjectAtSquare(xposition, yposition - 1)) == null ||
            (gameboard.GetObjectAtSquare(xposition, yposition - 1)).isSolid == false){

                //Remove current position in board
                gameboard.SetObjectAtSquare(xposition, yposition, null);

                //Update player's positional variables
                yposition--;

                //Set player's board position
                gameboard.SetObjectAtSquare(xposition, yposition, this);

        }
        
        //If not, perform contextual behavior based on object in square
    }
    
    public void MoveDown(){
        //Check if space is empty
       if ((gameboard.GetObjectAtSquare(xposition, yposition + 1)) == null ||
            (gameboard.GetObjectAtSquare(xposition, yposition + 1)).isSolid == false){

                //Remove current position in board
                gameboard.SetObjectAtSquare(xposition, yposition, null);

                //Update player's positional variables
                yposition++;

                //Set player's board position
                gameboard.SetObjectAtSquare(xposition, yposition, this);

        }
        
        //If not, perform contextual behavior based on object in square
    }
    
    public void MoveRight(){
        //Check if space is empty
       if ((gameboard.GetObjectAtSquare(xposition + 1, yposition)) == null ||
            (gameboard.GetObjectAtSquare(xposition + 1, yposition)).isSolid == false){

                //Remove current position in board
                gameboard.SetObjectAtSquare(xposition, yposition, null);

                //Update player's positional variables
                xposition++;

                //Set player's board position
                gameboard.SetObjectAtSquare(xposition, yposition, this);

        }
        
        //If not, perform contextual behavior based on object in square
    }
    
    public void MoveLeft(){
        //Check if space is empty
       if ((gameboard.GetObjectAtSquare(xposition - 1, yposition)) == null ||
            (gameboard.GetObjectAtSquare(xposition - 1, yposition)).isSolid == false){

                //Remove current position in board
                gameboard.SetObjectAtSquare(xposition, yposition, null);

                //Update player's positional variables
                xposition--;

                //Set player's board position
                gameboard.SetObjectAtSquare(xposition, yposition, this);

        }
        
        //If not, perform contextual behavior based on object in square
    }

    @Override
    public char GetSymbol() {
        return symbol;
    }
}
