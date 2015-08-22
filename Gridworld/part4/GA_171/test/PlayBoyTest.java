import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;

public class PlayBoyTest {
    private BoundedGrid<Actor> gr = new BoundedGrid<Actor>(10, 10);
    private static final double DARKENING_FACTOR = 0.05;

    @Test
    public void testGetActors() {
        PlayBoy test0 = new PlayBoy(null);
        test0.putSelfInGrid(gr, new Location(5, 5));
        Flower flower0 = new Flower();
        Flower flower1 = new Flower();
        Rock rock0 = new Rock();
        // NW flower returned
        flower0.putSelfInGrid(gr, new Location(4, 4));
        // N flower not returned
        flower1.putSelfInGrid(gr, new Location(4, 5));
        rock0.putSelfInGrid(gr, new Location(4, 6));

        ArrayList<Actor> actors0 = test0.getActors();
        assertEquals(actors0.size(), 1);
        assertEquals(actors0.get(0).getLocation(), new Location(4, 4));
        test0.removeSelfFromGrid();
        flower0.removeSelfFromGrid();
        flower1.removeSelfFromGrid();
        rock0.removeSelfFromGrid();
    }

    @Test
    public void testProcessActors() {
        PlayBoy test1 = new PlayBoy(null);
        test1.putSelfInGrid(gr, new Location(5, 5));
        Flower flower2 = new Flower();
        Flower flower3 = new Flower();
        // NW flower collected
        flower2.putSelfInGrid(gr, new Location(4, 4));
        // NE flower collected
        flower3.putSelfInGrid(gr, new Location(4, 6));
        
        Color colorBefore = test1.getColor();
        int flowerBefore = test1.getFlower();
        int redBefore = (int) (colorBefore.getRed() * (1 - DARKENING_FACTOR));
        int greenBefore = (int) (colorBefore.getGreen() * (1 - DARKENING_FACTOR));
        int blueBefore = (int) (colorBefore.getBlue() * (1 - DARKENING_FACTOR));

        ArrayList<Actor> actors1 = test1.getActors();
        test1.processActors(actors1);

        Color colorAfter = test1.getColor();
        int redAfter = colorAfter.getRed();
        int greenAfter = colorAfter.getGreen();
        int blueAfter = colorAfter.getBlue();
        int flowerAfter = test1.getFlower();

        assertEquals(flowerAfter, flowerBefore + 2);
        assertEquals(redAfter, (int) ( redBefore * (1 - DARKENING_FACTOR) )) ;
        assertEquals(greenAfter, (int) ( greenBefore * (1 - DARKENING_FACTOR) ));
        assertEquals(blueAfter, (int) ( blueBefore * (1 - DARKENING_FACTOR) ));

        test1.removeSelfFromGrid();
    }

    @Test
    public void testGetMoveLocations() {
        PlayBoy test2 = new PlayBoy(null);
        test2.putSelfInGrid(gr, new Location(5, 5));

        boolean ok = true;
        Actor a;
        ArrayList<Location> locs0 = test2.getMoveLocations();
        for ( Location loc : locs0 ) {
            a = gr.get(loc);
            if ( !(a == null || a instanceof Flower) ) {
                ok = false;
            }
        }
        assertEquals(ok, true);
    }

    @Test
    public void testMakeMove() {
        PlayBoy test3 = new PlayBoy(null);
        Location center = new Location(5, 5);
        test3.putSelfInGrid(gr, center);
        Location loc = new Location(1, 1);
        Flower flower1 = new Flower();
        Flower flower2 = new Flower();
        Flower flower3 = new Flower();
        Flower flower4 = new Flower();
        flower1.putSelfInGrid(gr, new Location(4, 4));
        flower2.putSelfInGrid(gr, new Location(6, 6));
        flower3.putSelfInGrid(gr, new Location(4, 6));
        flower4.putSelfInGrid(gr, new Location(6, 4));
        ArrayList<Actor> actors3 = test3.getActors();
        test3.processActors(actors3);
        test3.makeMove(loc);
        assertEquals(test3.getLocation(), loc);
        assertEquals(test3.getChildren(), 0);

        test3.makeMove(center);
        flower1 = new Flower();
        flower2 = new Flower();
        flower3 = new Flower();
        flower4 = new Flower();
        flower1.putSelfInGrid(gr, new Location(4, 4));
        flower2.putSelfInGrid(gr, new Location(6, 6));
        flower3.putSelfInGrid(gr, new Location(4, 6));
        flower4.putSelfInGrid(gr, new Location(6, 4));
        actors3 = test3.getActors();
        test3.processActors(actors3);
        test3.makeMove(loc);
        assertEquals(test3.getChildren(), 0);

        test3.makeMove(center);
        flower1 = new Flower();
        flower2 = new Flower();
        flower3 = new Flower();
        flower4 = new Flower();
        flower1.putSelfInGrid(gr, new Location(4, 4));
        flower2.putSelfInGrid(gr, new Location(6, 6));
        flower3.putSelfInGrid(gr, new Location(4, 6));
        flower4.putSelfInGrid(gr, new Location(6, 4));
        actors3 = test3.getActors();
        test3.processActors(actors3);
        test3.makeMove(loc);
        assertEquals(test3.getChildren(), 1);
    }

}
