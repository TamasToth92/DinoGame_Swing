
package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public class ImageDrawer extends JComponent{
    
    String path;
    BufferedImage img;

    public ImageDrawer(String path) {
        this.path = path;
    }
    
    

    @Override
    public void paint(Graphics g) {
        
        img = ImageLoader.loadImage(path);
        g.drawImage(img, 1, 1, 120, 120, null);
        
    }
    
    
    
}
