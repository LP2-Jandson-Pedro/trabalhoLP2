package leitor;

import java.io.BufferedReader;
import java.io.IOException;



public class Arvore2
{
	private String chave;
	private Arvore2 filho;
	private Arvore2 irmao_dir;
	
	public Arvore2()
	{
		chave = "";
		filho = null;
		irmao_dir = null;
	}
	
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
	
	public void deleta()
	{
		if (this.irmao_dir != null) {this.irmao_dir.deleta();}
		if (this.filho != null) {this.filho.deleta();}
		this.irmao_dir = null;
		this.filho = null;
	}
	
	public void lerArquivo(BufferedReader is) throws IOException
	{		
		int caracter = 0;
		
		while ((caracter = is.read()) != -1)
		{
				switch((char)caracter)
				{
					case '\n':
					case '\r':
						break;
					case ')':
						//System.out.println(this.chave);
						return;
					case '(':
						if (this.chave == "TOP" && this.filho.chave != null)
						{
							this.deleta();
							this.filho = new Arvore2();
						}
						else
						{
							if (this.filho == null) {this.filho = new Arvore2();}
							else
							{
								this.irmao_dir = new Arvore2();
								this.irmao_dir.filho = new Arvore2();
								this.irmao_dir.lerArquivo(is);
							}
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
