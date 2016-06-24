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
			connection.connect(); // 发出连接  
		    long ld = connection.getDate(); // 取得网站日期时间  
		    Date date = new Date(ld); // 转换为标准时间对象  
		    // 分别取得时间中的小时，分钟和秒，并输出  
		    Log.d("getnettime",  
		            date + ", " + date.getHours() + "时" + date.getMinutes()  
		                    + "分" + date.getSeconds() + "秒");  
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
