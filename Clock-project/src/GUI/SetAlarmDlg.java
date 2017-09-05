package GUI;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileReader;

import javax.swing.SpinnerDateModel;

import java.util.Date;
import java.util.Calendar;

import GUI.UIType.Item_Alarm;
import Managers.SystemClockManager;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import java.awt.event.MouseAdapter;

public class SetAlarmDlg extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JButton saveBtn;
	private JButton cancelBtn;
	private JPanel panel_1;
	private JCheckBox onOffChkbox;
	private JTextField contentField;
	private JSpinner formatSpinner;
	private JButton chooseSongBtn;
	private JCheckBox repeatChkbox;
	private JPanel chkBoxPanel;
	private JCheckBox[] dayChk;
	private JTextField mp3NameField;
	private JPanel datePanel;
	private JDateChooser fromDateChooser;
	private JLabel toLbl;
	private JDateChooser toDateChooser;
	private JSpinField hourField;
	private JSpinField minField;
	private boolean enable;
	private Item_Alarm temp;
	private JLabel errorLbl;
	private boolean buttonClicked;
	private Item_Alarm item = new Item_Alarm();

	/**
	 * Create the dialog.
	 */
	public SetAlarmDlg(JFrame parent, Item_Alarm item)
	{
		super(parent, true);
		this.item = item;
		setTitle("Set Alarm");
		setBounds(100, 100, 451, 335);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 435, 297);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(12, 160, 411, 96);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		chkBoxPanel = new JPanel();
		FlowLayout fl_chkBoxPanel = (FlowLayout) chkBoxPanel.getLayout();
		fl_chkBoxPanel.setHgap(15);
		chkBoxPanel.setBounds(12, 51, 387, 35);
		panel_1.add(chkBoxPanel);

		dayChk = new JCheckBox[] { new JCheckBox("M"), new JCheckBox("T"),
				new JCheckBox("W"), new JCheckBox("T"), new JCheckBox("F"),
				new JCheckBox("S"), new JCheckBox("S") };

		//if day checkbox is clicked, then daychk array will save it.
		for (int i = 0; i < dayChk.length; i++)
		{
			chkBoxPanel.add(dayChk[i]);
		}

		datePanel = new JPanel();
		FlowLayout fl_datePanel = (FlowLayout) datePanel.getLayout();
		fl_datePanel.setHgap(30);
		datePanel.setBounds(12, 10, 387, 40);
		panel_1.add(datePanel);

		fromDateChooser = new JDateChooser();
		fromDateChooser.setDateFormatString("yyyy. MM. dd");
		datePanel.add(fromDateChooser);

		toLbl = new JLabel("to");
		toLbl.setFont(new Font("±¼¸²", Font.BOLD, 17));
		datePanel.add(toLbl);

		toDateChooser = new JDateChooser();
		toDateChooser.setDateFormatString("yyyy. MM. dd");
		datePanel.add(toDateChooser);

		onOffChkbox = new JCheckBox("On/ Off");
		//this listener get information when i click button.
		onOffChkbox.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (onOffChkbox.isSelected())
					enable = true;
				else
					enable  = false;
			}
		});

		onOffChkbox.setBounds(8, 6, 115, 23);
		contentPanel.add(onOffChkbox);

		contentField = new JTextField();
		contentField.setBounds(12, 46, 239, 33);
		contentField.setText("Wake up!!!");
		contentPanel.add(contentField);
		contentField.setColumns(10);

		SpinnerCircularListModel listModel = new SpinnerCircularListModel(
				new String[] { "AM", "PM" });
		formatSpinner = new JSpinner(listModel);
		formatSpinner.setBounds(367, 46, 56, 33);
		contentPanel.add(formatSpinner);

		chooseSongBtn = new JButton("Choose Song");
		// this button will get name of music.
		chooseSongBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				try
				{
					JFileChooser fc = new JFileChooser();
					int returnVal = fc.showOpenDialog(SetAlarmDlg.this);
					File file = fc.getSelectedFile();
					mp3NameField.setText(file.getName());
				} catch (Exception e1)
				{
					e1.getStackTrace();
				}
			}
		});
		chooseSongBtn.setBounds(272, 88, 151, 33);
		contentPanel.add(chooseSongBtn);

		repeatChkbox = new JCheckBox("Repeat");
		repeatChkbox.setBounds(12, 131, 115, 23);
		contentPanel.add(repeatChkbox);

		panel = new JPanel();
		panel.setBounds(0, 266, 435, 26);
		contentPanel.add(panel);
		panel.setLayout(null);

		saveBtn = new JButton("Save");
		//if i click this button, every value is saved in item.
		saveBtn.addActionListener(new ActionListener()
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0)
			{

				getItem().content = contentField.getText();
				getItem().mp3Name = mp3NameField.getText();
				getItem().sHour = "" + hourField.getValue();
				getItem().sMin = "" + minField.getValue();
				getItem().day = new boolean[7];
				getItem().alarm = isEnable();

				System.out.println(getItem().sHour);

				for (int i = 0; i < getItem().day.length; i++)
				{
					getItem().day[i] = dayChk[i].isSelected();
				}
			

				Date fromDate = fromDateChooser.getDate();
				Date toDate = toDateChooser.getDate();
				if (fromDate != null && toDate != null)
				{
					getItem().fromYear = "" + (1900+fromDate.getYear());
					getItem().fromMonth = "" + (fromDate.getMonth()+1);
					getItem().fromDay = "" + fromDate.getDay();

					getItem().toYear = "" + (1900+toDate.getYear());
					getItem().toMonth = "" + (toDate.getMonth()+1);
					getItem().toDay = "" + toDate.getDay();
					System.out.println(getItem().fromYear);
				}
				buttonClicked = true;
				setVisible(false);
			}
		});
		saveBtn.setBounds(12, 0, 97, 23);
		panel.add(saveBtn);

		cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				buttonClicked = false;
				setVisible(false);
			}
		});
		cancelBtn.setBounds(326, 0, 97, 23);
		panel.add(cancelBtn);

		mp3NameField = new JTextField();
		mp3NameField.setColumns(10);
		mp3NameField.setBounds(12, 88, 239, 33);
		contentPanel.add(mp3NameField);

		hourField = new JSpinField();
		hourField.setValue(8);
		hourField.setMinimum(1);
		hourField.setMaximum(12);
		hourField.setBounds(263, 46, 42, 33);
		contentPanel.add(hourField);

		minField = new JSpinField();
		minField.setMinimum(0);
		minField.setMaximum(59);
		minField.setBounds(327, 46, 42, 33);
		contentPanel.add(minField);

		errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorLbl.setBounds(104, 135, 302, 19);
		contentPanel.add(errorLbl);

	}
	
	//get and set method.
	public boolean isEnable()
	{
		return enable;
	}

	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}

	public Item_Alarm getTemp()
	{
		System.out.println(temp.sHour);
		System.out.println(temp.sMin);
		System.out.println(temp.sSec);
		return temp;
	}

	public void setTemp(Item_Alarm temp)
	{

		this.temp = temp;
	}

	public boolean getButtonClicked()
	{
		return buttonClicked;

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
