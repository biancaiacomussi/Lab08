package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

	Model model = new Model();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGenera;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnGradoMax;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doGenera(ActionEvent event) {

    	txtResult.clear();
    	try {
    	int numeroLettere = Integer.parseInt(txtNumLettere.getText());
    	model.createGraph(numeroLettere);
    	txtResult.setText("Grafo creato correttamente");
    	}catch(NumberFormatException e) {
    		txtResult.setText("Inserire un numero");
    		System.err.println("Inserire un numero");
    	}
    }

    @FXML
    void doGradoMax(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	if(model.getGrafo()!=null) {

    	txtResult.appendText("Grado massimo: "+model.findMaxDegree()+"\n");
    	
    	txtResult.appendText("Vertice con grado massimo: "+model.findMaxVertex()+"\n");
    	
    	txtResult.appendText(model.displayNeighbours(model.findMaxVertex()).toString());
    	
    	} else {
    		txtResult.setText("Prima devi creare un grafo");
    	}
    	
    }

    @FXML
    void doReset(ActionEvent event) {

    	txtResult.clear();
    	txtParola.clear();
    	txtNumLettere.clear();
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	
    	txtResult.clear();

    	if(model.getGrafo()!=null) {
    	
    	String parolaInserita = txtParola.getText();
    	
    	if(!parolaInserita.matches("[a-zA-Z]+")) {
    		txtResult.appendText("Devi inserire una parola\n");
    		throw new InvalidParameterException("Devi inserire una parola\n");
    	} else {
    		
    		if(parolaInserita.length()!=model.getNumero()) {
    			txtResult.setText("Parola con numero di lettere diverso dal grafo creato");
    		}
    	
    		else {
    		txtResult.setText("Parole vicine: "+model.displayNeighbours(parolaInserita).toString());
    		}
    	
    	}
    	
    	} else {
    		txtResult.setText("Prima devi creare un grafo");
    	}
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

    @FXML
    void initialize() {
        assert txtNumLettere != null : "fx:id=\"txtNumLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGenera != null : "fx:id=\"btnGenera\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGradoMax != null : "fx:id=\"btnGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
}
