/*

 */
package roguelike;

/**
 * @author Anthony
 */
public interface StateMachine {

    public void start(); 
    
    public void update();
    
    public void changeState(State state);
    
}
