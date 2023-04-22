package task.bipolarchat.nativej;

public class HTest {

    static {
        System.loadLibrary("MySLib");
    }

    public static native void greeting();
}
