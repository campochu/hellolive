package hit.campo.live.util;

import android.os.Build;

/**
 * ckb on 2017/2/20.
 */

public final class ApiHelper {

    public static final boolean preL() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }

}
