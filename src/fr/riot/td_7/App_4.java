package fr.riot.td_7;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;

public class App_4 {

	private JFrame frame;
	private JTextField textField;
	private JButton v_7;
	private JButton v_8;
	private JButton v_9;
	private JButton v_4;
	private JButton v_5;
	private JButton v_6;
	private JButton v_1;
	private JButton v_2;
	private JButton v_3;
	private JButton v_0;
	private JButton clear;
	private JButton calculate;
	private JButton substract;
	private JButton multiply;
	private JButton minus;
	private JButton plus;
	
	private String chain = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_4 window = new App_4();
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
	public App_4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AppActionListener actionListener = new AppActionListener(this);
		
		frame = new JFrame();
		frame.setTitle("Calculette");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 0, 434, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		v_7 = new JButton("7");
		v_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		v_7.addActionListener(actionListener);
		v_7.setBounds(10, 37, 87, 45);
		frame.getContentPane().add(v_7);
		
		v_8 = new JButton("8");
		v_8.addActionListener(actionListener);
		v_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		v_8.setBounds(107, 37, 87, 45);
		frame.getContentPane().add(v_8);
		
		v_9 = new JButton("9");
		v_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		v_9.addActionListener(actionListener);
		v_9.setBounds(204, 37, 87, 45);
		frame.getContentPane().add(v_9);
		
		v_4 = new JButton("4");
		v_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		v_4.addActionListener(actionListener);
		v_4.setBounds(10, 93, 87, 45);
		frame.getContentPane().add(v_4);
		
		v_5 = new JButton("5");
		v_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		v_5.addActionListener(actionListener);
		v_5.setBounds(107, 93, 87, 45);
		frame.getContentPane().add(v_5);
		
		v_6 = new JButton("6");
		v_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		v_6.addActionListener(actionListener);
		v_6.setBounds(204, 93, 87, 45);
		frame.getContentPane().add(v_6);
		
		v_1 = new JButton("1");
		v_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		v_1.addActionListener(actionListener);
		v_1.setBounds(10, 149, 87, 45);
		frame.getContentPane().add(v_1);
		
		v_2 = new JButton("2");
		v_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		v_2.addActionListener(actionListener);
		v_2.setBounds(107, 149, 87, 45);
		frame.getContentPane().add(v_2);
		
		v_3 = new JButton("3");
		v_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		v_3.addActionListener(actionListener);
		v_3.setBounds(204, 149, 87, 45);
		frame.getContentPane().add(v_3);
		
		v_0 = new JButton("0");
		v_0.setFont(new Font("Tahoma", Font.PLAIN, 20));
		v_0.addActionListener(actionListener);
		v_0.setBounds(10, 205, 87, 45);
		frame.getContentPane().add(v_0);
		
		clear = new JButton("CE");
		clear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chain = "";
				textField.setText("");
			}
		});
		clear.setBounds(107, 205, 87, 45);
		frame.getContentPane().add(clear);
		
		calculate = new JButton("=");
		calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("js");
				try {
					String result = String.valueOf(engine.eval(chain));
					chain = result;
					textField.setText(chain);
				} catch (ScriptException e1) {
					e1.printStackTrace();
				}
			}
		});
		calculate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		calculate.setBounds(204, 205, 87, 45);
		frame.getContentPane().add(calculate);
		
		substract = new JButton("/");
		substract.setFont(new Font("Tahoma", Font.PLAIN, 20));
		substract.addActionListener(actionListener);
		substract.setBounds(327, 37, 87, 45);
		frame.getContentPane().add(substract);
		
		multiply = new JButton("*");
		multiply.setFont(new Font("Tahoma", Font.PLAIN, 20));
		multiply.addActionListener(actionListener);
		multiply.setBounds(327, 93, 87, 45);
		frame.getContentPane().add(multiply);
		
		minus = new JButton("-");
		minus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		minus.addActionListener(actionListener);
		minus.setBounds(327, 149, 87, 45);
		frame.getContentPane().add(minus);
		
		plus = new JButton("+");
		plus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		plus.addActionListener(actionListener);
		plus.setBounds(327, 205, 87, 45);
		frame.getContentPane().add(plus);
	}
	
	public void addToChain(String value) {
		chain += value;
		textField.setText(chain);
	}
	
	class AppActionListener implements ActionListener {
		private final App_4 app;
		
		public AppActionListener(App_4 app) {
			this.app = app;
		}
		
		public void actionPerformed(ActionEvent e) {
			if (!(e.getSource() instanceof JButton))
				return;
			
			JButton button = (JButton) e.getSource();
			app.addToChain(button.getText());
		}
	}
}
