/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 *
 * @author Tomi
 */
public class Dino extends JComponent {

    String path;
    BufferedImage img;
    int jumpHeight;
    int boundX;
    int boundY;
    int ground = 390;
    int fallRate = 10;

    public Dino(String path, int boundX, int boundY, int jumpHeight) {
        this.path = path;
        this.boundX = boundX;
        this.boundY = boundY;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public void paint(Graphics g) {
        img = ImageLoader.loadImage(path);
        g.drawImage(img, 0, 0, 150, 150, null);
    }

    public int jump() {
        return boundY -= jumpHeight;

    }

    public int fall() {
        if (boundY <= ground) {
            return boundY += fallRate;
        } else {
            return boundY;
        }
    }
    
    public void setBoundY(int a){
        this.boundY = a;
    }

}
