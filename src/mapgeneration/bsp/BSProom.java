
package mapgeneration.bsp;

/**
 * @author Anthony
 */
public class BSProom {
    
    int x, y; //The smallest x and y bounds, represents the top-left coordinate
    int width, height;
    
    public BSProom(int x, int y, int width, int height) {
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
    }
    
    /* Setter methods */
    
    public void setX(int x) {
        
        this.x = x;
        
    }
    
    public void setY(int y) {
        
        this.y = y;
        
    }
    
    public void setWidth(int width) {
        
        this.width = width;
        
    }
    
    public void setHeight(int height) {
        
        this.height = height;
        
    }
    
    /* Getter methods */
    
    public int getX() {
        
        return x;
        
    }
    
    public int getY() {
        
        return y;
        
    }
    
    public int getWidth() {
        
        return width;
        
    }
    
    public int getHeight() {
        
        return height;
        
    }

}
