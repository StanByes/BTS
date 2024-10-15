package fr.riot.algo.one;
import java.util.Arrays;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Exercice 1
		System.out.println("Entrez votre prénom :");
		
		String userName = scanner.nextLine();
		System.out.println("Bienvenue " + userName);
		
		// Exercice 2
		System.out.println("Veuillez entrer le prix de votre produit HT :");
		double price = scanner.nextDouble();
		
		System.out.println("Veuillez entrer la taxe à appliquer : ");
		double taxe = scanner.nextDouble();
		
		price *= 1 + (taxe / 100);
		
		System.out.println("Le prix avec taxe est de : " + price + " €");
		
		// Exercice 4
		double[] notes = new double[4];
		for (int i = 1; i <= 4; i++) {
			System.out.println("Veuillez entrer la note " + i);
			notes[i - 1] = scanner.nextFloat();
		}
		
		double moyenne = Arrays.stream(notes).sum() / 4;
		System.out.println("Votre moyenne est de " + moyenne + "/20");
		
		scanner.close();
	}

}
