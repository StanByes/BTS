package fr.riot;

import java.util.Scanner;

import fr.riot.algo.one.App;
import fr.riot.algo.two.Conditions;
import fr.riot.algo.two.Date;
import fr.riot.algo.two.Loop;
import fr.riot.algo.two.Switch;
import fr.riot.algo.two.TD_2;
import fr.riot.algo.two.TD_3;
import fr.riot.algo.two.TD_4;
import fr.riot.algo.two.TD_5;
import fr.riot.td_7.App_1;
import fr.riot.td_7.App_2;
import fr.riot.td_7.App_3;
import fr.riot.td_7.App_4;
import fr.riot.td_7.App_5;
import fr.riot.td_7.Battleship;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		String replay = "O";
		do {
			show();
			int v = sc.nextInt();
			
			String[] emptyArgs = new String[0];
			switch(v) {
				case 0: {
					replay = "N";
					System.out.println("Au revoir !");
					break;
				}
				case 1: {
					App.main(emptyArgs);
					break;
				}
				
				case 2: {
					Conditions.main(emptyArgs);
					break;
				}
				
				case 3: {
					Date.main(emptyArgs);
					break;
				}
				
				case 4: {
					Loop.main(emptyArgs);
					break;
				}
				
				case 5: {
					Switch.main(emptyArgs);
					break;
				}
				
				case 6: {
					TD_2.main(emptyArgs);
					break;
				}
				
				case 7: {
					TD_3.main(emptyArgs);
					break;
				}
				
				case 8: {
					TD_4.main(emptyArgs);
					break;
				}
				
				case 9: {
					try {
						TD_5.main(emptyArgs);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
			
				case 10: {
					App_1.main(emptyArgs);
					break;
				}
				
				case 11: {
					App_2.main(emptyArgs);
					break;
				}
				
				case 12: {
					App_3.main(emptyArgs);
					break;
				}
				
				case 13: {
					App_4.main(emptyArgs);
					break;
				}
				
				case 14: {
					App_5.main(emptyArgs);
					break;
				}
				
				case 15: {
					Battleship.main(emptyArgs);
					break;
				}
				
				default: {
					System.err.println("Valeur incorrecte !");
				}
			}
			
			sc.reset();
			Thread.sleep(1500L);
		} while (replay.equals("O"));
		
		sc.close();
	}

	public static void show() {
		System.out.println("####################################################");
		System.out.println("Veuillez entrez un numéro de programme à exécuter :");
		System.out.println("####################################################");
		System.out.println();
		System.out.println("0 : Quitter la navigation");
		System.out.println();
		System.out.println("####################################################");
		System.out.println("APPLICATION CONSOLE");
		System.out.println("####################################################");
		System.out.println();
		System.out.println("1 : Calcul TVA");
		System.out.println("2 : Catégorie par âge (conditions)");
		System.out.println("3 : Vérification de date");
		System.out.println("4 : Boucle de calcul");
		System.out.println("5 : Catégorie par âge (switch)");
		System.out.println("6 : Entrez une valeur entre 50 et 100");
		System.out.println("7 : Devine le nombre et calcul de moyenne (+ challenge)");
		System.out.println("8 : Pierre feuille ciseaux");
		System.out.println("9 : Bataille navale");
		System.out.println();
		System.out.println("####################################################");
		System.out.println("APPLICATION VISUELLE");
		System.out.println("####################################################");
		System.out.println();
		System.out.println("10 : Simple Addition");
		System.out.println("11 : Calculatrice simplifiée");
		System.out.println("12 : Devine le nombre");
		System.out.println("13 : Calculatrice avancée");
		System.out.println("14 : Pierre feuille ciseaux");
		System.out.println("15 : Bataille navale");
		System.out.println();
		System.out.println("####################################################");
	}

}
