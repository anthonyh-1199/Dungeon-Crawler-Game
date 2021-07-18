
package roguelike.objects.entities.goblin;

import java.awt.Color;
import roguelike.Board;
import roguelike.objects.entities.ParentEntity;
import roguelike.State;
import roguelike.StateMachine;
import roguelike.items.weapons.*;

/**
 * @author Anthony
 */
public class EntityGoblin extends ParentEntity implements StateMachine {
    
    //State machine variables
    State STATE_IDLE = new GoblinStateIdle();
    State STATE_CHASE= new GoblinStateChase();
    State STATE_DEAD = new GoblinStateDead();
    State currentState;
    
    //AI variables
    ParentEntity targetObject;
    
    //Behavioral variables
    int statCharisma, statConstitution, statDexterity, statIntelligence, statStrength, statWisdom;
    ParentWeapon meleeWeapon;
    ParentWeapon rangedWeapon;

    //Constructor
    public EntityGoblin(int x, int y, Board b) {
        
        //Call parent constructor
        super(x, y, b);

        //Board attributes
        isOpaque = false;
        objectColor = new Color(36,191,32);
        isSolid = true;
        objectName = "goblin";
        objectSymbol = 'G';
        objectType = "enemy";
        
        //Stat attributes
        armorClass = 12;
        hitPoints = 7;
        meleeWeapon = new WeaponFists(); 
        moveSpeed = 1;
        rangedWeapon = new WeaponShortbow();
        statCharisma = -1;
        statConstitution = 0;
        statDexterity = 2;
        statIntelligence = -1;
        statStrength = -1;
        statWisdom = -1;
        
        //AI variables
        targetObject = null;
        
        //Add self to actionQueue
        gameboard.addObjectToList(this);
        
        start();
        
    }
    
    /* State machine methods */
    
    @Override
    public void start() {
        
        changeState(STATE_IDLE);
        
    } 
    
    @Override
    public void changeState(State state) {
        
        currentState = state;
        currentState.enterState(this);
        
    }
    
    /* Class methods */
    
    public void doMeleeAttack(ParentEntity damageTarget) {

        damageTarget.takeDamage(this, meleeWeapon.getDamage(), meleeWeapon.getAccuracy(statDexterity));
        
    }
    
    public void doRangedAttack(ParentEntity damageTarget) {

        damageTarget.takeDamage(this, rangedWeapon.getDamage(), rangedWeapon.getAccuracy(statDexterity));
        
    }
    
    @Override
    public void takeDamage(ParentEntity damageSource, int damageRoll, int hitRoll) {
        
        if (targetObject == null && damageSource != null) {
            
            targetObject = damageSource;
            
        }
        
        if (hitRoll < armorClass) {
            
            gameboard.getCamera().AddMessage("The goblin dodges the attack!");
            
            return;
            
        }
        
        gameboard.getCamera().AddMessage("The goblin is hit with your   weapon!");
        
        this.hitPoints -= damageRoll;
        
        //Check if object is dead
        if (hitPoints <= 0) {

            changeState(STATE_DEAD);
            
        }
        
    }
    
    @Override
    public void update() {
        
        currentState.update();
        
    }

}
