package mygrid;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.util.*;

/**
* This class runs a world with additional grid choices.
*/
public class SparseGridRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("mygrid.SparseBoundedGrid");
        world.addGridClass("mygrid.SparseBoundedGrid2");
        //world.addGridClass("SparseBoundedGrid3");
        world.addGridClass("mygrid.UnboundedGrid2");

        Grid gr = world.getGrid();
        world.add(new Location(2, 2), new Critter());
        world.add(new Location(2, 3), new Rock());
        Critter c0 = new Critter();
        c0.putSelfInGrid(gr, new Location(2, 4));
        Critter c1 = new Critter();
        c1.putSelfInGrid(gr, new Location(2, 5));

        c0.removeSelfFromGrid();
        c1.moveTo(new Location(2, 6));

        world.show();
    }
}