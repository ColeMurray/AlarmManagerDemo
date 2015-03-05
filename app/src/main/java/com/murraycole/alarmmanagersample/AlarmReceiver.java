package com.murraycole.alarmmanagersample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by User on 3/5/15.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {
    PendingIntent alarmIntent;
    AlarmManager alarm;

    /* Returns an alarm manager that will fire at 11:59:59 and ever day there after*/
    public AlarmManager getAlarmManager(Context context){
        PendingIntent alarmIntent;
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context,0,intent,0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                alarmIntent
                );

        return alarm;
    }
    public AlarmManager setTestAlarmManager(Context context){
        Log.d("ALARMRCV", "Set alarm");
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context,0,intent,0);



    /* Should go off 1 minute from now and repeat every minute */
        alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                30*1000,
                alarmIntent
        );

        return alarm;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmRecv", "ALARM ");
        Toast.makeText(context, "ALARM",Toast.LENGTH_LONG).show();

    }
}
