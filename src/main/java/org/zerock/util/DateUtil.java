package org.zerock.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil{
	
	public String gettmFc() {
		Date now = new Date();
		String tmFc = "";
		
		tmFc += Integer.toString(now.getYear()+1900);
		
		if((now.getMonth()+1)<10)
			tmFc += "0" + (now.getMonth()+1); 
		else
			tmFc += Integer.toString(now.getMonth()+1);
	
		if(now.getDate()<10)
			tmFc += "0" + now.getDate();
		else
			tmFc += Integer.toString(now.getDate());
		
		if(now.getHours() < 6 && now.getHours() >= 18)
			tmFc += "1800"; 
		else
			tmFc += "0600";
					
		return tmFc;
	}
	
	public Map<String, String> getFestivalDate() {
		
		Date now = new Date();
		int year = now.getYear()+1900;
		Map<String,String> festivalDate = new HashMap<>();
		
		String date = "";
		
		date += Integer.toString(year);
		
		if((now.getMonth()+1)<10)
			date += "0" + (now.getMonth()+1); 
		else
			date += Integer.toString(now.getMonth()+1);
		
		festivalDate.put("eventStartDate", date + "01");
		
		if(now.getMonth()==1||now.getMonth()==3||now.getMonth()==5||
				now.getMonth()==7||now.getMonth()==8||now.getMonth()==10||now.getMonth()==12)
			festivalDate.put("eventEndDate", date + "31");
		else if(now.getMonth()==4||now.getMonth()==6||now.getMonth()==9||now.getMonth()==11)
			festivalDate.put("eventEndDate", date + "30");
		else {
			if(year%4==0 && ( year%100!=0 || year%400==0 ))
				festivalDate.put("eventEndDate", date + "29");
			else
				festivalDate.put("eventEndDate", date + "28");
		}
		
		return festivalDate;
	}
	
}
