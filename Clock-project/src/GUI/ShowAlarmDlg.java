package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javazoom.jl.player.Player;
import GUI.MP3;
import GUI.UIType.Item_Alarm;
//import Managers.MP3;
//import Managers.MP3;
import Managers.SystemClockManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ShowAlarmDlg extends JDialog
{
	//this dialog get mp3 class and play music.
	private final JPanel contentPanel = new JPanel();
	private MP3 mp3;
	
	public ShowAlarmDlg(JFrame parent, Item_Alarm item)
	{
		super(parent, true);
		mp3 = new MP3(item.mp3Name);
		mp3.play();
		setBounds(100, 100, 298, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel contentLbl = new JLabel("");
			contentLbl.setFont(new Font("±¼¸²", Font.BOLD, 18));
			contentLbl.setHorizontalAlignment(SwingConstants.CENTER);
			contentLbl.setBounds(0, 39, 282, 50);
			contentLbl.setText(item.content);
			contentPanel.add(contentLbl);
		}
		
		//if i click 'cancel' button, it  turn off music and close the dialog.
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{	mp3.close();
						setVisible(false);
					}
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		


		
	}
}


