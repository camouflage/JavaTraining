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
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>KingCrab</code> looks at a limited set of neighbors when it pushes, eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter
{
    /**
     * A KingCrab causes each actor that it processes to move one location further away from the KingCrab. 
     * If the actor cannot move away, the KingCrab removes it from the grid. 
     */
    public void processActors(ArrayList<Actor> actors)
    {
        Location loc = getLocation();
        int row = loc.getRow();
        int col = loc.getCol();
        for (Actor a : actors)
        {
            Location aLoc = a.getLocation();
            int aRow = aLoc.getRow();
            int aCol = aLoc.getCol();
            int newRow = row + 2 * (aRow - row);
            int newCol = col + 2 * (aCol - col);
            Location newLoc = new Location(newRow, newCol);
            if ( getGrid().isValid(newLoc) ) {
                a.moveTo(newLoc);
            } else {
                a.removeSelfFromGrid();
            }
        }
    }
}
