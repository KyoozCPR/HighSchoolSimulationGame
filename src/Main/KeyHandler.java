package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class KeyHandler implements KeyListener {
    public boolean upPressed;
    public boolean downPressed;
    public boolean rightPressed;
    public boolean leftPressed;
    int keyCode;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();

        if (keyCode == VK_DOWN || keyCode == VK_S)
            downPressed = true;
        else if (keyCode == VK_UP || keyCode == VK_W) {
            upPressed = true;
        }
        else if (keyCode == VK_LEFT || keyCode == VK_A){
            leftPressed = true;
        }
        else if (keyCode == VK_RIGHT || keyCode == VK_D) {
            rightPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCode = e.getKeyCode();

        if (keyCode == VK_DOWN || keyCode == VK_S)
            downPressed = false;
        else if (keyCode == VK_UP || keyCode == VK_W) {
            upPressed = false;
        }
        else if (keyCode == VK_LEFT || keyCode == VK_A){
            leftPressed = false;
        }
        else if (keyCode == VK_RIGHT || keyCode == VK_D) {
            rightPressed = false;
        }
    }
}
