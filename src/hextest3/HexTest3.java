package hextest3;

public class HexTest3 {
    
    
    
    public static void main(String[] args) {
        
//        int id = 0;
//        
//        int idget = 38;
//        int row = 3;
//        int col = 4;
//        
//        
//        System.out.println();
//        System.out.print("ID: ");
//        int derivedID = (row*BOUND) + col;
//        System.out.print(derivedID);
//        
//        System.out.println();
//        System.out.print("Coordinates: " + derivedID/BOUND + ",");
//        System.out.println(derivedID%BOUND);

        //HexGrid grid = new HexGrid();
        
        char[] tiles = new char[100];
        
        for(int i = 0; i < 100; i++)
        {
            if(i % 2 == 0)
            {
                tiles[i] = 'P';
            }
            else
                tiles[i] = 'H';
        }
        
//        for(int i = 0; i < 100; i++)
//        {
//            System.out.println(tiles[i]);
//        }
//        
//        System.out.println(tiles.length);
        
        HexGrid terrainGrid = new HexGrid(tiles);
        
        String gridprint = terrainGrid.printTerrainGrid();
        
        System.out.println(gridprint);
        
//        HexTile test = grid.getTile(41);
//        
//        System.out.println("Original tile: " + test.getID());
//        
//        int test2 = grid.cubeToID(test.getCubeX(), test.getCubeY(), test.getCubeZ());
//        
//        System.out.println("New tile: " + test2);
//        
//        HexTile test3 = grid.getTile(99);
//                
//        System.out.println("Distance from: " + test.getID() + " to " + test3.getID());
//        
//        System.out.println(grid.offsetDistance(test, test3));
        
        System.out.println("\n\n\n");
        
        terrainGrid.coordinateRange(44, 2);
        
        while(!terrainGrid.rangeSet.empty())
        {
            System.out.print(terrainGrid.rangeSet.pop() + " ");
        }
        
        System.out.println(terrainGrid.aStarSearch(44, 46));
        
        System.out.println("\n\n\n");
        
        terrainGrid.movementRange(44, 2);
        
        while(!terrainGrid.moveSet.empty())
        {
            System.out.print(terrainGrid.moveSet.pop() + " ");
        }
        
        
        //TESTING NEIGHBOR METHOD
//        grid.coordinateRange(44,10);
        
//        grid.printGrid();
//        
//        System.out.println(grid.getNeighborList().size());
//        
//        String neighbor = "";
//        
//        while(!grid.getNeighborList().empty())
//        {
//            //Add id to neighbor string
//            neighbor += grid.getNeighborList().peek() + " ";
//            
//            //Unmark tile.
//            grid.getTile(grid.getNeighborList().peek()).mark();
//            
//            //Testing if tile does become unmarked.
//            //System.out.println(grid.getTile(grid.getNeighborList().peek()).getMark());
//            
//            //Remove tileID from stack
//            grid.getNeighborList().pop();
//        }
        
        //Print neighbor list
//        System.out.println(neighbor + "\n");
        
        //Testing if stack is emptied properly.
        //System.out.println(grid.getNeighborList().empty());
        
//        gridprint = grid.printGrid();
//        
//        System.out.println(gridprint);
        
    }
    
}
