/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hextest3;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class HexGrid {

    /**
     * BOUND is a constant which determines the limits of the 2D array that
     * represents the hex grid. hexGrid is the aforementioned 2D array.
     * neighbors is a stack which holds the list of neighbors of a given tile
     */
    static final int BOUND = 10;
    private HexTile[][] hexGrid = new HexTile[BOUND][BOUND];
    Stack<Integer> neighbors = new Stack<Integer>();
    Stack<Integer> rangeSet = new Stack<Integer>();
    Stack<Integer> moveSet = new Stack<Integer>();

    //initializing the hex grid
    public HexGrid() {
        int id = 0;

        //Generate grid, each tile has a unique ID
        for (int i = 0; i < hexGrid.length; i++) {
            for (int j = 0; j < hexGrid[i].length; j++) {
                hexGrid[i][j] = new HexTile(id);
                id++;
            }
        }
    }
    
    public HexGrid(char[] tiles)
    {
        int id = 0;

        //Generate grid, each tile has a unique ID
        for (int i = 0; i < hexGrid.length; i++) {
            for (int j = 0; j < hexGrid[i].length; j++) {
//                System.out.println(id);
                hexGrid[i][j] = new HexTile(id);
//                System.out.println(tiles[id]);
                hexGrid[i][j].setTerrain(tiles[id]);
                id++;
            }
        }
    }
    
    //returns list of neighbors of the given coordinates
    public void neighbors(int row, int col) {
        if (col - 1 >= 0) {
            //hexGrid[row][col - 1].mark();
            neighbors.push(((row*BOUND) + col-1));
        }

        if (col + 1 < BOUND) {
            //hexGrid[row][col + 1].mark();
            neighbors.push(((row*BOUND) + col+1));
        }

        if (row % 2 == 1) {
            if (row - 1 >= 0) {
                //hexGrid[row - 1][col].mark();
                neighbors.push((((row-1)*BOUND) + col));

                if (col + 1 < BOUND) {
                    //hexGrid[row - 1][col + 1].mark();
                    neighbors.push((((row-1)*BOUND) + col+1));
                }
            }

            if (row + 1 < BOUND) {
                //hexGrid[row + 1][col].mark();
                neighbors.push((((row+1)*BOUND) + col));

                if (col + 1 < BOUND) {
                    //hexGrid[row + 1][col + 1].mark();
                    neighbors.push((((row+1)*BOUND) + col+1));
                }
            }
        } else //Top and bottom neighbors of EVEN row tile.
        {
            if (row - 1 >= 0) {
                //hexGrid[row - 1][col].mark();
                neighbors.push((((row-1)*BOUND) + col));

                if (col - 1 >= 0) {
                    //hexGrid[row - 1][col - 1].mark();
                    neighbors.push((((row-1)*BOUND) + col-1));
                }
            }

            if (row + 1 < BOUND) {
                //hexGrid[row + 1][col].mark();
                neighbors.push((((row+1)*BOUND) + col));

                if (col - 1 >= 0) {
                    //hexGrid[row + 1][col - 1].mark();
                    neighbors.push((((row+1)*BOUND) + col));
                }
            }
        }
    }
    
    //Calculates tile from cube coordinates - currently unused
    public HexTile cubeToTile(int cubex, int cubey, int cubez)
    {
        int col = cubex + (cubez - (cubez%2)) / 2;
        int row = cubez;
        
        return hexGrid[row][col];
    }
    
    //Calculates gridID from cube coordinates
    public int cubeToID(int cubex, int cubey, int cubez)
    {
        int col = cubex + (cubez - (cubez%2)) / 2;
        int row = cubez;
        
        return (row* BOUND) + col;
    }
    
    //Calculates cube coordinates from tile ID
    public int[] tileToCube(HexTile tile)
    {
        int row = tile.getID() / BOUND;
        int col = tile.getID() % BOUND;
        
        int x = col - (row - row%2) / 2;
        int z = row;
        int y = -x-z;
        
        int[] cube = {x,y,z};
        
        return cube;
    }
    
    public int[] idToCube(int id)
    {
        int row = id / BOUND;
        int col = id % BOUND;
        
        int x = col - (row - row%2) / 2;
        int z = row;
        int y = -x-z;
        
        int[] cube = {x,y,z};
        
        return cube;
    }
    
    //Calculates cube distance from cube coordinates.
    public int cubeDistance(int[] source, int[] target)
    {
        
        return (Math.abs(source[0]-target[0])
                + Math.abs(source[1]-target[1])
                + Math.abs(source[2]-target[2])) / 2;
    }
    
    //Calculates offset distance
    public int offsetDistance(HexTile source, HexTile target)
    {
        int[] sourceCube = tileToCube(source);
        int[] targetCube = tileToCube(target);
        
        return cubeDistance(sourceCube, targetCube);
    }
    
    //Adds given coordinates to cubic coordinates of tile with given ID,
    //calculating the ID of the resulting tile.
    public int cubeAdd(int gridID, int cubex, int cubey, int cubez)
    {
        int row = gridID / BOUND;
        int col = gridID % BOUND;
        
        int x = col - (row - row%2) / 2;
        int z = row;
        int y = -x-z;

        int nx = x + cubex;
        int ny = y + cubey;
        int nz = z + cubez;
        
        return cubeToID(nx,ny,nz);
    }
    
    //checks if given coordinates are within 
    public boolean withinBounds(int row, int col)
    {
        return (row >= 0 && row < BOUND && col >= 0 && col < BOUND);
    }
    
    //Determines which hexes fall within the given range of given tile.
    public void coordinateRange(int centerID, int range)
    {   
        int dx, dy, dz, tempID;
        
        for(dx = -range; dx <= range; dx++)
        {
            for(dy = Math.max(-range, -dx-range); dy <= Math.min(range, -dx+range); dy++ )
            {
                dz=-dx-dy;
                tempID=cubeAdd(centerID, dx,dy,dz);
                if(withinBounds(tempID/BOUND,tempID%BOUND))
                    rangeSet.push(tempID);
            }
        }
    }
    
    //Used for the priority queue
    private class NodeWeight
    {
        public int gridID;
        public int weight;
        
        public NodeWeight(int id, int wgt)
        {
            gridID = id;
            weight = wgt;
        }
    }
    
    //Comparator for the priority queue
    private class WeightCompare implements Comparator<NodeWeight>
    {
        public int compare(NodeWeight a, NodeWeight b)
        {
            return a.weight - b.weight;
        }
    }
    
    //A* Search for shortest path. Returns weight of that path.
    public int aStarSearch(int startID, int endID)
    {
        int newCost, nextID, priority, path;
        NodeWeight current;
        
        //Creating and initializing structures used for search algorithm.
        PriorityQueue<NodeWeight> frontier = new PriorityQueue<NodeWeight>(1, new WeightCompare());
        frontier.add(new NodeWeight(startID, 0));
        
        LinkedList<Integer> cameFrom = new LinkedList<Integer>();
        Hashtable<Integer, Integer> costSoFar = new Hashtable<Integer, Integer>();
        
        //cameFrom.add(startID);
        costSoFar.put(startID, 0);
        path = 0;
        
        while(frontier.size() > 0)
        {
            current = frontier.poll();
            
            if(current.gridID == endID)
                break;
            
            neighbors(current.gridID/BOUND, current.gridID%BOUND);
            
            while(!neighbors.empty())
            {
                nextID = neighbors.pop();
                newCost = costSoFar.get(current.gridID) + getTile(nextID).getMoveCost();
                
                if(!costSoFar.containsKey(nextID))
                {
                    costSoFar.put(nextID, newCost);
                    priority = newCost + cubeDistance(idToCube(nextID),idToCube(endID));
                    
                    frontier.add(new NodeWeight(nextID, priority));
                    cameFrom.add(current.gridID);
                }
                else if(newCost < costSoFar.get(nextID))
                {
                    costSoFar.replace(nextID, newCost);
                    priority = newCost + cubeDistance(idToCube(nextID),idToCube(endID));
                    
                    frontier.add(new NodeWeight(nextID, priority));
                    cameFrom.add(current.gridID);
                }
            }
        }
        
        path = costSoFar.get(endID);
        
        return path;
    }
    
    public void movementRange(int centerID, int movementRange)
    {
        coordinateRange(centerID, movementRange);
        
        while(!rangeSet.empty())
        {
            if(aStarSearch(centerID, rangeSet.peek()) <= movementRange && rangeSet.peek() != centerID)
            {
                moveSet.add(rangeSet.pop());
            }
            else
            {
                rangeSet.pop();
            }
        }
    }

    //returns tile with given ID
    public HexTile getTile(int id) {
        return hexGrid[id / BOUND][id % BOUND];
    }
    
    //Method to clear neighbor list - not used but may be needed later
    public void clearNeighbors()
    {
        neighbors.clear();
    }

    public Stack<Integer> getNeighborList() {
        return neighbors;
    }
    
    public void clearRangeSet()
    {
        rangeSet.clear();
    }
    
    public Stack<Integer> getRangeSet()
    {
        return rangeSet;
    }

    //printing the grid
    public String printGrid() {
        String grid = "";
        for (int i = 0; i < hexGrid.length; i++) {
            if (i % 2 == 1) {
                grid += "\t";
            }

            for (int j = 0; j < hexGrid[i].length; j++) {
                if (j > 0) {
                    grid += "\t*\t" + hexGrid[i][j].getID();
                    if(hexGrid[i][j].getMark())
                       grid += "' ";
                    else
                        grid += " ";
                } else {
                    grid += "\t\t" + hexGrid[i][j].getID();
                    if(hexGrid[i][j].getMark())
                        grid += "' ";
                    else
                        grid += " ";
                }
            }

            grid += "\n\n";
        }
        return grid;
    }

    public String printTerrainGrid() {
        String grid = "";
        for (int i = 0; i < hexGrid.length; i++) {
            if (i % 2 == 1) {
                grid += "\t";
            }

            for (int j = 0; j < hexGrid[i].length; j++) {
                if (j > 0) {
                    grid += "\t*\t" + hexGrid[i][j].getID() + hexGrid[i][j].getTerrainPrint();
                } else {
                    grid += "\t\t" + hexGrid[i][j].getID() + hexGrid[i][j].getTerrainPrint();
                }
            }

            grid += "\n\n";
        }
        return grid;
    }
}
