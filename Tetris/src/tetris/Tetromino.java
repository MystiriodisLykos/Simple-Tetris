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

import java.awt.Color;

/**
 * Tetromino represents the set of blocks while they are falling
 *
 * @author Team2
 */
public class Tetromino {

    // Array to keep track of what code has run in this class
    public static boolean[] coverage = new boolean[37];

    // This is a list of possible rotation states (grid coordinates)
    public P2[][] rotations;
    public int rotationState = 0;
    public P2 current;  // Position of the Tetromino (screen coordinates)
    public Color c;

    /**
     * Simple constructor for a Tetromino
     *
     * @param rotations Describes the possible rotations
     * @param start The starting position of the Tetromino
     * @param c The Color of the Tetromino
     */
    public Tetromino(P2[][] rotations, P2 start, Color c) {
        coverage[0] = true;
        this.rotations = rotations;
        this.current = start;
        this.c = c;
    }

    /**
     * Static Factory to make an I piece
     *
     * @param start The starting position of the Tetromino
     * @param c The Color of the Tetromino
     * @return An I Tetromino at position start and color c
     */
    public static Tetromino tetI(P2 start, Color c) {
        coverage[1] = true;
        // The possible rotations for an I piece
        P2[] pos1 = {new P2(-2, 0), new P2(-1, 0), new P2(0, 0), new P2(1, 0)};
        P2[] pos2 = {new P2(0, -2), new P2(0, -1), new P2(0, 0), new P2(0, 1)};
        P2[] pos3 = {new P2(-1, 0), new P2(0, 0), new P2(1, 0), new P2(2, 0)};
        P2[] pos4 = {new P2(0, -1), new P2(0, 0), new P2(0, 1), new P2(0, 2)};

        P2[][] tet = {pos1, pos2, pos3, pos4};

        return new Tetromino(tet, start, c);
    }

    /**
     * Static Factory to make a J piece
     *
     * @param start The starting position of the Tetromino
     * @param c The Color of the Tetromino
     * @return A J Tetromino at position start and color c
     */
    public static Tetromino tetJ(P2 start, Color c) {
        coverage[2] = true;
        // The possible rotations for a J piece
        P2[] pos1 = {new P2(-1, -1), new P2(-1, 0), new P2(0, 0), new P2(1, 0)};
        P2[] pos2 = {new P2(1, -1), new P2(0, -1), new P2(0, 0), new P2(0, 1)};
        P2[] pos3 = {new P2(-1, 0), new P2(0, 0), new P2(1, 0), new P2(1, 1)};
        P2[] pos4 = {new P2(-1, 1), new P2(0, 1), new P2(0, 0), new P2(0, -1)};

        P2[][] tet = {pos1, pos2, pos3, pos4};
        return new Tetromino(tet, start, c);
    }

    /**
     * Static Factory to make a L piece
     *
     * @param start The starting position of the Tetromino
     * @param c The Color of the Tetromino
     * @return A L Tetromino at position start and color c
     */
    public static Tetromino tetL(P2 start, Color c) {
        coverage[3] = true;
        // The possible rotations for a L piece
        P2[] pos1 = {new P2(-1, 0), new P2(0, 0), new P2(1, 0), new P2(1, -1)};
        P2[] pos2 = {new P2(0, -1), new P2(0, 0), new P2(0, 1), new P2(1, 1)};
        P2[] pos3 = {new P2(-1, 1), new P2(-1, 0), new P2(0, 0), new P2(1, 0)};
        P2[] pos4 = {new P2(-1, -1), new P2(0, -1), new P2(0, 0), new P2(0, 1)};

        P2[][] tet = {pos1, pos2, pos3, pos4};
        return new Tetromino(tet, start, c);
    }

    /**
     * Static Factory to make an O piece
     *
     * @param start The starting position of the Tetromino
     * @param c The Color of the Tetromino
     * @return An O Tetromino at position start and color c
     */
    public static Tetromino tetO(P2 start, Color c) {
        coverage[4] = true;
        // The possible rotation for an O piece
        P2[] pos1 = {new P2(0, 0), new P2(1, 0), new P2(0, 1), new P2(1, 1)};

        P2[][] tet = {pos1};
        return new Tetromino(tet, start, c);
    }

    /**
     * Static Factory to make a S piece
     *
     * @param start The starting position of the Tetromino
     * @param c The Color of the Tetromino
     * @return A S Tetromino at position start and color c
     */
    public static Tetromino tetS(P2 start, Color c) {
        coverage[5] = true;
        // The possible rotations for a S piece
        P2[] pos1 = {new P2(-1, 0), new P2(0, 0), new P2(0, -1), new P2(1, -1)};
        P2[] pos2 = {new P2(0, -1), new P2(0, 0), new P2(1, 0), new P2(1, 1)};
        P2[] pos3 = {new P2(-1, 1), new P2(0, 1), new P2(0, 0), new P2(1, 0)};
        P2[] pos4 = {new P2(-1, -1), new P2(-1, 0), new P2(0, 0), new P2(0, 1)};

        P2[][] tet = {pos1, pos2, pos3, pos4};
        return new Tetromino(tet, start, c);
    }

    /**
     * Static Factory to make a T piece
     *
     * @param start The starting position of the Tetromino
     * @param c The Color of the Tetromino
     * @return A T Tetromino at position start and color c
     */
    public static Tetromino tetT(P2 start, Color c) {
        coverage[6] = true;
        // The possible rotations for a T piece
        P2[] pos1 = {new P2(-1, 0), new P2(0, 0), new P2(0, -1), new P2(1, 0)};
        P2[] pos2 = {new P2(0, -1), new P2(0, 0), new P2(1, 0), new P2(0, 1)};
        P2[] pos3 = {new P2(-1, 0), new P2(0, 0), new P2(0, 1), new P2(1, 0)};
        P2[] pos4 = {new P2(-1, 0), new P2(0, -1), new P2(0, 0), new P2(0, 1)};

        P2[][] tet = {pos1, pos2, pos3, pos4};
        return new Tetromino(tet, start, c);
    }

    /**
     * Static Factory to make a Z piece
     *
     * @param start The starting position of the Tetromino
     * @param c The Color of the Tetromino
     * @return A Z Tetromino at position start and color c
     */
    public static Tetromino tetZ(P2 start, Color c) {
        coverage[7] = true;
        // The possible rotations for a Z piece
        P2[] pos1 = {new P2(-1, -1), new P2(0, -1), new P2(0, 0), new P2(1, 0)};
        P2[] pos2 = {new P2(0, 1), new P2(0, 0), new P2(1, 0), new P2(1, -1)};
        P2[] pos3 = {new P2(-1, 0), new P2(0, 0), new P2(0, 1), new P2(1, 1)};
        P2[] pos4 = {new P2(-1, 1), new P2(-1, 0), new P2(0, 0), new P2(0, -1)};

        P2[][] tet = {pos1, pos2, pos3, pos4};
        return new Tetromino(tet, start, c);
    }

    /**
     * Tries to drop the Tetromino by the gravity stored inside of GameState gs
     * If an intersection occurs the put the Tetromion down as far as possible
     * And return true if there was an intersection false if there wasn't
     *
     * @param gs The current GameState
     * @return Was there an intersection or not
     */
    public boolean drop(GameState gs) {
        coverage[8] = true;
        current.y += gs.gravity;
        P2 p = convPoint();
        if (this.intersect(gs)) {
            coverage[9] = true;
            current.y = p.y*Block.WIDTH;
            while(intersect(gs)) {
                coverage[10] = true;
                current.y -= Block.WIDTH;
            }
            return true;
        }

        coverage[11] = true;
        return false;
    }

    /**
     * Tries to move the Tetromino by the given direction d
     *
     * @param d Direction to move False: Left, True: Right
     * @param gs Current Game State
     */
    public void horizontalMove(boolean d, GameState gs) {
        coverage[12] = true;

        double prev = current.x;  // Previous x value
        if (d == false) {
            coverage[13] = true;
            current.x = current.x - Block.WIDTH;
        } else if (d == true) {
            coverage[14] = true;
            current.x = current.x + Block.WIDTH;
        }

        if (this.intersect(gs)) {
            // If there is an intersection
            coverage[15] = true;
            current.x = prev;
        } else {
            coverage[16] = true;
            gs.state = GameState.State.falling;
        }
    }

    /**
     * Tries to rotate the Tetromino by the given direction d
     *
     * @param d The direction to rotate False: counterclockwise, True: clockwise
     * @param gs The current GameState
     */
    public void rotate(boolean d, GameState gs) {
        coverage[17] = true;

        int prev = this.rotationState;  // Old rotationState

        if (d == false) {
            coverage[18] = true;
            this.rotationState = (this.rotationState - 1 + (this.rotations.length * 3)) % this.rotations.length;
        } else if (d == true) {
            coverage[19] = true;
            this.rotationState = (this.rotationState + 1) % this.rotations.length;
        }

        if (this.intersect(gs)) {
            // If there is an intersection
            coverage[20] = true;
            if (!kick(d, gs)) {
                coverage[21] = true;
                this.rotationState = prev;
            }
        } else {
            coverage[22] = true;
            gs.state = GameState.State.falling;
        }
        // change to conditional assignment ie: http://www.cafeaulait.org/course/week2/43.html
    }

    /**
     * Looks to see if any of the Blocks that make up the Tetromino are
     * occupying the same space as another Block in the Stack or is past the
     * walls or the bottom
     *
     * @param gs The current GameState
     * @return Is there an intersection or not
     */
    public boolean intersect(GameState gs) {
        coverage[23] = true;

        P2 p = convPoint();  // Convert current to grid coordinates

        for (P2 a : this.rotations[this.rotationState]) {
            coverage[24] = true;
            if (a.x + p.x < 0 || a.x + p.x >= 10 || a.y + p.y >= 20) {
                // Check for sides and bottom
                coverage[25] = true;
                return true;
            }
            if ((int) (a.y + p.y) < 0) {
                coverage[26] = true;
                return false;
            }
            if (gs.stack[(int) (a.y + p.y)][(int) (a.x + p.x)] != null) {
                // Check for an intsection with the Stack
                coverage[27] = true;
                return true;
            }
        }

        coverage[28] = true;

        return false;
    }

    /**
     * the kick method moves the Tetronimo to a kick space if it doesn't
     * intersect anything, if it does then it moves it back to the original
     *
     * @param b The direction to rotate False: counterclockwise, True: clockwise
     * @param s The current GameState
     * @return True = kick was successful; False = kick was NOT successful
     */
    public boolean kick(boolean b, GameState s) {
        coverage[29] = true;
        P2[] transitions = {new P2(0, 0), new P2(0, 0), new P2(0, 1), new P2(0, 2)};
        if (b) {
            coverage[30] = true;
            transitions[0] = new P2(-1, 0);
            transitions[1] = new P2(-1, -1);
        } else {
            coverage[31] = true;
            transitions[0] = new P2(1, 0);
            transitions[1] = new P2(1, -1);
        }

        for (P2 t : transitions) {
            coverage[32] = true;
            current = current.add(t.scale(Block.WIDTH));
            if (!intersect(s)) {
                coverage[33] = true;
                return true;
            } else {
                coverage[34] = true;
                current = current.add(t.scale(-Block.WIDTH));

            }
        }
        coverage[35] = true;
        return false;
    }

    /**
     * Converts the current point into grid coordinates non-destructively
     *
     * @return The new Point in grid coordinates
     */
    public P2 convPoint() {
        coverage[36] = true;
        int gsx = (int) (this.current.x / Block.WIDTH);
        int gsy = (int) Math.ceil(this.current.y / Block.HEIGHT);
        return new P2(gsx, gsy);
    }
}
