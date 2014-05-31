package leitor;

public class Node{
//	public char key;
	protected Node[] nodos;
	protected boolean isEnd;
	protected int prefixo; //Qtd de palavras na qual ele é prefixo;
	protected Node back; // Aponta para o seu pai -> Será necessário na hora de remover a string inserida
	
	
	
	public Node(boolean isEnd, int n){

		this.isEnd = isEnd;
		this.nodos = new Node[n];		
		this.prefixo = 0;
	}
	
	public Node(){

		this.isEnd = false;
		this.nodos = null;
		this.prefixo = 0;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		this.isEnd = false;
		super.finalize();
		
	}
	
}
