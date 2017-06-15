package com.mstar.tvsettings;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class ProjectService extends Service {
    public ProjectService() {
        Log.d("ken", "service started!");

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Intent intent = new Intent();
        //intent.setAction("com.optoma.launcher.show_setup");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
        intent.setComponent(new ComponentName("com.optoma.launcher", "com.optoma.launcher.Setup"));
        startActivity(intent);
        // sendBroadcast(intent);
        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
