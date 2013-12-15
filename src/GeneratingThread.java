public class GeneratingThread extends Thread {

    private CircleBuffer destinationCircleBuffer;

    public GeneratingThread(int i, CircleBuffer destinationCircleBuffer) {
        super("Gener.Thread.№" + i);
        super.setDaemon(true);
        this.destinationCircleBuffer = destinationCircleBuffer;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            destinationCircleBuffer.put(String.format("%s-%s",
                    Utils.generateString(10), getName()));
        }
    }
}
