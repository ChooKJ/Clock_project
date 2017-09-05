package GUI;

/**
 * Countdown Timer class
 * @author 12111684 추교정, 12114492 이기성
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class TimerTab extends JPanel implements ActionListener {

	private JLabel displayTimeLabel; // place to diplay time of the countdown
										// timer
	private Timer theChronometer; // time thread
	private JButton activateTimerButton; // 'start', 'pause', 'resume' button

	private int sec, min, hours; // second, minute, hour variable

	private String Svalue; // String value
	private String Shours, Ssec, Smin; // String time values
	private String outputString; // output String on TextArea
	private JPanel buttonPanel; // has button panels
	private JPanel startTimerPanel; // has startTimerSpinner
	private JSpinner startTimeSpinner; // set countdown time
	private final Date date = new Date(); // declare date variable
	JSpinner.DateEditor de_startTimeSpinner; // declare a date editor variable

	@SuppressWarnings("deprecation")
	public TimerTab() {

		setLayout(new GridLayout(2, 1, 0, 0));
		setPreferredSize(new Dimension(400, 200));

		JPanel activePanel = new JPanel();
		add(activePanel);
		activePanel.setLayout(new GridLayout(1, 3));

		// the display for elapsed time
		displayTimeLabel = new JLabel("Timer");
		activePanel.add(displayTimeLabel);
		displayTimeLabel.setHorizontalAlignment(JLabel.CENTER);

		// use a large font
		displayTimeLabel.setFont(new Font("Calibri", Font.BOLD, 29));

		displayTimeLabel.setOpaque(true);

		displayTimeLabel.setBackground(Color.LIGHT_GRAY);// gold
		displayTimeLabel.setForeground(new Color(153, 0, 0));

		startTimerPanel = new JPanel();
		activePanel.add(startTimerPanel);
		startTimerPanel.setLayout(new GridLayout(0, 1, 0, 0));

		// initialize time values in date, 0
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);

		// declare a SpinnerDateModel variable
		SpinnerDateModel dateModel = new SpinnerDateModel();

		// substitute the initialized values
		dateModel.setValue(date);

		// set dateModel in spinner
		startTimeSpinner = new JSpinner(dateModel);
		startTimeSpinner.setFont(new Font("Calibri", Font.PLAIN, 24));

		// initializes variable able to edit
		de_startTimeSpinner = new JSpinner.DateEditor(startTimeSpinner,
				"HH:mm:ss"); // expressed like 'HH:mm:ss'
		startTimeSpinner.setEditor(de_startTimeSpinner);

		startTimerPanel.add(startTimeSpinner);

		buttonPanel = new JPanel();
		add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));

		// perfume 'start' function
		activateTimerButton = new JButton("Start");
		buttonPanel.add(activateTimerButton);

		// register buttons to generate events when clicked
		activateTimerButton.addActionListener(this);
		activateTimerButton.setFont(new Font("Calibri", Font.BOLD, 25));
		activateTimerButton.setBackground(Color.LIGHT_GRAY);
		activateTimerButton.setForeground(Color.BLACK);

		// perfume 'stop' function
		JButton stopTimerButton = new JButton("Stop");
		buttonPanel.add(stopTimerButton);

		// perfume any action if pressed
		stopTimerButton.addActionListener(this);
		stopTimerButton.setFont(new Font("Calibri", Font.BOLD, 25));
		stopTimerButton.setBackground(Color.LIGHT_GRAY);// dark green
		stopTimerButton.setForeground(Color.BLACK);

		/**
		 * Count down timer thread
		 * 
		 * @param perfume
		 *            each 1 second display in Label, how much passing
		 */
		theChronometer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// when timer ends
				if (sec == 0 && min == 0 && hours == 0) {
					theChronometer.stop();
					activateTimerButton.setText("Start");

					/**
					 * call the dialog from the method
					 */
					ActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
						}
					});
				} else {
					if (sec == 0) {
						if (min == 0) {
							--hours;
							min = 60;
						}
						--min;
						sec = 59;
					} else
						--sec;

					if (sec < 10)
						Ssec = new String("0" + sec);
					else
						Ssec = new String("" + sec);

					if (min < 10)
						Smin = new String("0" + min);
					else
						Smin = new String("" + min);

					if (hours < 10)
						Shours = new String("0" + hours);
					else
						Shours = new String("" + hours);

					outputString = new String(Shours + " : " + Smin + " : "
							+ Ssec);

					// display timer in label
					displayTimeLabel.setText(outputString);
				}
			}

			/**
			 * display dialog
			 */
			private void ActionListener(ActionListener actionListener) {
				MessageDlg setDlg = new MessageDlg();
				setDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				setDlg.setVisible(true);
			}
		});
	}

	// action performed for the button events
	public void actionPerformed(ActionEvent e) {

		// invoke the Timer Thread stop command
		if (e.getActionCommand().equals("Stop")) {
			theChronometer.stop();

			// initialize values
			outputString = new String("00 : 00 : 00");
			displayTimeLabel.setText(outputString);

			// start when next pressing
			activateTimerButton.setText("Start");
		}

		// either start the Timer Thread at zero or pick up where paused.
		else if (e.getActionCommand().equals("Start")) {

			// save returned String value in Svalue
			Svalue = de_startTimeSpinner.getTextField().getText();

			// divide each value like 'hours', 'min', 'sec'
			hours = Integer.parseInt(Svalue.split(":")[0]);
			min = Integer.parseInt(Svalue.split(":")[1]);
			sec = Integer.parseInt(Svalue.split(":")[2]);

			// timer start
			theChronometer.start();

			// pause when next pressing
			activateTimerButton.setText("Pause");
		}

		// restart timer
		else if (e.getActionCommand().equals("Resume")) {

			// restart timer
			theChronometer.start();

			// set the button display to Start, it may have been Resume
			activateTimerButton.setText("Pause");
		}

		// there is no pause for Timer so we kludge one
		else if (e.getActionCommand().equals("Pause")) {
			theChronometer.stop();

			// set the button display to Resume instead of Start
			activateTimerButton.setText("Resume");
		}
	}
}

class MessageDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog. display "Time is UP!" message
	 */
	public MessageDlg() {
		// dialog title
		setTitle("Timer                              ");
		setBounds(100, 100, 216, 172);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// only has close button
				JButton closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				closeButton.setActionCommand("Close");
				buttonPane.add(closeButton);
				getRootPane().setDefaultButton(closeButton);
			}
		}
		{
			// display message in label
			JLabel lblTimeIsUp = new JLabel("Time is UP!");
			lblTimeIsUp.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblTimeIsUp, BorderLayout.CENTER);
			lblTimeIsUp.setFont(new Font("Javanese Text", Font.BOLD, 25));
		}
	}

}