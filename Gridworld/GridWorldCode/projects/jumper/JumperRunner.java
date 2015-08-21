import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * This class runs a world that contains jumper. <br />
 */
public final class JumperRunner
{
    private JumperRunner() {}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Jumper bob = new Jumper();
        bob.setColor(Color.BLUE);
        world.add(new Location(7, 8), alice);
        world.add(new Location(5, 5), bob);
        world.add(new Rock());
        world.show();
    }
}
