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
	
<<<<<<< HEAD
		Arvore arvoreInterna = new Arvore();
		File saida = null;
		FileWriter escrita = null;
=======
		Arvore2 arvoreInterna = new Arvore2();
>>>>>>> dd52c14f0264456a94b6ee37ca7932623288466c
		try
		{
			saida = new File("saida.txt");

			escrita = new FileWriter(saida);
				//writerBuffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saida)));
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(args[1])));

			if (args[0].compareTo("-p")== 0){arvoreInterna.lerArquivo(bf, escrita);}

			escrita.flush();
			escrita.close();
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}
//C:\\Users\\labisic\\Downloads\\arquivo3.txt