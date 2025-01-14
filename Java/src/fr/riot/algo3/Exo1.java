package fr.riot.algo3;

import java.util.Scanner;

public class Exo1 {

	public static void main(String[] args) {
		String replay = "O";
		
		Scanner sc = new Scanner(System.in);
		while (replay.equals("O")) {
			int choice, nb, max, resultat;
			
			System.out.println("Que souhaitez vous faire (1 : factorielle ; 2 : multiplication)");
			choice = sc.nextInt();
			
			if (choice == 1) {
				System.out.println("Saisir un nombre pour afficher sa factorielle : ");
				nb = sc.nextInt();
				
				resultat = factorielle(nb);
				System.out.println("La factorielle de " + nb + " est " + resultat);
			} else if (choice == 2) {
				System.out.println("Saisir un nombre pour afficher sa table de multiplication :");
				nb = sc.nextInt();
				
				System.out.println("Jusqu'où souhaitez vous aller ? ");
				max = sc.nextInt();
				
				System.out.println("*****");
				tableMultiplication(nb, max);
				System.out.println("*****");
			}
			
			System.out.println("Souhaitez-vous relancer le programme (O/N) ? ");
			replay = sc.next();
		}
		
		sc.close();
	}
	
	static int factorielle(int nb) {
		int resultat = nb;
		for (int i = nb - 1; i >= 1; i--)
			resultat *= i;
		return resultat;
	}
	
	static void tableMultiplication(int n, int nb) {
		System.out.println("Table des " + n + " : ");
		for (int i = 1; i <= nb; i++)
			System.out.println(i + "*" + n + "=" + (i * n));
	}

}
