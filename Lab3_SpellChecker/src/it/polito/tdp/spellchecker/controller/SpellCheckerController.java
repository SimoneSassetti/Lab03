package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {

	Dictionary model;
	public void setModel(Dictionary model) {
		this.model = model;
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbLanguage;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpell;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Label txtError;

    @FXML
    private Button btnClearText;

    @FXML
    private Label txtTime;

    @FXML
    void doClearText(ActionEvent event) {
    	txtInput.clear();
    	txtOutput.clear();
    	txtError.setVisible(false);
    	txtTime.setVisible(false);
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	int numErrate=0;
    	
    	
    	String lingua=cmbLanguage.getValue();
    	if(lingua.equals("Italiano")){
    		lingua="rsc/Italian.txt";
    	}
    	else
    		lingua="rsc/English.txt";
    	
    	String paroleInserite=txtInput.getText();
    	
    	/*txtOutput.appendText(*/paroleInserite=paroleInserite.toLowerCase().replaceAll("[\\p{Punct}]", "");
    	String array[]=paroleInserite.split(" ");
    	List<String> listaInput=new ArrayList<String>(Arrays.asList(array));
    	long t=System.nanoTime();
    	model.loadDictionary(lingua);
    	List<RichWord> listaOutput=new ArrayList<RichWord>(model.spellCheckText(listaInput));
    	
    	for(RichWord r: listaOutput){
    		if(!r.isCorretta()){
    			txtOutput.appendText(r.getParola()+"\n");
    			numErrate++;
    		}	
    	}
    	long t1=System.nanoTime();
    	
    	txtError.setText("Il testo contiene "+ numErrate+" errore/i.");
    	txtTime.setText("Tempo necessario per la traduzione "+(t1-t)+ " nanosecondi.");
    	txtError.setVisible(true);
    	txtTime.setVisible(true);
    }

    @FXML
    void initialize() {
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtError != null : "fx:id=\"txtError\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        cmbLanguage.getItems().addAll("Italiano","English");
        if(cmbLanguage.getItems().size()>0)
        	cmbLanguage.setValue(cmbLanguage.getItems().get(0));
        
        txtError.setVisible(false);
    	txtTime.setVisible(false);
    }
}

