import java.util.ArrayList;
import java.awt.Color;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


/**
 * A <code>ModifiedChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * If the list of actors to process is empty,
 * the color of the ChameleonCritter will darken.
 */
public class ChameleonKid extends ChameleonCritter {
    /**
     * Randomly selects a neighbor immediately in front or behind and 
     * changes this critter's color to be the same as that neighbor's. 
     * If there are no neighbors, the color of the ChameleonCritter will darken.
     */

    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actor = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, 180 };

        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
            {
                actor.add(a);
            }
        }

        return actor;
    }

    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
            {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }    
}
