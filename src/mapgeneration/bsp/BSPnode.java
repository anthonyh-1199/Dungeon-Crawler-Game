
package mapgeneration.bsp;

/**
 * @author Anthony
 * 
 * Basic structure of a BSP tree, contains geometric information and pointers to the two child nodes in the tree
 */
public class BSPnode {
    
    int x; //minimum x-boundary
    int y; //minimum y-boundary
    int h; //height
    int w; //width
    BSPnode front;
    BSPnode back;
    int level; //level of the node in the tree
    
    public BSPnode(int x, int y, int h, int w, BSPnode front, BSPnode back, int level) {
        
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.front = front;
        this.back = back;
        this.level = level;
        
    }
    
    public BSPnode() {
        
        this.x = -1;
        this.y = -1;
        this.h = -1;
        this.w = -1;
        this.front = null;
        this.back = null;
        this.level = -1;
        
    }
    
    //Getter methods
    
    public int getX() {
        
        return x;
        
    }
    
    public int getY() {
        
        return y;
        
    }
    
    public int getH() {
        
        return h;
        
    }
    
    public int getW() {
        
        return w;
        
    }
    
    public BSPnode getFront() {
        
        return front;
        
    }
    
    public BSPnode getBack() {
        
        return back;
        
    }
    
    //Setter methods
    
    public void setX(int x) {
        
        this.x = x;
        
    }
    
    public void setY(int y) {
        
        this.y = y;
        
    }
    
    public void setH(int h) {
        
        this.h = h;
        
    }
    
    public void setW(int w) {
        
        this.w = w;
        
    }
    
    public void setFront(BSPnode front) {
        
        this.front = front;
        
    }
    
    public void setBack(BSPnode back) {
        
        this.back = back;
        
    }
    
}
