/*
 * Represents a board coordinate and contains a trail of parents nodes used to 
 * create a path
 */
package roguelike;

/**
 *
 * @author Anthony
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

}