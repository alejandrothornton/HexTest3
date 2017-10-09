/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hextest3;

public class HexTile {
    
    private int gridID;         //Unique identifier for the tile on grid.
    private int row,col;
    Terrain type;
    
    boolean mark;               //Used for path/search algorithms.
    
    //Create the tile
    public HexTile(int id)
    {
        mark = false;
        gridID = id;
        row = gridID/HexGrid.BOUND;
        col = gridID%HexGrid.BOUND;
    }
    
    public void setTerrain(char terrType)
    {
        if(terrType == 'P')
        {
            type = new Plains();
        }
        if(terrType == 'H')
        {
            type = new Hills();
        }
    }
    
    //Retrieve tile ID.
    public int getID()
    {
        return gridID;
    }
    
    //May not be neccessary.
    public int[] getCoordinates()
    {
        int[] coordinates = {gridID/HexGrid.BOUND,gridID/HexGrid.BOUND};
        //coordinates[0] = gridID/HexGrid.BOUND;
        //coordinates[1] = gridID/HexGrid.BOUND;
        return coordinates;
    }
    
    public int getCubeX()
    {
        return col - (row - row%2) / 2;
    }
    
    public int getCubeY()
    {
        return -getCubeX()-getCubeZ();
    }
    
    public int getCubeZ()
    {
        return row;
    }
    
    //Methods for manipulating and accessing the mark.
    public void mark()
    {
        mark = true;
    }
    
    public void unmark()
    {
        mark = false;
    }
    
    public boolean getMark()
    {
        return mark;
    }
    
    public int getMoveCost()
    {
        return type.getMoveCost();
    }
    
    
    public char getTerrainPrint()
    {
        return type.getType();
    }
}
