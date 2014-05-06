package leitor;

import java.io.*;  
import java.util.StringTokenizer;

public class Teste {
	
	public static void LePalavras()
	{
		BufferedReader leitor;
		try {
			leitor = new BufferedReader(new FileReader("/home/jandson/Documents/arquivo3.txt") );
			String linha = leitor.readLine();
			StringTokenizer st = new StringTokenizer(linha, " "); //passa o arq e o token
			
			while (st.hasMoreTokens()) // enquanto existir algo para ler  
		    {  
				System.out.println("palavra1:"+ st.nextToken());// faz qualquer coisa  
			}  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
	}    
	
	public static void main(String [] args)
	{
			LePalavras();
	}
}
