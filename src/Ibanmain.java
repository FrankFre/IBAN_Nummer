import java.util.Arrays;

public class Ibanmain {

	static int[] blza = new int[10]; 		// Hilfs-array für "Nullen-auffüllen"   Klassenvariable !
	static int kto2;
		
	

	public static void main(String[] args) {

		String laecode = "de"; 				// Initialisierung mit worst-case Ländercode
		long blz = 12345678;
		int kto = 123456;
	
		long bban;

		int buchst1;
		int buchst2;
		

		laecode = normalisiereLaendercode(laecode);
		System.out.println(laecode);
		
		
		normalisiereKtoNr(kto);
	    System.out.println("Integer Array: " + Arrays.toString(blza));			// Array auslesen wg. führender Nullen 

	}
	
	

	private static int[] normalisiereKtoNr(int kto) {				// Algorithmus zum Auffuellen mit Nullen			
		
		int kto2 = kto; 
	
		while (kto2 > 0) {
			
			System.out.println(kto2);
			
			for (int i = 9; i <= 8; i--) {
							
				blza[i] = kto2 % 10;
				
				System.out.println("Test" + blza[i]);

//				if (kto2 < 1) {
//					blza[i] = 0;
				}
			kto2 = kto2 / 10;
			}

		return blza;
		
	}
	
	


	
	
	private static String normalisiereLaendercode(String laecode) {

		return laecode.toUpperCase(); 							// Normalisierung Grossbuchstaben
	}

	
	
	
	
	
	
//	public static String normalisieren(String laecode, long blz, int kto) {
//
//		
//
//
//
//		for (int i = 9; i <= 0; i--) { // Erzeugung der BBAN
//
//			temp = temp + (blza[i] * faktor);
//			faktor = faktor * 10;
//
//		}
//
//		bban = temp + (blz * 10000000000L);
//
//		System.out.println(bban);
//
//		// Generierung der Prüfziffer
//
//		char c = laecode.charAt(1); // Erzeugung des Zahlencodes der Länderbuchstaben
//		int buchst1 = ((int) (c)) - 65 + 9;
//
//		char d = laecode.charAt(0); // Erzeugung des Zahlencodes der Länderbuchstaben
//		int buchst2 = ((int) (d)) - 65 + 9;
//
//		return laecode;
//
//	}

}
