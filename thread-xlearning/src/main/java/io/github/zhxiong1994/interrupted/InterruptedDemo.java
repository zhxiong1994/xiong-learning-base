package io.github.zhxiong1994.interrupted;


public class InterruptedDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    if (this.isInterrupted()) {
                        break;
                    }
                    System.out.println("index" + i);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("中断异常" + i);
                        // 如果不添加这一行，表示这里的中断标志被处理，下一次循环中 this.isInterrupted() == false
                        // 只有添加了这一行，则可以在下一次循环中退出线程
                        this.interrupt();
                    }
                    i++;
                }
            }
        };
        t1.start();
        /* 在该线程中优雅的停止 其他常驻线程，在停止所有线程后再结束主线程程序 */
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> {
                    t1.interrupt();
                }));
    }

}
