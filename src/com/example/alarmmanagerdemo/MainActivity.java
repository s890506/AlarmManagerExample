package com.example.alarmmanagerdemo;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TimePicker mTimePicker;
	private Button mButton1;
	private Button mButtonCancel;
	private int mHour = -1;
	private int mMinute = -1;
	public static final long DAY = 1000L * 60 * 60 * 24;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 獲取當前時間
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		if (mHour == -1 && mMinute == -1) {
			mHour = calendar.get(Calendar.HOUR_OF_DAY);
			mMinute = calendar.get(Calendar.MINUTE);
		}

		mTimePicker = (TimePicker) findViewById(R.id.timePicker);
		mTimePicker.setCurrentHour(mHour);
		mTimePicker.setCurrentMinute(mMinute);
		mTimePicker
				.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

					@Override
					public void onTimeChanged(TimePicker view, int hourOfDay,
							int minute) {

						mHour = hourOfDay;
						mMinute = minute;
					}
				});

		mButton1 = (Button) findViewById(R.id.normal_button);
		mButton1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						AlarmReceiver.class);
				PendingIntent sender = PendingIntent.getBroadcast(
						MainActivity.this, 0, intent, 0);

				
				Calendar calendar = Calendar.getInstance();
			 	calendar.setTimeInMillis(System.currentTimeMillis());
			 	calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			 	calendar.set(Calendar.MINUTE, mMinute);
			 	calendar.set(Calendar.HOUR_OF_DAY, mHour);
			 	calendar.set(Calendar.SECOND, 0);
			 	calendar.set(Calendar.MILLISECOND, 0);

				// 進行鬧鐘註冊
				AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
				manager.set(AlarmManager.RTC_WAKEUP,
						calendar.getTimeInMillis(), sender);

				Toast.makeText(MainActivity.this, "設置提醒成功!!",
						Toast.LENGTH_LONG).show();
			}
		});

		mButtonCancel = (Button) findViewById(R.id.cancel_button);
		mButtonCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						AlarmReceiver.class);
				PendingIntent sender = PendingIntent.getBroadcast(
						MainActivity.this, 0, intent, 0);
				// 取消提醒
				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.cancel(sender);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
