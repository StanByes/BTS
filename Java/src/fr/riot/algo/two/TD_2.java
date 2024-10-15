package fr.riot.algo.two;
import java.util.Scanner;

public class TD_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez entrer un nombre entier :");
		int input = sc.nextInt();
		
		if (input < 50 || input > 100) {
			while (input < 50 || input > 100) {
				System.out.println("Valeur incorrecte ! Vous devez entre un nombre compris entre 50 et 100 inclus");
				input = sc.nextInt();
			}
		}
		
		System.out.println("Le nombre " + input + " est bien compris entre 50 et 100");
	}
}
