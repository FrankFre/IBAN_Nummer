import java.util.Arrays;

public class Ibanmain {

	static Long kto1[] = new Long[10];
	static Long kto2;
	static Long bban;
	static int zcode1, zcode2, pruefz;

	static Long kto = 123456L;
	static long blz = 12345678;
	static final int con = 98;
	
	static int zcode09, modvar;
	static Long zcode15, zcodemitte, zcoderechts, zcoderechtstemp, zcoderechts2, zcoderechts3;
	static String laecode = "de"; 
		
	static String ibannr, pruef, ktonr;
	
	


	public static void main(String[] args) {

	
		laecode = normalisiereLaendercode(laecode);
		System.out.println(laecode);

		normalisiereKtoNr(kto);
		System.out.println("Integer Array normalisierte Kto: " + Arrays.toString(kto1)); // Array auslesen wg. führender Nullen

		erstelleBbanNr();
		System.out.println("BBAN-Nummer 18stellig:   " + bban);

		generiereZahlencodes();
		System.out.print("Zahlencode 1.Buchstabe:  " + zcode1 + ";   ");
		System.out.println("Zahlencode 2.Buchstabe:  " + zcode2);
		
	
		erstelleZahlencodeSchritt5();
		System.out.println("Modulo-Wert aus gegebenen 24stelligem Bankdaten:  " + zcoderechts3);
		
		erstellePruefzahl();
		System.out.println("Prüfzahl:   " + pruefz);
		
		erzeugeIban();
		System.out.println("IBAN-Nummer:   " + ibannr);
	
	}
	
		
	
//	public static String erzeugeIban(String laenderkennung, String blz, String nummer)  {
//		
		
		
	private static void erzeugeIban() {
						
		ibannr = laecode + pruefz + blz;

		for (int i = 0; i < 10; i++)  {
			
			ibannr = ibannr + kto1[i];
			}
		
	}



	private static void erstellePruefzahl() {
		
		pruefz = (int) (con % zcoderechts3);
		
		if (pruefz < 10) {
	
				pruef = pruef +'0';
				}
	
		pruef = pruef + pruefz;
				
	}




	private static void erstelleZahlencodeSchritt5() {

		zcode09 = (int) (bban / 1000000000L);		
		System.out.println("9-stelliger Anteil:  " + zcode09);		// 9stellige Zahl
		
		zcode15 = ((((kto * 100) + zcode1) * 100) + zcode2) *100;
		System.out.println("15- stelliger Anteil mit Ländercode:  " + zcode15);		// 15stellige Zahl
		
		
		
		modvar = (int) (zcode09 % 97);
		System.out.println("Inhalt Mod- Variable:  " + modvar);
			
		zcodemitte = (zcode15 / 100000000) + (modvar * 10000000L);
		System.out.println("Inhalt nach Addition:   " + zcodemitte);
	
		modvar = (int) (zcodemitte % 97);
		System.out.println("Modulo:   " + modvar);
		

			
		zcoderechtstemp = zcode15 / 100000000;
		zcoderechts = ((zcode15 - (zcoderechtstemp * 100000000)) / 10) + (modvar * 10000000);
		System.out.println("Inhalt Coderechts:   " + zcoderechts);
		
		zcoderechts2 = (zcoderechts % 97) * 10;
		System.out.println("Inhalt Coderechts2:   " + zcoderechts2);
		
		zcoderechts3 = zcoderechts2 % 97;
		System.out.println("Inhalt Coderechts3:   " + zcoderechts3);
	
		
	
	}




	private static void generiereZahlencodes() {
	
		zcode1 = (laecode.charAt(0) - 64 + 9);
		zcode2 = (laecode.charAt(1) - 64 + 9);

	}


	private static Long erstelleBbanNr() {

		bban = (blz * 10000000000L) + kto;

		return bban;
	
	}
	

	private static Long normalisiereKtoNr(Long kto) { // Algorithmus zum Auffuellen mit Nullen

		kto2 = kto;

		for (int i = 9; i >= 0; i--) {
			kto1[i] = kto2 % 10;
			kto2 = kto2 / 10;
		}

		return kto;

	}

	private static String normalisiereLaendercode(String laecode) {
		return laecode.toUpperCase(); // Normalisierung Grossbuchstaben
	}
	


}
