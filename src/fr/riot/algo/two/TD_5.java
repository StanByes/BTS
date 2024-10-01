package fr.riot.algo.two;
import java.util.Scanner;

public class TD_5 {
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		
		int[][] tabjoueur = new int[10][10];
		int[][] tabordi = new int[10][10];
		
		int[][] tabJoueurCache = new int[10][10];
		int[][] tabOrdiCache = new int[10][10];
		
		int nbPionTrouveJoueur = 0;
		int nbPionTrouveOrdi = 0;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				tabjoueur[i][j] = 0;
				tabordi[i][j] = 0;

				tabJoueurCache[i][j] = 0;
				tabOrdiCache[i][j] = 0;
			}
		}
		
		for (int i = 0; i < 5; i++) {
			System.out.println("Veuillez entrer la position de votre pion n°" + (i + 1) + " sous le format : ligne colonne (1 4)");

			Integer line = null;
			Integer row = null;
			do {
				int[] userInput = awaitUserInput(sc);
				line = userInput[0] - 1;
				row = userInput[1] - 1;
					
				if (tabjoueur[line][row] == 1)
					System.out.println("Un bateau est déjà placé sur cette position");
			} while (tabjoueur[line][row] == 1);
			
			tabjoueur[line][row] = 1;
		}

		for (int i = 0; i < 5; i++) {
			int randRow, randLine;
			do {
				randLine = (int) (Math.random() * 10);
				randRow = (int) (Math.random() * 10);
				
				System.out.println(randLine);
				System.out.println(randRow);
			} while (tabordi[randLine][randRow] == 1);
			
			tabordi[randLine][randRow] = 1;
		}
		
		affichageTab(tabjoueur, 10);
		affichageTab(tabordi, 10);
		
		do {
			System.out.println("A vous de Jouer !");
			
			int[] userInput = awaitUserInput(sc);
			int line = userInput[0] - 1;
			int row = userInput[1] - 1;
			
			System.out.println("Tir en cours");
			Thread.sleep(2 * 1000);
			
			if (tabJoueurCache[line][row] == 2 || tabJoueurCache[line][row] == 3) {
				System.out.println("Tir à blanc!");
			} else {
				if (tabordi[line][row] == 1) {
					System.out.println("Touché !");
					nbPionTrouveJoueur++;
					
					tabJoueurCache[line][row] = 2;
				} else {
					System.out.println("Raté !");
					
					tabJoueurCache[line][row] = 3;
				}
			}
			
			affichageTabCache(tabJoueurCache, 10);
			
			
			System.out.println("Tour de l'ordinateur");
			line = (int) (Math.random() * 10);
			row = (int) (Math.random() * 10);
			
			System.out.println("Tir en cours");
			Thread.sleep(2 * 1000);
			
			if (tabOrdiCache[line][row] == 2 || tabOrdiCache[line][row] == 3) {
				System.out.println("Tir à blanc!");
			} else {
				if (tabjoueur[line][row] == 1) {
					System.out.println("Touché !");
					nbPionTrouveOrdi++;
					
					tabOrdiCache[line][row] = 2;
				} else {
					System.out.println("Raté !");
					
					tabOrdiCache[line][row] = 3;
				}
			}
			
			affichageTabCache(tabOrdiCache, 10);
		} while (nbPionTrouveJoueur != 5 || nbPionTrouveJoueur != 5);
		
		System.out.println("Fin de la partie !");
		if (nbPionTrouveJoueur == 5)
			System.out.println("Félicitations à vous pour avoir gagné !");
		else if (nbPionTrouveOrdi == 5)
			System.out.println("Félicitations à l'ordinateur pour avoir gagné !");
		
		System.out.println("Souhaitez-vous rejouer ? (1)");
		if (sc.nextInt() == 1)
			main(args);
		else
			System.exit(0);
	}

	private static void affichageTab(int[][] tab, int nbCase) {
		System.out.print("    ");
		for (int i = 1; i <= nbCase; i++)
			System.out.print(i + " ");
		
		for (int line = 0; line < nbCase; line++) {
			System.out.println();
			System.out.print((line + 1) + "   ");
			for (int row = 0; row < nbCase; row++) {
				System.out.print(tab[line][row] == 1 ? "o" : "~");
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	private static void affichageTabCache(int[][] tab, int nbCase) {
		System.out.print("    ");
		for (int i = 1; i <= nbCase; i++)
			System.out.print(i + " ");
		
		for (int line = 0; line < nbCase; line++) {
			System.out.println();
			System.out.print((line + 1) + "   ");
			for (int row = 0; row < nbCase; row++) {
				System.out.print(tab[line][row] == 3 ? "x" : tab[line][row] == 2 ? "o" : "?");
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	private static int[] awaitUserInput(Scanner sc) {
		Integer line = null;
		Integer row = null;
		do {
			String[] values = new String[0];
			do {
				String input = sc.nextLine();
				values = input.split(" ");
				
				if (values.length < 2)
					System.out.println("Veuillez entrer deux valeurs sous le format : ligne colonne (1 4)");
			} while (values.length != 2);
			
			try {
				line = Integer.parseInt(values[0]);
				row = Integer.parseInt(values[1]);
			} catch (NumberFormatException exception) {
				System.out.println("Les valeurs entrées ne sont pas des nombres entiers, veuillez entrer à nouveau les valeurs");
			}
		} while (line == null && row == null);
		
		return new int[] { line, row };
	}
}
