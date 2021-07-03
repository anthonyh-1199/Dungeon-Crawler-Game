
package View;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    //Returns a String that represents the symbols of objects within the view
    public String GetSymbols(){
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
                if (CheckSightLine(focalPoint.GetX(), focalPoint.GetY(), x, y) &&
                    CheckSightLine(x, y, focalPoint.GetX(), focalPoint.GetY())){
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
    
    //Returns a 1-dimensional array that contains the colors of each symbol
    //in the view
    public Color[] GetColors() {

        //Create array to hold colors
        Color[] colors = new Color[GetSymbols().length()];

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
        
        //Create iterator for colors
        int i = 0;
        
        //Loop through the area that will be in the view
        for (int y = starty; y < starty + size; y++){
            
            for (int x = startx; x < startx + size; x++) {
                
                //If an object is obstructing our view of the square, don't draw
                if (CheckSightLine(focalPoint.GetX(), focalPoint.GetY(), x, y) &&
                    CheckSightLine(x, y, focalPoint.GetX(), focalPoint.GetY())){
                    i++;
                    i++;
                    continue;
                }

                //If the square is empty/null, set the character to 0
                if (gameboard.CheckIfSquareIsEmpty(x, y)){
                    colors[i++] = Color.WHITE;
                    i++;;
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
                            colors[i++] = ((GameObject)gameboard.GetSquare(x, y)).GetColor();
                            i++;
                        } else {
                            colors[i++] = ((GameObject)gameboard.GetSquare(x, y)).GetColor();
                            i++;
                    }
                }
            }
            
            i++;
        }

        return colors;
    }
    
    //http://www.roguebasin.com/index.php/Improved_Shadowcasting_in_Java
    
    //Creates a 2d array where 1 - opaque square and 0 - transparent
    public int[][] ComputeLightMap(){
        
        //Create 2d array to hold values
        int[][] lightMap = new int[size][size];
        
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
            
            String s = "";
            
            for (int x = startx; x < startx + size; x++) {
                
                lightMap[x - startx][y - starty] = 0;

                if (gameboard.GetSquare(x, y) != null &&
                    gameboard.GetSquare(x, y).getClass().getSimpleName().equals("ObjectWall")){
                    lightMap[x - startx][y - starty] = 1;
                }
                
                s += lightMap[x - startx][y - starty];
            }
            
            System.out.println(s);
        }
        
        return lightMap;
    }
    
    //Returns true is an object is blocking the line
    public boolean CheckSightLine(int x0, int y0, int x1, int y1){
        
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
