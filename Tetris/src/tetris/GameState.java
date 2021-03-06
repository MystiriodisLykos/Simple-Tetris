/*
 * Copyright (C) 2017
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tetris;

import tetris.Skins.Skin;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Holds all the variables and methods used in the game and that make it go.
 *
 * @author Team1
 */
public class GameState {

    // Array to keep track of what code has run in this class
    public static boolean[] coverage = new boolean[35];

    // Enum of the posible states
    public enum State {
        falling, locking, animation, paused
    }

    public int score = 0;
    public int level = 0;
    
    public double gravity = 10;

    public State state;
    public int untilLock = 10;  // Time until a Tetromino is locked
    // Array of lines that need to be deleted
    public int deletedLines[] = {-1, -1, -1, -1};
    public GamePlay gp;
    public Skin s;
    public Block[][] stack = new Block[20][10];
    public Tetromino currentTet;

    /**
     * Constructor for GameState
     *
     * @param gp GamePlay object for the GameState
     * @param s Skin object for the GameState
     */
    public GameState(GamePlay gp, Skin s) {
        coverage[0] = true;
        this.gp = gp;
        this.s = s;
        gp.nextTet(this);  // Produce the first Tetromino
        this.state = State.falling;  // Set the state to falling
    }

    /**
     * This drops or locks the Tetromino based on the state. If the state is
     * falling then only drop is called If the state is locking one of two
     * things happens. 1) The Tetronimo is not ready to lock so the untilLock is
     * decremented 2) The Tetromino is ready to lock so it locks the Tetromino
     * and sets the state to animation is it results in lines being deleted
     */
    public void tick() {
        coverage[1] = true;

        if (state == State.falling) {
            coverage[2] = true;
            drop();
        } else if (state == State.locking) {
            coverage[3] = true;
            if (untilLock != 0) {
                coverage[4] = true;
                untilLock--;
            } else {
                coverage[5] = true;
                untilLock = 20;
                lock();
                boolean deleted = deleteLines();
                if (deleted) {
                    coverage[6] = true;
                    state = State.animation;
                } else {
                    coverage[7] = true;
                    state = State.falling;
                    gp.nextTet(this);
                }
            }
        }
    }

    /**
     * Simply delegates to the Tetromino's drop and sets the state if the
     * Tetromino's drop returns true
     */
    public void drop() {
        coverage[8] = true;
        if (currentTet.drop(this)) {
            coverage[9] = true;
            state = State.locking;
        }
    }
    
    public void drop(double gravity) {
        coverage[10] = true;
        double old = this.gravity;
        this.gravity = gravity;
        drop();
        this.gravity = old;
    }

    /**
     * Breaks apart the Tetromino into a set of blocks that are then placed into
     * the stack
     */
    public void lock() {
        coverage[11] = true;

        // Local grid coordinates of the blocks in the current rotation
        P2[] rot = currentTet.rotations[currentTet.rotationState];
        // Global position of the Tetromino in grid coordinates
        P2 gridP = currentTet.convPoint();

        for (int i = 0; i < rot.length; i++) {
            coverage[12] = true;
            // Global X, Y coordinate of the current block
            int x = (int) (rot[i].x + gridP.x);
            int y = (int) (rot[i].y + gridP.y);
            stack[y][x] = new Block(x, y, currentTet.c);
        }
    }

    /**
     * Finds all of the full rows in the stack and adds the indeces of those
     * rows to the deletedLines array and tells the method that called it if
     * lines need to be deleted or not.
     *
     * @return Boolean to say if lines need to be deleted or not
     */
    public boolean deleteLines() {
        coverage[14] = true;

        resetLines();  // Resets the deletedLines array to -1

        int index = 0;  // Index of deletedLines

        for (int i = 0; i < stack.length; i++) {
            coverage[15] = true;
            int numBlk = 0;  // Blocks in the row
            for (int j = 0; j < stack[i].length; j++) {
                coverage[16] = true;
                if (stack[i][j] != null) {
                    coverage[17] = true;
                    numBlk++;
                }
            }
            if (numBlk == 10) {
                coverage[18] = true;
                deletedLines[index] = i;
                index++;
            }
        }
        if (index > 0) {
            coverage[19] = true;
            s.background(this);
            update(index);  // Updates the score based on the lines cleared
            return true;
        }
        coverage[20] = true;
        return false;
    }

    /**
     * Delegates to the GamePlay gp update method.
     *
     * @param lines Number of Lines cleared
     */
    public void update(int lines) {
        coverage[21] = true;
        gp.update(this, lines);
    }

    /**
     * Delegates the Tetromino currentTet horizontalMove method
     *
     * @param dirc Direction to move False: Left, True: Right
     */
    public void horizontalMove(boolean dirc) {
        coverage[22] = true;
        currentTet.horizontalMove(dirc, this);
    }

    /**
     * Delegates to the Tetromino currentTet rotate method
     *
     * @param dirc Direction to rotate False: counter-clockwise, True: clockwise
     */
    public void rotate(boolean dirc) {
        coverage[23] = true;
        currentTet.rotate(dirc, this);
    }

    /**
     * Delegates to the Skin paint and animate methods. In the event that
     * animate returns true then move all the blocks down, create the next
     * Tetromino and set the state back to falling.
     *
     * @param g The Graphics context
     */
    public void paint(Graphics g) {
        coverage[24] = true;
        s.paint(this, g);

        if (state == State.animation) {
            coverage[25] = true;
            if (s.animate(this, g)) {
                coverage[26] = true;
                for (int i : deletedLines) {
                    coverage[27] = true;
                    if (i != -1) {
                        coverage[28] = true;
                        for (int z = 0; z < 10; z++) {
                            coverage[29] = true;
                            stack[i][z] = null;
                        }
                        for (int k = i-1; k >= 0; k--) {
                            coverage[30] = true;
                            for (int j = 0; j < 10; j++) {
                                coverage[31] = true;
                                if (stack[k][j] != null) {
                                    coverage[32] = true;
                                    stack[k][j].y += 1;
                                    stack[k+1][j] = stack[k][j];
                                    stack[k][j] = null;
                                }
                            }
                        }
                    }
                }
                gp.nextTet(this);
                state = State.falling;
            }
        }
    }

    /**
     * Re-initialized deletedLines to -1
     */
    public void resetLines() {
        coverage[33] = true;
        for (int i = 0; i < deletedLines.length; i++) {
            coverage[34] = true;
            deletedLines[i] = -1;
        }
    }

    /**
     * Test function for filling the well
     */
//    public void testWell() {
//        coverage[35] = true;
//        for (int i = stack.length-1; i > 10; i--) {
//            coverage[36] = true;
//            for (int j = 0; j < stack[i].length; j++) {
//                coverage[37] = true;
//                stack[i][j] = new Block(j, i, Color.red);
//            }
//        }
//    }

    /**
     * Test function for testing animation
     */
//    public void testAnim() {
//        coverage[38] = true;
//        deletedLines[0] = 3;
//        state = State.animation;
//    }
}
