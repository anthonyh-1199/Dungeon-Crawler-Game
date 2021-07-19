
package roguelike.objects.entities.chest;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import roguelike.Board;
import roguelike.items.ParentItem;
import roguelike.items.weapons.*;
import roguelike.objects.entities.*;

/**
 * @author Anthony
 */
public class EntityChest extends ParentEntity {
    
    boolean isOpen = false;
    ArrayList<ParentItem> itemPool, inventory;

    public EntityChest(int x, int y, Board b) {
        
        super(x, y, b);
        
        //Board attributes
        isOpaque = false;
        objectColor = new Color(170,85,0);
        isSolid = true;
        objectName = "chest";
        objectSymbol = 'C';
        objectType = "chest";
        
        //Stat attributes
        armorClass = 12;
        hitPoints = 7;
        moveSpeed = 0;
        
        itemPool = getItemPool();
        
        inventory = itemPool;

    }
    
    public void openChest() {
        
        for (ParentItem item : inventory) {
            
            item.putOnBoard(xposition, yposition, gameboard);
            
        }

        isOpen = true;
        
        deleteSelf();
        
    }
    
    public boolean isOpen() {
        
        return isOpen;
        
    }
    
    //Parses the pool of possible items to hold from a CSV file
    public ArrayList<ParentItem> getItemPool() {
        
        ArrayList<ParentItem> itemPool = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("ChestItemPool.txt"))) {

            String line = br.readLine();

            while (line != null) {
                
                switch (line) {
                    
                    case "WeaponDagger":
                        itemPool.add(new WeaponDagger());
                        break;
                    case "WeaponShortbow":
                        itemPool.add(new WeaponShortbow());
                        break;
                    case "WeaponSword":
                        itemPool.add(new WeaponSword());
                        break;
                    
                }

                line = br.readLine();
                
            }

        } catch (IOException e) {

            return new ArrayList<>();
            
        }
        
        return itemPool;
        
    }

    @Override
    public void update() {

        
        
    }
    
}
