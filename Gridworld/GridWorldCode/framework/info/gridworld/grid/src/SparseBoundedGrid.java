package mygrid;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private ArrayList<LinkedList> occupantArray; // the array storing the linkedList of col elements
    private int row;
    private int col;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        row = rows;
        col = cols;
        occupantArray = new ArrayList<LinkedList>();
        for ( int i = 0; i < cols; i++ ) {
            occupantArray.add(new LinkedList<OccupantInCol>());
        }
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
        for (int r = 0; r < getNumRows(); r++) {
            LinkedList<OccupantInCol> colOccupant = occupantArray.get(r);
            for ( OccupantInCol o : colOccupant ) {
                Location loc = new Location(r, o.getCol());
                theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        LinkedList<OccupantInCol> colOccupant = occupantArray.get(loc.getRow());
        E occ = null;

        int col = loc.getCol();
        for ( OccupantInCol o : colOccupant ) {
            if ( o.getCol() == col ) {
                occ = (E) o.getOccupant();
            }
        }

        return occ;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        LinkedList<OccupantInCol> colOccupant = occupantArray.get(loc.getRow());

        OccupantInCol occ = new OccupantInCol((Object) obj, loc.getCol());
        remove(loc);
        colOccupant.add(occ);

        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        LinkedList<OccupantInCol> colOccupant = occupantArray.get(loc.getRow());
        int col = loc.getCol();

        for ( OccupantInCol o : colOccupant ) {
            if ( o.getCol() == col ) {
                colOccupant.remove(o);
                break;
            }

        }

        return r;
    }
}
