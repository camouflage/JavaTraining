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
import info.gridworld.grid.BoundedGrid;
import java.awt.Color;
import java.util.*;
import java.util.ArrayList;

/**
 * A <code>PlayBoy</code> 
 */
public class PlayBoy extends Critter
{
    private static final double DARKENING_FACTOR = 0.05;

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
            colorArray = new Color[10];
            Random random = new Random();
            int red, green, blue;
            for (int i = 0; i < 10; ++i) {
                red = Math.abs(random.nextInt() % 256);
                green = Math.abs(random.nextInt() % 256);
                blue = Math.abs(random.nextInt() % 256);
                colorArray[i] = new Color(red, green, blue);
            }
        } else {
            colorArray = Arrays.copyOf(color, color.length);
        }
    }

    /* Get flowers from four diagonal directions.
     * e.g. NE, SE, SW, NW
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> result = super.getActors();
        int directionToward;
        for (int i = 0; i < result.size(); ++i) {
            directionToward =
                getLocation().getDirectionToward(result.get(i).getLocation());
            if (result.get(i) instanceof Flower &&
                (directionToward == Location.NORTHEAST ||
                 directionToward == Location.SOUTHEAST ||
                 directionToward == Location.SOUTHWEST ||
                 directionToward == Location.NORTHWEST)) {
            } else {
                result.remove(i);
                --i;
            }
        }
        return result;
    }

    // Collect all the flowers. Darken its color.
    public void processActors(ArrayList<Actor> actors)
    {
        Color c;
        int red, green, blue;
        for (Actor a : actors) {
            c = getColor();
            red = (int)(c.getRed() * (1 - DARKENING_FACTOR));
            green = (int)(c.getGreen() * (1 - DARKENING_FACTOR));
            blue = (int)(c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
            a.removeSelfFromGrid();
            ++flower;
        }
    }
    
    /* Return all valid locations where there it is occupied
     * by a flower or unoccupied
     * IN THE WHOLE GRID.
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> result = new ArrayList<Location>();
        Grid<Actor> gr = getGrid();
        Actor a;
        int rows = gr.getNumRows(), cols = gr.getNumCols();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                a = gr.get(new Location(i, j));
                if (a == null || a instanceof Flower)
                    result.add(new Location(i, j));
            }
        }
        return result;
    }

    /* Decide whether to reproduce or not.
     * Its child has a different color.
     */
    public void makeMove(Location loc)
    {
        Location oldLoc = getLocation();
        moveTo(loc);
        if (flower >= THRESHOLD) {
            PlayBoy child = new PlayBoy(colorArray);
            child.setColor(colorArray[children % colorArray.length]);
            child.putSelfInGrid(getGrid(), oldLoc);
            ++children;
            flower -= THRESHOLD;
        }
    }

    // For Test:
    public int getFlower() {
        return flower;
    }

    public int getChildren() {
        return children;
    }
}
