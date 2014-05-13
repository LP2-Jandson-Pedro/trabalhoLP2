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
	/*
	public void lerArquivo(BufferedReader is) throws IOException
	{
		int caracter = 0;
		while (caracter != -1)
		{
			switch((char)caracter)
			{
			case '\n':
			case '\r':
				break;
			case '(':
				
			}
		}
	}
	*/
	
	private void limpaArvore()
	{
		if (this.irmao_dir != null){irmao_dir.limpaArvore();}
		if (this.filho != null){filho.limpaArvore();}
		irmao_dir = null;
		filho = null;
	}
	
	public void lerArquivo(BufferedReader is) throws IOException
	{		
		int caracter = 0;
		
		while (caracter != -1)
		{
			caracter = is.read();
			if (caracter != -1)
			{
				char atual = (char)caracter;
				switch(atual)
				{
					case '\n':
					case '\r':
						break;
					case ')':
						return;
					case '(':
						if (this.chave.contains("T") &&this.chave.contains("O")&&this.chave.contains("P"))
						{
							//lerArvore();
							System.out.println("aqui");
							this.limpaArvore();
							this.filho = new Arvore();
							this.chave = "";
						}
						if (this.filho == null) {this.filho = new Arvore();}
						else
						{
							this.irmao_dir = new Arvore();
							this.irmao_dir.filho = new Arvore();
							this.irmao_dir.lerArquivo(is);
						}
						break;
					case ' ':
						if (this.filho != null)
						{
							if (this.filho.chave == "") {this.filho.lerArquivo(is);}
						}
						break;
					default:
						this.chave = this.chave+(char)caracter;
						break;
				}
				if (this.irmao_dir != null){return;}
				
			}
		}
	}
}
