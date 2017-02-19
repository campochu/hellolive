package hit.campo.hellolive;

import android.app.Application;

import hit.campo.live.base.Global;

/**
 * ckb on 2017/2/18.
 */

public class LiveApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Global.init(this);

    }
}
