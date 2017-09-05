package GUI;

/**
 * Stop watch class
 * @author 12111684 추교정, 12114492 이기성
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import java.awt.Font;
import javax.swing.SwingConstants;

public class StopWatchTab extends JPanel implements ActionListener {

	private JLabel displayTimeLabel; // place to display Stop watch time
	private Timer theChronometer; // time thread
	private JButton start_pause_Button; // 'start', 'pause', 'resume' button
	private JButton record_stop_Button; // 'record', 'stop' button
	private JScrollPane scrollPane; // scrollpane variable
	private JTextArea txtArea; // place to display recording time
	private boolean pauseState = true; // if the state of the stop watch isn't
										// 'stop' or 'pause', this value will
										// change 'false'
	private int value; // No. record
	private int milsec, sec, min; // millisecond, second, minute variable

	private String Svalue; // String value
	private String Smilsec, Ssec, Smin; // String time values
	private String outputString; // output String on TextArea
	private int Tmilsec, Tsec, Tmin; // gap of time and previous time
	private int Tmilsec2, Tsec2, Tmin2; // These temporary time values are
										// stored when you press 'Record' button
	private JPanel buttonPanel; // has button panels

	/**
	 * default constructor
	 */
	public StopWatchTab() {
		setLayout(new GridLayout(2, 1, 0, 0));
		setPreferredSize(new Dimension(400, 200));

		JPanel activePanel = new JPanel();
		add(activePanel);
		activePanel.setLayout(new GridLayout(0, 2));

		// the display for elapsed time
		displayTimeLabel = new JLabel("Stop Watch");
		activePanel.add(displayTimeLabel);
		displayTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// use a large font
		displayTimeLabel.setFont(new Font("Calibri", Font.BOLD, 29));

		displayTimeLabel.setOpaque(true);

		displayTimeLabel.setBackground(Color.LIGHT_GRAY);// gold
		displayTimeLabel.setForeground(new Color(153, 0, 0));

		buttonPanel = new JPanel();
		activePanel.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));

		// perfume 'start' function
		start_pause_Button = new JButton("Start");
		buttonPanel.add(start_pause_Button);

		// register buttons to generate events when clicked
		start_pause_Button.addActionListener(this);
		start_pause_Button.setFont(new Font("Calibri", Font.BOLD, 21));
		start_pause_Button.setBackground(Color.LIGHT_GRAY);
		start_pause_Button.setForeground(Color.BLACK);

		// perfume 'record' function
		record_stop_Button = new JButton("Record");
		buttonPanel.add(record_stop_Button);
		record_stop_Button.addActionListener(this);
		record_stop_Button.setFont(new Font("Calibri", Font.BOLD, 21));
		record_stop_Button.setBackground(Color.LIGHT_GRAY);
		record_stop_Button.setForeground(Color.BLACK);

		JPanel pnList = new JPanel();
		add(pnList);
		pnList.setLayout(new BorderLayout(0, 0));

		txtArea = new JTextArea();
		txtArea.setFont(new Font("Calibri", Font.PLAIN, 19));
		txtArea.setEditable(false);

		scrollPane = new JScrollPane(txtArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		pnList.add(scrollPane);

		// basic sentence in the text Area
		txtArea.append("   No.       Recording time          Time gap\n");

		/**
		 * Stop watch thread
		 * 
		 * @param perfume
		 *            each 1 millisecond display in Label, how much passing
		 */
		theChronometer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				milsec++;
				if (milsec == 100) {
					milsec = 0;
					sec++;
					if (sec == 60) {
						sec = 0;
						min++;
					}
				}

				if (milsec < 10)
					Smilsec = new String("0" + milsec);
				else
					Smilsec = new String("" + milsec);

				if (sec < 10)
					Ssec = new String("0" + sec);
				else
					Ssec = new String("" + sec);

				if (min < 10)
					Smin = new String("0" + min);
				else
					Smin = new String("" + min);

				outputString = new String(Smin + " : " + Ssec + " : " + Smilsec);
				displayTimeLabel.setText(outputString);
			}
		});
	}

	// action performed for the button events
	public void actionPerformed(ActionEvent e) {

		// invoke the Timer Thread stop command
		if (e.getActionCommand().equals("Stop")) {
			theChronometer.stop();

			// initialize values
			value = 0;

			milsec = 0;
			sec = 0;
			min = 0;

			Tmilsec2 = 0;
			Tsec2 = 0;
			Tmin2 = 0;

			outputString = new String("00 : 00 : 00");
			displayTimeLabel.setText(outputString);
			start_pause_Button.setText("Start");
			record_stop_Button.setText("Record");

			txtArea.setText(""); // clear JTextArea
			txtArea.append("   No.       Recording time          Time gap\n");
		}

		// either start the Timer Thread at zero or pick up where paused.
		else if (e.getActionCommand().equals("Start")) {

			theChronometer.start();
			start_pause_Button.setText("Pause");
			pauseState = false;
		}

		// if you want to restart stopwatch
		else if (e.getActionCommand().equals("Resume")) {
			theChronometer.start();

			record_stop_Button.setText("Record");
			// set the button display to Start, it may have been Resume
			start_pause_Button.setText("Pause");
			pauseState = false;
		}

		// there is no pause for Timer so we kludge one
		else if (e.getActionCommand().equals("Pause")) {

			theChronometer.stop();

			record_stop_Button.setText("Stop");
			// set the button display to Resume instead of Start
			start_pause_Button.setText("Resume");
			pauseState = true;
		}

		// if you want to record
		else if (e.getActionCommand().equals("Record")) {

			if (!pauseState) {
				++value;
				if (value < 10)
					Svalue = new String("0" + value + "            ");
				else
					Svalue = new String("" + value + "            ");

				txtArea.append("   " + Svalue + outputString + "            ");

				// sub previous recording value value from current value
				Tmilsec = milsec - Tmilsec2;
				Tsec = sec - Tsec2;
				Tmin = min - Tmin2;

				if (Tmilsec < 0) {
					Tmilsec += 100;
					--Tsec;
				}
				if (Tsec < 0) {
					Tsec += 60;
					--Tmin;
				}
				if (Tmilsec < 10)
					Smilsec = new String("0" + Tmilsec);
				else
					Smilsec = new String("" + Tmilsec);

				if (Tsec < 10)
					Ssec = new String("0" + Tsec);
				else
					Ssec = new String("" + Tsec);

				if (Tmin < 10)
					Smin = new String("0" + Tmin);
				else
					Smin = new String("" + Tmin);

				outputString = new String(Smin + " : " + Ssec + " : " + Smilsec);
				// display recording value
				txtArea.append(outputString + "\n");

				// previous recording
				Tmilsec2 = milsec;
				Tsec2 = sec;
				Tmin2 = min;
			}

		}

	}
}