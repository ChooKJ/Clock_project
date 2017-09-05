package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import Managers.SystemClockManager;

public class MainFrame extends JFrame
{

	private JPanel contentPane;
	private JPanel panel;
	private JTabbedPane tabbedPane;
	private SystemClockManager systemClockManager;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager // set ui manager
							.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		systemClockManager = new SystemClockManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		//make and initialize each panel.
		JPanel timePanel = new SystemClockTab(systemClockManager);
		tabbedPane.addTab("Current Time", timePanel);
		
		JPanel alarmPanel = new AlarmTab(systemClockManager);
		tabbedPane.addTab("Alarm", alarmPanel);
		
		JPanel stopwatchPanel = new StopWatchTab();
		tabbedPane.addTab("StopWatch", stopwatchPanel);
		
		JPanel timerPanel = new TimerTab();
		tabbedPane.addTab("Timer", timerPanel);
		panel.add(tabbedPane);

	}

}
