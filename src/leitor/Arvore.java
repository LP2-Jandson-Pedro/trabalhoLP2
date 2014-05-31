package leitor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
/*
	private void lerArvore(BufferedWriter escrita)
	{
		ler(0,escrita);
		try {
			escrita.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ler(int Espacos,BufferedWriter escrita)
	{
		try
		{
			if (this.filho != null) {escrita.write("(");}
			
			escrita.write(this.chave);
			
			if (this.filho != null)
			{
				escrita.write(" ");
				this.filho.ler(this.chave.length() + 2 + Espacos,escrita);
				escrita.write(")");
				escrita.flush();
			}
		
			if (irmao_dir != null)
			{
				escrita.write("\n");
				for (int counter = 0; counter < Espacos; counter++) {escrita.write(" ");}
				this.irmao_dir.ler(Espacos,escrita);
				escrita.flush();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
*/
	
	private void limpaArvore()
	{
		if (this.irmao_dir != null){irmao_dir.limpaArvore();}
		if (this.filho != null){filho.limpaArvore();}
		irmao_dir = null;
		filho = null;
		System.gc();
	}
	
	public boolean lerArquivo(BufferedReader is,BufferedWriter escrita) throws IOException
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
					if (this.filho == null) {return true;}
					else {return false;}
				case '(':
					if (this.filho == null) {this.filho = new Arvore();}
					else
					{
						this.irmao_dir = new Arvore();
						this.irmao_dir.filho = new Arvore();
						this.irmao_dir.lerArquivo(is, escrita);
					}
					break;
				case ' ':
					if (this.filho != null)
					{
						if (this.filho.chave == "")
						{
							if (this.filho.lerArquivo(is, escrita) == true)
							{
								escrita.write(this.chave+" "+this.filho.chave+"\n");
								escrita.flush();
							}
						}
					}
					break;
				default:
					this.chave = this.chave+(char)caracter;
					break;
			}
			if (this.irmao_dir != null){return false;}
			if (this.chave.compareTo("TOP") == 0 && this.filho.chave != "")
			{
				this.limpaArvore();
				this.chave = "";
			}
		}
	return false;
	}
}

