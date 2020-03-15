import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import java.util.Scanner;


public class Ibanmain {

	static Long kto1[] = new Long[10];
	static Long kto2;
	static Long bban;
	static int zcode1, zcode2, pruefz;

	static Long kto;
	static long blz;
	static String laecode = "de"; 
	static final int con = 98;
	
	static int zcode09, modvar;
	static Long zcode15, zcodemitte, zcoderechts, zcoderechtstemp, zcoderechts2, zcoderechts3;
	static String ibannr, pruef, ktonr;
	static String vierUndZwanzigString = "";


	// initialisiere einen statischen Scanner für Eingabe der LänderCode, Blz Kto
	public static Scanner ourScanner = new Scanner(System.in);
	

	public static void main(String[] args) {
		
		leseBankdatenEin();
			
		laecode = normalisiereLaendercode(laecode);

		normalisiereKtoNr(kto);
		System.out.println("Integer Array normalisierte Kto: " + Arrays.toString(kto1)); // Array auslesen wg. führender Nullen

		erstelleBbanNr();
		System.out.println("BBAN-Nummer 18stellig:   " + bban);

		generiereZahlencodes();
		
		erstelleZahlencodeSchritt5();   
		System.out.println("Modulo-Wert aus gegebenen 24stelligem Bankdaten:  " + zcoderechts3);
		
		erstellePruefzahl();
//		System.out.println("Prüfzahl:   " + pruefz);
		
		System.out.println();
		
		erzeugeIban();
		System.out.println("IBAN-Nummer:   " + ibannr);
		
		ourScanner.close();
	}
	
		
			
	private static void leseBankdatenEin() {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String eingabe = null;
	
		System.out.println("Herzlich Willkommen bei der IBAN-Berechnung!\n");
		System.out.println("Bitte geben Sie die BLZ ein:\n");
				
			try {
				eingabe = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				blz = Integer.parseInt(eingabe);
		
		System.out.println();
		System.out.println("Bitte geben Sie die Kto-Nr ein:\n");
				
				try {
					eingabe = br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
				kto = Long.parseLong(eingabe);
	}



	private static void erzeugeIban() {
						
		ibannr = laecode + pruef + blz;

		for (int i = 0; i < 10; i++)  {
			ibannr = ibannr + kto1[i];
			}
	}

	private static void erstellePruefzahl() {
		
		pruefz = (int) (con % zcoderechts3);
		
		if (pruefz < 10) {
	
				pruef = "0" +pruefz;
				System.out.println("pruef = pruef +'0'; -->" +pruef);
				}else {
					pruef = "" +pruefz;
				}
				
	}

	private static void erstelleZahlencodeSchritt5() {

		zcode09 = (int) (bban / 1000000000L);	
		
		System.out.println("bban: ---------->" +bban);
		
		System.out.println("9-stelliger Anteil:  " + zcode09);		// 9stellige Zahl ok
		
		zcode15 = ((((kto * 100) + zcode1) * 100) + zcode2) *100;
		zcode15 = zcode15-((zcode15/ 1000000000000000L) * 1000000000000000L);
		//System.out.print("9- stelliger Anteil Schritt 4:  " + zcode2);		// 15stellige Zahl
		
		//System.out.println("15- stelliger Anteil Schritt 4:  " + zcode15);		// 15stellige Zahl
		System.out.println("zcode09: " +zcode09);
		System.out.println("zcode15 " + zcode15);
		System.out.println("24-Stellen: " +zcode09 +""+ zcode15);
		
		//vierUndZwanzigString = "" +zcode09 + zcode15;
		vierUndZwanzigString = "" +bban +zcode1 + zcode2 +"00";
		
		
		/*
		// KontoNummer: 10-Ziffern!
		String kontoZehnStringStart = Integer.toString(kontoNr);
		String kontoZehnStringFinal = "";

		for (int i = 10; i > kontoZehnStringStart.length(); i--) {
			kontoZehnStringFinal = kontoZehnStringFinal + "0";
		}
		kontoZehnStringFinal = kontoZehnStringFinal + kontoZehnStringStart;

		// BBAN
		String bban = blz + kontoZehnStringFinal;
		*/
		
		
		int modulo97Wert = modulo97(vierUndZwanzigString);
		
		int pruefZahl = 98 - modulo97Wert;
		
		String pruefZahlString = "";
		
		if (pruefZahl < 10) {
			pruefZahlString = "0" + pruefZahl;
		} else {
			pruefZahlString = "" + pruefZahl;
		}
		
		String finalerIban = laecode + pruefZahlString + bban;
		
		System.out.println("Frank's IBAN-Nummer:   " + finalerIban);
		System.out.println("Frank's IBAN-Nummer:   " + finalerIban);
		System.out.println("Frank's IBAN-Nummer:   " + finalerIban);
		
		
		modvar = (int) (zcode09 % 97);
		System.out.println("Inhalt Mod- Variable:  " + modvar);		
			
		zcodemitte = (zcode15 / 100000000) + (modvar * 10000000L);
//		System.out.println("Inhalt nach Addition:   " + zcodemitte);
	
		modvar = (int) (zcodemitte % 97);
		System.out.println("Modulo:   " + modvar);

			
		zcoderechtstemp = zcode15 / 100000000;
		zcoderechts = ((zcode15 - (zcoderechtstemp * 100000000)) / 10) + (modvar * 10000000);
//		System.out.println("Inhalt Coderechts:   " + zcoderechts);
		
		zcoderechts2 = (zcoderechts % 97) * 10;
//		System.out.println("Inhalt Coderechts2:   " + zcoderechts2);
		
		zcoderechts3 = zcoderechts2 % 97;
//		System.out.println("Inhalt Coderechts3:   " + zcoderechts3);
	
	}
	
		

	private static int modulo97(String vierUndZwanzigStringLang) {
		// TODO Auto-generated method stub

			// int moduloWert;
			String checkSum = vierUndZwanzigStringLang;

			while (checkSum.length() > 9) {
				checkSum = Integer.parseInt(checkSum.substring(0, 9)) % 97 + checkSum.substring(9);
			}
			int checkNum = Integer.parseInt(checkSum) % 97;

			System.out.println("checkNum: " +checkNum);
			return checkNum;
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
		return laecode.toUpperCase(); // Normalisierung zu Grossbuchstaben
	}
}
