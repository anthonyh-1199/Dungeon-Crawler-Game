/*

 */
package roguelike;

/**
 * @author Anthony
 */
public interface StateMachine {

    public void Start(); 
    
    public void Update();
    
    public void ChangeState(State state);
    
}
