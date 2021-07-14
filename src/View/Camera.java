
package View;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import roguelike.Board;
import roguelike.Objects.GameObject;

/**
 * @author Anthony
 */

/**
 * This object's main purpose is to take a Board object and a focal point and
 * create a representation of the area around said focal point
 * 
 * This object also contains data for showing miscellaneous UI elements
 */
public class Camera {
    
    GameObject focalPoint; //Often the player
    Board gameboard;
    int size;
    ArrayList<String> messages = new ArrayList<>();
    final int MESSAGES_MAX_SIZE = 17;
            
    //Constructor
    
    public Camera(GameObject o, Board b, int s){
        
        this.focalPoint = o;
        this.gameboard = b;
        this.size = s;
        
    }
    
    //Class methods
    
    public void GetView(JTextPane j) {
        
        //Initialize variables
        String s = "";
        StyledDocument doc = j.getStyledDocument();
        Color[] colors = new Color[2 * (size * size) + size];
        int i = 0;
        Color darkColor = new Color(20,40,50);
        
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
                
                //Drawing out-of-sight
                if ((Board.CheckSightLine(focalPoint.GetX(), focalPoint.GetY(), x, y, gameboard) &&
                    Board.CheckSightLine(x, y, focalPoint.GetX(), focalPoint.GetY(), gameboard)) ||
                    (Math.sqrt(Math.pow((focalPoint.GetX() - x), 2) + Math.pow((focalPoint.GetY() - y), 2)) > 8.5)){
                    
                    //If we haven't seen that square yet, don't draw it
                    if (!gameboard.GetSeen(x, y)){
                        s += "  ";
                        i++;
                        i++;
                        continue;
                    }

                    //If the square is surrounded on all 4 sides, don't draw it
                    if (gameboard.CheckIfSquareIsEmpty(x, y)){
                        s += '.' + " ";
                        colors[i++] = darkColor;
                        i++;
                        continue;
                    }

                    if ((!gameboard.CheckIfSquareIsEmpty(x - 1, y) &&
                        !gameboard.CheckIfSquareIsEmpty(x + 1, y) &&
                        !gameboard.CheckIfSquareIsEmpty(x, y - 1) &&
                        !gameboard.CheckIfSquareIsEmpty(x, y + 1)) &&
                        gameboard.GetSquare(x - 1, y).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.GetSquare(x + 1, y).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.GetSquare(x, y - 1).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.GetSquare(x, y + 1).getClass().getSimpleName().equals("ObjectWall")){
                            s += "  ";
                            i++;
                            i++;
                        continue;
                    } 
                    
                    if (gameboard.GetSquare(x, y).getClass().getSimpleName().equals("ObjectWall")){
                        s += ((GameObject)gameboard.GetSquare(x, y)).GetSymbol() + " ";
                        colors[i++] = darkColor;
                        i++;
                        continue;
                    }
                    
                    s += '.' + " ";
                    colors[i++] = darkColor;
                    i++;
                    continue;
                }
                
                //Make square seen
                gameboard.SetSeen(x, y);

                //If the square is empty/null, set the character to .
                if (gameboard.CheckIfSquareIsEmpty(x, y)){
                    s += '.' + " ";
                    colors[i++] = Color.WHITE;
                    i++;
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
                            s += "  ";
                            colors[i++] = ((GameObject)gameboard.GetSquare(x, y)).GetColor();
                            i++;
                        } else {
                            s += ((GameObject)gameboard.GetSquare(x, y)).GetSymbol() + " ";
                            colors[i++] = ((GameObject)gameboard.GetSquare(x, y)).GetColor();
                            i++;
                    }
                }
            }
            
            i++;
            s += "\n";
        }
        
        //Set text
        j.setText(s);
        
        //Set colors
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setBackground(set, Color.BLACK);

        for (int k = 0; k < s.length(); k++) {
            Color c = colors[k];
            if (c != null){
                StyleConstants.setForeground(set, c);
            }
            doc.setCharacterAttributes(k, 1, set, true);
        }
    }
    
    public void AddMessage(String s) {
        
        //Add bullet point to message
        s = "• " + s;

        //Split larger messages into smaller ones
        for (int i = 0; i < s.length(); i++){
            
            if (i == 27) {
                
                //Put the beginning part of the message in its own line
                messages.add(s.substring(0, i) + " ");
                
                s = s.substring(i, s.length());
                
                //Reset for loop
                i = 0;

            }
            
        }
        
        //Add padding to shorter messages (helps with formating)
        while (s.length() <= 27) {
            
            s += " ";
            
        }
        
        //Add message
        messages.add(s);
        
        //Remove old messages if we've exceeded capacity
        while (messages.size() > MESSAGES_MAX_SIZE) {
            
            messages.remove(0);
            
        }

    }

    //Getter methods
    
    public GameObject GetFocalPoint() {
        return this.focalPoint;
    }
    
    public int GetSize() {
        return this.size;
    }
    
    public ArrayList<String> GetMessages() {
        
        ArrayList<String> output = (ArrayList<String>) messages.clone();
        
        //Formating hack - if not at capacity, fill the remainder with blanks
        while (output.size() <= MESSAGES_MAX_SIZE) {
            
            output.add("                            ");
            
        }
        
        return output;
    }
    
    //Setter methods
    
    public void SetFocalPoint(GameObject o) {
        this.focalPoint = o;
    }
    
    public void SetSize(int s) {
        this.size = s;
    }
}
