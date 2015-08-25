package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

import java.util.*;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public boolean isStart = true;
	public Stack<Location> crossLocation = new Stack<Location>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			if ( isStart ) {
				crossLocation.push(getLocation());
				isStart = false;
			}

			move();
			//increase step count when move 
			stepCount++;
		} 
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();

		int d = Location.NORTH;
        for ( int i = 0; i < 4; i++ ) {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if ( gr.isValid(neighborLoc) ) {
            	Actor a = gr.get(neighborLoc);
            	if ( a == null ||
            		( a instanceof Rock && a.getColor().equals(Color.RED) ) ) {
            		valid.add(neighborLoc);
            	}
            } 
                
            d = d + Location.RIGHT;
        }
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        } else {
        	return true;
        }
	}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;

		Location loc = crossLocation.peek();
		//System.out.println(loc.toString());
		ArrayList<Location> validLoc = getValid(loc);
		//System.out.println(validLoc.size());

		if ( validLoc.size() == 0 ) {
			loc = crossLocation.pop();
			Location newLoc = crossLocation.peek();
			//System.out.println(newLoc.toString());
			int dir = loc.getDirectionToward(newLoc);
			setDirection(dir);
			moveTo(newLoc);
			Flower flower = new Flower(getColor());
        	flower.putSelfInGrid(gr, loc);
		} else {
			for ( Location locs : validLoc ) {
				Actor a = gr.get(locs);
				if ( a instanceof Rock && a.getColor().equals(Color.RED) ) {
					isEnd = true;
					break;
				}
			}

			if ( isEnd == false ) {
				Random random = new Random();
				int index = random.nextInt(validLoc.size());
				Location newLoc = validLoc.get(index);
				crossLocation.push(newLoc);
				int dir = loc.getDirectionToward(newLoc);
				setDirection(dir);
				moveTo(newLoc);
				Flower flower = new Flower(getColor());
        		flower.putSelfInGrid(gr, loc);
			}
		}

		//System.out.println(crossLocation.peek().toString());
	}
}
