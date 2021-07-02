
package View;

import java.util.ArrayList;
import java.util.List;
import roguelike.Board;
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
    
    //Returns a String that represents the area within the view
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
                
                //If an object is obstructing our view of the square, don't draw
                if (CheckLine(focalPoint.GetX(), focalPoint.GetY(), x, y) ||
                    CheckLine(x, y, focalPoint.GetX(), focalPoint.GetY())){
                    s += "  ";
                    continue;
                }

                //If the square is empty/null, set the character to 0
                if (gameboard.CheckIfSquareIsEmpty(x, y)){
                    s += '.' + " ";
                }
                
                //Else, add the respective character to represent the object
                else {
                    //If the square is surrounded on all 4 sides, don't draw it
                    if ((!gameboard.CheckIfSquareIsEmpty(x - 1, y) &&
                        !gameboard.CheckIfSquareIsEmpty(x + 1, y) &&
                        !gameboard.CheckIfSquareIsEmpty(x, y - 1) &&
                        !gameboard.CheckIfSquareIsEmpty(x, y + 1)) &&
                        gameboard.GetSquare(x - 1, y).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.GetSquare(x + 1, y).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.GetSquare(x, y - 1).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.GetSquare(x, y + 1).getClass().getSimpleName().equals("ObjectWall")){
                            s += "▪ ";
                        } else {
                            s += ((GameObject)gameboard.GetSquare(x, y)).GetSymbol() + " ";
                    }
                }
            }
            
            s += "\n";
        }
        
        return s;
    }
    
    //Returns true is an object is blocking the line
    public boolean CheckLine(int x0, int y0, int x1, int y1){
        
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
                //System.out.println(i[0] + ", " + i[1]);
                if (gameboard.GetSquare(i[0], i[1]) != null){
                    return true;
                }
            }
        }
        
        return false;
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
