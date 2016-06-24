package com.csw.getnettime;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			GetAndChangeTimeService.alarmManager=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("GetAndChangeTimeService.alarmManager:"+GetAndChangeTimeService.alarmManager);
		
		Intent startIntent = new Intent(MainActivity.this, GetAndChangeTimeService.class);
		startService(startIntent);
		
		
		this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
}
