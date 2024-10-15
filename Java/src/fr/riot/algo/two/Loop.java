package fr.riot.algo.two;

public class Loop {
	public static void main(String[] args) {
		// Q.11
		/*
		for (int i = 0; i <= 10; i++)
			show(5, i);
		*/
		
		// Q.9
		/*
		for (int i = 0; i <= 20; i++)
			for (int j = 0; j <= 10; j++)
				show(i, j);
		*/
		
		// Q.10
		/*
		System.out.println("Table multiplication");
		System.out.println("----------------------------------------------");
		for (int i = 0; i <= 20; i++) {
			System.out.println("Table des " + i);
			
			for (int j = 0; j <= 10; j++)
				show(i, j);

			System.out.println("----------------------------------------------");
		}
		*/
		
		// Q.12
		/*
		System.out.println("décompte");
		for (int i = 10; i >= 0; i--)
			System.out.println(i);
		System.out.println("Départ !");
		*/
		
		// Q.13
		/*
		int i = 0;
		while (i <= 10) {
			show(5, i);
			i++;
		}
		*/
		
		// Q.14
		int i = 0;
		do {
			show(5, i);
			i++;
		} while (i <= 10);
	}
	
	private static void show(int i, int j) {
		System.out.println(j + " x " + i + " : " + (j * i));
	}
}
