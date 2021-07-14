/*
 */
package roguelike.Objects.Player;

import java.awt.Color;
import roguelike.Board;
import roguelike.Items.ParentItem;
import roguelike.Objects.Entity;
import roguelike.Objects.GameObject;

/**
 * @author Anthony
 */
public class Player extends GameObject{
    
    public int maxHitPoints, hitPoints, weaponDamage, classLevel;
    public int statCharisma, statConstitution, statDexterity, statIntelligence, statStrength, statWisdom;
    public int walletBalance;
    public PlayerInventory inventory = new PlayerInventory();

    
    public Player(int x, int y, Board b, int h){
        
        //Call parent constructor
        super(x, y, b);
        
        //Set variables
        this.maxHitPoints = this.hitPoints = h;
        this.symbol = '@';
        this.weaponDamage = 1;
        this.name = "Player";
        this.color = new Color(255,255,255);
        this.statCharisma = statConstitution = statDexterity = statIntelligence = statStrength = statWisdom = 1;
        this.classLevel = 1;
        this.walletBalance = 10;
        
        gameboard.SetPlayer(this);
    }
    
    public void Move(String direction){
        
        //Initialize variables
        int xgoal = xposition;
        int ygoal = yposition;
        
        //Set x and y positions of goal based on direction
        switch (direction){
            case "UP":
                ygoal--;
                break;
            case "DOWN":
                ygoal++;
                break;
            case "RIGHT":
                xgoal++;
                break;
            case "LEFT":
                xgoal--;
                break;
        }
        
        //If space is empty, move into it
        if (gameboard.CheckIfSquareIsEmpty(xgoal, ygoal)) {

            //Remove current position in board
            gameboard.SetSquare(xposition, yposition, null);

            //Update player's positional variables
            xposition = xgoal;
            yposition = ygoal;

            //Set to new position in board
            gameboard.SetSquare(xposition, yposition, this);
            
            return;

        }
        
        //Get non-null object occupying the square
        GameObject object = (GameObject)gameboard.GetSquare(xgoal, ygoal);
        
        //If object is nonsolid, interact with it
        if ((object).IsSolid() == false){

            switch (object.getClass().getSuperclass().getSimpleName()){
                case "ParentWeapon":
                    ParentItem i = (ParentItem)(object);
                    //If our inventory isn't full, add it to inventory and
                    //remove it from the board
                    if (!inventory.isFull()) {
                        inventory.addItem(i);
                        gameboard.SetSquare(xposition, yposition, null);
                    }
                    //Else, we move around it
                    else {
                        gameboard.SetSquare(xposition, yposition, object);
                    }
            }

            //Update player's positional variables
            xposition = xgoal;
            yposition = ygoal;

            //Set to new position in board
            gameboard.SetSquare(xposition, yposition, this);

        }
        
        //If not, perform contextual behavior based on object in square
        else {
            switch (object.getClass().getSuperclass().getSimpleName()){
                case "Entity":
                    Entity entity = (Entity)gameboard.GetSquare(xgoal, ygoal);
                    entity.hitPoints -= weaponDamage;
                    break;
            }
        }
    }

    @Override
    public void Update() {}
}
