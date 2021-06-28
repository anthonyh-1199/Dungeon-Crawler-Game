
package roguelike;

import roguelike.Objects.GameObject;

/**
 * @author Anthony
 */

/**
 * This object's main purpose is to take a Board object and a focal point and
 * create a String representation of the area around said focal point
 */
public class Camera {
    
    GameObject focalPoint; //Often the player
    Board gameboard;
    int size;
    
    //Constructor
    
    public Camera(GameObject o, Board b, int s){
        this.focalPoint = o;
        this.gameboard = b;
        this.size = s;
    }
    
    //Class methods
    
    public String ToString(){
        String s = "";
        
        //Set the start point to the top-left corner relative to the focal point
        int startx = (focalPoint.GetX() - (size / 2));
        int starty = (focalPoint.GetY() - (size / 2));
        
        //Clamp the view so that it doesn't go out of range
        if (startx < 0){
            startx = 0;
        }
        if (starty < 0){
            starty = 0;
        }
        if ((startx + size) > gameboard.GetSize()){
            startx = gameboard.GetSize() - size;
        }
        if ((starty + size) > gameboard.GetSize()){
            starty = gameboard.GetSize() - size;
        }
        
        //Loop through the area that will be in the view
        for (int y = starty; y < starty + size; y++){
            
            for (int x = startx; x < startx + size; x++) {
                
                //If the square is empty/null, set the character to 0
                if (gameboard.CheckIfSquareIsEmpty(x, y)){
                    s += '.' + " ";
                }
                
                //Else, add the respective character to represent the object
                else {
                    s += ((GameObject)gameboard.GetSquare(x, y)).GetSymbol() + " ";
                }
            }
            
            s += "\n";
        }
        
        return s;
    }
    
    //Getter methods
    
    public GameObject GetFocalPoint(){
        return this.focalPoint;
    }
    
    public int GetSize(){
        return this.size;
    }
    
    //Setter methods
    
    public void SetFocalPoint(GameObject o){
        this.focalPoint = o;
    }
    
    public void SetSize(int s){
        this.size = s;
    }
}
