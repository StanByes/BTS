package fr.riot.td_7;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

public class App_2 {

	private JFrame frame;
	private JTextField first;
	private JTextField second;
	private JTextField result;
	private final ButtonGroup action = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_2 window = new App_2();
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
	public App_2() {
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
		first.setBounds(10, 53, 86, 20);
		frame.getContentPane().add(first);
		first.setColumns(10);
		
		second = new JTextField();
		second.setColumns(10);
		second.setBounds(160, 53, 86, 20);
		frame.getContentPane().add(second);
		
		result = new JTextField();
		result.setColumns(10);
		result.setBounds(349, 53, 86, 20);
		frame.getContentPane().add(result);
		
		JToggleButton plus = new JToggleButton("+");
		action.add(plus);
		plus.setSelected(true);
		plus.setBounds(106, 53, 49, 20);
		plus.setActionCommand("plus");
		frame.getContentPane().add(plus);
		
		JToggleButton minus = new JToggleButton("-");
		action.add(minus);
		minus.setBounds(106, 84, 49, 20);
		minus.setActionCommand("minus");
		frame.getContentPane().add(minus);
		
		JToggleButton multiply = new JToggleButton("*");
		action.add(multiply);
		multiply.setBounds(106, 115, 49, 20);
		multiply.setActionCommand("multiply");
		frame.getContentPane().add(multiply);
		
		JToggleButton substract = new JToggleButton("/");
		action.add(substract);
		substract.setBounds(106, 146, 49, 20);
		substract.setActionCommand("substract");
		frame.getContentPane().add(substract);
		
		JButton btnNewButton = new JButton("=");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int firstValue = Integer.parseInt(first.getText());
				int secondValue = Integer.parseInt(second.getText());
				
				result.setText(String.valueOf(calculate(action.getSelection().getActionCommand(), firstValue, secondValue)));
			}
		});
		btnNewButton.setBounds(250, 52, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	private static double calculate(String action, int first, int second) {
		switch (action) {
			case "plus": return first + second;
			case "minus": return first - second;
			case "multiply": return first * second;
			case "substract": return first / second;
			default: return first + second;
		}
	}
}
