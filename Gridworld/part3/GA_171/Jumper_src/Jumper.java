import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

/**
 * A <code>Jumper</code> jumps two cells forward in each move. <br />
 */
public class Jumper extends Bug {
    /**
     * Constructs a red jumper.
     */
    public Jumper()
    {
        setColor(Color.RED);
    }

    /**
     * Constructs a jumper of a given color.
     * @param jumperColor the color for this jumper
     */
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * Turns the jumper 90 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.RIGHT);
    }


    /**
     * Moves the jumper forward two cells.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
        {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nextTwo = next.getAdjacentLocation(getDirection());
        if (gr.isValid(nextTwo))
        {
            moveTo(nextTwo);
        }
        else
        {
            removeSelfFromGrid();
        }
    }


    /**
     * Tests whether this jumper can move forward into a location.
     * @return true if this jumper can move.
     */
    public boolean canMove()
    {
        // If the jumper is not in the grid, it cannot move.
        Grid<Actor> gr = getGrid();
        if (gr == null)
        {
            return false;
        }

        Location loc = getLocation();
        // If the location in front of the jumper is out of the grid, it cannot move.
        Location nextOne = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(nextOne))
        {
            return false;
        }

        // If the location two cells in front is out of the grid, it cannot move.
        Location nextTwo = nextOne.getAdjacentLocation(getDirection());
        if (!gr.isValid(nextTwo))
        {
            return false;
        }

        Actor neighbor = gr.get(nextTwo);
        return (neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Rock);
        // ok to move into empty location or onto flower and rock.
        // not ok to move onto any other actor
    }
}
