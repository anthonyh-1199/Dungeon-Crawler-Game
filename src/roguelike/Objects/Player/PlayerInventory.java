
package roguelike.Objects.Player;

import roguelike.Items.ParentItem;

/**
 * @author Anthony
 */
public class PlayerInventory {
    
    public ParentItem[] inventory = new ParentItem[10];
    
    public PlayerInventory() {}
    
    public boolean isFull() {
        
        for (ParentItem item : inventory) {
            
            if (item == null) {
                
                return false;
            }
            
        }
        
        return true;
    }
    
    public void addItem(ParentItem item) {
        
        if (!isFull()) {
            
            for (int i = 0; i < inventory.length; i++) {

                if (inventory[i] == null) {

                    inventory[i] = item;
                    return;
                    
                }

            }
            
        }
        
    }
    
}
