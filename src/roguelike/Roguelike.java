/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import MapGeneration.DungeonLayoutGenerator;
import View.Camera;

/**
 *
 * @author Anthony
 */
public class Roguelike {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Camera c = new Camera(null, null, 35);
        boolean b = c.CheckLine(6, 4, 0, 1);
        //DungeonLayoutGenerator dg = new DungeonLayoutGenerator();
    }
    
}