/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import tetris.Skins.Skin;
import tetris.Skins.Fancy;

/**
 *
 * @author JesseRichmond
 */
public class InputKeyEvent extends KeyAdapter {
    
    public static boolean coverage[] = new boolean[9];

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case (KeyEvent.VK_LEFT):
                coverage[0] = true;
                gui.gs.horizontalMove(false);
                break;
            case (KeyEvent.VK_RIGHT):
                coverage[1] = true;
                gui.gs.horizontalMove(true);
                break;
            case (KeyEvent.VK_A):
                coverage[2] = true;
                gui.gs.rotate(false);
                break;
            case (KeyEvent.VK_D):
                coverage[3] = true;
                gui.gs.rotate(true);
                break;
            case (KeyEvent.VK_SPACE):
                coverage[4] = true;
                gui.gs.drop(1000);
                gui.gs.reset();
                break;
            case (KeyEvent.VK_R):
                coverage[5] = true;
                gui.gs.stack = new Block[20][10];
                break;
            case (KeyEvent.VK_T):
                coverage[6] = true;
                if (gui.gs.s instanceof Fancy) {
                    coverage[7] = true;
                    gui.gs.s = new Skin();
                }
                else if (gui.gs.s instanceof Skin) {
                    coverage[8] = true;
                    gui.gs.s = new Fancy();
                }
        }
    }
}
