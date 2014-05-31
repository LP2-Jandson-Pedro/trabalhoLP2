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

	public static void main(String[] args) {
	
		Arvore arvoreInterna = new Arvore();
		BufferedWriter escrita = null;
		try
		{
			escrita = new BufferedWriter(new FileWriter(new File("saida.txt")));
			
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));

			arvoreInterna.lerArquivo(bf, escrita);

			escrita.flush();
			escrita.close();
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
}