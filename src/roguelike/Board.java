/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.util.*;

/**
 *
 * @author Anthony
 */
public class Board<T> {
    
    GameObject[][] gameboard;
    int size;
    LinkedList<GameObject> actionList = new LinkedList<>();
    Player gamePlayer;
    
    public Board(int size) {
        this.size = size;
        gameboard = new GameObject[size][size];
    }
    
    //Returns the object at a specified square, returns null if empty
    public T GetObjectAtSquare(int x, int y){
        return (T)gameboard[x][y];
    }
    
    //Sets the object at a specified square
    public void SetObjectAtSquare(int x, int y, GameObject object){
        gameboard[x][y] = object;
    }
    
    //Adds object to ActionQueue
    public void AddObjectToList(GameObject object) {
        actionList.add(object);
    }
    
    public void SetPlayer(Player player){
        gamePlayer = player;
    }
    
    public Player GetPlayer(){
        return this.gamePlayer;
    }
    
    //Removes object from ActionQueue
    public void RemoveObjectFromList(GameObject object) {
        actionList.remove(object);
    }
    
    //Updates the game state oops through all objects in the action list and calls their Update() functions
    public void Update() {
        
        for (GameObject object : actionList) {
            object.Update();
        }
        
    }
    
    //Returns a representation of the board in a String
    public String ToString(){
        String s = "";
        
        for (int y = 0; y < gameboard.length; y++){
            
            for (int x = 0; x < gameboard.length; x++) {
                
                //If the square is empty/null, set the character to 0
                if (GetObjectAtSquare(x, y) == null){
                    s += '0' + " ";
                }
                
                //Else, add the respective character to represent the object
                else {
                    s += ((GameObject)GetObjectAtSquare(x, y)).GetSymbol() + " ";
                }
            }
            
            s += "\n";
        }
        
        return s;
    }
    
}
