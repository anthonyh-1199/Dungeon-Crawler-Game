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
public class Board<T> {
    GameObject[][] gameboard;
    int size;
    
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
