package fr.riot.algo.two;
import java.awt.Toolkit;
import java.util.Scanner;

public class TD_3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// Ex 1
		/*
		int value = (int) (Math.random() * (100 - 1)) + 1;
		
		System.out.println("A vous de jouer !");
		System.out.println("Trouvez la valeur correcte :");
		
		int input = sc.nextInt();
		while (input != value) {
			if (input > value)
				System.out.println("Plus petit !");
			else if (input < value)
				System.out.println("Plus grand !");
			
			System.out.println("Entrez une nouvelle valeur :");
			input = sc.nextInt();
		}
		
		System.out.println("Bien joué ! La bonne valeur était " + value);
		*/
		
		
		// Ex 2
		/*
		System.out.println("Combien de notes voulez-vous entrer ?");
		int count = sc.nextInt();
		if (count < 1) {
			while (count < 1) {
				System.out.println("Veuillez entre un nombre de notes supérieur ou égal à 1");
				count = sc.nextInt();
			}
		}
		
		float[] notes = new float[count];
		float moyenne = 0;
		for (int i = 0; i < count; i++) {
			System.out.println("Entrez la note :");
			
			notes[i] = sc.nextFloat();
			moyenne += notes[i];
		}
		moyenne /= count;
		
		int higherNotesCount = 0;
		for (float note : notes)
			if (note > moyenne)
				higherNotesCount++;
		
		System.out.println("Il y a " + higherNotesCount + " notes au-dessus de la moyenne");
		*/
		
		// Challenge
		int[] countTotal = new int[2];
		while (countTotal[0] < 3 && countTotal[1] < 3) {
			System.out.println("Veuillez entre la borne maximum");
			int max = sc.nextInt();
			int value = (int) (Math.random() * (max - 1)) + 1;
			
			int[] count = new int[2];
			do {
				for (int i = 0; i < 2; i++) {
					System.out.println("Au joueur " + (i + 1) + " de jouer !");
					System.out.println("Trouvez la valeur correcte :");
					
					int input = sc.nextInt();
					int j = 1;
					while (input != value) {
						if (input > value)
							System.out.println("Plus petit !");
						else if (input < value)
							System.out.println("Plus grand !");
						
						System.out.println("Entrez une nouvelle valeur :");
						input = sc.nextInt();
						
						j++;	
					}
					
					count[i] = j;
				}
				
				if (count[0] == count[1]) {
					System.out.println("Egalité ! On recommence la partie :");
					value = (int) (Math.random() * (max - 1)) + 1;
				}
			} while (count[0] == count[1]);
			
			System.out.println("Le joueur 1 a trouvé en " + count[0] + " coups");
			System.out.println("Le joueur 2 a trouvé en " + count[1] + " coups");
			if (count[0] < count[1])
				countTotal[0] = countTotal[0] + 1;
			else 
				countTotal[1] = countTotal[1] + 1;
		}
		
		System.out.println("Le joueur 1 a gagné " + countTotal[0] + " manches");
		System.out.println("Le joueur 2 a gagné " + countTotal[1] + " manches");
		
		if (countTotal[0] == 3) {
			Toolkit.getDefaultToolkit().beep();
		} else if (countTotal[1] == 3) {
			try {
				Toolkit.getDefaultToolkit().beep();
				Thread.sleep(2 * 1000);
				Toolkit.getDefaultToolkit().beep();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
