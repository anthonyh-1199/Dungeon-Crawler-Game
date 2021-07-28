/*
 */
package roguelike.objects.entities.player;

import java.awt.Color;
import roguelike.Board;
import roguelike.items.ParentItem;
import roguelike.items.weapons.ParentWeapon;
import roguelike.items.weapons.WeaponFists;
import roguelike.objects.entities.*;
import roguelike.objects.ParentGameObject;
import roguelike.objects.entities.chest.EntityChest;

/**
 * @author Anthony
 */
public class Player extends ParentEntity{
    
    public int maxHitPoints, weaponDamage, classLevel;
    public int statCharisma, statConstitution, statDexterity, statIntelligence, statStrength, statWisdom;
    public int walletBalance;
    public PlayerInventory inventory = new PlayerInventory();

    
    public Player(int x, int y, Board b, int h){
        
        //Call parent constructor
        super(x, y, b);
        
        //Set variables
        this.maxHitPoints = this.hitPoints = h;
        this.objectSymbol = '@';
        this.weaponDamage = 1;
        this.objectName = "Player";
        this.objectColor = new Color(255,255,255);
        this.statCharisma = statConstitution = statDexterity = statIntelligence = statStrength = statWisdom = 4;
        this.classLevel = 1;
        this.walletBalance = 10;
        this.isSolid = true;
        armorClass = 12;
        
        gameboard.setPlayer(this);
        
    }
    
    public ParentWeapon getWeapon() {

        for (ParentItem item : getInventory()) {
            
            if (item == null) continue;
            
            if (item.itemType.equals("weapon")) {

                return (ParentWeapon)item;
                
            }
            
        }
        
        return new WeaponFists();
        
    }
    
    public void move(String direction){
        
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
        if (gameboard.checkIfSquareIsEmpty(xgoal, ygoal)) {

            //Remove current position in board
            changeSquare(xgoal, ygoal);
            
            return;

        }
        
        //Get non-null object occupying the square
        ParentGameObject object = gameboard.getObjectAtSquare(xgoal, ygoal, 0);
        
        //If object is nonsolid, interact with it
        if ((object).isSolid() == false) {

            changeSquare(xgoal, ygoal);

        }
        
        //If not, perform contextual behavior based on object in square
        else {
            
            switch (object.objectType) {
                
                case "chest":
                {
                    
                    EntityChest entity = (EntityChest)object;
                    
                    if (!entity.isOpen()) {
                        
                        entity.openChest();
                        
                    }
                    
                    break;
                    
                }
                
                case "enemy":
                {
                    
                    ParentEntity entity = (ParentEntity)object;
                    
                    ParentWeapon weapon = getWeapon();
                    
                    entity.takeDamage(this, weapon.getDamage(), weapon.getAccuracy(statDexterity));
                    
                    break;
                    
                }
                    
            }
            
        }
        
    }
    
    public void makeRangedAttack(int x, int y, ParentWeapon weapon) {
        
        //If space is empty, do nothing
        if (gameboard.checkIfSquareIsEmpty(x, y)) {

            return;

        }
        
        //Get non-null object occupying the square
        ParentGameObject object = gameboard.getObjectAtSquare(x, y, 0);
        
        //Perform contextual behavior based on object in square
        switch (object.getType()){

            case "enemy":

                ParentEntity entity = (ParentEntity)object;

                entity.takeDamage(this, weapon.getDamage(), weapon.getAccuracy(statDexterity));

                break;


        }
        
    }
    
    /* Getter methods */
    
    public ParentItem[] getInventory() {
        
        return inventory.inventory;
        
    }
    
    //Moves the item at index i to the gameboard at position (x, y)
    public void dropItem(int x, int y, int i) {
        
        ParentItem item = getInventory()[i];
        
        if (item != null) {
            
            inventory.removeItem(i);
            
            item.putOnBoard(x, y, gameboard);
            
        }
        
    }
    
    @Override
    public void takeDamage(ParentEntity damageSource, int damageRoll, int hitRoll) {

        if (hitRoll < armorClass) {
            
            gameboard.getCamera().addMessage("You dodge the attack!");
            
            return;
            
        }
        
        gameboard.getCamera().addMessage("You are hit!");
        
        this.hitPoints -= damageRoll;
        
    }

    @Override
    public void update() {}
}
