public class CircleBuffer {

    private String[] buffer;
    private String name;
    private int head;
    private int tail;
    private int actualSize;

    public CircleBuffer(String name) {
        this(name, 10);
    }

    public CircleBuffer(String name, int size) {
        this.name = name;
        head = 0;
        tail = 0;
        actualSize = 0;
        buffer = new String[size];
    }

    public synchronized void put(String element) {
        while (actualSize == buffer.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
        //Utils.log("put: " + actualSize + " " + buffer.length);
        actualSize++;
        tail++;
        tail %= buffer.length;
        buffer[tail] = element;
        Utils.log(String.format("%s putted by %s to the %s", element, Thread.currentThread().getName(), getName()));
        this.notifyAll();
    }

    public synchronized String get() {
        //Utils.log("get: " + actualSize + " " + buffer.length);
        while (actualSize == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
        actualSize--;
        head++;
        head %= buffer.length;
        Utils.log(String.format("%s getted by %s from the %s", buffer[head], Thread.currentThread().getName(), getName()));
        this.notifyAll();
        return buffer[head];
    }

    public String getName() {
        return name;
    }
}
