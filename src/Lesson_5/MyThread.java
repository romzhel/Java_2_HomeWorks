package Lesson_5;

public class MyThread extends Thread {
    private float[] treatingArray;

    public MyThread(float[] treatingArray, int correction) {
        super(() -> {
            int arrayItemIndex;
            for (int i = 0; i < treatingArray.length; i++) {
                arrayItemIndex = i + correction;
                treatingArray[i] = (float) (treatingArray[i] * Math.sin(0.2f + (float) arrayItemIndex / 5) *
                        Math.cos(0.2f + (float) arrayItemIndex / 5) * Math.cos(0.4f + (float) arrayItemIndex / 2));
            }
        });

        this.treatingArray = treatingArray;
        System.out.printf("thread %s, treating items from %d to %d\n", getName(), correction,
                correction + treatingArray.length - 1);
    }

    public float[] getTreatingArray() {
        return treatingArray;
    }
}
