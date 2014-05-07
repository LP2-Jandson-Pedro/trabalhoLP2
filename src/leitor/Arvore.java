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
		int caracter = 0;
		
		while (caracter != -1)
		{
			caracter = is.read();
			if (caracter != -1)
			{
				switch((char)caracter)
				{
					case '\n':
					case '\r':
						break;
					case ')':
						return;
					case '(':
						if (this.filho == null) {this.filho = new Arvore();}
						else
						{
							this.irmao_dir = new Arvore();
							this.irmao_dir.filho = new Arvore();
							this.irmao_dir.lerArquivo(is);
						}
						break;
					case ' ':
						if (this.filho.chave == "") {this.filho.lerArquivo(is);}
						break;
					default:
						this.chave = this.chave+(char)caracter;
						break;
				}
				if (this.irmao_dir != null){return;}
			}
			
		}
	}
	

	public static void main(String [] args)
	{
		BufferedReader bf;
		Arvore oi = new Arvore();
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\labisic\\Downloads\\arquivo3.txt")));
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
