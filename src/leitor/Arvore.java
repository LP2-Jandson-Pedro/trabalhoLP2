package leitor;

import java.io.BufferedReader;
import java.io.IOException;

public class Arvore extends ArvorePatricia
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
	
	private void limpaArvore()
	{
		if (this.irmao_dir != null){irmao_dir.limpaArvore();}
		if (this.filho != null){filho.limpaArvore();}
		irmao_dir = null;
		filho = null;
		System.gc();
	}
	
	public boolean lerArquivo(BufferedReader is, ArvorePatricia substantivos, ArvorePatricia verbos, ArvorePatricia adjetivos, ArvorePatricia adverbios) throws IOException
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
						this.irmao_dir.lerArquivo(is, substantivos, verbos, adjetivos, adverbios);
					}
					break;
				case ' ':
					if (this.filho != null)
					{
						if (this.filho.chave == "")
						{
							if (this.filho.lerArquivo(is, substantivos, verbos, adjetivos, adverbios) == true)
							{
								if (this.chave.compareTo("NNS") == 0 || this.chave.compareTo("NN") == 0)
								{substantivos.insere(this.filho.chave);}								
								else if (this.chave.charAt(0) == 'V') {verbos.insere(this.filho.chave);}
								else if (this.chave.compareTo("JJ") == 0 || this.chave.compareTo("JJR") == 0 || this.chave.compareTo("JJS") == 0)
								{adjetivos.insere(this.filho.chave);}
								else if (this.chave.compareTo("RB") == 0 || this.chave.compareTo("RBR") == 0 || this.chave.compareTo("RBS") == 0)
								{adverbios.insere(this.filho.chave);}
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
				System.out.println("Classe dos substantivos");//escrita.write();
				substantivos.leSemelhantes();
				System.out.println("Classe dos verbos");//escrita.write();
				verbos.leSemelhantes();
				System.out.println("Classe dos adjetivos");//escrita.write();
				adjetivos.leSemelhantes();
				System.out.println("Classe dos adverbios \n");//escrita.write();
				adverbios.leSemelhantes();
				this.limpaArvore();
				this.chave = "";
			}
		}
	return false;
	}
}

