/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import View.Camera;
import roguelike.Objects.Player.Player;
import roguelike.Objects.GameObject;
import roguelike.Objects.EntitySheep;
import roguelike.Objects.Goblin.EntityGoblin;
import roguelike.Objects.ObjectWall;
import roguelike.Objects.EntityCrate;
import java.util.*;
import roguelike.Items.ItemDagger;

/**
 *
 * @author Anthony
 */
public class Board<T> {
    
    Tile[][] gameboard;
    int size;
    LinkedList<GameObject> actionList = new LinkedList<>();
    Player player;
    Camera camera;
    
    //Creates a square board with given dimensions
    public Board(int size) {
        this.size = size;
        gameboard = new Tile[size][size];
        
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++){
                gameboard[x][y] = new Tile();
            }
        }
    }
    
    //Creates a square board and fills it based on a pre-made seed
    //Seed must match the dimensions of the board
    public Board(int size, String seed){
        
        this.size = size;
        gameboard = new Tile[size][size];
        
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++){
                gameboard[x][y] = new Tile();
            }
        }
        
        int currentCharacter = 0;
        
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++){
                
                char c = seed.charAt(currentCharacter);
                
                //Add game object based on character in String
                switch (c){
                    case '#':
                        new ObjectWall(x, y, this);
                        break;
                    case 'd':
                        new ItemDagger(x, y, this);
                        break;
                    case 'C':
                        new EntityCrate(x, y, this, 2);
                        break;
                    case 'P':
                        new Player(x, y, this, 10);
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
    public T GetSquare(int x, int y) {
        if ((x < size && x >= 0) &&
            (y < size && y >= 0)) {
                return (T)gameboard[x][y].GetObject();
        }
        return null;
    }
    
    public Camera GetCamera() {
        return this.camera;
    }
    
    //Return true if the square is empty
    public boolean CheckIfSquareIsEmpty(int x, int y) {
        return (GetSquare(x, y) == null);
    }
    
    //Sets the object at a specified square
    public void SetSquare(int x, int y, GameObject object) {
        if ((x < size && x >= 0) &&
            (y < size && y >= 0)){
                gameboard[x][y].SetObject(object);
        }
    }
    
    //Adds object to ActionQueue
    public void AddObjectToList(GameObject object) {
        actionList.add(object);
    }
    
    public void SetSeen(int x, int y) {
        gameboard[x][y].SetSeen(true);
    }
    
    public boolean GetSeen(int x, int y) {
        if ((x < size && x >= 0) &&
            (y < size && y >= 0)) {
                return (gameboard[x][y].GetSeen());
        }
        return false;
    }
    
    public void SetPlayer(Player player) {
        this.player = player;
        this.camera = new Camera(player, this, 35);
    }
    
    public Player GetPlayer() {
        return this.player;
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
    
        //Returns true is an object is blocking the line
    public static boolean CheckSightLine(int x0, int y0, int x1, int y1, Board gameboard){
        
        //Holds starting coordinates
        int startx = x0;
        int starty = y0;
        
        //Create list to store points on line
        List<int[]> points = new ArrayList<>();
        
        //Line generation via Bresenham's complete line algorithm
        int dx = Math.abs(x1 - x0);
        int sx = x0 < x1 ? 1 : -1;
        int dy = -Math.abs(y1 - y0);
        int sy = y0 < y1 ? 1 : -1;
        int err = dx + dy;
        
        while (true){
            points.add(new int[]{x0,y0});
            if (x0 == x1 && y0 == y1){
                break;
            }
            int e2 = 2*err;
            if (e2 >= dy){
                err += dy;
                x0 += sx;
            }
            if (e2 <= dx){
                err += dx;
                y0 += sy;
            }
        }

        //Check line for obstructions

        for (int[] i : points){
            if (!((i[0] == x1 && i[1] == y1) || (i[0] == startx && i[1] == starty))){
                if (gameboard.GetSquare(i[0], i[1]) != null){
                    return true;
                }
            }
        }
        
        return false;
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
