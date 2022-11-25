package display;

import Element.EleButton;
import Element.EleLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Aprae
 */
public class Menu extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
    public long point;
    
    public Menu(){
        
    }
    
    public Menu(long point , ActionListener main){
        try{
            //--------page after game over
            this.point = point;
            this.setBackground(new Color(225, 140, 155));
            this.setBounds(0, 0, 1000, 600);
            this.setFocusable(true);
            this.setLayout(null);
            
            EleLabel status = new EleLabel("Game over", 40, 385, 100, 250, 100); //String title, int size, int x, int y, int w, int h
            status.setForeground(Color.black);
            
            EleLabel showPoint = new EleLabel("Total : " + this.point, 30, 410, 180, 200, 100);
            showPoint.setForeground(Color.black);
            
            EleButton replay = new EleButton("Replay", 25, 270, 300, 200, 50); //("Replay", 15, 380, 300, 200, 50);
            replay.addActionListener(main);
            
            EleButton exit = new EleButton("Exit", 25, 480, 300, 200, 50); //("Exit", 15, 380, 300, 200, 50);
            exit.addActionListener(this);
            
            this.add(showPoint);
            this.add(status);
            this.add(replay);
            this.add(exit);
        }catch(Exception e){
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        System.exit(0);   
    }
}
