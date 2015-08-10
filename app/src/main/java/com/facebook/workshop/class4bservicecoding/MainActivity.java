package com.facebook.workshop.class4bservicecoding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

  LocalBroadcastManager mLocalBroadcastManager;
  TextView mCounterTextView;

  /**
   * This broadcast receiver updates the counter TextView with the
   * CounterService.COUNT intent char sequence value.
   */
  BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      mCounterTextView.setText(intent.getCharSequenceExtra(CounterService.COUNT));
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    mCounterTextView = (TextView) findViewById(R.id.counter_textview);

    Button goButton = (Button) findViewById(R.id.go_button);

    goButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // TODO: start the CounterService service. Hint: see newCounterServiceIntent().

      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();

    // TODO: register the mBroadcastReceiver receiver for CounterService.ACTION.
    IntentFilter intentFilter = new IntentFilter(CounterService.ACTION);
    mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
  }

  @Override
  protected void onPause() {
    super.onPause();

    // TODO: unregister the mBroadcastReceiver receiver.
    // Bonus: make sure not to unregister the service if it's already unregistered.

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    // TODO: stop the service. Bonus: only stop the service if it was started.

  }

  /**
   * Make a new intent referring to the CounterService service.
   */
  private Intent newCounterServiceIntent() {
    return new Intent(MainActivity.this, CounterService.class);
  }
}
