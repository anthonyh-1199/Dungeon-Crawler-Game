
package roguelike;

import roguelike.items.weapons.*;
import roguelike.objects.entities.EntitySheep;
import roguelike.objects.ObjectWall;
import roguelike.objects.ParentGameObject;
import java.util.*;
import roguelike.objects.entities.chest.EntityChest;
import view.Camera;
import roguelike.objects.entities.player.*;
import roguelike.objects.entities.goblin.*;

/**
 * @author Anthony
 */
public class Board<T> {
    
    Tile[][] gameboard;
    int size;
    public LinkedList<ParentGameObject> actionList = new LinkedList<>();
    Player player;
    Camera camera;
    int[] cursor = {-1, -1}; 
    
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
    
    //Creates a square board and fills it based on a pre-made seed - seed must match the dimensions of the board
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
                        new WeaponSword(x, y, this);
                        break;
                        
                    case 'C':
                        new EntityChest(x, y, this);
                        break;
                        
                    case 'P':
                        new Player(x, y, this, 10);
                        break;
                        
                    case 'G':
                        new EntityGoblin(x, y, this);
                        break;
                        
                    case 'S':
                        new EntitySheep(x, y, this, 3);
                        break;
                        
                }
                
                currentCharacter++;
                
            }
            
        }
        
    }
    
    public int[] getCursor() {
        
        return cursor;
        
    }
    
    public void setCursor(int x, int y) {

        cursor[0] = x;
        cursor[1] = y;
        
    }
    
    public void moveCursor(int x, int y) {
        
        if (isWithinBounds(cursor[0] + x, cursor[1] + y)) {
            
            cursor[0] += x;
            cursor[1] += y;
                
        }
        
    }
    
    //Returns the tile at coordinates (x, y)
    public Tile getTile(int x, int y) {
        
        if (isWithinBounds(x, y)) {
            
                return gameboard[x][y];
                
        }
        
        return null;
        
    }
    
    //Returns the object at a specified square, returns null if the square is empty or out of bounds
    public List<ParentGameObject> getObjectsAtSquare(int x, int y) {
        
        if (isWithinBounds(x, y)) {

            return gameboard[x][y].getObjects();
                
        }
        
        return null;
        
    }
    
    //Returns a specific object at index i in the Tile's objects, returns null if the List is empty or square coordinates are invalid
    public ParentGameObject getObjectAtSquare(int x, int y, int i) {
        
        if (isWithinBounds(x, y)) {

                return (gameboard[x][y].getObject(i));
                
        }
        
        return null;
        
    }
    
    //Removes the object from its current square
    public void removeObjectFromSquare(ParentGameObject o) {
        
        gameboard[o.xposition][o.yposition].removeObject(o);
        
    }

    //Removes all the objects in a square from that Tile's objects List and from the board's ActionQueue if applicable
    public void clearSquare(int x, int y) {
        
        if (getObjectsAtSquare(x, y) == null) {
            
            return;
            
        }
        
        for (ParentGameObject o : getObjectsAtSquare(x, y)) {

            removeObjectFromActionList(o);
            
        }
        
        getObjectsAtSquare(x, y).clear();
        
    }
    
    //Returns true if an object with a specific objectName is occupying the square
    public boolean checkIfSquareContainsObject(int x, int y, String s) {
        
        if (isWithinBounds(x, y)) {

            for (ParentGameObject o : getObjectsAtSquare(x, y)) {

                if (o.objectName == s) {

                    return true;

                }

            }
        
        }
        
        return false;
        
    }
    
    //Returns true if an object with (isSolid == true) is occupying the square
    public boolean checkIfSquareHasSolid(int x, int y) {
        
        for (ParentGameObject o : getObjectsAtSquare(x, y)) {
            
            if (o.isSolid == true) {
                
                return true;
                
            }
            
        }
        
        return false;
        
    }
    
    //Returns the board's Camera object
    public Camera getCamera() {
        
        return this.camera;
        
    }
    
    //Returns true if the square is currently unoccupied
    public boolean checkIfSquareIsEmpty(int x, int y) {
        
        if (isWithinBounds(x, y)) {

                return (gameboard[x][y].isEmpty());
                
        }

        return false;
        
    }
    
    //Adds the object to a specified square
    public void addObjectToSquare(int x, int y, ParentGameObject object) {
        
        if (isWithinBounds(x, y)) {
            
                gameboard[x][y].addObject(object);
                
        }
        
    }
    
    //Adds object to ActionQueue
    public void addObjectToList(ParentGameObject object) {
        
        actionList.add(object);
        
    }
    
    //Changes a tile's seen flag
    public void setSeen(int x, int y) {
        
        if (isWithinBounds(x, y)) {

            gameboard[x][y].setSeen(true);

        }
        
    }
    
    //Returns a tile's seen flag
    public boolean getSeen(int x, int y) {
        
        if (isWithinBounds(x, y)) {
            
                return (gameboard[x][y].getSeen());
                
        }
        
        return false;
    }
    
    public void setPlayer(Player player) {
        
        this.player = player;
        this.camera = new Camera(player, this, 35);
        
    }
    
    public Player getPlayer() {
        
        return this.player;
        
    }
    
    public int getSize() {
        
        return this.size;
        
    }
    
    //Removes object from ActionQueue
    public void removeObjectFromActionList(ParentGameObject object) {
        
        actionList.remove(object);
        
    }
    
    //Loops through all objects in the action list and calls their Update() functions
    public void update() {
        
        for (ParentGameObject object : actionList) {
            
            if (player != null && calculateDistance(player.xposition, player.yposition, object.xposition, object.yposition) < 25) {
            
                object.update();
            
            }

        }
        
    }
    
    //Returns true if an opaque object is blocking the line
    public static boolean checkSightLine(int x0, int y0, int x1, int y1, Board gameboard){
        
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
                //Check if square isn't null
                if (gameboard.getTile(i[0], i[1]) != null){
                    //Check if an opaque object is occupying the square
                    for (Object o : gameboard.getObjectsAtSquare(i[0], i[1])) {
                        if (((ParentGameObject)o).isOpaque == true) {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    public static double calculateDistance(int xstart, int ystart, int xgoal, int ygoal) {
        
        return Math.sqrt(Math.pow((xgoal - xstart), 2) + Math.pow((ygoal - ystart), 2));
        
    }
    
    //Returns true if the x and y coordinates are within the bounds of the board
    private boolean isWithinBounds(int x, int y) {
        
        if ((x < size && x >= 0) &&
            (y < size && y >= 0)) {
            
                return true;
                
        }
        
        return false;
        
    }
    
    //Returns a representation of the board in a String
    @Override
    public String toString(){
        String s = "";
        
        for (int y = 0; y < gameboard.length; y++){
            
            for (int x = 0; x < gameboard.length; x++) {
                
                //If the square is empty/null, set the character to 0
                if (checkIfSquareIsEmpty(x, y)){
                    
                    s += '.' + " ";
                    continue;
                    
                }
                
                //Else, add the respective character to represent the object
                ParentGameObject o = getTile(x, y).getObject(0);
                
                s += (o.getSymbol()) + " ";
            }
            
            s += "\n";
        }
        
        return s;
    }
    
}
