package roguelike;

import java.util.PriorityQueue;

/**
 * @author Anthony
 * 
 * Represents a board coordinate and contains a trail of parents nodes used to create a path
 */
public class Node implements Comparable<Node> {

    /* Initialize variables */
    //Positional
    public int x, y;
    //Heuristic
    public int f;
    //Parent in path
    public Node parent = null;
    
    public Node(int x, int y, int f){
        this.x = x;
        this.y = y;
        this.f = f;
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.f, n.f);
    }

    public int calculateHeuristic(Node goal){
        
        //Manhattan distance - no diagonals
        //return Math.abs(x - goal.x) + Math.abs(y - goal.y);
        
        //Euclidean distance - yes diagonals
        return (int)Math.sqrt(Math.pow((x - goal.x), 2) + Math.pow((y - goal.y), 2));

    }
    
    public static boolean containsNode(PriorityQueue q, Node n){
        
        for (Object o : q){
            if (n.equals(o)){
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean equals(Object o){

        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        final Node n = (Node) o;
        
        return ((x == n.x) && (y == n.y));
    }
    
    //Calculate path using a* algorithm
    public static Node calculatePath(int xstart, int ystart, int xgoal, int ygoal, Board gameboard){
        
        //Create node to represent target
        Node target = new Node(xgoal, ygoal, 0);
        
        //Initialize list for open squares and add starting square
        PriorityQueue<Node> openList = new PriorityQueue<>();
        openList.add(new Node(xstart, ystart, 0));
        
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

                        return m;
                        
                    }
                    
                    //If the node is occupyied by a solid object, add it directly to the closed list
                    if (gameboard.checkIfSquareHasSolid(m.x, m.y)) {

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
        
        return null;
        
    }

}