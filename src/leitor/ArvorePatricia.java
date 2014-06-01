package leitor;

import java.util.ArrayList;
import java.util.List;

public class ArvorePatricia
{
	private String node;
	private List<ArvorePatricia> filhos = null;
	
	public ArvorePatricia()
	{
		node = "";
		filhos = new ArrayList<ArvorePatricia>();
	}
	
	public ArvorePatricia(String palavra)
	{
		node = palavra;
		filhos = new ArrayList<ArvorePatricia>();
	}
	
	private boolean comparaStrings(String string1,String string2)
	{
		if (string1.length() == string2.length())
		{
			int chardif = 0;
			for(int counter = 0; counter < string1.length(); counter++)
			{if (string1.charAt(counter) != string2.charAt(counter)) {chardif++;}}
			if (chardif == 1) {return true;}
			else {return false;}
		}
		else
		{
			String stringMaior="";
			String stringMenor="";
			
			if(string1.length() > string2.length())
			{
				stringMaior = string1;
				stringMenor = string2;
			}
			else
			{
				stringMaior = string2;
				stringMenor = string1;
			}
			if((stringMaior.length() - stringMenor.length()) == 1)
			{
				if(stringMaior.contains(stringMenor)) {return true;}
				else
				{
					for(int counter = 0; counter < stringMenor.length(); counter++)
					{
						if(string1.charAt(counter) != string2.charAt(counter))
						{
							String sub =  stringMenor.substring(counter);
							if(stringMaior.contains(sub)){return true;}
							else {return false;}
						}						
					}
				}
			}
		}
		return false;
	}
	
	private boolean inserefolhas(String palavra,String aux)
	{	
		for (int counter = 0; counter < this.filhos.size(); counter++)
		{
			if (palavra.compareTo(aux+this.filhos.get(counter).node) == 0) {return true;}

			String tmp = aux+this.filhos.get(counter).node;
			if (comparaStrings(palavra, tmp))
			{
				int counter2 = 0;
				boolean insert2 = true;
				while(counter2 < palavra.length() && counter2 < tmp.length() && palavra.charAt(counter2) == tmp.charAt(counter2))
				{counter2++;}
				ArvorePatricia auxiliar = new ArvorePatricia(palavra.substring(counter2));
				ArvorePatricia auxiliar2 = new ArvorePatricia(tmp.substring(counter2));
				filhos.get(counter).filhos.add(auxiliar);
				for (int counter3 = 0; counter3 < this.filhos.get(counter).filhos.size(); counter3++)
				{if (filhos.get(counter).filhos.get(counter3).node.compareTo(auxiliar2.node) == 0){insert2 = false;}}
				if (insert2){filhos.get(counter).filhos.add(auxiliar2);}
				if (counter2 > aux.length()) {filhos.get(counter).node = tmp.substring(aux.length(),counter2);}
				else {filhos.get(counter).node = "";}
				//System.out.println(filhos.get(counter).node);
				return true;
			}
		}
		for (int counter = 0; counter < this.filhos.size(); counter++)
		{if(this.filhos.get(counter).inserefolhas(palavra,aux+this.filhos.get(counter).node)) {return true;}}
		return false;
	}
	
	public void insere(String palavra)
	{
		if (this.filhos.size() > 0)
		{
			if (!this.inserefolhas(palavra,""))
			{
				ArvorePatricia auxiliar = new ArvorePatricia(palavra);
				this.filhos.add(auxiliar);
			}
		}
		else
		{
			ArvorePatricia auxiliar = new ArvorePatricia(palavra);
			this.filhos.add(auxiliar);
		}
	}
	
	public void lerarvore(String palavra)
	{
		if (this.filhos.size() > 0)
		{
			for (int counter = 0; counter < this.filhos.size(); counter++)
			{this.filhos.get(counter).lerarvore(palavra+this.node);}
		}
		else {System.out.println(palavra+node);}
	}

	public void leSemelhantes()
	{
		for (int counter = 0; counter < this.filhos.size(); counter++)
		{
			System.out.println("Semelhantes "+ (counter+1) +":");
			this.filhos.get(counter).lerarvore("");
		}
	}
}
