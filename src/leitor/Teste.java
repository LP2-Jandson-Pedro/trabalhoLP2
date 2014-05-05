package leitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Teste {
	
	public static void lerArquivo(InputStream is) throws IOException
	{		
		int caracter;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		caracter = bf.read();
	
		while ( (caracter = bf.read()) != -1){System.out.print((char)caracter);}
	}
	
	public static void main(String [] args)
	{
		try {
			lerArquivo(new FileInputStream("/home/jandson/Documents/arquivo3.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
