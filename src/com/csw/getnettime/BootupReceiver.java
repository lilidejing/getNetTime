package com.csw.getnettime;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class BootupReceiver extends BroadcastReceiver {

	private static final String ACTION = "android.intent.action.BOOT_COMPLETED";  //开机启动广播
    private static final String TAG = "ZED_Led_Receiver";  
    private static final String FileName = "/sys/class/zed/led1";  
    private static final String action_boot="android.intent.action.BOOT_COMPLETED"; //鍚姩APK   
  


	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("-------------");
        if (intent.getAction().equals(ACTION)){  
            {   //开机启动服务 
//                Intent intent2 = new Intent();
                Intent mBootIntent = new Intent(context, MainActivity.class);
    			mBootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    			context.startActivity(mBootIntent);
            }  
        }     
	}
	
	
	
}
