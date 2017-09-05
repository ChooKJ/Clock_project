package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.SpinnerCircularListModel;
import Managers.SystemClockManager;

import com.toedter.calendar.JCalendar;

import javax.swing.JCheckBox;

import com.toedter.components.JSpinField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JSpinner;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ClockSetDlg extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JCalendar calendar;
	private JCheckBox clkFormatChkbox;
	private JSpinField hourField;
	private JSpinField minField;
	private JSpinField secField;
	private Calendar nowCalendar;
	private JLabel lblNewLabel;
	private JLabel lblSec;
	private JLabel lblSec_1;
	private JLabel lblAmpm;
	private JLabel formatLabel;
	private JLabel testLbl;
	private JSpinner formatSpinner;
	private SystemClockManager systemClockManager;

	public ClockSetDlg(JFrame parent, SystemClockManager clkManager)
	{

		super(parent, true);
		systemClockManager = new SystemClockManager();
		nowCalendar = Calendar.getInstance();
		setBounds(100, 100, 484, 326);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		calendar = new JCalendar();
		//it shows date when calendar is changed.
		calendar.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent arg0)
			{
				testLbl.setText("" + calendar.getYearChooser().getYear()
						+ " / " + (calendar.getMonthChooser().getMonth() + 1)
						+ " / " + calendar.getDayChooser().getDay());

			}
		});

		calendar.setBounds(0, 10, 225, 235);
		contentPanel.add(calendar);

		clkFormatChkbox = new JCheckBox("Use 24 hour format");
		
		//if i click this check box, it will change 24 hour format.
		clkFormatChkbox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent arg0)
			{
				if (clkFormatChkbox.isSelected())
				{
					hourField.setMinimum(0);
					hourField.setMaximum(23);
					hourField.setValue(hourField.getValue() + 12);
					formatSpinner.setBounds(0, 0, 0, 0);
				} else if (!clkFormatChkbox.isSelected())
				{
					hourField.setMinimum(1);
					hourField.setMaximum(11);
					hourField.setValue(hourField.getValue() - 12);
					formatSpinner.setBounds(408, 108, 60, 45);
				}
			}
		});

		clkFormatChkbox.setBounds(244, 79, 173, 23);
		contentPanel.add(clkFormatChkbox);

		hourField = new JSpinField();
		hourField.setMinimum(1);
		hourField.setMaximum(12);
		hourField.setBounds(237, 108, 45, 45);
		hourField.setValue(nowCalendar.get(Calendar.HOUR));
		hourField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(hourField);

		minField = new JSpinField();
		minField.setMinimum(0);
		minField.setMaximum(59);
		minField.setBounds(294, 108, 45, 45);
		minField.setHorizontalAlignment(SwingConstants.CENTER);
		minField.setValue(nowCalendar.get(Calendar.MINUTE));
		contentPanel.add(minField);

		secField = new JSpinField();
		secField.setMinimum(0);
		secField.setMaximum(59);
		secField.setBounds(351, 108, 45, 45);
		secField.setHorizontalAlignment(SwingConstants.CENTER);
		secField.setValue(nowCalendar.get(Calendar.SECOND));
		contentPanel.add(secField);

		lblNewLabel = new JLabel("HOUR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(237, 163, 45, 15);
		contentPanel.add(lblNewLabel);

		lblSec = new JLabel("MIN");
		lblSec.setHorizontalAlignment(SwingConstants.CENTER);
		lblSec.setBounds(294, 163, 45, 15);
		contentPanel.add(lblSec);

		lblSec_1 = new JLabel("SEC");
		lblSec_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSec_1.setBounds(351, 163, 45, 15);
		contentPanel.add(lblSec_1);

		lblAmpm = new JLabel("AM/PM");
		lblAmpm.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmpm.setBounds(403, 163, 53, 15);
		contentPanel.add(lblAmpm);

		formatLabel = new JLabel("");

		formatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		formatLabel.setBounds(403, 181, 45, 45);
		contentPanel.add(formatLabel);

		testLbl = new JLabel("");
		testLbl.setFont(new Font("±¼¸²", Font.BOLD, 16));
		testLbl.setBounds(237, 31, 219, 45);
		testLbl.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(testLbl);

		SpinnerCircularListModel listModel = new SpinnerCircularListModel(
				new String[] { "AM", "PM" });
		formatSpinner = new JSpinner(listModel);
		
		//it shows AM of PM.
		formatSpinner.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent arg0)
			{
				if (nowCalendar.get(Calendar.AM_PM) == 1)
					formatSpinner.setValue("PM");
				if (nowCalendar.get(Calendar.AM_PM) == 0)
					formatSpinner.setValue("AM");
			}
		});
		formatSpinner.setBounds(408, 108, 60, 45);
		contentPanel.add(formatSpinner);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			//if i click save button, it save value temporally, and call change time method.
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						int temp;
						if (formatSpinner.getValue() == "PM")
							temp = (int) hourField.getValue() + 12;
							
						else
							temp = (int) hourField.getValue();
						String time = temp + ":"
								+ minField.getValue() + ":"
								+ secField.getValue();
						String date = calendar.getYearChooser().getYear() + "-"
								+ (calendar.getMonthChooser().getMonth() + 1)
								+ "-" + calendar.getDayChooser().getDay();

						systemClockManager.changeTime(date, time);

						setVisible(false);
					}
				});

				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	//get and set method.
	public SystemClockManager getSystemClockManager()
	{
		return systemClockManager;
	}

	public void setSystemClockManager(SystemClockManager systemClockManager)
	{
		this.systemClockManager = systemClockManager;
	}

	public JCheckBox getClkFormatChkbox()
	{
		return clkFormatChkbox;
	}

	public void setClkFormatChkbox(JCheckBox clkFormatChkbox)
	{
		this.clkFormatChkbox = clkFormatChkbox;
	}
	

}
