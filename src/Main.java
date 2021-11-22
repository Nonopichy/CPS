import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;

public class Main extends Canvas implements Runnable, MouseListener {
    public static final int width = 150, height = 250, click = 0, batter = 0, button = 1, por = 1, last_click = 0;
    public Main(){
        Dimension d = new Dimension(width,height);
        this.setPreferredSize(d);
        addMouseListener(this);
    }
    public static void main(String args[]){
        Main main = new Main();
        JFrame jframe = new JFrame("CPS");
        jframe.add(main);
        jframe.setLocationRelativeTo(null);
        jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);

        new Thread(main).start();
        new Thread(new ClickRest()).start();
    }
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        final Graphics g = bs.getDrawGraphics();
        draw(g);
        bs.show();
    }
    private final Color a = new Color(240, 248, 255);
    private final Color b = new Color(107, 124, 130);
    private final Color c = new Color(100, 100, 100);
    public void draw(final Graphics g){
        //CPS
        g.setColor(a);
        g.fillRect(0, 0, width, height);
        g.setColor(b);
        g.setFont(new Font("Arial", Font.PLAIN, 23));
        g.drawString("CPS: " + click, width / 2 - 40, 20);
        //LAST
        g.setColor(b);
        g.setFont(new Font("Arial", Font.PLAIN, 23));
        g.drawString("Last: " + last_click, width / 2 - 40, 40);
        //BATTER
        g.setColor(b);
        g.setFont(new Font("Arial", Font.PLAIN, 23));
        g.drawString("Batter: " + batter, width / 2 - 40, 60);
        //BUTTON
        g.setColor(Color.RED);
        g.fillRect(0, 230, 30, 20);
        g.setColor(c);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        if(button==1){
            g.drawString("Left", 0, 245);
        else 
            g.drawString("Right", 0, 245);
        //CONTADOR
        g.setColor(Color.GREEN);
        g.fillRect(120, 230, 30, 20);
        g.setColor(c);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Por: "+por, 120, 245);
        //SUBTITLE
        g.setColor(c);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("By NoRois", 45, 245);
    }
    public void update(){
        if(click>batter)batter=click;
    }
    public void run() {
        while(true){
            render();
            update();
            try{ Thread.sleep(1000/60); } 
                catch (InterruptedException e) {e.printStackTrace();}
        }
    }
    public Boolean isWithin(int x, int y, int x1, int x2, int y1, int y2){
        return(x >= x1 && x <= x2 && y <= y1 && y >= y2) 
    }
    public void mouseClicked(MouseEvent e) {}
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
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
