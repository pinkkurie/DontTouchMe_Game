package Character;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Cat {
    
    public int x, y;
    public int health = 180;
    public static int speed = 120;
    
    public Cat(){
    }
    
    public Cat(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void jump(JPanel page){
        this.y -= speed;
        page.repaint();
        //--------fall
        Timer timer;
        timer = new Timer(450, new ActionListener(){ //time use for action in actionPerformed() and sent ActionListener() into inside
            @Override
            public void actionPerformed(ActionEvent e){
                y += speed;
                page.repaint();
            }
        });
        timer.setRepeats(false); //defend for infinitly loop 
        timer.start();
    }
    
    public BufferedImage getImage(){
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResource("/img/cat.png"));
            return image;
        }catch(IOException e){
        }
        return image;
    }
    
}
