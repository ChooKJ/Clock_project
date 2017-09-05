package GUI;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Timer;

import Managers.SystemClockManager;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SystemClockTab extends JPanel
{
	private JLabel timeLbl;
	private JLabel dateLbl;
	private JButton changeBtn;
	private JPanel panel;
	private JLabel formatLbl;
	private SystemClockManager systemClockManager;
	private JCheckBox formatChkBox;
	private JLabel AMFMLbl;

	/**
	 * Create the panel.
	 */
	public SystemClockTab(SystemClockManager clkManager)
	{

		this.setPreferredSize(new Dimension(400, 200));
		setLayout(new BorderLayout(0, 0));
		systemClockManager = clkManager;
		panel = new JPanel();
		add(panel);
		panel.setLayout(null);

		timeLbl = new JLabel("New label");
		timeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		timeLbl.setBounds(0, 50, 400, 75);
		panel.add(timeLbl);
		timeLbl.setFont(new Font("ÇÔÃÊ·Ò¹ÙÅÁ", Font.BOLD, 67));

		changeBtn = new JButton("Change date and time");
		changeBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//get dialog and update systemClockmanager.
				ClockSetDlg setDlg = new ClockSetDlg(null, systemClockManager);
				setDlg.setVisible(true);
				systemClockManager = setDlg.getSystemClockManager();

			}
		});

		changeBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		changeBtn.setBounds(85, 153, 227, 37);
		panel.add(changeBtn);

		dateLbl = new JLabel("New label");
		dateLbl.setBounds(10, 121, 378, 37);
		panel.add(dateLbl);
		dateLbl.setHorizontalAlignment(SwingConstants.CENTER);

		formatLbl = new JLabel("");
		formatLbl.setBounds(343, 106, 57, 15);
		panel.add(formatLbl);

		formatChkBox = new JCheckBox("Use 24 hour format");
		formatChkBox.setBounds(8, 30, 173, 23);
		panel.add(formatChkBox);
		
		AMFMLbl = new JLabel("");
		AMFMLbl.setFont(new Font("±¼¸²", Font.BOLD, 12));
		AMFMLbl.setBounds(323, 106, 77, 23);
		panel.add(AMFMLbl);
		
		this.repaint();

		//this method update current time. and set text.
		Timer t = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				dateLbl.setText(systemClockManager.item_SystemClock.showDate);
				if (getFormatChkBox().isSelected())
				{
					timeLbl.setText(systemClockManager.item_SystemClock.nonFormatTime);
					getAMFMLbl().setVisible(false);
				}
		
				else
				{
					timeLbl.setText(systemClockManager.item_SystemClock.formatTime);
					getAMFMLbl().setVisible(true);
					if(systemClockManager.item_SystemClock.clockType.getfHour() >= 12)
					getAMFMLbl().setText("PM");
					else
						getAMFMLbl().setText("AM");
						
				}
			}

		});

		t.start();
		
	}
	
	//get and set method.

	public JCheckBox getFormatChkBox()
	{
		return formatChkBox;
	}

	public void setFormatChkBox(JCheckBox formatChkBox)
	{
		this.formatChkBox = formatChkBox;
	}

	public JLabel getAMFMLbl()
	{
		return AMFMLbl;
	}

	public void setAMFMLbl(JLabel aMFMLbl)
	{
		AMFMLbl = aMFMLbl;
	}
	
}
