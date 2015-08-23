package mygrid;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
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
    //world.addGridClass("SparseBoundedGrid2");
    //world.addGridClass("SparseBoundedGrid3");
    //world.addGridClass("UnboundedGrid2");
    world.add(new Location(2, 2), new Critter());
    world.add(new Location(2, 3), new Critter());
    System.out.println(world.getGrid().remove(new Location(2, 2)));
    world.show();
  }
}