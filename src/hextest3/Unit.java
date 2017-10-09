/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hextest3;

/**
 * Abstract class for a unit object. Includes inherent and active properties.
 * Inherent: defined apriori (endemic to specific character)
 * Active: used for purposes of in-game mechanics
 */
public abstract class Unit {
    
    //Inherent properties to unit
    String name;
    String type;
    int hitpoints;
    int move;
    int critthresh;
    
    //Ability toggle (display/time purposes)
    boolean special1;
    boolean special2;
    boolean skill1;
    boolean skill2;
    
    //Damage-type resistances
    int resist1;
    
    //'Active' (in-game) properties of unit
    int tileID;             //Current location of unit
    boolean player;         //Controlling player
    int cooldown1;
    int cooldown2;
    
    //Unit effects
    int status1;
    int status2;
    
    
    //Unit abilities
    public abstract void basic();
    public abstract void special1();
    public abstract void special2();
    public abstract void skill1();
    public abstract void skill2();
    public abstract void printStatus();
    
}
