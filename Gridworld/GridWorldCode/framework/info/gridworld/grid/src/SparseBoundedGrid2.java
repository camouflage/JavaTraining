package mygrid;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;

/**
 * A <code>SparseBoundedGrid2</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
    private Map<Location, E> occupantMap;
    private int row;
    private int col;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid2
     * @param cols number of columns in SparseBoundedGrid2
     */
    public SparseBoundedGrid2(int rows, int cols)
    {
        occupantMap = new HashMap<Location, E>();
        row = rows;
        col = cols;
    }

    public int getNumRows()
    {
        return row;
    }

    public int getNumCols()
    {
        return col;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for ( Location loc : occupantMap.keySet() ) {
            theLocations.add(loc);
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.

        return occupantMap.put(loc, obj);
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        
        return occupantMap.remove(loc);
    }
}
