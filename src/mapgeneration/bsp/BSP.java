
package mapgeneration.bsp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * @author Anthony
 */
public class BSP {
    
    BSPnode rootNode;
    int depth;
    final double MAX_RATIO = 1.5;//2.1;
    
    public BSP(int xBoundary, int yBoundary, int shapeHeight, int shapeWidth, int depth) {
        
        //Set rootNode to cover the entire area covered by the tree
        this.rootNode = new BSPnode();
        
        this.rootNode.x = xBoundary;
        this.rootNode.y = yBoundary;
        this.rootNode.w = shapeWidth;
        this.rootNode.h = shapeHeight;
        
        this.depth = depth;
        
        //Get random split direction
        Random r = new Random();

        //Start recursive call
        recursiveSplit(rootNode, 1, r.nextBoolean());

    }
    
    public BSPnode getRoot() {
        
        return rootNode;
        
    }
    
    //Uses breadth-first search to get all the nodes at a certain depth
    public List<BSPnode> getNodesAtDepth(int goalDepth) {
        
        //Initialize output list
        List<BSPnode> nodes = new ArrayList<>();
        
        //Initialize queue for breadth-first search
        Queue<BSPnode> openNodes = new LinkedList<>();

        //Add the rootNode to the openNodes
        openNodes.add(rootNode);
        
        /* BREADTH-FIRST SEARCH ALGORITHM */
        
        //While openNodes is not empty
        while (!openNodes.isEmpty()) {
            
            //Remove the node from the top of the queue
            BSPnode node = openNodes.poll();
            
            //If the node is one our goal depth, add it to the output list and move on in the queue
            if (node.level == goalDepth) {
                
                nodes.add(node);

                continue;
                
            }
            
            //If the node has children, add them to the queue
            if (node.front != null) {
                
                openNodes.add(node.front);
                
                openNodes.add(node.back);

            }
            
            
        }
        
        return nodes;
        
    }
    
    public void recursiveSplit(BSPnode region, int currentDepth, boolean splitVertically) {
        
        //Set the level of the region to the current level
        region.level = currentDepth;
        
        //If the current level is deeper than the goal # of layers, exit
        if (currentDepth > depth) {

            return;
            
        }

        //Split the region, creating new regions for the tree
        split(region, splitVertically);
        
        //Call the recursive function for the child nodes
        recursiveSplit(region.front, currentDepth + 1, !splitVertically);

        recursiveSplit(region.back, currentDepth + 1, !splitVertically);
        
    }
    
    //Creates two child nodes for a node
    public void split(BSPnode parentNode, boolean splitVertically) {
        
        //Initialize child nodes
        BSPnode childFront = new BSPnode();
        BSPnode childBack = new BSPnode();
        
        //Initialize Random variable to determine split locations
        Random r = new Random();
        
        //Initialize ratio variable to check if the resulting split is appropriate
        double ratioFront = 0;
        double ratioBack = 0;
        
        //Calculate bounds for split
        int[] splitBounds = getSplitBounds(parentNode, splitVertically);

        /* HORIZONTAL SPLIT */

        if (!splitVertically) {

            //Set first child node
            childFront.setX(parentNode.x);
            childFront.setY(parentNode.y);
            childFront.setW(splitBounds[0] + r.nextInt(splitBounds[1] - splitBounds[0] + 1));
            childFront.setH(parentNode.h);

            //Set second child node
            childBack.setX(parentNode.x + childFront.w);
            childBack.setY(parentNode.y);
            childBack.setW(parentNode.w - childFront.w);
            childBack.setH(parentNode.h);

            ratioBack = (childBack.h > childBack.w) ? ((double)childBack.h / (double)childBack.w) : ((double)childBack.w / (double)childBack.h);

            ratioFront = (childFront.h > childFront.w) ? ((double)childFront.h / (double)childFront.w) : ((double)childFront.w / (double)childFront.h);

        }

        /* VERTICAL SPLIT */

        if (splitVertically) {

            //Set first child node
            childFront.setX(parentNode.x);
            childFront.setY(parentNode.y);
            childFront.setW(parentNode.w);
            childFront.setH(splitBounds[0] + r.nextInt(splitBounds[1] - splitBounds[0] + 1));

            //Set second child node
            childBack.setX(parentNode.x);
            childBack.setY(parentNode.y + childFront.h);
            childBack.setW(parentNode.w);
            childBack.setH(parentNode.h - childFront.h);

            ratioBack = (childBack.h > childBack.w) ? ((double)childBack.h / (double)childBack.w) : ((double)childBack.w / (double)childBack.h);

            ratioFront = (childFront.h > childFront.w) ? ((double)childFront.h / (double)childFront.w) : ((double)childFront.w / (double)childFront.h);

        }

        parentNode.front = childFront;
        parentNode.back = childBack;
        
    }
    
    //Helper method that computes the maximum and minimum bounds a split can be to produce two cells below the desired ratio
    private int[] getSplitBounds(BSPnode parentNode, boolean splitVertically) {
        
        //Initialize array to hold bounds - [0] = min, [1] = max
        int[] splitBounds = new int[2];
        
        /* CALCULATE SPLITS */
        
        if (!splitVertically) {
            
            //Go through each potential split in the region
            for (int newWidth = 1; newWidth < parentNode.w; newWidth++) {
                
                //Calculate largest ratio of the shape resulting from this split
                double ratio = (parentNode.h > newWidth) ? ((double)parentNode.h / (double)newWidth) : ((double)newWidth / (double)parentNode.h);

                //Set the bounds once we find a width that results in a region within our desired ratio
                if (ratio < MAX_RATIO || newWidth == (parentNode.w / 2)) {
                    
                    splitBounds[0] = newWidth;
                    
                    splitBounds[1] = parentNode.w - newWidth;
                    
                    break;

                }
                
            }
            
        }
        
        if (splitVertically) {
            
            //Go through each potential split in the region
            for (int newHeight = 1; newHeight < parentNode.h; newHeight++) {
                
                //Calculate largest ratio of the shape resulting from this split
                double ratio = (newHeight > parentNode.w) ? ((double)newHeight / (double)parentNode.w) : ((double)parentNode.w / (double)newHeight);

                //Set the bounds once we find a height that results in a region within our desired ratio
                if (ratio < MAX_RATIO || newHeight == (parentNode.h / 2)) {
                    
                    splitBounds[0] = newHeight;
                    
                    splitBounds[1] = parentNode.h - newHeight;
                    
                    break;
                    
                }
                
            }
            
        }

        return splitBounds;
        
    }
    
    public int getDepth() {
        
        return depth + 1;
        
    }
    
}
