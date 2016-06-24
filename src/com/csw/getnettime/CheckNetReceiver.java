package com.csw.getnettime;

import java.io.IOException;
import java.util.Calendar;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
/*
 * �������״̬�㲥
 */
public class CheckNetReceiver extends BroadcastReceiver {
	
	private final String WIRED_ON_ACTION = "android.settings.WIRELESS_SETTINGS_UP";
	private final String WIRED_OFF_ACTION = "android.settings.WIRELESS_SETTINGS_DOWN";
	private final String WIRED_REMOVE_ACTION = "android.settings.WIRELESS_SETTINGS_REMOVED";

	private final String youxian = "android.net.ethernet.ETHERNET_STATE_CHANGED";
	
	private Context context;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 String action = intent.getAction();
         if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
        	 
        	 this.context=context;
    		 ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	     NetworkInfo networkinfo = cm.getActiveNetworkInfo();
    	     if(networkinfo!=null&&networkinfo.isConnected()){//�������״̬
    	    	 checkNetHandler.sendEmptyMessage(CONNECT);
    	     }else{//�������״̬
    	    	 checkNetHandler.sendEmptyMessage(BREAK);
    	     }
    	     Log.d("�������״̬�㲥", "�㲥����");
//    	               return;
         }else{
        	 return;
         }
		
		
	}
   
	private final int CONNECT=1;
	private final int BREAK=2;
	private Handler checkNetHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 1:
//				GetAndChangeTimeService getAndChangeTimeService=new GetAndChangeTimeService();
//				getAndChangeTimeService.changeThread();
				 Intent mBootIntent = new Intent(context, MainActivity.class);
	    			mBootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    			context.startActivity(mBootIntent);
				Log.d("��⵽�����", "��⵽�����");
				break;
			case 2:
				
				break;
			default:
					break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	
	
	
	
	
	
	
}
