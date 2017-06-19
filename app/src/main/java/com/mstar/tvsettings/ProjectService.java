package com.mstar.tvsettings;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.mstar.tvsettings.R;

public class ProjectService extends Service {

    private WindowManager windowManager;
    private ImageView chatHead;
    private boolean isEnabled = false;

    public ProjectService() {
        Log.d("ken", "service started!");

    }

    @Override
    public void onCreate() {
        super.onCreate();

        // startSetup();

        // sendBroadcast(intent);
        // stopSelf();
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        super.onStartCommand(intent, flags, startId);
//        isEnabled = !isEnabled;
//        if(isEnabled) {
//            createChatHead();
//        } else {
//            stopSelf();
//        }
//        return START_STICKY;
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        startSetup();
        stopSelf();
        return START_NOT_STICKY;
    }

    private void startSetup() {
        Log.d("ken", "got intent from remote controller!");
        final Intent intent = new Intent();
        //intent.setAction("com.optoma.launcher.show_setup");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
        intent.setComponent(new ComponentName("com.optoma.launcher", "com.optoma.launcher.Setup"));
        startActivity(intent);
    }

    private void createChatHead() {
        chatHead = new ImageButton(this);
        chatHead.setImageResource(R.drawable.bird);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;

        windowManager = (WindowManager) this.getSystemService(getApplicationContext().WINDOW_SERVICE);
        windowManager.addView(chatHead, params);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null) windowManager.removeView(chatHead);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
