package display;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
/**
 *
 * @author Aprae
 */
public class Display extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    private final Dimension size = new Dimension(1000, 600);
    
    public Display(){
        this.setting();
        this.getContentPane().add(new Game());
    }
    
    public void setting(){
        this.setTitle("Don't touch me! CUCUMBERS!");
        this.setSize(size);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(800, 300);
        this.setVisible(true);
        this.setResizable(false);
    }
    
    private void removeContent(){
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }
    
    public void endGame(long point){
        removeContent();
        this.getContentPane().add(new Menu(point, this));
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Replay")){
            removeContent();
            Game game = new Game();
            this.getContentPane().add(game);
            game.requestFocus();
        }
    }
}