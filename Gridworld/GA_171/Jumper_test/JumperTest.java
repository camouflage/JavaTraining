import static org.junit.Assert.*;
import org.junit.*;
import java.util.List;
import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;

public class JumperTest {
    private BoundedGrid<Actor> gr = new BoundedGrid<Actor>(10, 10);
    @Test
    public void testConstructor() {
        Jumper test0 = new Jumper();
        Jumper test1 = new Jumper(Color.GREEN);
        assertEquals(Color.RED, test0.getColor());
        assertEquals(Color.GREEN, test1.getColor());
    }
    @Test
    public void testTurn() {
        Jumper test2 = new Jumper();
        test2.setDirection(Location.NORTH);
        test2.putSelfInGrid(gr, new Location(2, 3));
        test2.turn();
        assertEquals(Location.EAST, test2.getDirection());
        assertEquals(new Location(2, 3), test2.getLocation());
        test2.removeSelfFromGrid();
    }
    @Test
    public void testMove() {
        Jumper test3 = new Jumper();
        test3.setDirection(Location.NORTH);
        test3.putSelfInGrid(gr, new Location(2, 0));
        test3.move();
        assertEquals(new Location(0, 0), test3.getLocation());
        test3.move();
        assertEquals(0, gr.getOccupiedLocations().size());
    }
    @Test
    public void testCanMove() {
        Jumper test4 = new Jumper();
        assertEquals(false, test4.canMove());
        test4.putSelfInGrid(gr, new Location(3, 0));
        assertEquals(true, test4.canMove());
        gr.put(new Location(2, 0), new Rock());
        assertEquals(true, test4.canMove());
        gr.put(new Location(1, 0), new Rock());
        assertEquals(true, test4.canMove());
        gr.get(new Location(1, 0)).removeSelfFromGrid();
        gr.put(new Location(1, 0), new Flower());
        assertEquals(true, test4.canMove());
        test4.move();
        assertEquals(false, test4.canMove());
        gr.put(new Location(0, 0), new Rock());
        assertEquals(false, test4.canMove());
        test4.removeSelfFromGrid();
    }
}
