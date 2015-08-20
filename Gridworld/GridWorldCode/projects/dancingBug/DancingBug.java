/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> traces out a dancing pattern of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int steps;
    private int[] moveArray;

    /**
     * Constructs a box bug that traces a dancing pattern of a given side length
     * @param length the side length
     */
    public DancingBug(int[] array)
    {
        steps = 0;
        moveArray = array;
    }

    /**
     * Moves to the next location of the dancing pattern.
     */
    public void act()
    {   
        // Turn the bug according to the array.
        for ( int i = 0; i < moveArray[steps]; i++ ) {
            turn();
        }
        steps++;
        // If reaching the end of the array, back to the front.
        if ( steps == moveArray.length )
        {
            steps -= moveArray.length;
        }
        // Bug act.
        if ( canMove() )
        {
            move();
        }
        else
        {
            turn();
        }
    }
}
