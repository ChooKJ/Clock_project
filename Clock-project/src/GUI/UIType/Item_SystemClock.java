package GUI.UIType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Timer;

import Managers.ClockType;

public class Item_SystemClock
{
	public ClockType clockType;
	public Calendar nowCalendar;
	public String sYear;
	public String sMonth;
	public String sDay;
	public String sHour;
	public String sfHour;
	public String sMin;
	public String sSec;
	public String day_Of_Week;
	public String MONTH;
	
	public String date;
	public String showDate;
	public String compareTime;
	public String nonFormatTime;
	public String formatTime;
	public String sAMFM_Format;

	public Item_SystemClock()
	{
		
		//this method will update every second and update value of variables.
		Timer t = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				update(clockType);
			}});
		t.start();
		
	}


	//this method will change value of variables suitably.
	//if hour value is 8, it change 08.
	
	public void update(ClockType clocktype)
	{
		try{
		sYear = "" + clockType.getYear();
		sMonth = "" + clockType.getMonth();
		sDay = "" + clockType.getDay();
		day_Of_Week = "" + clockType.getDayOfWeek();
		sfHour = "" + clocktype.getfHour();
		
		if (clockType.getHour() < 10)
			sHour = new String("0" + clockType.getHour());
		else
			sHour = new String("" + clockType.getHour());
		
		if (clockType.getfHour() < 10)
			sfHour = new String("0" + clockType.getfHour());
		else
			sfHour = new String("" + clockType.getfHour());

		if (clockType.getSec() < 10)
			sSec = new String("0" + clockType.getSec());
		else
			sSec = new String("" + clockType.getSec());

		if (clockType.getMin() < 10)
			sMin = new String("0" + clockType.getMin());
		else
			sMin = new String("" + clockType.getMin());

		date = new String(sYear + "-" + sMonth + "-" + sDay);
		formatTime = new String(sHour + ":" + sMin + ":" + sSec);
		nonFormatTime = new String(sfHour + ":" + sMin + ":" + sSec);
		compareTime = new String(clockType.getHour() + ":" + clockType.getMin());
		
		if (clockType.getAMFM_Format() == 1)
			sAMFM_Format = "PM";
		else if (clockType.getAMFM_Format() == 0)
			sAMFM_Format = "AM";
		
		day_Of_Week = clockType.getDAY_OF_WEEK()[clockType.getDayOfWeek()+1];
		MONTH = clockType.getMONTH()[clockType.getMonth()];
		
		showDate = day_Of_Week + ", " + MONTH + " " + sDay + ", " + sYear;
		}
		catch(Exception e1)
		{
			e1.getStackTrace();
		}
	}
	
	public ClockType getClockType()
	{
		return clockType;
	}

	public void setClockType(ClockType clockType)
	{
		this.clockType = clockType;
	}
	

}
