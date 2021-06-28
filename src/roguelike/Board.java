/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import roguelike.Objects.Player;
import roguelike.Objects.GameObject;
import roguelike.Objects.EntitySheep;
import roguelike.Objects.EntityGoblin;
import roguelike.Objects.ObjectWall;
import roguelike.Objects.EntityCrate;
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
    
    //Creates a square board with given dimensions
    public Board(int size) {
        this.size = size;
        gameboard = new GameObject[size][size];
    }
    
    //Creates a square board and fills it based on a pre-made seed
    //Seed must match the dimensions of the board
    public Board(int size, String seed){
        
        this.size = size;
        gameboard = new GameObject[size][size];
        
        int currentCharacter = 0;
        
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++){
                
                char c = seed.charAt(currentCharacter);
                
                //Add game object based on character in String
                switch (c){
                    case '#':
                        new ObjectWall(x, y, this);
                        break;
                    case 'C':
                        new EntityCrate(x, y, this, 2);
                        break;
                    case 'P':
                        new Player(10, x, y, this);
                        break;
                    case 'G':
                        new EntityGoblin(x, y, this, 5);
                        break;
                    case 'S':
                        new EntitySheep(x, y, this, 3);
                        break;
                }
                
                currentCharacter++;
                
            }
        }
    }
    
    //Returns the object at a specified square, returns null if empty
    public T GetSquare(int x, int y){
        if ((x < size && x > 0) &&
            (y < size && y > 0)){
                return (T)gameboard[x][y];
        }
        return null;
    }
    
    public boolean CheckIfSquareIsEmpty(int x, int y){
        return (GetSquare(x, y) == null);
    }
    
    //Sets the object at a specified square
    public void SetSquare(int x, int y, GameObject object){
        
        if ((x < size && x > 0) &&
            (y < size && y > 0)){
                gameboard[x][y] = object;
        }
        
    }
    
    //Adds object to ActionQueue
    public void AddObjectToList(GameObject object) {
        actionList.add(object);
    }
    
    public void SetPlayer(Player player) {
        gamePlayer = player;
    }
    
    public Player GetPlayer() {
        return this.gamePlayer;
    }
    
    public int GetSize() {
        return this.size;
    }
    
    //Removes object from ActionQueue
    public void RemoveObjectFromList(GameObject object) {
        actionList.remove(object);
    }
    
    //Loops through all objects in the action list and calls their Update() functions
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
                if (GetSquare(x, y) == null){
                    s += '.' + " ";
                }
                
                //Else, add the respective character to represent the object
                else {
                    s += ((GameObject)GetSquare(x, y)).GetSymbol() + " ";
                }
            }
            
            s += "\n";
        }
        
        return s;
    }
    
}
