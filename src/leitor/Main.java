package leitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
	
		Arvore2 arvoreInterna = new Arvore2();
		try
		{
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(args[1])));
			switch(args[0])
			{
				case "-p":
					arvoreInterna.lerArquivo(bf);
					arvoreInterna.lerArvore();
					break;
				case "-s":
					break;
			}
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