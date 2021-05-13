import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;

public class Main extends
        Canvas implements Runnable, MouseListener {
    public static int button = 1;
    public static int por = 1;
    public static int last_click = 0;
    public static final int width = 150, height = 250;
    public Main(){
        Dimension d = new Dimension(width,height);
        this.setPreferredSize(d);
        addMouseListener(this);
    }

    public static int click = 0;
    public static int batter = 0;
    public static void main(String args[]){
        Main main = new Main();
        JFrame jframe = new JFrame("CPS");
        jframe.add(main);
        jframe.setLocationRelativeTo(null);
        jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);

      //  JButton button = new JButton("Click Here..!");
       // button.setBounds(width/2,height/2,1,2); /*Distance from left,
        //              Distance from top,length of button, height of button*/
       // jframe.add(button);


      //  JButton b = new JButton("Button");
      //  jframe.setLayout(null);
      //  jframe.add(b);
     //   b.setSize(400,400);
       // b.setVisible(true);

        new Thread(main).start();
        new Thread(new e()).start();
    }
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(240, 248, 255));
        g.fillRect(0, 0, width, height);
        g.setColor(new Color(107, 124, 130));
        g.setFont(new Font("Arial", Font.PLAIN, 23));
        g.drawString("CPS: " + click, width / 2 - 40, 20);
        //
        g.setColor(new Color(107, 124, 130));
        g.setFont(new Font("Arial", Font.PLAIN, 23));
        g.drawString("Last: " + last_click, width / 2 - 40, 40);
        //
        g.setColor(new Color(107, 124, 130));
        g.setFont(new Font("Arial", Font.PLAIN, 23));
        g.drawString("Batter: " + batter, width / 2 - 40, 60);
        //
        g.setColor(Color.RED);
        g.fillRect(0, 230, 30, 20);
        g.setColor(new Color(100, 100, 100));
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        if(button==1){
            g.drawString("Left", 0, 245);
        } else if (button == 3){
            g.drawString("Right", 0, 245);
        }
        g.setColor(Color.GREEN);
        g.fillRect(120, 230, 30, 20);
        g.setColor(new Color(100, 100, 100));
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Por: "+por, 120, 245);

        g.setColor(new Color(100, 100, 100));
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("By NoRois", 45, 245);

        bs.show();
    }
    public void update(){
        if(click>batter)batter=click;
    }
    public void run() {
        while(true){
            render();
            update();
            try{
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public Boolean isWithin(int x, int y, int x1, int x2, int y1, int y2){
        if(x >= x1 && x <= x2) {
            if (y <= y1 && y >= y2) {
                return true;
            }
        }
        return false;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if(isWithin(e.getX(), e.getY(), 121, 149, 248, 229)) {
            if(e.getButton()==1){
                if(por<5)
                por++;
            } else {
                if(por>1)
                por--;
            }
            return;
        }
        if(isWithin(e.getX(), e.getY(), 0, 30, 248, 220)) {
            if (button == 1)
                button = 3;
             else
                button = 1;
             return;
        }
        if (e.getButton() == button)
            click++;

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
