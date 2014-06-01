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
			String stringMaior;
			String stringMenor;
			
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
				if(stringMaior.contains((CharSequence)stringMenor)) {return true;}
				else
				{
					for(int counter = 0; counter < stringMaior.length(); counter++)
					{
						if(string1.charAt(counter) != string2.charAt(counter))
						{
							String sub =  stringMenor.substring(counter);
							if(stringMaior.contains((CharSequence)sub)){return true;}
						}						
					}
				}
			}
		}
		return false;
	}
	
	public boolean inserefolhas(String palavra,String aux)
	{
		boolean inserido = false;
		if (this.filhos.size() > 0)
		{
			for (int counter = 0; counter < this.filhos.size(); counter++)
			{
				if (palavra.compareTo(aux+this.node+this.filhos.get(counter).node) == 0) {return true;}
				
				String tmp = aux+this.node+this.filhos.get(counter).node;
				if (comparaStrings(palavra, tmp))
				{
					int counter2 = 0;
					while(counter2 < palavra.length() && counter2 < tmp.length() && palavra.charAt(counter2) == tmp.charAt(counter2))
					{counter2++;}
					ArvorePatricia auxiliar = new ArvorePatricia(palavra.substring(counter2));
					filhos.add(auxiliar);
					return true;
				}
			}
			for (int counter = 0; counter < this.filhos.size(); counter++)
			{if(this.filhos.get(counter).inserefolhas(palavra, aux+this.node)) {return true;}}
		}
		else
		{
			if (comparaStrings(palavra, aux+this.node))
			{
				int counter2 = 0;
				while(counter2 < palavra.length() && counter2 < (this.node+this.filhos.get(counter).node).length() && palavra.charAt(counter2) == (this.node+this.filhos.get(counter).node).charAt(counter2))
				{counter2++;}
				ArvorePatricia auxiliar = new ArvorePatricia(palavra.substring(counter2));
				filhos.add(auxiliar);
				return true;
			}
		}
		
		
		
		
		
		
		/*
		if (this.node =="") {node = palavra;}
		else
		{
			if (comparaStrings(palavra,this.node) && palavra != node)
			{
				boolean insere = true;
				for(int counter = 0; counter < this.filhos.size();counter++)
				{if (this.filhos.get(counter).node == palavra){insere = false;}}
				
				if (insere == true)
				{
					ArvorePatricia aux = new ArvorePatricia(palavra);
					filhos.add(aux);
				}
			}
		}
		
		
		*/
	}
	
}
