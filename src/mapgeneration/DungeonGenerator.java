
package mapgeneration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                //TO-DO: find out how many exits the room has and rotate it to fit the map
                generateRoom(roomsGrid[x][y], gameboard);
                
            }

        }

    }

    public void generateRoom(DungeonRoom room, Board gameboard) {

        char[][] roomSeed = getRoomFromFile("DungeonRoomLayouts1A.txt", 1);
        
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
    
    //Rotates the String representation of a room clockwise
    private char[][] rotateRoom(char[][] oldSeed) {
        
        char[][] newSeed = new char[ROOM_SIZE][ROOM_SIZE];

        //Go through the board, inserting the 
        for (int y = 0; y < ROOM_SIZE; y++){
            
            for (int x = ROOM_SIZE - 1; x >= 0; x--){

                //Flip the x and y coordinates
                //newSeed[x][ROOM_SIZE - 1 - y] = oldSeed[y][x];
                newSeed[y][x] = oldSeed[x][ROOM_SIZE - 1 - y];
                
            }
            
        }
        
        return newSeed;
        
    }
    
}
