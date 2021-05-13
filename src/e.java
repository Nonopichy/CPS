import java.util.concurrent.TimeUnit;

public class e implements Runnable{
    public void run() {
        while(true){
            try{
                TimeUnit.SECONDS.sleep(Main.por);
                //acada 1 segundo reseta para 0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Main.click!=0)
                Main.last_click=Main.click;

            Main.click = 0;
            //vo fazer oq c disse
        }
    }
}
