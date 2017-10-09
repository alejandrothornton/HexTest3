/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hextest3;

/**
 *
 * 
 */
public abstract class Terrain {
    
    char type;        //The 'name' of the terrain
    int movecost;       //Cost of movement for this terrain (applies only to walk-type movement)
    boolean walk;       //Walking possible on this tile?
    boolean fly;        //Flying possible on this tile?
    
    public char getType()
    {
        return type;
    }
    
    public int getMoveCost()
    {
        return movecost;
    }
    
    public boolean walkAllowed()
    {
        return walk;
    }
    
    public boolean flyAllowed()
    {
        return fly;
    }
    
}

class Plains extends Terrain
{
    public Plains()
    {
        type = 'P';
        movecost = 1;
        walk = true;
        fly = true;
    } 
}

class Hills extends Terrain
{
    public Hills()
    {
        type = 'H';
        movecost = 2;
        walk = true;
        fly = true;        
    }
}
