/*

 */
package roguelike.objects.entities.goblin;

import java.util.PriorityQueue;
import roguelike.Node;
import roguelike.objects.entities.ParentEntity;
import roguelike.objects.entities.player.Player;
import roguelike.State;

/**
 * @author Anthony
 */
public class GoblinStateChase extends State {
    
    EntityGoblin parent;

    @Override
    public void enterState(ParentEntity e) {
        
        parent = (EntityGoblin) e;
        
    }

    @Override
    public void update() {
        
        Player p = parent.gameboard.getPlayer();

        checkTransitions();
        
        //Move to player
        if (p != null){
            
            calculatePath(p.getX(), p.getY());
            
        }
        
    }

    @Override
    public void checkTransitions() {
        
        //Check if object is dead
        if (parent.hitPoints <= 0) {

            parent.changeState(parent.STATE_DEAD);
            
        }
        
    }
    
    public void move(int xgoal, int ygoal){

        //If space is not occupied by a solid object, move into it
        if ((!parent.gameboard.checkIfSquareHasSolid(xgoal, ygoal))) {

            parent.changeSquare(xgoal, ygoal);
            
        }
        
    }
    
    //Pathfinding algorithm using A*
    public void calculatePath(int xgoal, int ygoal){
        
        //Create node to represent target
        Node target = new Node(xgoal, ygoal, 0);
        
        //Initialize list for open squares and add starting square
        PriorityQueue<Node> openList = new PriorityQueue<>();
        openList.add(new Node(parent.xposition, parent.yposition, 0));
        
        //Initialize list for closed squares
        PriorityQueue closedList = new PriorityQueue<>();
        
        //While the open list is not empty
        while (!openList.isEmpty()){
            
            //Get the node that is currently closest to the goal
            Node n = openList.peek();
            
            //Get q's four neighbors
            Node[] neighbors = new Node[4];
            neighbors[0] = new Node(n.x + 1, n.y, 0);
            neighbors[1] = new Node(n.x - 1, n.y, 0);
            neighbors[2] = new Node(n.x, n.y + 1, 0);
            neighbors[3] = new Node(n.x, n.y - 1, 0);
            
            //Go through each neighor
            for (Node m : neighbors) {

                //If the node isn't in the closed list or open list
                if(!Node.containsNode(openList, m) && !Node.containsNode(closedList, m)){
                    
                    //If the node is the target, exit
                    if ((m.x == xgoal) && (m.y == ygoal)){
                        
                        m.parent = n;
                        
                        //Return the next node in the path
                        while (m.parent.parent != null){
                            m = m.parent;
                        }

                        move(m.x, m.y);

                        return;
                    }
                    
                    //If the node is occupyied by a solid object, add it directly to the closed list
                    if (parent.gameboard.checkIfSquareHasSolid(m.x, m.y)) {
                    //if (((gameboard.GetObjectAtSquare(m.x, m.y)) != null) &&
                    //!(gameboard.GetObjectAtSquare(m.x, m.y).getClass().getSimpleName().equals(this.getClass().getSimpleName()))) {
                        
                        closedList.add(m);
                        
                    }
                    
                    //Else, calculate its heuristic and add it to the openlist
                    else {

                        m.parent = n;
                        m.f = m.calculateHeuristic(target);
                        openList.add(m);
                        
                    }
                    
                }
                
            }
            
            //Move current node to closedList
            openList.remove(n);
            closedList.add(n);
            
        }
        
        return;
    }
    
}
