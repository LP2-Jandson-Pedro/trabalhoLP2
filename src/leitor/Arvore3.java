package leitor;

import java.io.BufferedReader;
import java.io.IOException;



public class Arvore3
{
	private String chave;
	private Arvore3 filho;
	
	public Arvore3()
	{
		chave = "";
		filho = null;
	}
/*
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
*/	
	public char lerArquivo(BufferedReader is) throws IOException
	{		
		int caracter = 0;
		char ultimo = '\n';
		while ((caracter = is.read()) != -1)
		{
			switch((char)caracter)
			{
				case '\n':
				case '\r':
					break;
				case ')':
					System.out.print(')');
					return ultimo;
				case '(':
					System.out.print('(');
					if (this.filho == null) {this.filho = new Arvore3();}
					else {this.filho = new Arvore3();}
					break;
				case ' ':
					if (this.filho.chave != "" && ultimo != ' ') {System.out.print("\n ");}
					else {System.out.print(' ');}
					if (this.filho.chave == "") {this.filho.lerArquivo(is);}
					break;
				default:
					System.out.print((char)caracter);
					this.chave = this.chave+(char)caracter;
					break;
			}
			ultimo = (char)caracter;
		}
		return ultimo;
	}
}
