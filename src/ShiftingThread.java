public class ShiftingThread extends Thread {

    private CircleBuffer buffer1, buffer2;

    public ShiftingThread(int i, CircleBuffer buffer1, CircleBuffer buffer2) {
        super("Shift.Thread.№" + i);
        super.setDaemon(true);
        this.buffer1 = buffer1;
        this.buffer2 = buffer2;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            buffer2.put(buffer1.get());
        }
    }
}
