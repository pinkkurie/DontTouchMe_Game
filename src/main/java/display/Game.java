package display;
import Character.*;
import Element.Element;
import Event.Event;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener{
    private static final long serialVersionUID = 1L;
    private static int speed = 50, catSize = 100, waveHeight = 45;
    private static int base = 400, xStart = 1000;
    private long point = 0, lastPress = 0;
    private Cat cat = new Cat(100, base-80);
    static Display display;
    //---------Wave size
    private Wave[] waveSet = makeWave(4); 
    //---------Cloud
    private Environment[] envSet = makeEnv(2, Environment.CLOUD);
    private Environment building = new Environment(xStart-100, base-150, this, Environment.BUILDING, 4);
    
    public Game(){
        this.setBounds(0,0,1000,600);
        this.addKeyListener(this);
	this.setLayout(null);
	this.setFocusable(true);
    }
    
    @Override
    public void paint(Graphics g){
        try{
            super.paint(g);
            Graphics2D g2 = (Graphics2D) g;
            this.drawBackground(g2);
            //---------score
            g2.setFont(Element.getFont(30));
            g2.setColor(Color.black);
            g2.drawString("Score : " + point, 750, 40);
            //---------cat
            g2.setColor(Color.red);
            drawCatHealth(g2);
            g2.drawImage(cat.getImage(), cat.x, cat.y, catSize, catSize, null);
            //---------wave
            for(Wave item : waveSet){
                drawWave(item, g2);
            }
            this.point += 1;         
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void drawBackground(Graphics2D g2) throws IOException{

        g2.drawImage(ImageIO.read(getClass().getResource("/img/cloud.png")), 0, 0, 2000, 1000, null);
        g2.drawImage(building.getImage(), building.x, building.y, 500, 200, null);
        g2.drawImage(ImageIO.read(getClass().getResource("/img/dir.png")), 0, base + 10, 2000, 220, null);
        for(Environment item : envSet){
            g2.drawImage(item.getImage(), item.x, item.y, 250, 160, null);
        }
    }
    
    private void drawCatHealth(Graphics2D g2){
        try{
            g2.drawImage(ImageIO.read(getClass().getResource("/img/heart.png")), 10, 20, 20, 20, null);
            g2.setStroke(new BasicStroke(18.0f));
            g2.setColor(new Color(241, 98, 69));
            g2.drawLine(60, 30, 60 + cat.health, 30);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(6.0f));
            g2.drawRect(50, 20, 200, 20);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private Wave[] makeWave(int size){
        Wave[] waveSet = new Wave[size];
        int far = 500;
        for(int i=0; i<size; i++){
                        // random y
            int randomBase = (Math.random() <= 0.5) ? 355 : 400;
            waveSet[i] = new Wave(xStart + far, randomBase, speed, this);
            far += 500;
        }
        return waveSet;
    }
    
    private Environment[] makeEnv(int size, int eType){
        Environment[] envSet = new Environment[size];
        int far = 0;
        for(int i=0; i<size; i++){
            envSet[i] = new Environment(xStart + far, 20, this, eType, 10);
            far += 600; //far += 600;
        }
        return envSet;
    }
    
    private void drawWave(Wave wave, Graphics2D g2){
        g2.drawImage(wave.getImage(), wave.x, (wave.y - waveHeight), 40, waveHeight + 10, null);
        if(Event.checkHit(cat, wave, catSize, waveHeight)){
            g2.setColor(new Color(241, 98, 69));
            g2.fillRect(0, 0, 1000, 1000);
            cat.health -= 20;
            if(cat.health <= 0){
                display.endGame(this.point);
                cat.health = new Cat().health;
                this.point = 0;
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //-----------
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(System.currentTimeMillis() - lastPress > 200){  //200 is enough ----> Double Jump
            if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode()==38){  
                    cat.jump(this);
                    lastPress = System.currentTimeMillis(); //keep current time in lastPress
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //-----------
    }
    
    public static void main(String[] arg){
        display = new Display();
    }
}
   
