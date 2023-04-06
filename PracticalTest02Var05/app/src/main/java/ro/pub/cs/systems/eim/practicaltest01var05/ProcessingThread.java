package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;


class ProcessingThread extends Thread {
    private String template;
    private Context context;

    ProcessingThread(Context context, String template) {
        this.template = template;
        this.context = context;
    }

    @Override
    public void run() {
        StringTokenizer st = new StringTokenizer(template, ", ");
        while (st.hasMoreTokens()) {
            Intent i = new Intent();
            i.setAction(Constants.ACTION_MESSAGE);
            i.putExtra(Constants.MESSAGE, st.nextToken());
            context.sendBroadcast(i);
            sleep();
        }
    }

    void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

