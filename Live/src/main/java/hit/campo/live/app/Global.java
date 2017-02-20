package hit.campo.live.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * App的设备相关基础模块构建
 * <p/>
 * ckb on 15/11/26.
 */
public final class Global {

    private static Context appContext = null;

    private static Global instance = null;
    private Date startDate;

    public static Context getContext() {
        if (appContext == null) {
            throw new RuntimeException("app is not extend base appliction in androidmanifest.xml");
        }
        return appContext;
    }

    private final static void setContext(Context appContext) {
        Global.appContext = appContext;
    }

    public static void init(Context appContext) {
        setContext(appContext);
        if (instance != null) {
            throw new RuntimeException("a Global already exists");
        }
        instance = new Global();
    }

    public Date getStartDate() {
        return startDate;
    }

    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public final static Object getSystemService(@NonNull String name) {
        return getContext().getSystemService(name);
    }

    public final Intent registerReceiver(@Nullable BroadcastReceiver receiver, IntentFilter filter) {
        return getContext().registerReceiver(receiver, filter);
    }

    public final void unregisterReceiver(BroadcastReceiver receiver) {
        getContext().unregisterReceiver(receiver);
    }

}
