package com.facebook.workshop.class4bservicecoding;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;

public class CounterService extends Service {
  public static final String TAG = CounterService.class.getSimpleName();
  public static final String COUNT = "count";
  public static final String ACTION = "com.facebook.class4b.go";

  private volatile Looper mServiceLooper;
  private LocalBroadcastManager mLocalBroadcastManager;
  private ServiceHandler mServiceHandler;
  private int mCount;
  private static final int DELAY_MS = 1000;

  /**
   * Handler running on a background handler thread.
   */
  private final class ServiceHandler extends Handler {
    public ServiceHandler(Looper looper) {
      super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
      Intent intent = new Intent(ACTION);
      intent.putExtra(COUNT, Integer.toString(++mCount));

      // TODO: broadcast the message to the activity


      mServiceHandler.sendEmptyMessageDelayed(0, DELAY_MS);
    }
  }

  public void onCreate() {
    super.onCreate();

    mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

    // An Android handler thread internally operates on a looper.
    HandlerThread thread = new HandlerThread("IntentService[" + TAG + "]");
    thread.start();
    mServiceLooper = thread.getLooper();

    // An Android service handler is a handler running on a specific background thread.
    mServiceHandler = new ServiceHandler(mServiceLooper);
    mServiceHandler.sendEmptyMessageDelayed(0, DELAY_MS);
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onDestroy() {
    mServiceLooper.quit();
  }
}
