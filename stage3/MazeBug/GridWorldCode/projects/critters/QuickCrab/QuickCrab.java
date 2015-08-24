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
 * A <code>QuickCrab</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
    /** A QuickCrab moves to one of the two locations, randomly selected,
     *  that are two spaces to its right or left,
     *  if that location and the intervening location are both empty.
     *  Otherwise, a QuickCrab moves like a CrabCritter.
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid grid = getGrid();
        Location curLoc = getLocation();
        int dir = getDirection();
        Location locLeft = curLoc.getAdjacentLocation(dir + Location.LEFT);
        Location locRight = curLoc.getAdjacentLocation(dir + Location.RIGHT);

        if ( grid.isValid(locLeft) && grid.get(locLeft) == null )
        {
            Location locLeftTwo = locLeft.getAdjacentLocation(dir + Location.LEFT);
            if ( grid.isValid(locLeftTwo) && grid.get(locLeftTwo) == null ) {
                locs.add(locLeftTwo);
            } else {
                locs.add(locLeft);
            }
        }
        if ( grid.isValid(locRight) && grid.get(locRight) == null )
        {
            Location locRightTwo = locRight.getAdjacentLocation(dir + Location.RIGHT);
            if ( grid.isValid(locRightTwo) && grid.get(locRightTwo) == null ) {
                locs.add(locRightTwo);
            } else {
                locs.add(locRight);
            }
        }
        return locs;
    }
}
