package win.smile.enumdemo;

import java.util.concurrent.TimeUnit;

/**
 * 线程测试
 * @author weifw
 * @date 2018-12-23-14:45
 */
public class ThreadTest {

    private Integer queneNum = 1;


    public void consumOne() throws InterruptedException{

        for (int i = 0; i < 100000; i++) {
            if(queneNum == 1){
                System.out.println("---------one---"+i);
                Thread.sleep(1000);
            }
        }
    }

  public void consumTwo() throws InterruptedException{
      for (int i = 0; i < 100000; i++) {
          if(queneNum == 2){
              System.out.println("---------two---"+i);
              Thread.sleep(1000);
          }
      }

    }

  public void consumThree() throws InterruptedException {

      for (int i = 0; i < 100000; i++) {
          if(queneNum==3){
              System.out.println("---------three---"+i);
              Thread.sleep(1000);
          }
      }
    }


    public void consum() throws InterruptedException {
        while (true){

             consumOne();
             consumTwo();
             consumThree();

            TimeUnit.SECONDS.sleep(10);


        }
    }


}
