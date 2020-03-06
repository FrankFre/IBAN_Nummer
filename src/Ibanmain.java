import java.util.Arrays;

public class Ibanmain {

	static int[] kto1 = new int[10]; // Hilfs-array für "Nullen-auffüllen" Klassenvariable !
	static int kto2;
	static Long bban;
	static int i;
	static int zcode1, zcode2;

	static int kto = 123456;
	static long blz = 12345678;
	static int zcode11;
	static Long zcode12;
	static String laecode = "de"; 
	
	/* Variable für Aufg. 5 */
	
	static int modvar;
	
	


	public static void main(String[] args) {

		
	
		laecode = normalisiereLaendercode(laecode);
		System.out.println(laecode);

		normalisiereKtoNr(kto);
		System.out.println("Integer Array: " + Arrays.toString(kto1)); // Array auslesen wg. führender Nullen

		erstelleBbanNr();
		System.out.println(bban);

		generiereZahlencodes();
		System.out.println(zcode1);
		System.out.println(zcode2);
		
		
		
		erstelleZahlencodeSchritt5();
		
		
		
	}

	private static void erstelleZahlencodeSchritt5() {

		zcode11 = (int) (bban /    1000000000L);
		
		System.out.println(zcode11);		// 9stellige Zahl
		
		zcode12 = bban -(zcode11 * 1000000000L);
		
		System.out.println(zcode12);		// 15stellige zahl
		
		
		modvar = (int) (zcode12 % 97);
		
		System.out.println(modvar);
		
		zcode12 = zcode12 + (modvar * 1000000000000000L);
		
		System.out.println(zcode12);
		
	}




	private static void generiereZahlencodes() {
	
		zcode1 = (laecode.charAt(0) - 64 + 9);
		zcode2 = (laecode.charAt(1) - 64 + 9);

	}
	
	

	private static Long erstelleBbanNr() {


		bban = (blz * 10000000000L) + kto;

		return bban;
		
		

	}

	private static int[] normalisiereKtoNr(int kto) { // Algorithmus zum Auffuellen mit Nullen

		int kto2 = kto;

		i = 9;

		while (kto2 > 0) {

			kto1[i] = kto2 % 10;

			kto2 = kto2 / 10;
			i--;

		}

		return kto1;

	}

	private static String normalisiereLaendercode(String laecode) {

		return laecode.toUpperCase(); // Normalisierung Grossbuchstaben
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
