package com.example.alarmmanagerdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlarmReceiver extends BroadcastReceiver {


	@Override
	public void onReceive(Context context, Intent intent) {

		try { //部分設備不允許此作法，因此要用 try..catch包起來
			
			NotificationManager notificationManager = (NotificationManager) context
					.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
			Notification notification = new Notification();
			// 會有通知預設的鈴聲、振動、light
			notification.defaults = Notification.DEFAULT_ALL;
			notificationManager.notify(0, notification);
			
		} catch (Exception e) {
			
		}
		
		Intent i = new Intent(context, RemindActivity.class); 
	    i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT
				| Intent.FLAG_ACTIVITY_NEW_TASK); 
	    context.startActivity(i);


	}



}
