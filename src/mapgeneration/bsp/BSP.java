/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgeneration.bsp;

import java.util.Random;

/**
 *
 * @author Anthony
 */
public class BSP {
    
    BSPnode rootNode;
    int depth;
    
    public BSP(int xBoundary, int yBoundary, int shapeHeight, int shapeWidth, int depth) {
        
        //Set variables
        this.rootNode = new BSPnode();
        
        this.rootNode.x = xBoundary;
        this.rootNode.y = yBoundary;
        this.rootNode.w = shapeWidth;
        this.rootNode.h = shapeHeight;
        
        this.depth = depth;
        
        //Start recursive call
        recursiveSplit(rootNode, 0, true);

    }
    
    public BSPnode getRoot() {
        
        return rootNode;
        
    }
    
    public void recursiveSplit(BSPnode region, int currentDepth, boolean splitVertically) {
        
        //If the current level is deeper than the goal # of layers, exit
        if (currentDepth > depth) {
            
            return;
            
        }
        
        //Set the level of the region to the current level
        region.level = currentDepth;
        
        //Split the region, creating new regions for the tree
        split(region, splitVertically);
        
        //Invert the split direction
        splitVertically = !splitVertically;
        
        System.out.println(region.front.x + ", " + region.front.y);
        System.out.println(region.back.x + ", " + region.back.y + "\n");
        
        
        //Call the recursive function for the child nodes
        recursiveSplit(region.front, currentDepth + 1, splitVertically);

        recursiveSplit(region.back, currentDepth + 1, splitVertically);
        
    }
    
    //Creates two child nodes for a node
    public void split(BSPnode parentNode, boolean splitVertically) {
        
        //Initialize child nodes
        BSPnode childFront = new BSPnode();
        BSPnode childBack = new BSPnode();
        
        //Determine vertical split or horizontal split: 0 - horizontal, 1 - vertical
        Random r = new Random();

        /* HORIZONTAL SPLIT */
        
        if (splitVertically) {
            
            //Set first child node
            childFront.setX(parentNode.x);
            childFront.setY(parentNode.y);
            childFront.setW(r.nextInt((parentNode.w - 1) + 1) + 1);
            childFront.setH(parentNode.h);
            
            //Set second child node
            childBack.setX(parentNode.x + childFront.w);
            childBack.setY(parentNode.y);
            childBack.setW(parentNode.w - childFront.w);
            childBack.setH(parentNode.h);
            
        }
        
        /* VERTICAL SPLIT */
        
        if (!splitVertically) {
            
            //Set first child node
            childFront.setX(parentNode.x);
            childFront.setY(parentNode.y);
            childFront.setW(parentNode.w);
            childFront.setH(r.nextInt((parentNode.h - 1) + 1) + 1);
            
            //Set second child node
            childBack.setX(parentNode.x);
            childBack.setY(parentNode.y + childFront.h);
            childBack.setW(parentNode.w);
            childBack.setH(parentNode.h - childFront.h);
            
        }
        
        parentNode.front = childFront;
        parentNode.back = childBack;
        
    }
    
}
