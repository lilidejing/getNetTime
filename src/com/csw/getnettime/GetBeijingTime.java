package com.csw.getnettime;


import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.util.Log;

public class GetBeijingTime {

	public static String GetNetTime() {
		URL infoUrl = null;
		InputStream inStream = null;
		try {
//			infoUrl = new URL("http://www.beijing-time.org/time.asp");
			infoUrl = new URL("http://www.bjtime.cn");
			URLConnection connection = infoUrl.openConnection();
			connection.connect(); // ��������  
		    long ld = connection.getDate(); // ȡ����վ����ʱ��  
		    Date date = new Date(ld); // ת��Ϊ��׼ʱ�����  
		    // �ֱ�ȡ��ʱ���е�Сʱ�����Ӻ��룬�����  
		    Log.d("getnettime",  
		            date + ", " + date.getHours() + "ʱ" + date.getMinutes()  
		                    + "��" + date.getSeconds() + "��");  
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		    formatter.setTimeZone(TimeZone.getTimeZone("GMT+08")); 
		    String dateString = formatter.format(date);
		    
		    return dateString;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
