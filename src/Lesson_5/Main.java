package Lesson_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static final int SIZE = 10000000;

    public static void main(String[] args) {
        float[] array = new float[SIZE];

        try {
            for (int threadsCount = 0; threadsCount < 11; threadsCount++) {
                Arrays.fill(array, 1.0f);
                System.out.printf("Execution time: %d (threads count: %d)\n\n",
                        treatAndGetOperationTime(array, threadsCount), threadsCount);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * input parameters:
     * - array - treated array
     * - threadsCount:
     * - 0 - main thread will be used for treating the array
     * - > 0 - additional threads will be created and used for treating the array
     */
    public static long treatAndGetOperationTime(float[] array, int threadsCount) throws InterruptedException {
        List<MyThread> threads = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        if (threadsCount > 1) {
            int interval = array.length / threadsCount;
            for (int subArrayStart = 0; subArrayStart < array.length; subArrayStart += interval) {
                if (array.length - subArrayStart < interval * 2) {
                    interval = array.length - subArrayStart;
                }

                float[] subArray = Arrays.copyOfRange(array, subArrayStart, subArrayStart + interval);
                MyThread thread = new MyThread(subArray, subArrayStart);
                threads.add(thread);

                thread.start();
                thread.join();
            }

            int startIndex = 0;
            for (MyThread thread : threads) {
                System.arraycopy(thread.getTreatingArray(), 0, array, startIndex, thread.getTreatingArray().length);
                startIndex += thread.getTreatingArray().length;
            }
        } else if (threadsCount == 1) {
            MyThread thread = new MyThread(array, 0);
            thread.start();
            thread.join();
        } else if (threadsCount == 0) {
            System.out.printf("thread %s\n", Thread.currentThread().getName());
            for (int i = 0; i < array.length; i++) {
                array[i] = (float) (array[i] * Math.sin(0.2f + (float) i / 5) *
                        Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));
            }
        }

        long executingTime = System.currentTimeMillis() - startTime;

        System.out.printf("item 9999995 = %.2f\n", array[9999995]);
        System.out.printf("item 999995 = %.2f\n", array[999995]);
        System.out.printf("item 99995 = %.2f\n", array[99995]);

        return executingTime;
    }
}
