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
	
	private void limpaArvore()
	{
		if (this.irmao_dir != null){irmao_dir.limpaArvore();}
		if (this.filho != null){filho.limpaArvore();}
		irmao_dir = null;
		filho = null;
		System.gc();
	}

	private boolean search2(String padrao2, BufferedWriter escrita)
	{
		if (this.chave.compareTo(padrao2) == 0) {return true;}
		if (this.filho != null) {if (this.filho.search2(padrao2, escrita) == true) {return true;}}
		if (this.irmao_dir != null) {if (this.irmao_dir.search2(padrao2, escrita) == true) {return true;}}
		return false;
	}
	
	private void searchMain(String padrao1, String padrao2,BufferedWriter escrita)
	{
		if (this.chave.compareTo(padrao1) == 0)
		{
			if (this.filho != null)
			{
				if (this.filho.search2(padrao2, escrita) == true) {this.lerArvore(escrita);}
			}
			if (padrao2.compareTo("-") == 0) {this.lerArvore(escrita);}
		}
		if (this.filho != null) {this.filho.searchMain(padrao1, padrao2, escrita);}
		if (this.irmao_dir != null) {this.irmao_dir.searchMain(padrao1, padrao2, escrita);}
	}
	
	private boolean removeMain(String padrao1, String padrao2,BufferedWriter escrita)
	{
		if (this.irmao_dir != null)
		{
			if (this.irmao_dir.removeMain(padrao1, padrao2, escrita) == true)
			this.irmao_dir = null;
		}
		if (this.filho != null)
		{
			if (this.chave.compareTo(padrao1) == 0 && (this.filho.search2(padrao2, escrita) || padrao2.compareTo("-") == 0))
			{
				if (this.irmao_dir != null)
				{
					this.chave = this.irmao_dir.chave;
					this.filho = this.irmao_dir.filho;
					this.irmao_dir = this.irmao_dir.irmao_dir;
				}
				else
				{
					this.filho = null;
					return true;
				}
			}
			else
			{
				if (this.filho.removeMain(padrao1, padrao2, escrita) == true)
				{
					if (this.irmao_dir != null)
					{
						this.chave = this.irmao_dir.chave;
						this.filho = this.irmao_dir.filho;
						this.irmao_dir = this.irmao_dir.irmao_dir;
					}
					else
					{
						this.filho = null;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void lerArquivo(BufferedReader is,BufferedWriter escrita, String opt, String padrao1, String padrao2) throws IOException
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
					return;
				case '(':
					if (this.filho == null) {this.filho = new Arvore();}
					else
					{
						this.irmao_dir = new Arvore();
						this.irmao_dir.filho = new Arvore();
						this.irmao_dir.lerArquivo(is, escrita, opt, padrao1, padrao2);
					}
					break;
				case ' ':
					if (this.filho.chave == "") {this.filho.lerArquivo(is, escrita, opt, padrao1, padrao2);}
					break;
				default:
					this.chave = this.chave+(char)caracter;
					break;
			}
			if (this.irmao_dir != null){return;}
			if (this.chave.compareTo("TOP") == 0 && this.filho.chave != "")
			{
				if (opt.compareTo("-p") == 0) {this.lerArvore(escrita);}
				if (opt.compareTo("-s") == 0) {this.searchMain(padrao1, padrao2, escrita);}
				if (opt.compareTo("-r") == 0)
				{
					this.removeMain(padrao1, padrao2, escrita);
					if (this.filho != null) {this.lerArvore(escrita);}
					else
					{
						escrita.write("("+this.chave+")\n");
						escrita.flush();
					}
				}
				this.limpaArvore();
				this.chave = "";
			}
		}
	}
}

