import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GeneratingThreadTest {

    private ArrayList<GeneratingThread> generatingThreads;
    private ArrayList<ShiftingThread> shiftingThreads;
    private CircleBuffer buffer1, buffer2;

    @Before
    public void setupTest() {
        buffer1 = new CircleBuffer("Buffer1");
        buffer2 = new CircleBuffer("Buffer2");
        s
        int numberOfGeneratingThreads = 5;
        generatingThreads = new ArrayList<GeneratingThread>(numberOfGeneratingThreads);
        for (int i = 1; i <= numberOfGeneratingThreads; i++)
            generatingThreads.add(new GeneratingThread(i, buffer1));

        int numberOfShiftingThreads = 2;
        shiftingThreads = new ArrayList<ShiftingThread>(numberOfShiftingThreads);
        for (int i = 1; i <= numberOfShiftingThreads; i++)
            shiftingThreads.add(new ShiftingThread(i, buffer1, buffer2));
    }

    @Test
    public void generatingTest1() {
        for (GeneratingThread genThread : generatingThreads)
            genThread.start();
        for (ShiftingThread shiftingThread : shiftingThreads)
            shiftingThread.start();

        for (int i = 0; i < 100; i++)
            Utils.log("\t\t" + buffer2.get());

        for (GeneratingThread genThread : generatingThreads)
            genThread.interrupt();
        for (ShiftingThread shiftingThread : shiftingThreads)
            shiftingThread.interrupt();
    }

    @Test
    public void generatingTest2() {
        for (GeneratingThread genThread : generatingThreads)
            genThread.start();
        for (ShiftingThread shiftingThread : shiftingThreads)
            shiftingThread.start();

        for (int i = 0; i < 000; i++)
            Utils.log("\t\t" + buffer2.get());

        for (GeneratingThread genThread : generatingThreads)
            genThread.interrupt();
        for (ShiftingThread shiftingThread : shiftingThreads)
            shiftingThread.interrupt();
    }
}
