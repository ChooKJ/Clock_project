package GUI.UIType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Timer;

import Managers.ClockType;

public class Item_Alarm
{
	public ClockType clockType;
	public Calendar nowCalendar;
	public String sYear;
	public String sMonth;
	public String sDay;
	public String sHour;
	public String sMin;
	public String sSec;
	public String[] day_Of_Week;
	public String MONTH;

	public String date;
	public String showDate;
	public String time;
	public String sAMFM_Format;
	public String content;
	public String mp3Name;
	public boolean[] day = new boolean[7];
	public String fromYear;
	public String toYear;
	public String fromMonth;
	public String toMonth;
	public String fromDay;
	public String toDay;
	public String fromDate;
	public String toDate;
	public boolean alarm;

	public Item_Alarm()
	{
		//this method will update every second and update value of variables.
		Timer t = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update(clockType);
			}
		});
		t.start();		
	}

	
	//this method will change value of variables suitably.
	//if hour value is 8, it change 08.
	public void update(ClockType clocktype)
	{

		try
		{
			sYear = "" + clockType.getYear();
			sMonth = "" + clockType.getMonth();
			sDay = "" + clockType.getDay();
			
			if (clockType.getHour() < 10)
				sHour = new String("0" + clockType.getHour());
			else
				sHour = new String("" + clockType.getHour());

			if (clockType.getSec() < 10)
				sSec = new String("0" + clockType.getSec());
			else
				sSec = new String("" + clockType.getSec());

			if (clockType.getMin() < 10)
				sMin = new String("0" + clockType.getMin());
			else
				sMin = new String("" + clockType.getMin());

			date = new String(sYear + "-" + sMonth + "-" + sDay);
			time = new String(sHour + ":" + sMin + ":" + sSec);

			if (clockType.getAMFM_Format() == 1)
				sAMFM_Format = "PM";
			else if (clockType.getAMFM_Format() == 0)
				sAMFM_Format = "AM";

			for (int i = 0; i < day.length; i++)
			{
				day_Of_Week[i] = clockType.getDAY_OF_WEEK()[i];
			}
			
			System.out.println(day_Of_Week);
			MONTH = clockType.getMONTH()[clockType.getMonth()];

			showDate = day_Of_Week + ", " + MONTH + " " + sDay + ", " + sYear;
			fromDate = new String(fromYear + "-" + fromMonth + "-" + fromDay);
			toDate = new String(toYear + "-" + toMonth + "-" + toDay);
		} catch (Exception e1)
		{
			e1.getStackTrace();
		}
	}


}
