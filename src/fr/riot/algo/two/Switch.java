package fr.riot.algo.two;
import java.util.Scanner;

public class Switch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez entrer votre âge");
		int age = sc.nextInt();
		
		float cotisation = 0;
		if (age >= 40) {
			System.out.println("Vous êtes vétérant");
			cotisation = 65;

			System.out.println("Veuillez entrer votre genre (1 = homme / 2 = femme)");
			int genre = sc.nextInt();
			switch(genre) {
				case 2: {
					cotisation *= 0.9f;
				}
			}
		} else if (age >= 18) {
			cotisation = 75;
			System.out.println("Vous êtes majeur");
		} else {
			cotisation = 55;
			System.out.println("Vous êtes mineur");
		}
		
		System.out.println(cotisation);
	}

}
