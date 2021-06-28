/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.Objects;

import java.util.PriorityQueue;
import roguelike.Board;
import roguelike.Node;

/**
 *
 * @author Anthony
 */
public class EntityGoblin extends Entity{

    //Constructor
    public EntityGoblin(int x, int y, Board b, int h){
        
        //Call parent constructor
        super(x, y, b);
        
        //Set variables
        this.health = h;
        this.symbol = 'G';
        this.isSolid = true;
        this.speed = 1;
        
        //Add self to actionQueue
        gameboard.AddObjectToList(this);
    }
    
    public void Move(int xgoal, int ygoal){

        //If space is empty, move into it
        if ((gameboard.GetSquare(xgoal, ygoal)) == null) {

            //Remove current position in board
            gameboard.SetSquare(xposition, yposition, null);
            
            //Update positional variables
            xposition = xgoal;
            yposition = ygoal;

            //Set to new position in board
            gameboard.SetSquare(xgoal, ygoal, this);
            
        }
        
    }
    
    //Pathfinding algorithm using A*
    public void CalculatePath(int xgoal, int ygoal){
        
        //Create node to represent target
        Node target = new Node(xgoal, ygoal, 0);
        
        //Initialize list for open squares and add starting square
        PriorityQueue<Node> openList = new PriorityQueue<>();
        openList.add(new Node(xposition, yposition, 0));
        
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
                if(!containsNode(openList, m) && !containsNode(closedList, m)){
                    
                    //If the node is the target, exit
                    if ((m.x == xgoal) && (m.y == ygoal)){
                        
                        m.parent = n;
                        
                        //Return the next node in the path
                        while (m.parent.parent != null){
                            m = m.parent;
                        }

                        Move(m.x, m.y);

                        return;
                    }
                    
                    //If the node isn't empty, add it directly to the closed list
                    if (!gameboard.CheckIfSquareIsEmpty(m.x, m.y)) {
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
    
    public boolean containsNode(PriorityQueue q, Node n){
        
        for (Object o : q){
            if (n.equals(o)){
                return true;
            }
        }
        
        return false;
    }

    public void Die(){
        
        //Remove self from board
        gameboard.SetSquare(xposition, yposition, null);
        
        //Remove self from actionList
        gameboard.RemoveObjectFromList(this);
        
    }
    
    @Override
    public void Update() {

        //Check if object is dead
        if (health <= 0){

            Die();
            return;
            
        }
        
        //Else, move
        for (int i = 0; i < speed; i++){
            CalculatePath(gameboard.GetPlayer().xposition, gameboard.GetPlayer().yposition);
        }

    }
    
}
