/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.Objects;

import java.awt.Color;
import roguelike.Board;

/**
 *
 * @author Anthony
 */
public class Player extends GameObject{
    
    int health, damage;
    
    public Player(int x, int y, Board b, int h){
        
        //Call parent constructor
        super(x, y, b);
        
        //Set variables
        this.health = h;
        this.symbol = '@';
        this.damage = 1;
        this.color = new Color(255,255,255);
        
        gameboard.SetPlayer(this);
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
        if ((gameboard.CheckIfSquareIsEmpty(xgoal, ygoal)) ||
            ((GameObject)gameboard.GetSquare(xgoal, ygoal)).IsSolid() == false){

                //Remove current position in board
                gameboard.SetSquare(xposition, yposition, null);
                
                //Update player's positional variables
                xposition = xgoal;
                yposition = ygoal;

                //Set to new position in board
                gameboard.SetSquare(xposition, yposition, this);

        }
        
        //If not, perform contextual behavior based on object in square
        else {
            switch (gameboard.GetSquare(xgoal, ygoal).getClass().getSuperclass().getSimpleName()){
                case "Entity":
                    Entity object = (Entity)gameboard.GetSquare(xgoal, ygoal);
                    object.health -= damage;
                    break;
            }
        }
    }

    @Override
    public void Update() {}
}
