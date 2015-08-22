/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;

import java.util.ArrayList;

/**
 * A <code>PlayBoy</code> 
 */
public class PlayBoy extends Critter
{
    /* If the number of flowers collected exceeds this threshold,
     * it reproduces.
     */
    private static final int THRESHOLD = 10;
    // The number of flowers it has collected. Reset after reproduce.
    private int flower;
    // The number of children.
    private int children;
    // Color pattern.
    private Color[] colorArray;

    // Constructor: set flower and children to 0, and get color pattern as param.
    public PlayBoy(Color[] color)
    {
        super();
        flower = 0;
        children = 0;
        if ( color == null ) {
            colorArray = new Color[0];
        } else {
            colorArray = Arrays.copyOf(color, color.length);
        }
    }

    /* Get flowers from four diagonal directions.
     * e.g. NE, SE, SW, NW
     */
    public ArrayList<Actor> getActors()
    {
        return
    }

    // Collect all the flowers. Darken its color.
    public void processActors(ArrayList<Actor> actors)
    {

    }
    
    /* Return all valid locations where there it is occupied by a flower or unoccupied
     * IN THE WHOLE GRID.
     */
    public ArrayList<Location> getMoveLocations()
    {
        return
    }

    /* Decide whether to reproduce or not.
     * Its child has a different color.
     */
    public void makeMove(Location loc)
    {

    }
}
