package leitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
	
		Arvore arvoreInterna = new Arvore();
		File saida = null;
		FileWriter escrita = null;
		try
		{
			saida = new File("saida.txt");

			//escrita = new FileWriter(saida);
			
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(args[1])));

			if (args.length > 2) {arvoreInterna.lerArquivo(bf, new FileWriter(saida), args[0], args[2], args[3]);}
			else {arvoreInterna.lerArquivo(bf, new FileWriter(saida), args[0], "-", "-");}

			//escrita.flush();
			//escrita.close();
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}