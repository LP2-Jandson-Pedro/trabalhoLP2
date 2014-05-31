package pedro.ufrn.buscaDigital;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArvoreDigital{
	
	private Node raiz;
	private String chave;
	private int tamAlfabeto = 26;/*Porque se trata de um alfabeto comum [A...Z]*/ 
	
	public ArvoreDigital(int tam){
		this.tamAlfabeto = tam;
		this.raiz = new Node(false, tam);
		this.chave = "";		
	}
	
	public ArvoreDigital(){
		this.chave = "";
	}
	
	public void init() throws Throwable{
		StringBuilder action = new StringBuilder();
		String str = null;
		
		do{			
//			Converte a string pra um char
			action.append(str);
			char word[] = new char[str.length()];
			word = action.toString().toCharArray();
			
			switch (word[0]) {
			case 'i':
//				Instrução para inserir uma palavra na árvore
				System.out.println(insert(word));
				break;
			case 'r':
//				Instrução para remover uma palavra na árvore
				System.out.println(remove(word));
				
				break;
			case 'f':
				System.out.println(find(word));
				break;
			default:
				System.gc();
				System.exit(0);			
			}		
			action.delete(0, action.length());			
		}while(str != null);		
	}
	
	public boolean insert(char[] key){
		Node nodo = new Node(false, 26);
		nodo.prefixo++;
		
		if(this.find(key))
			return false;	
		
		for(int a = 2; a < key.length; a++){
			int cint = this.posInAlfa(key[a]);
			if(nodo.nodos[cint] == null){
				try {
					nodo.nodos[cint] = new Node(false, tamAlfabeto);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Erro ao instanciar/Ou estouro de memória!!!");
				}
				if(nodo.nodos[cint] == null)
					return false;
				
				nodo.nodos[cint].back = nodo;
			}
			nodo.nodos[cint].prefixo++;
			nodo = nodo.nodos[cint];
		}
		nodo.isEnd = true;		
		return true;
	}
	
		
	public boolean remove(char[] key) throws Throwable{
		Node nodo = null;
		int a;
		
		for(a = 2; a < key.length; a++){
			if(nodo.nodos[ this.posInAlfa(key[a]) ] != null){
				nodo = nodo.nodos[ this.posInAlfa(key[a]) ];
			}
			else{
				return false;
			}
		}		
/*			System.out.println("Passou por aqui!");*/
		if(nodo.isEnd != true){
			System.out.println("Passou por aqui!");
			return false;
		}
		nodo.isEnd = false;
		Node aux;
		
		while(nodo.back != null){
			if(nodo.prefixo <= 0){
				aux = nodo;
				nodo = nodo.back;
				nodo.prefixo--;
				aux.finalize();
				System.gc();
			}
			else
				nodo = nodo.back;
		}
		
		if(nodo.equals(this.raiz))
			return true;
		
		return false;
	}
	
	public boolean find(char[] key){
		Node nodo = this.raiz;
		
		for(int a = 2; a < key.length; a++){
			System.out.println(a);
			if(nodo.nodos[ this.posInAlfa(key[a]) ] != null){
				nodo = nodo.nodos[ this.posInAlfa(key[a]) ];
			}
			else{
				return false;
			}
		}	
		return true; 
	}
	
	public int posInAlfa(char a){
		return (int)a - 97;
	}
	
	
	
	
	
	
}
