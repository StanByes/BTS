package fr.riot.td_7;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Battleship {
	public static enum GAME_STEP {
		PREPARATION,
		VALIDATION,
		IN_GAME,
		END
	}
	
	private static final Color defaultColor = new Color(238, 238, 238);

	private JFrame frame;
	private JTable playerTable;
	private JTable botTable;
	private JLabel lblAdversaire;
	private JButton action;
	
	private String[] linesName = new String[] { "A", "B", "C", "D", "E" };
	private String[] columnsName = new String[] { "1", "2", "3", "4", "5" };
	
	private final JCheckBox[][] playerEntries = new JCheckBox[5][5];
	private final JCheckBox[][] botEntries = new JCheckBox[5][5];

	private JCheckBox[] playerPawns = new JCheckBox[5];
	private int playerChoiceCount = 0;
	
	private JCheckBox[] botPawns = new JCheckBox[5];
	
	private GAME_STEP step = GAME_STEP.PREPARATION;
	private int round = 0;
	
	private int playerPoint = 0;
	private int botPoint = 0;
	
	private JCheckBox playerAttackSelection = null;
	private JCheckBox[] playerAllAttackSelection = new JCheckBox[25];
	
	private JCheckBox[] botAllAttackSelection = new JCheckBox[25];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Battleship window = new Battleship();
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
	public Battleship() {
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

		UserChoiceListener choiceListener = new UserChoiceListener(this);
		UserAttackListener attackListener = new UserAttackListener(this);
		
		int baseY = 60;
		for (int i = 0; i < 2; i++) {
			int baseX = i == 0 ? 35 : 270;
			for (int x = 0; x < 5; x++) {
				JLabel label = new JLabel(columnsName[x]);
				label.setBounds(baseX + (x * 30), 35, 20, 20);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				frame.getContentPane().add(label);
			}

			for (int y = 0; y < 5; y++) {
				JLabel label = new JLabel(linesName[y]);
				label.setBounds(baseX - 25, baseY + (y * 30), 20, 20);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				frame.getContentPane().add(label);

				for (int x = 0; x < 5; x++) {
					JCheckBox checkBox = new JCheckBox("");
					checkBox.setEnabled(i == 0);
					checkBox.setBackground(defaultColor);
					checkBox.setBounds(baseX + (x * 30), baseY + (y * 30), 20, 20);
					frame.getContentPane().add(checkBox);
					
					if (i == 0) {
						checkBox.addActionListener(choiceListener);
						playerEntries[y][x] = checkBox;
					} else {
						checkBox.addActionListener(attackListener);
						botEntries[y][x] = checkBox;
					}
				}
			}
		}
		
		JSeparator botSeparator = new JSeparator();
		botSeparator.setBounds(244, 36, 180, 2);
		frame.getContentPane().add(botSeparator);
		
		JSeparator playerSeparator = new JSeparator();
		playerSeparator.setBounds(10, 36, 180, 2);
		frame.getContentPane().add(playerSeparator);
		
		JLabel lblNewLabel = new JLabel("VOUS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 180, 14);
		frame.getContentPane().add(lblNewLabel);
		
		playerTable = new JTable();
		playerTable.setBounds(10, 11, 180, 200);
		frame.getContentPane().add(playerTable);
		
		lblAdversaire = new JLabel("ADVERSAIRE");
		lblAdversaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdversaire.setBounds(244, 11, 180, 14);
		frame.getContentPane().add(lblAdversaire);
		
		botTable = new JTable();
		botTable.setBounds(244, 11, 180, 200);
		frame.getContentPane().add(botTable);
		
		action = new JButton("JOUER");
		action.setEnabled(false);
		action.setBounds(169, 227, 101, 23);
		action.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (step.equals(GAME_STEP.VALIDATION)) {
					// Start game
					for (JCheckBox pawn : playerPawns) {
						pawn.setEnabled(false);
					}
					
					for (int i = 0; i < 5; i++) {
						boolean used = false;
						do {
							used = false;

							int randLine = (int) (Math.random() * 5);
							int randRow = (int) (Math.random() * 5);
							
							JCheckBox foundCheckBox = botEntries[randRow][randLine];
							for (JCheckBox botPawn : botPawns) {
								if (botPawn != null && botPawn.equals(foundCheckBox)) {
									used = true;
									break;
								}
							}
							
							if (!used) {
								botPawns[i] = foundCheckBox;
								
								// DEBUG
								// foundCheckBox.setBackground(Color.green);
							}
						} while (used);
					}
					setEnabledAllCheckBoxes(botEntries, true);
			
					step = GAME_STEP.IN_GAME;
					
					action.setEnabled(false);
					action.setText("ATTAQUER");
				} else if (step.equals(GAME_STEP.IN_GAME)) {
					boolean touched = false;
					for (JCheckBox botPawn : botPawns) {
						if (botPawn.equals(playerAttackSelection)) {
							touched = true;
							break;
						}
					}
					
					if (touched) {
						playerAttackSelection.setBackground(Color.blue);
						playerPoint++;
					} else {
						playerAttackSelection.setBackground(Color.red);
					}
					
					playerAllAttackSelection[round] = playerAttackSelection;
					playerAttackSelection.setSelected(false);
					playerAttackSelection.setEnabled(false);
					setEnabledAllCheckBoxes(botEntries, true, playerAllAttackSelection);
					
					if (playerPoint == 5) {
						endGame();
						return;
					}

					// BOT ATTACK
					JCheckBox botAttackSelection;
					boolean alreadyTouched;
					do {
						alreadyTouched = false;

						int randLine = (int) (Math.random() * 5);
						int randRow = (int) (Math.random() * 5);
						
						botAttackSelection = playerEntries[randRow][randLine];
						
						for (JCheckBox previousBotSelection : botAllAttackSelection) {
							if (previousBotSelection == botAttackSelection) {
								alreadyTouched = true;
								break;
							}
						}
					} while (alreadyTouched);

					touched = false;
					for (JCheckBox playerPawn : playerPawns) {
						if (playerPawn.equals(botAttackSelection)) {
							touched = true;
							break;
						}
					}
					
					if (touched) {
						botAttackSelection.setBackground(Color.blue);
						botPoint++;
					} else {
						botAttackSelection.setBackground(Color.red);
					}
					
					botAllAttackSelection[round] = botAttackSelection;
					
					if (botPoint == 5) {
						endGame();
						return;
					}
					
					round++;
					action.setEnabled(false);
				} else if (step.equals(GAME_STEP.END)) {
					resetGame();
				}
			}
		});
		frame.getContentPane().add(action);
	}

	
	class UserChoiceListener implements ActionListener {
		private final Battleship app;
		
		public UserChoiceListener(Battleship app) {
			this.app = app;
		}
		
		public void actionPerformed(ActionEvent e) {
			if (!(e.getSource() instanceof JCheckBox))
				return;

			JCheckBox checkBox = (JCheckBox) e.getSource();
			if (checkBox.isSelected()) {
				app.playerPawns[app.playerChoiceCount] = checkBox;
				app.playerChoiceCount++;

				if (app.playerChoiceCount == 5) {
					setEnabledAllCheckBoxes(app.playerEntries, false, app.playerPawns);
					
					app.action.setEnabled(true);
					app.step = GAME_STEP.VALIDATION;
				}
			} else {
				JCheckBox[] playerPawns = app.playerPawns.clone();
				app.playerPawns = new JCheckBox[5];
				app.playerChoiceCount = 0;

				for (JCheckBox pawn : playerPawns) {
					if (pawn != null && pawn != checkBox) {
						app.playerPawns[app.playerChoiceCount] = pawn;
						app.playerChoiceCount++;
					}
				}

				if (app.step.equals(GAME_STEP.VALIDATION)) {
					setEnabledAllCheckBoxes(app.playerEntries, true, app.playerPawns);
					
					app.action.setEnabled(false);
					app.step = GAME_STEP.PREPARATION;
				}
			}
		}
	}

	class UserAttackListener implements ActionListener {
		private final Battleship app;
		
		public UserAttackListener(Battleship app) {
			this.app = app;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			if (!(event.getSource() instanceof JCheckBox))
				return;

			if (!app.step.equals(GAME_STEP.IN_GAME))
				return;
			
			JCheckBox checkBox = (JCheckBox) event.getSource();
			setEnabledAllCheckBoxes(app.botEntries, !checkBox.isSelected(), new JCheckBox[] { checkBox });

			app.action.setEnabled(checkBox.isSelected());
			playerAttackSelection = checkBox;
		}
	}

	private static void setEnabledAllCheckBoxes(JCheckBox[][] checkBoxes, boolean state) {
		setEnabledAllCheckBoxes(checkBoxes, state, new JCheckBox[0]);
	}
	private static void setEnabledAllCheckBoxes(JCheckBox[][] checkBoxes, boolean state, JCheckBox[] exclude) {
		for (JCheckBox[] userColumn : checkBoxes) {
			for (JCheckBox userEntry : userColumn) {
				boolean excluded = false;
				for (JCheckBox excludeBox : exclude) {
					if (userEntry.equals(excludeBox)) {
						excluded = true;
						break;
					}
				}
				
				if (!excluded) {
					userEntry.setEnabled(state);
				}
			}
		}
	}
	
	private void endGame() {
		this.step = GAME_STEP.END;
		
		setEnabledAllCheckBoxes(botEntries, false);
		setEnabledAllCheckBoxes(playerEntries, false);
		
		action.setText("REJOUER");
		action.setEnabled(true);
	}
	
	private void resetGame() {
		for (JCheckBox[] column : playerEntries) {
			for (JCheckBox entry : column) {
				entry.setSelected(false);
				entry.setEnabled(true);
				entry.setBackground(defaultColor);
			}
		}

		for (JCheckBox[] column : botEntries) {
			for (JCheckBox entry : column) {
				entry.setSelected(false);
				entry.setEnabled(false);
				entry.setBackground(defaultColor);
			}
		}
		
		action.setText("JOUER");
		action.setEnabled(false);
		
		this.playerChoiceCount = 0;
		this.round = 0;
		
		this.playerPawns = new JCheckBox[5];
		this.botPawns = new JCheckBox[5];
		
		this.playerPoint = 0;
		this.botPoint = 0;
		
		this.playerAttackSelection = null;
		
		this.playerAllAttackSelection = new JCheckBox[25];
		this.botAllAttackSelection = new JCheckBox[25];
		
		this.step = GAME_STEP.PREPARATION;
	}
}
