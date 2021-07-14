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
        this.hitPoints = h;
        this.symbol = 'C';
        this.isSolid = true;
        this.speed = 0;
        this.color = color.WHITE;
        this.name = "Crate";
        
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