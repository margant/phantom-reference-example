import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        System.loadLibrary("native");

        List<NativeProxy> proxyList = new ArrayList();

        for (int i = 0; i < 1000; i++) {
            // create a lot of native objects
            NativeProxy nativeProxy = Factory.create();
            Thread.sleep(10);
            if (i % 100 == 0) System.gc();
            System.out.print("Created " + i + " " + nativeProxy);
            proxyList.add(nativeProxy);
        }

        Thread.sleep(1000);
        System.gc();
        System.gc();
        System.gc();
        System.out.println("///////////// ");

        for (int i = 1000; i < 10000; i++) {
            // create a lot of native objects
            NativeProxy nativeProxy = Factory.create();
            Thread.sleep(10);
            if (i % 100 == 0) System.gc();
            System.out.print("Created " + i + " " + nativeProxy);
        }
    }
}
