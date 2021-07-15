/*

 */
package roguelike.Objects;

import roguelike.Board;

/**
 * @author Anthony
 */
public class EntityCrate extends Entity{
    
    //Constructor
    public EntityCrate(int x, int y, Board b, int h){
        
        //Call parent constructor
        super(x, y, b);
        
        //Set variables
        hitPoints = h;
        symbol = 'C';
        isSolid = true;
        speed = 0;
        color = color.WHITE;
        name = "Crate";
        isOpaque = true;
        
        //Add self to actionQueue
        gameboard.AddObjectToList(this);
        
    }

    public void Die(){
        
        //Remove self from board
        gameboard.SetSquare(xposition, yposition, null);
        
        //Remove self from actionList
        gameboard.RemoveObjectFromList(this);
        
        //TO-DO: Add items drops
        
    }
    
    @Override
    public void Update() {

        //Check if object is dead
        if (hitPoints <= 0){

            Die();
            return;
            
        }

    }
    
}