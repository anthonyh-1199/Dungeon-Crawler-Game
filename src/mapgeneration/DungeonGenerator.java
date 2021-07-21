
package mapgeneration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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
    
    final int ROOM_SIZE = 20;
    
    public DungeonGenerator(Board gameboard) {
        
        super(gameboard);
        
        generateDungeonFromLayout(generateLayout(), gameboard);
        
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
        int xoffset = room.GetX() * 20;
        int yoffset = room.GetY() * 20;
        
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
    
    private char[][] getRoomFromFile(String fileName, int rotationsCount) {
        
        char[][] roomSeed = new char[ROOM_SIZE][ROOM_SIZE];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line = br.readLine();
            
            int x = 0;
            int y = 0;

            for (int i = 0; i < ROOM_SIZE; i++) {

                for (int j = 0; j < line.length(); j++) {

                    roomSeed[x++][y] = line.charAt(j);
                    
                }

                y++;
                x = 0;

                line = br.readLine();
                
            }

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
