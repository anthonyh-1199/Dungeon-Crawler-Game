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
    Board gameboard;
    
    public Player(int health, int xposition, int yposition, Board gameboard){
        
        //Set variables
        this.health = health;
        this.xposition = xposition;
        this.yposition = yposition;
        this.gameboard = gameboard;
        this.symbol = '@';
        
        gameboard.SetObjectAtSquare(xposition, yposition, this);
    }
    
    public void Move(String direction){
        
        //Initialize variables
        int xgoal = xposition;
        int ygoal = yposition;
        
        //Set x and y positions of goal based on direction
        switch (direction){
            case "UP":
                ygoal--;
                break;
            case "DOWN":
                ygoal++;
                break;
            case "RIGHT":
                xgoal++;
                break;
            case "LEFT":
                xgoal--;
                break;
        }
        
        //If space is empty or nonsolid, move into it
        if ((gameboard.GetObjectAtSquare(xgoal, ygoal)) == null ||
            ((GameObject)gameboard.GetObjectAtSquare(xgoal, ygoal)).IsSolid() == false){

                //Remove current position in board
                gameboard.SetObjectAtSquare(xposition, yposition, null);
                
                //Update player's positional variables
                xposition = xgoal;
                yposition = ygoal;

                //Set to new position in board
                gameboard.SetObjectAtSquare(xposition, yposition, this);

        }
        
        //If not, perform contextual behavior based on object in square
        else {
            switch (gameboard.GetObjectAtSquare(xgoal, ygoal).getClass().getSuperclass().getSimpleName()){
                case "Entity":
                    Entity object = (Entity)gameboard.GetObjectAtSquare(xgoal, ygoal);
                    object.health--;
                    break;
            }
        }
    }

    @Override
    public void Update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
