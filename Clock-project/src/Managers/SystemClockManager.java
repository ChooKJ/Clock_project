package Managers;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.Timer;

import GUI.ReservationPanel;
import GUI.ShowAlarmDlg;
import GUI.UIType.Item_Alarm;
import GUI.UIType.Item_SystemClock;

public class SystemClockManager
{
	public ClockType clockType;
	public Item_SystemClock item_SystemClock;
	public Item_Alarm item_Alarm;
	ArrayList<ReservationPanel> panelList;
	public JPanel pList;

	public SystemClockManager()
	{

		// initialize each class.
		clockType = new ClockType();

		pList = new JPanel();

		pList.setLayout(new GridLayout(0, 1, 0, 0));
		pList.setPreferredSize(new Dimension(400, 200));

		// this method will update current time every second.
		Timer t = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				clockType.setNowCalendar(Calendar.getInstance());
				clockType
						.setYear(clockType.getNowCalendar().get(Calendar.YEAR));
				clockType.setMonth(clockType.getNowCalendar().get(
						Calendar.MONTH) + 1);
				clockType.setDay(clockType.getNowCalendar().get(
						Calendar.DAY_OF_MONTH));
				clockType.setfHour(clockType.getNowCalendar().get(
						Calendar.HOUR_OF_DAY));
				clockType
						.setHour(clockType.getNowCalendar().get(Calendar.HOUR));
				clockType.setMin(clockType.getNowCalendar()
						.get(Calendar.MINUTE));
				clockType.setSec(clockType.getNowCalendar()
						.get(Calendar.SECOND));
				clockType.setAMFM_Format(clockType.getNowCalendar().get(
						Calendar.AM_PM));
				item_SystemClock.setClockType(clockType);

				// this for loop is check if elements of panellist exist.
				// if it exist, get dialog and turn on the music.
				try
				{
					for (int i = 0; i < panelList.size(); i++)
					{

						if (item_SystemClock.compareTime.equals(panelList
								.get(i).getTime()))
						{
							if (panelList.get(i).getItem().alarm)
							{
								ShowAlarmDlg showAlarmDlg = new ShowAlarmDlg(
										null, panelList.get(i).getItem());
								showAlarmDlg.setVisible(true);
								panelList.get(i).getItem().alarm = false;
								panelList.get(i).getItem().update(clockType);
							}
						}
					}
				} catch (Exception e1)
				{
					e1.getStackTrace();
				}

			}

		});
		t.start();
		
		item_SystemClock = new Item_SystemClock();
		item_Alarm = new Item_Alarm();

	}

	//this method will be used in clockSetDlg. it changes system time and date.
	public void changeTime(String date, String time)
	{
		try
		{
			Runtime.getRuntime().exec("cmd /C time " + time);
			Runtime.getRuntime().exec("cmd /C date " + date);
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	//get and set methods.
	public void AddPanelList(ReservationPanel panel)
	{
		panelList.add(panel);
	}

	public void removePanelList(ReservationPanel panel)
	{
		panelList.remove(panel);
	}

	public JPanel getpList()
	{
		return pList;
	}

	public void setpList(JPanel pList)
	{
		this.pList = pList;
	}

	public ClockType getClockType()
	{
		return clockType;
	}

	public void setClockType(ClockType clockType)
	{
		this.clockType = clockType;
	}

	public Item_SystemClock getItem_SystemClock()
	{
		return item_SystemClock;
	}

	public void setItem_SystemClock(Item_SystemClock item_SystemClock)
	{
		this.item_SystemClock = item_SystemClock;
	}

	public Item_Alarm getItem_Alarm()
	{
		return item_Alarm;
	}

	public void setItem_Alarm(Item_Alarm item_Alarm)
	{
		this.item_Alarm = item_Alarm;
	}

	public ArrayList<ReservationPanel> getPanelList()
	{
		return panelList;
	}

	public void setPanelList(ArrayList<ReservationPanel> panelList)
	{
		this.panelList = panelList;
	}

}
