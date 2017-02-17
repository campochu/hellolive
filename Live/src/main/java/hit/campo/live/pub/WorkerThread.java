package hit.campo.live.pub;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import hit.campo.live.util.log.Logger;
import hit.campo.live.util.log.QLog;

/**
 * ckb on 2017/2/15.
 */

public class WorkerThread extends HandlerThread {

    private static final Logger Log = QLog.getLogger(WorkerThread.class);

    private Handler mWorker;

    public WorkerThread() {
        super("WorkerThread");
    }

    @Override
    protected void onLooperPrepared() {
        mWorker = new Worker(getLooper());
    }

    static class Worker extends Handler {

        public Worker(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                default: {
                    break;
                }
            }
        }
    }

    public native String stringFromJNI();

    static {
        System.loadLibrary("native-lib");
    }

}
