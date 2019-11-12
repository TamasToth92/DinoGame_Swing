
package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;


public class GameOver extends JComponent{
    String path;
    BufferedImage img;

    public GameOver(String path) {
        this.path = path;
    }
    
    @Override
    public void paint(Graphics g) {
        img = ImageLoader.loadImage(path);
        g.drawImage(img, 0, 0, 300, 300, null);
    }
}
