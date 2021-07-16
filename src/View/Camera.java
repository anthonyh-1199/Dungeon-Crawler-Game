
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
    final int MESSAGES_MAX_SIZE = 19;
            
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
        Color[] colors = new Color[2 * ((size + 4) * (size + 4)) + (size + 4)];
        int i = 0;
        Color darkColor = new Color(20,40,50);
        
        //Set the start point to the top-left corner relative to the focal point
        int startx = (focalPoint.getX() - (size / 2));
        int starty = (focalPoint.getY() - (size / 2));
        
        //Clamp the view so that it doesn't go out of range
        if (startx < 0) {
            
            startx = 0;
            
        }
        if (starty < 0) {
            
            starty = 0;
            
        }
        if ((startx + size) > gameboard.getSize()) {
            
            startx = gameboard.getSize() - size;
            
        }
        if ((starty + size) > gameboard.getSize()) {
            
            starty = gameboard.getSize() - size;
            
        }
        
        //Format upper border
        for (int n = 0; n < size + 2; n++) {
            
            s += "  ";
            i++;
            i++;
            
        }
        
        i++;
        i++;
        s += "\n  ";
        
        //Loop through the area that will be in the view
        for (int y = starty; y < starty + size; y++){
            
            for (int x = startx; x < startx + size; x++) {
                
                //Drawing out-of-sight
                if ((Board.checkSightLine(focalPoint.getX(), focalPoint.getY(), x, y, gameboard) &&
                    Board.checkSightLine(x, y, focalPoint.getX(), focalPoint.getY(), gameboard)) ||
                    (Math.sqrt(Math.pow((focalPoint.getX() - x), 2) + Math.pow((focalPoint.getY() - y), 2)) > 8.5)){
                    
                    //If we haven't seen that square yet, don't draw it
                    if (!gameboard.getSeen(x, y)){
                        s += "  ";
                        i++;
                        i++;
                        continue;
                    }

                    //If the square is surrounded on all 4 sides, don't draw it
                    if (gameboard.checkIfSquareIsEmpty(x, y)){
                        s += '.' + " ";
                        colors[i++] = darkColor;
                        i++;
                        continue;
                    }

                    if ((!gameboard.checkIfSquareIsEmpty(x - 1, y) &&
                        !gameboard.checkIfSquareIsEmpty(x + 1, y) &&
                        !gameboard.checkIfSquareIsEmpty(x, y - 1) &&
                        !gameboard.checkIfSquareIsEmpty(x, y + 1)) &&
                        gameboard.getSquare(x - 1, y).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.getSquare(x + 1, y).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.getSquare(x, y - 1).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.getSquare(x, y + 1).getClass().getSimpleName().equals("ObjectWall")){
                            s += "  ";
                            i++;
                            i++;
                        continue;
                    } 
                    
                    if (gameboard.getSquare(x, y).getClass().getSimpleName().equals("ObjectWall")){
                        s += ((GameObject)gameboard.getSquare(x, y)).getSymbol() + " ";
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
                gameboard.setSeen(x, y);

                //If the square is empty/null, set the character to .
                if (gameboard.checkIfSquareIsEmpty(x, y)){
                    s += '.' + " ";
                    colors[i++] = Color.WHITE;
                    i++;
                }
                
                //Else, add the respective character to represent the object
                else {
                    //If the square is surrounded on all 4 sides, don't draw it
                    if ((!gameboard.checkIfSquareIsEmpty(x - 1, y) &&
                        !gameboard.checkIfSquareIsEmpty(x + 1, y) &&
                        !gameboard.checkIfSquareIsEmpty(x, y - 1) &&
                        !gameboard.checkIfSquareIsEmpty(x, y + 1)) &&
                        gameboard.getSquare(x - 1, y).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.getSquare(x + 1, y).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.getSquare(x, y - 1).getClass().getSimpleName().equals("ObjectWall") &&
                        gameboard.getSquare(x, y + 1).getClass().getSimpleName().equals("ObjectWall")){
                            s += "  ";
                            colors[i++] = ((GameObject)gameboard.getSquare(x, y)).getColor();
                            i++;
                        } else {
                            s += ((GameObject)gameboard.getSquare(x, y)).getSymbol() + " ";
                            colors[i++] = ((GameObject)gameboard.getSquare(x, y)).getColor();
                            i++;
                    }
                }
            }
            
            i++;
            i++;
            i++;
            
            s += "  \n  ";
            
            i++;
            i++;
            
        }
        
        //Format lower border
        for (int n = 0; n < size + 2 - 1; n++) {
            
            s += "  ";
            i++;
            i++;
            
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
            
            if (i == 31) {
                
                //Put the beginning part of the message in its own line
                messages.add(s.substring(0, i) + " ");
                
                s = s.substring(i, s.length());
                
                //Reset for loop
                i = 0;

            }
            
        }
        
        //Add padding to shorter messages (helps with formating)
        while (s.length() <= 31) {
            
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
            
            output.add("                                ");
            
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
