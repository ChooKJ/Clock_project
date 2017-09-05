package Managers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Timer;

public class ClockType
{
	private Calendar nowCalendar;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int fHour;
	private int min;
	private int sec;
	private int AMFM_Format;
	private int dayOfWeek;
	private String[] DAY_OF_WEEK;
	private String[] MONTH;
	private int fromYear;
	private int toYear;
	private int fromMonth;
	private int toMonth;
	private int fromDay;
	private int toDay;

	
	public ClockType()
	{
		//init arrays of day and month.
		DAY_OF_WEEK = new String[] { "", "SUN", "MON", "TUE", "WED", "THU",
				"FRI", "SAT" };
		MONTH = new String[] { "", "January", "February", "March", "April",
				"May", "June", "July", "August", "September", "October",
				"November", "December" };

		
	}
	
	//get and set methods.
	public Calendar getNowCalendar()
	{
		return nowCalendar;
	}

	public void setNowCalendar(Calendar nowCalendar)
	{
		this.nowCalendar = nowCalendar;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getMonth()
	{
		return month;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public int getDay()
	{
		return day;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public int getHour()
	{
		return hour;
	}

	public void setHour(int hour)
	{
		this.hour = hour;
	}

	public int getMin()
	{
		return min;
	}

	public void setMin(int min)
	{
		this.min = min;
	}

	public int getSec()
	{
		return sec;
	}

	public void setSec(int sec)
	{
		this.sec = sec;
	}

	public int getAMFM_Format()
	{
		return AMFM_Format;
	}

	public void setAMFM_Format(int aMFM_Format)
	{
		AMFM_Format = aMFM_Format;
	}

	public int getDayOfWeek()
	{
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}

	public String[] getDAY_OF_WEEK()
	{
		return DAY_OF_WEEK;
	}

	public void setDAY_OF_WEEK(String[] dAY_OF_WEEK)
	{
		DAY_OF_WEEK = dAY_OF_WEEK;
	}

	public String[] getMONTH()
	{
		return MONTH;
	}

	public void setMONTH(String[] mONTH)
	{
		MONTH = mONTH;
	}
	public int getfHour()
	{
		return fHour;
	}
	public void setfHour(int fHour)
	{
		this.fHour = fHour;
	}


	

}
