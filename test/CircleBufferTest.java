import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class CircleBufferTest {

    @Test
    public void testBuffer1() {
        CircleBuffer buffer = new CircleBuffer("Buffer1");

        buffer.put("a");
        assertEquals("a", buffer.get());

        buffer.put("b");
        buffer.put("c");
        buffer.put("d");
        assertEquals("b", buffer.get());
        assertEquals("c", buffer.get());
        assertEquals("d", buffer.get());
    }

    @Test
    public void testBuffer2() {
        final CircleBuffer buffer = new CircleBuffer("Buffer1");

        buffer.put("a");
        assertEquals("a", buffer.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                buffer.put("xxx");
            }
        }).start();
        assertEquals("xxx", buffer.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                buffer.put("xx0");
                buffer.put("xx1");
                buffer.put("xx2");

            }
        }).start();
        assertEquals("xx0", buffer.get());
        assertEquals("xx1", buffer.get());
        assertEquals("xx2", buffer.get());

        for (int i = 0; i < 100; i++) {
            buffer.put("new" + i);
            assertEquals("new" + i, buffer.get());
        }
    }

    @Test
    public void testBuffer3() {
        final CircleBuffer buffer = new CircleBuffer("Buffer1");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    buffer.put("m" + i);
                }
            }
        }).start();

        for (int i = 0; i < 200; i++) {
            assertEquals("m" + i, buffer.get());
        }
    }
}
