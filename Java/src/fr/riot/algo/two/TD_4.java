package fr.riot.algo.two;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TD_4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] points = new int[] { 0, 0 };
		
		try {
			// ETAPE 1 //
			int attemptCount = 0;
			do {
				System.out.println("Veuillez choisir en combien de manches gagnantes la partie doit se dérouler (3-5-10) :");
				attemptCount = sc.nextInt();
			} while (attemptCount != 3 && attemptCount != 5 && attemptCount != 10);
			
			
			while (points[0] < attemptCount && points[1] < attemptCount) {
				// ETAPE 2 //
				int userChoice = 0;
				do {
					System.out.println("Veuillez choisir entre Pierre (1), Feuille (2) ou Ciseaux (3)");
					userChoice = sc.nextInt();
				} while (userChoice != 1 && userChoice != 2 && userChoice != 3);
				
				// ETAPE 3 //
				int rValue = (int) (Math.random() * 3) + 1;
				
				// ETAPE 4 //
				Thread.sleep(3 * 1000);
				int resultGame = game(userChoice, rValue);
				
				// ETAPE 5 //
				if (resultGame == 1) {
					System.out.println("Victoire du joueur");
					points[0] = points[0] + 1;
				} else if (resultGame == 2) {
					points[1] = points[1] + 1;
					System.out.println("Victoire de la machine");
				} else {
					System.out.println("Egalité !");
				}
				
				System.out.println("Joueur : " + points[0] + " points");
				System.out.println("Machine : " + points[1] + " points");
				
				// ETAPE 6 //
			}
			
			// ETAPE 7 //
			System.out.println("Fin de la partie ! Félicitations à " + (points[0] == attemptCount ? " JOUEUR" : " MACHINE"));
			
			// ETAPE 8 //
			System.out.println("Souhaitez-vous rejouer ? (1)");
			if (sc.nextInt() == 1) {
				main(args);
			} else {
				System.exit(0);
			}
			
		} catch (InputMismatchException exception) {
			System.err.println("Valeur invalide ! Fin d'exécution.");
			System.exit(0);
		} catch (InterruptedException exception) {
			System.err.println("Une erreur est survenue ! Fin d'exécution.");
			System.exit(0);
		}	
	}
	
	/*
	 * Returns the result of the game, 0 if equality, 1 if player one has won, 2 if player two has won
	 */
	private static int game(int first, int second) {
		boolean playerOneWin = first == 1 && second == 3 || first == 2 && second == 1 || first == 3 && second == 2;
		return first == second ? 0 : playerOneWin ? 1 : 2;
	}
}
