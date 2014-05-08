package leitor;

import java.io.BufferedReader;
import java.io.IOException;



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
<<<<<<< HEAD
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
=======
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
>>>>>>> 731606462d66ac83d1227a9c13a005fbbfd4b08e
			}
			
		}
	}
}
