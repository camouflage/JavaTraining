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
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;

import java.util.ArrayList;

/**
 * A <code>BlusterCritter/code> brightens or darkens according to
 * the number of critters around.
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    private static final double FACTOR = 0.05;
    private int courage;
    
    public BlusterCritter(int cour)
    {
        // Call the constructor of Actor.(Must be first)
        super();
        courage = cour;
    }

    // Get neighboring critter within two steps.
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Grid<Actor> grid = getGrid();
        Location curLoc = getLocation();
        int row = curLoc.getRow();
        int column = curLoc.getCol();

        for ( int i = row - 2; i <= row + 2; i++ ) {
            for ( int j = column - 2; j <= column + 2; j++ ) {
                if ( i == row && j == column ) {
                    continue;
                }
                Location loc = new Location(i, j);
                if ( grid.isValid(loc) ) {
                    Actor a = grid.get(loc);
                    if ( a instanceof Critter ) {
                        actors.add(a);
                    }
                }
            }
        }
        return actors;
    }

    /** If the number of critters within two steps is fewer than courage, it will brighten,
     *  otherwise, it will darken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        Color c = getColor();
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        if ( actors.size() < courage ) {
            red = (int) (red * (1 + FACTOR));
            green = (int) (green * (1 + FACTOR));
            blue = (int) (blue * (1 + FACTOR));
            if ( red > 255 ) {
                red = 255;
            }
            if ( green > 255 ) {
                green = 255;
            }
            if ( blue > 255 ) {
                blue = 255;
            }

        } else if ( actors.size() > courage ) {
            red = (int) (red * (1 - FACTOR));
            green = (int) (green * (1 - FACTOR));
            blue = (int) (blue * (1 - FACTOR));  
        }

        setColor(new Color(red, green, blue));
    }
}
