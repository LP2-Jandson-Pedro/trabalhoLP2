package leitor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;



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

	public void lerArvore(FileWriter escrita)
	{ler(0,escrita);}

	public void ler(int Espacos,FileWriter escrita)
	{

		try
		{
			if (filho != null) {escrita.write("(");}
			
			escrita.write(chave);
			
			if (filho != null)
			{
				escrita.write(" ");
				filho.ler(chave.length() + 2 + Espacos,escrita);
				escrita.write(")");
			}
		
			if (irmao_dir != null)
			{
				escrita.write("\n");
				for (int counter = 0; counter < Espacos; counter++) {escrita.write(" ");}
				irmao_dir.ler(Espacos,escrita);
			}
		}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
<<<<<<< HEAD
	private void limpaArvore()
	{
		if (this.irmao_dir != null){irmao_dir.limpaArvore();}
		if (this.filho != null){filho.limpaArvore();}
		irmao_dir = null;
		filho = null;
		System.gc();
	}
	
	public void lerArquivo(BufferedReader is,FileWriter escrita) throws IOException
=======
	public void lerArquivo(BufferedReader is) throws IOException
>>>>>>> dd52c14f0264456a94b6ee37ca7932623288466c
	{		
		int caracter = 0;
		
		while ((caracter = is.read()) != -1)
		{
<<<<<<< HEAD
			switch((char)caracter)
			{
				case '\n':
				case '\r':
					break;
				case ')':
					return;
				case '(':
					if (this.chave.compareTo("TOP") == 0)
					{
						lerArvore(escrita);
						this.limpaArvore();
						this.chave = "";
					}
					if (this.filho == null) {this.filho = new Arvore();}
					else
					{
						this.irmao_dir = new Arvore();
						this.irmao_dir.filho = new Arvore();
						this.irmao_dir.lerArquivo(is,escrita);
					}
					break;
				case ' ':
					if (this.filho.chave == "") {this.filho.lerArquivo(is,escrita);}
					break;
				default:
					this.chave = this.chave+(char)caracter;
					break;
			}
			if (this.irmao_dir != null){return;}		
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
>>>>>>> dd52c14f0264456a94b6ee37ca7932623288466c
		}
	}
}

