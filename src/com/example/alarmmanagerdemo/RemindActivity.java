package com.example.alarmmanagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RemindActivity extends Activity implements OnClickListener
{
	Button button1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_remind);
		initialObject();
	}
	
	private void initialObject() {
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			RemindActivity.this.finish();
			break;
		default:
			break;
		}
		
		
	}

}
