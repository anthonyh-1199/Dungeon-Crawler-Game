
package mapgeneration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import mapgeneration.bsp.BSP;
import mapgeneration.bsp.BSPnode;
import roguelike.Board;
import roguelike.items.weapons.WeaponSword;
import roguelike.objects.ObjectWall;
import roguelike.objects.entities.chest.EntityChest;
import roguelike.objects.entities.goblin.EntityGoblin;
import roguelike.objects.entities.player.Player;

/**
 * @author Anthony
 */
public class DungeonGenerator extends Generator {
    
    final int ROOM_SIZE = 10;
    
    public DungeonGenerator(Board gameboard) {
        
        super(gameboard);
        
        generateDungeonFromLayout(generateLayout(), gameboard);
        
    }
    
    //Generates dungeon rooms based on binary space partitions
    public void generateRoomsFromBSP(Board gameboard, BSP bsp, int minimumRoomWidth, int minimumRoomArea) {
        
        //Get the smallest segments of the partition
        List<BSPnode> regions = bsp.getNodesAtDepth(bsp.getDepth());
        
        //Create a room in each region
        for (BSPnode region : regions) {

            //Get data from the region
            int xCenter = region.getX() + (region.getW() / 2);
            int yCenter = region.getY() + (region.getH() / 2);
            int[] regionTopLeft = {region.getX(), region.getY()};
            int[] regionBottomRight = {region.getX() + region.getW(), region.getY() + region.getH()};
            
            //Initalize variables for the room
            int[] topLeft = {xCenter - 1, yCenter - 1};
            int[] bottomRight = {xCenter + 1, yCenter + 1};
            
            int width = -1;
            int height = -1;
            
            //Initialize variable for determining if the room has completed generation
            boolean isNotComplete = true;
            
            //Continue expanding the room until it reaches the size requirements or has reached the maximum size of the region
            while (isNotComplete) {
                
                //Randomly expand one of the 4 sides of the room, unless it goes out of bounds
                Random r = new Random();
                
                switch (r.nextInt(4)) {
                    
                    //Up
                    case 0:
                        if (topLeft[1] > regionTopLeft[1] + 1) {
                            
                            topLeft[1] -= 1;
                            
                        }
                        
                    //Right
                    case 1:
                        if (bottomRight[1] < regionBottomRight[1]) {
                            
                            bottomRight[1] += 1;
                            
                        }
                        
                    //Down
                    case 2:
                        if (bottomRight[0] < regionBottomRight[0]) {
                            
                            bottomRight[0] += 1;
                            
                        }
                        
                    //Left
                    case 3:
                        if (topLeft[0] > regionTopLeft[0] + 1) {
                            
                            topLeft[0] -= 1;
                            
                        }
                    
                }
                
                //Check if room has reached the desired size
                width = (bottomRight[1] - topLeft[1]);
                height = (bottomRight[0] - topLeft[0]);
                
                if (width * height >= minimumRoomArea) {
                    
                    isNotComplete = false;
                    
                }
                
                //Check if room has reached the max size of the region
                if (topLeft[0] == regionTopLeft[0] + 1 && topLeft[1] == regionTopLeft[1] + 1) {
                    
                    if (bottomRight[0] == regionBottomRight[0] && bottomRight[1] == regionBottomRight[1]) {
                    
                        isNotComplete = false;
                    
                    }
                    
                }
                
            }
            
            //Carve out room from board
            for (int x = topLeft[1]; x < topLeft[1] + width; x++) {
                
                for (int y = topLeft[0]; y < topLeft[0] + height; y++) {
                
                    gameboard.clearSquare(x, y);
                
                }
                
            }
            
        }
        
    }
    
    //Generates connecting hallways between dungeon rooms
    public void generateHallwaysFromBSP(BSP bsp) {
        
        
        
    }
    
    //Generates a layout
    public DungeonRoom[][] generateLayout() {
        
        return new DungeonLayoutGenerator().generateLayout();
        
    }
    
    //Adds rooms to the board based on the layout
    public void generateDungeonFromLayout(DungeonRoom[][] roomsGrid, Board gameboard) {

        for (int y = 0; y < roomsGrid.length; y++){
            
            for (int x = 0; x < roomsGrid.length; x++) {
                
                //If the square is empty, move to the next one
                if (roomsGrid[x][y] == null){
                    
                    continue;
                    
                }
                
                //If square has a room, add it to the board
                generateRoom(roomsGrid[x][y], gameboard);
                
            }

        }

    }

    public void generateRoom(DungeonRoom room, Board gameboard) {

        char[][] roomSeed = new char[ROOM_SIZE][ROOM_SIZE];
        
        //Get the correct layout for the room based on its position/neighbors
        List<DungeonRoom> roomNeighbors = room.GetNeighbors();
        
        switch (roomNeighbors.size()) {
            
            case 1:
                
                //Deadend rooms
                if (room.hasNeighborEast()) {
                    
                    roomSeed = getRoomFromFile("DungeonRoomLayouts1.txt", 0);
                    
                }
                if (room.hasNeighborSouth()) {
                    
                    roomSeed = getRoomFromFile("DungeonRoomLayouts1.txt", 1);
                    
                }
                if (room.hasNeighborWest()) {
                    
                    roomSeed = getRoomFromFile("DungeonRoomLayouts1.txt", 2);
                    
                }
                if (room.hasNeighborNorth()) {
                    
                    roomSeed = getRoomFromFile("DungeonRoomLayouts1.txt", 3);
                    
                }
                
                break;
                
            case 2:
                
                //Straight hallways
                if (room.hasNeighborEast() && room.hasNeighborWest()) {
                    
                    roomSeed = getRoomFromFile("DungeonRoomLayouts2S.txt", 0);
                    
                }
                
                if (room.hasNeighborNorth() && room.hasNeighborSouth()) {
                    
                    roomSeed = getRoomFromFile("DungeonRoomLayouts2S.txt", 1);
                    
                }
                
                //L-shaped rooms
                if (room.hasNeighborEast()){
                    
                    if (room.hasNeighborNorth()){
                    
                        roomSeed = getRoomFromFile("DungeonRoomLayouts2L.txt", 0);
                    
                    }
                    
                    if (room.hasNeighborSouth()){
                    
                        roomSeed = getRoomFromFile("DungeonRoomLayouts2L.txt", 1);
                    
                    }
                    
                }
                
                if (room.hasNeighborWest()){
                    
                    if (room.hasNeighborNorth()){
                    
                        roomSeed = getRoomFromFile("DungeonRoomLayouts2L.txt", 3);
                    
                    }
                    
                    if (room.hasNeighborSouth()){
                    
                        roomSeed = getRoomFromFile("DungeonRoomLayouts2L.txt", 2);
                    
                    }
                    
                }
                
                break;
                
            case 3:
                
                if (!room.hasNeighborWest()) {
                    
                    roomSeed = getRoomFromFile("DungeonRoomLayouts3.txt", 0);
                    
                }
                
                if (!room.hasNeighborNorth()) {
                    
                    roomSeed = getRoomFromFile("DungeonRoomLayouts3.txt", 1);
                    
                }
                                
                if (!room.hasNeighborEast()) {
                    
                    roomSeed = getRoomFromFile("DungeonRoomLayouts3.txt", 2);
                    
                }
                                
                if (!room.hasNeighborSouth()) {
                    
                    roomSeed = getRoomFromFile("DungeonRoomLayouts3.txt", 3);
                    
                }
                
                break;
                
            default:

                roomSeed = getRoomFromFile("DungeonRoomLayouts4.txt", 0);
                
        }
        
        //Initialize variables
        int xoffset = room.GetX() * ROOM_SIZE;
        int yoffset = room.GetY() * ROOM_SIZE;
        
        //Go through the string file and put its game elements onto the board
        for (int y = 0; y < ROOM_SIZE; y++){
            
            for (int x = 0; x < ROOM_SIZE; x++){
                
                char c = roomSeed[x][y];
                
                //Clear the square before adding new objects
                gameboard.clearSquare(x + xoffset, y + yoffset);
                
                //Add game object based on character in String
                switch (c){

                    case '#':
                        new ObjectWall(x + xoffset, y + yoffset, gameboard);
                        break;
                        
                    case 'd':
                        new WeaponSword(x + xoffset, y + yoffset, gameboard);
                        break;
                        
                    case 'C':
                        new EntityChest(x + xoffset, y + yoffset, gameboard);
                        break;
                        
                    case 'P':
                        new Player(x + xoffset, y + yoffset, gameboard, 15);
                        break;
                        
                    case 'G':
                        new EntityGoblin(x + xoffset, y + yoffset, gameboard);
                        break;
                        
                }

            }
            
        }

    }
    
    //Helper method for getRoomFromFile
    private int getLengthOfFile(String fileName) {
        
        int linesCount = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while (reader.readLine() != null) linesCount++;
            reader.close();

        } catch (IOException e) {

            return 0;
            
        }
        
        return linesCount;
        
    }
    
    private char[][] getRoomFromFile(String fileName, int rotationsCount) {
        
        char[][] roomSeed = new char[ROOM_SIZE][ROOM_SIZE];

        //Pick random room in the file
        Random r = new Random();
        int seedOffset = r.nextInt(getLengthOfFile(fileName) / (ROOM_SIZE + 1) + 1);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line = reader.readLine();
            
            //Skip to correct seed in file
            for (int i = 0; i < seedOffset * (ROOM_SIZE + 1); i++) {
                
                line = reader.readLine();
                
            }

            int x = 0;
            int y = 0;

            for (int i = 0; i < ROOM_SIZE; i++) {

                for (int j = 0; j < line.length(); j++) {

                    roomSeed[x++][y] = line.charAt(j);
                    
                }

                y++;
                x = 0;

                line = reader.readLine();
                
            }
            
            reader.close();

        } catch (IOException e) {

            return new char[ROOM_SIZE][ROOM_SIZE];
            
        }
        
        for (int i = 0; i < rotationsCount; i++) {
            
            roomSeed = rotateRoom(roomSeed);
            
        }
        
        return roomSeed;
        
    }
    
    //Rotates the matrix representation of a room clockwise
    private char[][] rotateRoom(char[][] oldSeed) {
        
        char[][] newSeed = new char[ROOM_SIZE][ROOM_SIZE];

        for (int y = 0; y < ROOM_SIZE; y++){
            
            for (int x = ROOM_SIZE - 1; x >= 0; x--){

                //Transform the x and y coordinates
                newSeed[y][x] = oldSeed[x][ROOM_SIZE - 1 - y];
                
            }
            
        }
        
        return newSeed;
        
    }
    
    //Flips the matrix representation of a room vertically
    private char[][] flipRoom(char[][] oldSeed) {
        
        char[][] newSeed = new char[ROOM_SIZE][ROOM_SIZE];

        //Go through the board, inserting the 
        for (int y = 0; y < ROOM_SIZE; y++){
            
            for (int x = ROOM_SIZE - 1; x >= 0; x--){

                //Transform the x and y coordinates
                newSeed[x][y] = oldSeed[x][ROOM_SIZE - 1 - y];
                
            }
            
        }
        
        return newSeed;
        
    }
    
}
