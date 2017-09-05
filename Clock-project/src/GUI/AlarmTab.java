package GUI;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import GUI.UIType.Item_Alarm;
import Managers.SystemClockManager;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ListIterator;

public class AlarmTab extends JPanel
{
	private JButton addBtn;
	private JScrollPane scrollPane;
	private JPanel pnList;
	private SystemClockManager systemClockManager;
	public static ArrayList<ReservationPanel> panelList = new ArrayList<ReservationPanel>();

	public AlarmTab(SystemClockManager clkManager)
	{

		systemClockManager = clkManager;
		setLayout(new BorderLayout(0, 0));
		addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent arg0)
			{
				SetAlarmDlg alarm = new SetAlarmDlg(null, new Item_Alarm());
				alarm.setVisible(true);
				if (alarm.getButtonClicked())
				{
					addReservationPanel(new ReservationPanel(
							alarm.getItem(), AlarmTab.this));
					systemClockManager.setPanelList(panelList);
				}
				updateGui();
			}

		});

		add(addBtn, BorderLayout.SOUTH);

		pnList = new JPanel();
		setPreferredSize(new Dimension(400, 200));
		scrollPane = new JScrollPane(pnList);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnList.setLayout(new GridLayout(0, 1, 0, 0));
		add(scrollPane, BorderLayout.CENTER);

	}

	public JScrollPane getScrollPane()
	{
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane)
	{
		this.scrollPane = scrollPane;
	}

	public JPanel getPnList()
	{
		return pnList;
	}

	public void setPnList(JPanel pnList)
	{
		this.pnList = pnList;
	}
	
	public void addReservationPanel(ReservationPanel pn){
		panelList.add(pn);
	}
	
	public void removeReservationPanel(ReservationPanel pn){
		panelList.remove(pn);
	}
	
	public void updateGui()
	{
		// reset the panel
		pnList.removeAll();

		// loop all contact in the list
		ListIterator<ReservationPanel> litr = panelList.listIterator();
		while (litr.hasNext())
		{
			ReservationPanel element = litr.next();

			pnList.add(element);
		}
		System.out.println("이과정을지날까?");
		pnList.revalidate();
		pnList.repaint();

	}

	public ArrayList<ReservationPanel> getPanelList()
	{
		return panelList;
	}

	public void setPanelList(ArrayList<ReservationPanel> panelList)
	{
		panelList = panelList;
	}
	
	

}
