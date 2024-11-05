package fr.riot.td_7;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;

public class App_5 {
	private enum GameStep {
		START, IN_GAME, END
	}
	
	private enum Choices {
		PIERRE(1),
		FEUILLE(2),
		CISEAUX(3);
		
		private int id;
		Choices(int id) {
			this.id = id;
		}
		
		public static Choices getChoiceById(int id) {
			for (Choices choice : Choices.values())
				if (choice.id == id)
					return choice;
			
			return null;
		}
	}
	
	private DefaultComboBoxModel<String> gameCountChoices = new DefaultComboBoxModel<String>(new String[] {"3", "5", "10"});
	private DefaultComboBoxModel<String> gameChoices = new DefaultComboBoxModel<String>(new String[] {"Pierre", "Feuille", "Ciseaux"});
	
	private JFrame frame;
	
	private int matchCount;
	private int[] points = new int[] { 0, 0 };
	private GameStep step = GameStep.START;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_5 window = new App_5();
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
	public App_5() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("SHIFUMI !");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 17));
		title.setBounds(133, 11, 153, 20);
		frame.getContentPane().add(title);
		
		JLabel info = new JLabel("Veuillez choisir le nombre de manches gagnantes");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		info.setBounds(10, 54, 414, 42);
		frame.getContentPane().add(info);
		
		JComboBox<String> choice = new JComboBox<String>();
		choice.setModel(gameCountChoices);
		choice.setBounds(166, 107, 86, 20);
		frame.getContentPane().add(choice);
		
		JLabel score = new JLabel("SCORE : 0 - 0");
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setVisible(false);
		score.setBounds(338, 17, 86, 14);
		frame.getContentPane().add(score);
		
		JButton play = new JButton("START");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (step.equals(GameStep.START)) {
					matchCount = Integer.parseInt((String) choice.getSelectedItem());

					score.setVisible(true);
					info.setText("Veuillez choisir entre Pierre, Feuille et Ciseaux");
					choice.setModel(gameChoices);
					
					play.setText("COMBATRE");
					step = GameStep.IN_GAME;
				} else if (step.equals(GameStep.IN_GAME)) {
					int rValue = (int) (Math.random() * 3) + 1;
					Choices botChoice = Choices.getChoiceById(rValue);
					
					int userChoice = Choices.valueOf(((String) choice.getSelectedItem()).toUpperCase()).id;
					int resultGame = game(userChoice, rValue);

					if (resultGame == 1) {
						info.setText("Gagné ! Le choix de la machine était " + botChoice);
						points[0] = points[0] + 1;
					} else if (resultGame == 2) {
						points[1] = points[1] + 1;
						info.setText("Perdu ! Le choix de la machine était " + botChoice);
					} else {
						info.setText("Egalité !");
					}
					
					score.setText("SCORE : " + points[0] + " - " + points[1]);
					
					if (points[0] == matchCount || points[1] == matchCount) {
						info.setText("Victoire de " + (points[0] == matchCount ? "JOUEUR" : "MACHINE") + ", le choix de la machine était " + botChoice + " !");
						choice.setEnabled(false);
						play.setText("REJOUER");
						step = GameStep.END;
					}
				} else {
					matchCount = 0;
					step = GameStep.START;
					points = new int[] { 0, 0 };

					score.setVisible(false);
					score.setText("SCORE : " + points[0] + " - " + points[1]);
					choice.setModel(gameCountChoices);
					choice.setEnabled(true);
					info.setText("Veuillez choisir le nombre de manches gagnantes");
				}
			}
		});
		play.setBounds(133, 196, 161, 40);
		frame.getContentPane().add(play);
	}
	
	/*
	 * Returns the result of the game, 0 if equality, 1 if player one has won, 2 if player two has won
	 */
	private static int game(int first, int second) {
		boolean playerOneWin = first == 1 && second == 3 || first == 2 && second == 1 || first == 3 && second == 2;
		return first == second ? 0 : playerOneWin ? 1 : 2;
	}
}
