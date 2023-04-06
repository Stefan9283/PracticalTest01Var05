package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


class PracticalTest01Var05BroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra(Constants.MESSAGE);
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        Log.d("[BroadcastReceiver]", s);
    }
}
