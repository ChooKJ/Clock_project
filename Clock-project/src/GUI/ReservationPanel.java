package GUI;

import javax.swing.JPanel;

import GUI.UIType.Item_Alarm;
import Managers.SystemClockManager;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ListIterator;

public class ReservationPanel extends JPanel
{
	private JPanel panel;
	private JPanel panel_1;
	private JButton deleteBtn;
	private JButton editBtn;
	private JLabel timeLbl;
	private JLabel dayLbl;
	SystemClockManager systemClockManager;
	private AlarmTab parent;
	private JLabel rangeLbl;
	private String time;

	private Item_Alarm item;
	private JLabel alarmStateLbl;

	public ReservationPanel(Item_Alarm Item, AlarmTab p)
	{
		item = Item;

		time = item.sHour + ":" + item.sMin;
		setLayout(new BorderLayout(0, 0));
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
				Color.LIGHT_GRAY, null));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		timeLbl = new JLabel();

		timeLbl.setFont(new Font("±¼¸²", Font.BOLD | Font.ITALIC, 14));
		panel.add(timeLbl);
		dayLbl = new JLabel();
		panel.add(dayLbl);

		rangeLbl = new JLabel();

		panel.add(rangeLbl);

		alarmStateLbl = new JLabel("Alarm State : ");
		panel.add(alarmStateLbl);

		panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		editBtn = new JButton("Edit");
		//if i click button, it will get dialog and set item in here.
		editBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				SetAlarmDlg dlg = new SetAlarmDlg(null, new Item_Alarm());
				dlg.setVisible(true);
				Item_Alarm temp = dlg.getItem();
				if (temp != null)
				{
					setItem(temp);
					update();
				}
				dlg.dispose();
			}
		});
		panel_1.add(editBtn);

		deleteBtn = new JButton("Delete");
		
		//if i click this button, it remove element in the array list.
		deleteBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				parent.removeReservationPanel(ReservationPanel.this);
				parent.updateGui();
			}
		});
		panel_1.add(deleteBtn);
		parent = p;
		update();
	}

	//this method will set text to need in this panel.
	public void update()
	{
		if(Integer.parseInt(getItem().sHour) < 10)
			getItem().sHour = "0" + getItem().sHour;
		
		if(Integer.parseInt(getItem().sMin) < 10)
			getItem().sMin = "0" + getItem().sMin;
		
		timeLbl.setText("" + getItem().sHour + ":" + getItem().sMin + " "
				+ getItem().content);
		
		if (item.alarm)
			alarmStateLbl.setText("State : Enable");
		else
			alarmStateLbl.setText("State : Disable");
		try
		{
			for (int i = 0; i < 7; i++)
			{
				if (getItem().day[i])
				{
					dayLbl.setText(dayLbl.getText() + " "
							+ getItem().day_Of_Week[i]);
				}
			}

			if (getItem().fromDate != null || getItem().toDate != null)
				rangeLbl.setText(getItem().fromDate + "~" + getItem().toDate);
			else
				rangeLbl.setText("Today");

		} catch (Exception ex)
		{
			ex.getStackTrace();
		}
	}

	//get and set method.
	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public JLabel getTimeLbl()
	{
		return timeLbl;
	}

	public void setTimeLbl(JLabel timeLbl)
	{
		this.timeLbl = timeLbl;
	}

	public JLabel getDayLbl()
	{
		return dayLbl;
	}

	public void setDayLbl(JLabel dayLbl)
	{
		this.dayLbl = dayLbl;
	}

	public Item_Alarm getItem()
	{
		return item;
	}

	public void setItem(Item_Alarm item)
	{
		this.item = item;
	}

}
