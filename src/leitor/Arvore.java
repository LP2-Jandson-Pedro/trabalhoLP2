package leitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;

public class Arvore
{
	private String chave;
	private Arvore filho;
	private Arvore irmao_dir;
	
	public Arvore()
	{
		chave = "";
		filho = null;
		irmao_dir = null;
	}
	
	public void insereChave(char novaChave) {chave = chave + novaChave;}

	public void lerArvore() {ler(0);}

	public void ler(int Espacos)
	{
		if (filho != null) {System.out.print("(");}
		System.out.print(chave);
		if (filho != null) {
			System.out.print(" ");
			filho.ler(chave.length() + 2 + Espacos);
			System.out.print(")");
		}
		if (irmao_dir != null)
		{
			System.out.print("\n");
			for (int counter = 0; counter < Espacos; counter++) {System.out.print(" ");}
			irmao_dir.ler(Espacos);
		}
	}
	
	public void novoIrmaodir()
	{
		this.irmao_dir = new Arvore();
	}
	
	public void novoFilho()
	{
		this.filho = new Arvore();
	}
	
	public void lerArquivo(BufferedReader is) throws IOException
	{		
		int caracter;
		char ultimo = '.';
		
		while ((caracter = is.read()) != -1)
		{
			
			switch((char)caracter)
			{
				case ')':
					ultimo = (char)caracter;
					return;
				case '(':
					if (this.filho != null)
					{		
						this.irmao_dir = new Arvore();
						this.irmao_dir.lerArquivo(is);
					}
					ultimo = (char)caracter;
					break;
				case ' ':
					if (this.filho == null && ultimo != ' ')
					{
						this.filho = new Arvore();
						this.filho.lerArquivo(is);
						ultimo = (char)caracter;
					}
					break;
				default:
					this.chave = this.chave+(char)caracter;
					ultimo = (char)caracter;
					break;
			}
		}
	}
	

	public static void main(String [] args)
	{
		BufferedReader bf;
		Arvore oi = new Arvore();
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream("/home/jandson/Documents/arquivo3.txt")));
			oi.lerArquivo(bf);
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		oi.lerArvore();
	}

}
