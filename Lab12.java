import java.util.Arrays;

class MyThread implements Runnable{
    Thread thrd;
    float[] arr;
    MyThread(String name,float[] arr){
        thrd = new Thread(this,name);
        this.arr = arr;
        thrd.start();
    }
    @Override
    public void run() {
        System.out.println(thrd.getName()+ "  запуск");
        try {
            Arrays.fill(arr,1);
            for (int i = 0; i < arr.length; i++) {
                arr[i]=(float)(arr[i]* Math.sin(0.2f + i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f +i/2));
            }
        } catch (Exception exc){
            System.out.println(thrd.getName()+ " - прерван");
        }
        System.out.println(thrd.getName()+ " - завершен");
    }
}
public class Lab12 {
    public static void main(String[] args) throws InterruptedException {
        long a = System.currentTimeMillis();
        int size = 10000000;
        int halfSize = size /2;
        float[] arr =new float[size];
        float[] firstArr = new float[halfSize];
        float[] secondArr= new float[halfSize];
        System.arraycopy(arr,0,firstArr,0,halfSize);
        System.arraycopy(arr,0,secondArr,0,halfSize);
        MyThread mt1 = new MyThread("1 поток",firstArr);
        MyThread mt2 = new MyThread("2 поток",secondArr);
        System.arraycopy(firstArr,0,arr,0,halfSize);
        System.arraycopy(secondArr,0,arr,halfSize,halfSize);
        mt1.thrd.join();
        mt2.thrd.join();
        System.out.println("Время работы метода:"+(System.currentTimeMillis()-a));
        System.out.println("Завершение основного потока");
        System.out.println(firstArr.length);
        System.out.println(secondArr.length);
        System.out.println(arr.length);
    }
}