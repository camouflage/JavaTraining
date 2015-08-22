import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.gui.*;
import info.gridworld.world.*;

public final class PlayBoyRunner {
    private PlayBoyRunner() {
    }
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(0, 0), new Bug());
        world.add(new Location(2, 8), new Rock());
        world.add(new Location(4, 7), new Rock());
        world.add(new Location(2, 3), new PlayBoy(null));
        world.show();
    }
}
