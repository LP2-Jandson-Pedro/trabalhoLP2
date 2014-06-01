package leitor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static Arvore arvoreInterna = new Arvore();
	private static BufferedReader bf = null;
	private static BufferedWriter escrita = null;
	private static ArvorePatricia substantivos = new ArvorePatricia();
	private static ArvorePatricia verbos = new ArvorePatricia();
	private static ArvorePatricia adjetivos = new ArvorePatricia();
	private static ArvorePatricia adverbios = new ArvorePatricia();
	
	public static void main(String[] args) {
		
		
		
		try
		{			
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]))); 
			arvoreInterna.lerArquivo(bf, substantivos, verbos, adjetivos, adverbios);

			escrita.flush();
			escrita.close();
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e){e.printStackTrace();}
		
	}
}