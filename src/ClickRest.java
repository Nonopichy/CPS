import java.util.concurrent.TimeUnit;

public class ClickRest implements Runnable{
    public void run() {
        while(true){
            try{
                TimeUnit.SECONDS.sleep(Main.por);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Main.click!=0)
                Main.last_click=Main.click;
            Main.click = 0;
        }
    }
}
