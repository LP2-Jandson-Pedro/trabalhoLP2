package leitor;

import java.io.BufferedReader;
import java.io.FileWriter;
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

	private void lerArvore(FileWriter escrita) {ler(0,escrita);}

	private void ler(int Espacos,FileWriter escrita)
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
			}
		
			if (irmao_dir != null)
			{
				escrita.write("\n");
				for (int counter = 0; counter < Espacos; counter++) {escrita.write(" ");}
				this.irmao_dir.ler(Espacos,escrita);
			}
		}
		catch (IOException e) {
				// TODO Auto-generated catch block
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

	private boolean checkSons(String padrao)
	{
		if(padrao.compareTo("-") == 0) {return true;}
		if (this.chave.compareTo(padrao) == 0){return true;}
		if(this.irmao_dir != null){return irmao_dir.checkSons(padrao);}
		return false;
	}
	
	private void search2(String padrao2,int espaco, FileWriter escrita)
	{
		try
		{
			if (this.chave.compareTo(padrao2) == 0 || padrao2.compareTo("-") == 0)
			{
				if (this.filho != null)
				{
					escrita.write("(" + this.chave + " ");
					this.filho.ler(this.chave.length() + 2 + espaco, escrita);
					escrita.write(")");
				}
				else {System.out.print(this.chave);}
			}
			if (irmao_dir != null)
			{
				if ((irmao_dir.checkSons(padrao2) && this.chave.compareTo(padrao2) == 0) || padrao2.compareTo("-") == 0)
				{
					escrita.write("\n");
					for (int i = 0; i < espaco; i++){escrita.write(" ");}
				}
				irmao_dir.search2(padrao2,espaco, escrita);
			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void searchMain(String padrao1, String padrao2,FileWriter escrita)
	{
		try
		{
			if (this.filho != null)
			{
				if (this.chave.compareTo(padrao1) == 0 && this.filho.checkSons(padrao2))
				{
					escrita.write("("+this.chave+" ");
					this.filho.search2(padrao2, this.chave.length() + 2, escrita);
					escrita.write(")\n");
				}
				if (this.chave.compareTo(padrao1) != 0 || !this.filho.checkSons(padrao2)) {this.filho.searchMain(padrao1, padrao2, escrita);}
			}
			if (this.irmao_dir != null) {this.irmao_dir.searchMain(padrao1, padrao2, escrita);}			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean remove2(String padrao1, String padrao2,FileWriter escrita)
	{
		if (this.irmao_dir != null)
		{
			if (this.irmao_dir.remove2(padrao1, padrao2, escrita) == true)
			this.irmao_dir = null;
		}
		
		if (this.chave.compareTo(padrao2) == 0 || padrao2.compareTo("-") == 0)
		{
			if (this.irmao_dir != null)
			{
				this.chave = this.irmao_dir.chave;
				this.filho = this.irmao_dir.filho;
				this.irmao_dir = this.irmao_dir.irmao_dir;
			}
			else {return true;}
		}
		return false;
	}
	
	private boolean removeMain(String padrao1, String padrao2,FileWriter escrita)
	{
		if (this.irmao_dir != null)
		{
			if (this.irmao_dir.removeMain(padrao1, padrao2, escrita) == true)
			this.irmao_dir = null;
		}
		if (this.filho != null)
		{
			if (this.chave.compareTo(padrao1) == 0 && this.filho.checkSons(padrao2))
			{
				if(this.filho.remove2(padrao1, padrao2, escrita) == true)
				{
					if (this.irmao_dir != null)
					{
						this.chave = this.irmao_dir.chave;
						this.filho = this.irmao_dir.filho;
						this.irmao_dir = this.irmao_dir.irmao_dir;
					}
					else {return true;}
				}
			}
			//if ()
		}
		return false;
	}
	
	public void lerArquivo(BufferedReader is,FileWriter escrita, String opt, String padrao1, String padrao2) throws IOException
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
					if (this.chave.compareTo("TOP") == 0)
					{
						if (opt.compareTo("-p") == 0) {this.lerArvore(escrita);}
						if (opt.compareTo("-s") == 0) {this.searchMain(padrao1, padrao2, escrita);}
						this.limpaArvore();
						this.chave = "";
					}
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
		}
	}
}

