
package Character;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Aprae
 */
public class Wave {
    public int x, y;
    public int speed;
    Timer timeMove;
    public Wave(int x, int y, int speed, JPanel page){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.move(page);
    }
    
    public void move(JPanel page){
        this.timeMove = new Timer(speed, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(x <= 0){
                    x = (int)(1000+(300 + Math.random()*1000)); //check for wave is outframe then goto point X start
                }
                x -= 30;              
                page.repaint();
            }
        });
        this.timeMove.start();
    }
    
    public BufferedImage getImage(){
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResource("/img/cucumber1.png"));
            return image;
        }catch(IOException e){
        }
        return image;
    }
}

