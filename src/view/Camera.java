
package view;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.*;
import roguelike.Board;
import roguelike.objects.ParentGameObject;

/**
 * @author Anthony
 */

/**
 * This object's main purpose is to take a Board object and a focal point and create a representation of the area around said focal point
 * 
 * This object also contains data for showing miscellaneous UI elements
 */
public class Camera {
    
    ParentGameObject focalPoint; //Often the player
    Board gameboard;
    int size;
    ArrayList<String> messages = new ArrayList<>();
    final int MESSAGES_MAX_SIZE = 19;
            
    //Constructor
    
    public Camera(ParentGameObject o, Board b, int s){
        
        this.focalPoint = o;
        this.gameboard = b;
        this.size = s;
        
    }
    
    //Class methods
    
    public void getView(JTextPane j) {
        
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
        
        //Clamp the cursor if applicable
        
        if (gameboard.getCursor()[0] != -1 || gameboard.getCursor()[1] != -1){
            
            if (gameboard.getCursor()[0] < startx) {

                gameboard.getCursor()[0] = startx;

            }
            
            if (gameboard.getCursor()[1] < starty) {

                gameboard.getCursor()[1] = starty;

            }
            
            if (gameboard.getCursor()[0] >= (startx + size) - 1) {

                gameboard.getCursor()[0] = (startx + size) - 1;

            }
            
            if (gameboard.getCursor()[1] >= (starty + size) - 1) {

                gameboard.getCursor()[1] = (starty + size) - 1;

            }
            
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
                
                //Drawing cursor
                if (gameboard.getCursor()[0] == x && gameboard.getCursor()[1] == y) {
                    
                    s += 'X' + " ";
                    colors[i++] = Color.RED;
                    i++;

                    continue;
                    
                }
                
                //Drawing out-of-sight
                if ((Board.checkSightLine(focalPoint.getX(), focalPoint.getY(), x, y, gameboard) &&
                    Board.checkSightLine(x, y, focalPoint.getX(), focalPoint.getY(), gameboard)) ||
                    (Board.calculateDistance(focalPoint.getX(), focalPoint.getY(), x, y) > 8.5)){
                    
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

                    if (checkSurroundedByWalls(x, y)){
                        
                            s += "  ";
                            i++;
                            i++;
                            
                        continue;
                        
                    } 
                    
                    if (gameboard.getObjectAtSquare(x, y, 0).getClass().getSimpleName().equals("ObjectWall")){
                        
                        s += ((ParentGameObject)gameboard.getObjectAtSquare(x, y, 0)).getSymbol() + " ";
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

                //If the square is empty/null, set the character to '.'
                if (gameboard.checkIfSquareIsEmpty(x, y)){
                    
                    s += '.' + " ";
                    colors[i++] = Color.WHITE;
                    i++;
                    
                    continue;
                    
                }
                
                //Add the respective character to represent the object
                //If the square is surrounded on all 4 sides, don't draw it
                if (checkSurroundedByWalls(x, y)){

                        s += "  ";
                        colors[i++] = (gameboard.getObjectAtSquare(x, y, 0)).getColor();
                        i++;
                        
                    } else {
                    
                        s += ((ParentGameObject)gameboard.getObjectAtSquare(x, y, 0)).getSymbol() + " ";
                        colors[i++] = (gameboard.getObjectAtSquare(x, y, 0)).getColor();
                        i++;
                        
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
    
    //Helper method for getView()
    private boolean checkSurroundedByWalls(int x, int y) {
        
        int neighborsCount = 0;
        
        if (x > 0) {
            
            if (!gameboard.checkIfSquareIsEmpty(x - 1, y) &&
                gameboard.getObjectAtSquare(x - 1, y, 0).isType("wall")){
                
                neighborsCount++;
                
            }
            
        }
        
        if (x < gameboard.getSize()) {
            
            if (!gameboard.checkIfSquareIsEmpty(x + 1, y) &&
                gameboard.getObjectAtSquare(x + 1, y, 0).isType("wall")){
                
                neighborsCount++;
                
            }
            
        }
        
        if (y > 0) {
            
            if (!gameboard.checkIfSquareIsEmpty(x, y - 1) &&
                gameboard.getObjectAtSquare(x, y - 1, 0).isType("wall")){
                
                neighborsCount++;
                
            }
            
        }
        
        if (y < gameboard.getSize()) {
            
            if (!gameboard.checkIfSquareIsEmpty(x, y + 1) &&
                gameboard.getObjectAtSquare(x, y + 1, 0).isType("wall")){
                
                neighborsCount++;
                
            }
            
        }
        
        return neighborsCount == 4;
        
    }

    public void addMessage(String s) {
        
        //Add bullet point to message
        s = "??? " + s;

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
    
    public ParentGameObject getFocalPoint() {
        
        return this.focalPoint;
        
    }
    
    public int getSize() {
        
        return this.size;
        
    }
    
    public ArrayList<String> getMessages() {
        
        ArrayList<String> output = (ArrayList<String>) messages.clone();
        
        //Formating hack - if not at capacity, fill the remainder with blanks
        while (output.size() <= MESSAGES_MAX_SIZE) {
            
            output.add("                                ");
            
        }
        
        return output;
        
    }
    
    //Setter methods
    
    public void setFocalPoint(ParentGameObject o) {
        
        this.focalPoint = o;
        
    }
    
    public void setSize(int s) {
        
        this.size = s;
        
    }
}
