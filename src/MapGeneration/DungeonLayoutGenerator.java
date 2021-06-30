/*
 */

package MapGeneration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * @author Anthony
 */
public class DungeonLayoutGenerator {
    
    DungeonRoom[][] roomsGrid = new DungeonRoom[30][30];
    int roomGoal = 10;
    int bonusGoal = 9;
    
    public DungeonLayoutGenerator() { 
        do {
            GenerateBaseLayout();
        } 
        while (!AddExtraRooms());
        
        System.out.println(ToString());
    }
    
    //Generates a base layout consisting of one unbroken line of rooms that 
    //doesn't loop back on itself
    public void GenerateBaseLayout() {
        
        //Initialize variables
        Random r = new Random();
        int direction;
        int startx, starty;
        
        //Get the coordinates of the starting square
        startx = 15;
        starty = 15;
        
        //Set a variable to hold the # of rooms generated in each pass
        int roomsCount;
        
        //Repeat the following until a layout with the desired # of rooms
        //is generated
        do {
            
            //Create a queue to hold the rooms
            Queue<DungeonRoom> roomsQueue = new LinkedList<>();
            
            //Create a grid to hold the rooms
            roomsGrid = new DungeonRoom[30][30];
            
            //Put a room in the current square, then add it to the queue
            roomsGrid[startx][starty] = new DungeonRoom(startx, starty, false);
        
            roomsQueue.add(roomsGrid[startx][starty]);
            
            //Initialize # of rooms generated as 1
            roomsCount = 1;
        
            //Loop over the queue
            while (!roomsQueue.isEmpty()){

                //Get the current room off of the queue
                DungeonRoom room = roomsQueue.poll();

                //Get a random neighbor of that square
                int neighborx = room.GetX();
                int neighbory = room.GetY();

                direction = r.nextInt(4);

                switch (direction){
                    //Up
                    case 0: 
                        neighbory--;
                        break;
                    //Right
                    case 1:
                        neighborx++;
                        break;
                    //Down
                    case 2:
                        neighbory++;
                        break;
                    //Left
                    case 3:
                        neighborx--;
                        break;
                }
                
                //If the neighbor square has more than one neighbor, pass it
                int neighborsCount = 0;
                
                if (roomsGrid[neighborx + 1][neighbory] != null) {
                    neighborsCount++;
                }
                if (roomsGrid[neighborx - 1][neighbory] != null) {
                    neighborsCount++;
                }
                if (roomsGrid[neighborx][neighbory + 1] != null) {
                    neighborsCount++;
                }
                if (roomsGrid[neighborx][neighbory - 1] != null) {
                    neighborsCount++;
                }
                
                if (neighborsCount > 1){
                    continue;
                }

                //If the neighbor is empty, add a room to it, add it to the 
                //queue, then increment the # of rooms
                if (roomsGrid[neighborx][neighbory] == null) {
                    roomsGrid[neighborx][neighbory] = new DungeonRoom(neighborx, neighbory, false);
                    roomsQueue.add(roomsGrid[neighborx][neighbory]);
                    roomsCount++;
                }
                
                //Leave once we hit the goal # of rooms
                if (roomsCount == roomGoal){
                    break;
                }

            }
        
        }
        while (roomsCount < roomGoal);
    }
    
    public boolean AddExtraRooms() {
        
        //Initialize variables
        Random r = new Random();
        int direction;
        
        //Store all the rooms in the base layout in a list
        List<DungeonRoom> roomsList = new LinkedList<>();
        
        for (int y = 0; y < roomsGrid.length; y++){
            for (int x = 0; x < roomsGrid.length; x++) {
                if (roomsGrid[x][y] != null){
                    roomsList.add(roomsGrid[x][y]);
                }
            }
        }
        
        //Shuffle the list
        Collections.shuffle(roomsList);
        
        //Set a variable to hold the # of special rooms generated
        int roomsCount = 0;
        
        //Go through the list and attempt to add extra rooms
        for (DungeonRoom room : roomsList){
            
            //Get a random neighbor of the square
            int neighborx = room.GetX();
            int neighbory = room.GetY();

            direction = r.nextInt(4);

            switch (direction){
                //Up
                case 0: 
                    neighbory--;
                    break;
                //Right
                case 1:
                    neighborx++;
                    break;
                //Down
                case 2:
                    neighbory++;
                    break;
                //Left
                case 3:
                    neighborx--;
                    break;
            }
            
            //If the neighbor is empty, add a room to it
            if (roomsGrid[neighborx][neighbory] == null) {
                roomsGrid[neighborx][neighbory] = new DungeonRoom(neighborx, neighbory, true);
                roomsCount++;
            }

            //Leave once we hit the goal # of rooms
            if (roomsCount == bonusGoal){
                return true;
            }
            
        }
        
        return false;
        
    }
    
    public String ToString() {
        
        String s = "";
        
        for (int y = 0; y < roomsGrid.length; y++){
            
            for (int x = 0; x < roomsGrid.length; x++) {
                
                //If the square is empty, draw a 0
                if (roomsGrid[x][y] == null){
                    
                    s += "  ";
                    
                } else {
                    
                    if (roomsGrid[x][y].special){
                        s += "X ";
                    } else {
                        s += "O ";
                    }
                    
                }
            }
            
            s += "\n";
        }
        
        return s;
    }
}
