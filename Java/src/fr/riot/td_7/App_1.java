package fr.riot.td_7;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App_1 {

	private JFrame frame;
	private JTextField first;
	private JTextField second;
	private JTextField result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_1 window = new App_1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App_1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		first = new JTextField();
		first.setBounds(23, 53, 86, 20);
		frame.getContentPane().add(first);
		first.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("+");
		lblNewLabel.setBounds(119, 56, 14, 14);
		frame.getContentPane().add(lblNewLabel);
		
		second = new JTextField();
		second.setColumns(10);
		second.setBounds(143, 53, 86, 20);
		frame.getContentPane().add(second);
		
		result = new JTextField();
		result.setColumns(10);
		result.setBounds(338, 53, 86, 20);
		frame.getContentPane().add(result);
		
		JButton btnNewButton = new JButton("=");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int firstValue = Integer.parseInt(first.getText());
				int secondValue = Integer.parseInt(second.getText());
				
				result.setText(String.valueOf(firstValue + secondValue));
			}
		});
		btnNewButton.setBounds(239, 52, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
