package fr.riot.algo.two;
import java.util.Scanner;

public class Date {
	private static final String[] days = new String[] { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez entrer le jour de la semaine (entre 1 et 7)");
		int day = sc.nextInt();
		
		// Q.24
		if (day < 1 || day > 7) {
			while (day < 1 || day > 7) {
				System.out.println("Veuillez entrer un jour de la semaine correct ! (entre 1 et 7)");
				day = sc.nextInt();
			}
		}
		
		System.out.println("Le jour est " + days[day - 1]);
	}
}
