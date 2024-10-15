package fr.riot.td_7;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App_3 {

	private JFrame frame;
	private JTextField userInput;
	
	private int count;
	private int value;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_3 window = new App_3();
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
	public App_3() {
		initialize();
		
		this.count = 0;
		this.value = random();
		System.out.println(value);
	}
	
	private int random() {
		return (int) ((Math.random() * (100 - 0)) + 0);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		App_3 instance = this;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Devine le nombre :");
		lblNewLabel.setBounds(10, 31, 110, 36);
		frame.getContentPane().add(lblNewLabel);
		
		userInput = new JTextField();
		userInput.setBounds(130, 39, 86, 20);
		frame.getContentPane().add(userInput);
		userInput.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre de tentatives : ");
		lblNewLabel_1.setBounds(229, 90, 171, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel count = new JLabel("0");
		count.setBounds(388, 90, 46, 14);
		frame.getContentPane().add(count);
		
		JLabel info = new JLabel("");
		info.setFont(new Font("Tahoma", Font.PLAIN, 15));
		info.setBounds(57, 182, 320, 36);
		frame.getContentPane().add(info);

		JButton tryValue = new JButton("?");
		tryValue.setFont(new Font("Tahoma", Font.PLAIN, 38));
		tryValue.setBounds(130, 70, 89, 101);
		frame.getContentPane().add(tryValue);
		
		JButton playAgain = new JButton("Rejouer");
		playAgain.setVisible(false);
		playAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playAgain.setVisible(false);
				
				instance.count = 0;
				count.setText("0");
				instance.value = random();

				tryValue.setEnabled(true);
				userInput.setEditable(true);
				userInput.setText(null);
				
				info.setText("");
			}
		});
		playAgain.setFont(new Font("Tahoma", Font.PLAIN, 23));
		playAgain.setBounds(292, 213, 132, 37);
		frame.getContentPane().add(playAgain);
		
		tryValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userInput.getText().isEmpty()) {
					info.setText("Veuillez entrer une valeur");
					return;
				}
				
				int value = 0;
				try {
					value = Integer.parseInt(userInput.getText());
				} catch (NumberFormatException exception) {
					info.setText("Vous devez entrer un nombre entier");
					userInput.setText(null);
					return;
				}
				
				if (value < 0 || value > 100) {
					info.setText("La valeur doit être comprise entre et 0 et 100 inclus");
					userInput.setText(null);
					return;
				}
				
				instance.count++;
				count.setText(String.valueOf(instance.count));
				
				if (value < instance.value) {
					info.setText("Raté : le nombre à deviner est + grand !");
				} else if (value > instance.value) {
					info.setText("Raté : la nombre à deviner est - petit !");
				} else {
					info.setText("Gagné !");
					playAgain.setVisible(true);
					tryValue.setEnabled(false);
					userInput.setEditable(false);
				}
			}
		});
	}
}
