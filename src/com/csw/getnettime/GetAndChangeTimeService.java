package com.csw.getnettime;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


import android.app.AlarmManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class GetAndChangeTimeService extends Service {



	Context context;

	GetBeijingTime getBenjingTime;

	public static AlarmManager alarmManager;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
//		Toast.makeText(getApplicationContext(), "获取网络时间方法启动", Toast.LENGTH_LONG)
//				.show();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
//		getBenjingTime = new GetBeijingTime();
//		if (isNetworkConnected(GetAndChangeTimeService.this)) {
//			getTimeHandler.sendEmptyMessage(1);// 获取网络时间并更改系统时间
//		} else {
			getTimeHandler.sendEmptyMessage(2);// 60分钟监测一次，如果联网就同步时间
//		}

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}



	

 	Runnable changeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {
				
				String getNetTime = GetBeijingTime.GetNetTime();
				Log.d("getNetTime", getNetTime);
				
				if(!getNetTime.equals("")&&getNetTime!=null){
	
				    	    String year=getNetTime.substring(0,4);
				    	    String month=getNetTime.substring(4,6);
				    	    String date=getNetTime.substring(6,8);
				    	    String hour=getNetTime.substring(8,10);
				    	    String minute=getNetTime.substring(10,12);
				    	    String second=getNetTime.substring(12,14);
				    	    
				    	 
				    	    try {
								Calendar c = Calendar.getInstance();
//							c.set(Integer.parseInt("2015"),Integer.parseInt("12")-1,Integer.parseInt("12"),Integer.parseInt("22"),Integer.parseInt("44"),Integer.parseInt("33"));
								c.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(date),Integer.parseInt(hour),Integer.parseInt(minute),Integer.parseInt(second));
								System.out.println(c.getTimeInMillis());
								
								Log.d("GetAndChangeTimeServie", "c.getTimeInMillis()"+c.getTimeInMillis());
								if(alarmManager!=null){
									alarmManager.setTime(c.getTimeInMillis());
									System.out.println("系统时间同步成功！");
								}else{
									System.out.println("alarmManager为空");
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Log.d("MainActivity", "时间同步失败");
							}
				    
				}else{
					Log.d("获取网络时间", "没获取到网络时间");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("系统时间同步失败，莫非没有ROOT权限？");
				e.printStackTrace();
			}
		}

	};

	public void changeThread() {
		new Thread(changeRunnable).start();
	}

	public Handler getTimeHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 1:
				changeThread();
				break;
			case 2:

				new Timer().schedule(new TimerTask() {
					public void run() {
						if (isNetworkConnected(GetAndChangeTimeService.this)) {
							changeThread();
						} else {

							System.out.println("没有网络");
							// System.gc();
						}
					}
				}, 0,3600000);

				break;
			default:
				break;
			}

			super.handleMessage(msg);
		}

	};

	/*
	 * 网络是否联通
	 */
	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return true;
			}
		}
		return false;
	}

}
