
package roguelike;

import java.util.List;
import mapgeneration.PerlinNoise;
import mapgeneration.bsp.BSP;
import mapgeneration.bsp.BSPnode;
import view.Camera;

/**
 * @author Anthony
 */
public class Roguelike {

    public static void main(String[] args) {
        
        Camera c = new Camera(null, null, 35);
        Board gameboard = new Board(500);
        int[][] scene = new int[51][51];

        BSP binarySpacePartition = new BSP(0, 0, 50, 50, 3);

        List<BSPnode> nodes = binarySpacePartition.getEndNodes(4);
        
        for (BSPnode node : nodes) {
            
            System.out.print("(" + node.getX() + ", " + node.getY() + ") x ");
            System.out.print("(" + (node.getX() + node.getW()) + ", " + node.getY() + ") x ");
            System.out.print("(" + (node.getX() + node.getW()) + ", " + (node.getY() + node.getH()) + ") x ");
            System.out.println("(" + node.getX() + ", " + (node.getY() + node.getH()) + ")");
            
            for (int i = node.getX(); i < node.getX() + node.getW(); i++) {
                
                scene[i][node.getY()] = 1;
                
            }
            
            for (int i = node.getY(); i < node.getY() + node.getH(); i++) {
                
                scene[node.getX()][i] = 1;
                
            }
            
            for (int i = node.getX(); i < node.getX() + node.getW(); i++) {
                
                scene[i][node.getY() + node.getH()] = 1;
                
            }
            
            for (int i = node.getY(); i < node.getY() + node.getH(); i++) {
                
                scene[node.getX() + node.getW()][i] = 1;
                
            }
            
        }
        
        for (int x = 0; x < scene.length; x++) {
            
            for (int y = 0; y < scene.length; y++) {
                
                if (scene[x][y] == 0) {
                    
                    System.out.print("  ");
                    
                    continue;
                    
                }
                
                System.out.print("# ");
            
            }
            
            System.out.println();
            
        }
        
    }
    
}