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
package tetris.Skins;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import tetris.Block;
import tetris.GameState;
import tetris.P2;
import tetris.Skins.Background.Background;
import tetris.Skins.Background.PlainBackground;
import tetris.Tetromino;

/**
 * The methods related to painting that can be changed between different
 * versions of Tetris have been moved into this class which acts a base class
 * for a Templet of Skins
 *
 * @author Team4
 */
public class Skin {
    // TODO: add coverage to fance
    // TODO: change coverage to a list
    public static boolean[] coverage = new boolean[29];
    
    public int animationFrame = 20;  // Number of frames for an animation
    public Background b;
    
    public Skin() {
        coverage[0] = true;
        this.b = new PlainBackground();
    }

    /**
     * paints the row and current Tetromino inside of the GameState gs
     * This base version draws the the blocks with a 1 pixel border around it.
     *
     * @param gs GameState
     * @param g Graphics context
     */
    public void paint(GameState gs, Graphics g) {
        coverage[1] = true;
        b.paint(gs, (Graphics2D) g);
        Tetromino c = gs.currentTet;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        // Loop through blocks inside the stack
        for (Block[] row : gs.stack) {
            coverage[2] = true;
            for (Block b : row) {
                coverage[3] = true;
                if (b != null) {
                    coverage[4] = true;
                    // If b is not null draw it
                    g2.setColor(b.c);  // Sets color from Block b
                    // Draws a 26 x 26 rectangle Block b's X and Y
                    g2.fillRect(b.x * 26, b.y * 26, 26, 26);
                }
            }
        }
        /* 
            This loop finds the grab point for each of the blocks in the 
            Tetromino and draws a square and border based of that.
         */
        for (int k = 0; k < 4; k++) {
            coverage[5] = true;
            g2.setColor(c.c);  // Sets the color to the Tetromino color
            // Finds the grab point
            P2 tetBlockGrab = c.current.add((c.rotations[c.rotationState][k]).scale(26));
            // Draws a rectangle based off the block size
            g2.fillRect((int) tetBlockGrab.x, (int) tetBlockGrab.y, 26, 26);
        }
    }

    /**
     * This is the animation of lines disappearing and is called every frame The
     * boolean that is returned is to tell the function that calls animate if
     * the animation is done or not.
     * <p>
     * This basic version just blinks the rows black and white
     *
     * @param gs The current GameState
     * @param g The Graphics context
     * @return The boolean to say if the animation is done or not
     */
    public boolean animate(GameState gs, Graphics g) {
        coverage[6] = true;
        if (animationFrame <= 0) {
            coverage[7] = true;
            // If the animation is done return true
            animationFrame = 20;
            return true;
        }

        int[] lines = gs.deletedLines;  // Indecies of deleted lines
        if (animationFrame % 10 <= 5) {
            coverage[8] = true;
            g.setColor(Color.WHITE);
        } else {
            coverage[9] = true;
            g.setColor(Color.BLACK);
        }

        for (int i = 0; i < lines.length; i++) {
            coverage[10] = true;
            if (lines[i] != -1) {
                coverage[11] = true;
                g.fillRect(0, lines[i] * 26, 260, 26);
            }
        }
        animationFrame--;
        return false;
    }

    /**
     * Determines the background based on the level inside of GameState gs
     *
     * @param gs The current GameState
     */
    public void background(GameState gs) {
        coverage[12] = true;
        
        int del = 0;
        for (int i: gs.deletedLines) {
            coverage[13] = true;
            if (i != -1){
                coverage[14] = true;
                del++;
            }
        }
        
        if (Math.floor(gs.level/10f) < 
                Math.floor((gs.level+del)/10f)) {
            coverage[15] = true;
            b.change();
        }
    }
}