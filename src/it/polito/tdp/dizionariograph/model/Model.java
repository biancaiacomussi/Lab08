package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	private Graph<String, DefaultEdge> grafo;
	private Map<String,String> backVisit;
	private List<String> parole;
	private int numero;

	public int getNumero() {
		return numero;
	}

	public void createGraph(int numeroLettere) {
		
		numero = numeroLettere;

		//creo il grafo
		this.grafo = new SimpleGraph<>(DefaultEdge.class);
		
		//aggiungo i vertici
		WordDAO dao = new WordDAO();
		parole = dao.getAllWordsFixedLength(numeroLettere);
		Graphs.addAllVertices(this.grafo, this.parole);
		
		//creo gli archi
		
		for(String p1: grafo.vertexSet()) {
			for(String p2 : grafo.vertexSet()) {
				if(numeroLettereDiverse(p1,p2)) {
					this.grafo.addEdge(p1, p2);
				}
			}
		}
		
	}

	public List<String> displayNeighbours(String parolaInserita) {

		List<String> res = new ArrayList<>();
		
		for(DefaultEdge e : grafo.edgesOf(parolaInserita)) {
			String s = Graphs.getOppositeVertex(this.grafo, e, parolaInserita);
			res.add(s);
		}
		
		return res;
	}

	public int findMaxDegree() {
		int max=0;
		for(String v : grafo.vertexSet()) {
		if(grafo.degreeOf(v)>max) {
			max = grafo.degreeOf(v);
		}
		}
		return max;
	}
	
	public String findMaxVertex() {
		String vMax=null;
		int max=0;
		for(String v : grafo.vertexSet()) {
		if(grafo.degreeOf(v)>max) {
			max = grafo.degreeOf(v);
			vMax = v;
		}
		}
		return vMax;
	}
	
	public boolean numeroLettereDiverse(String p1, String p2){
		int conta = 0;
		char [] a1 = p1.toCharArray();
		char [] a2 = p2.toCharArray();
		
		for(int i =0; i<p1.length(); i++) {
			if(a1[i] != a2[i])
				conta++;
		}	
		
		return (conta==1);
		
	}

	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

	public void setGrafo(Graph<String, DefaultEdge> grafo) {
		this.grafo = grafo;
	}
}
