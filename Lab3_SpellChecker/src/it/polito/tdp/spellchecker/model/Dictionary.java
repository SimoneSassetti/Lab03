package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	private List<String> dizionario;

	public Dictionary() {
		dizionario=new ArrayList<String>();
	}
	
	public void loadDictionary(String language){
		try {
			FileReader fr = new FileReader(language);
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				dizionario.add(word);
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> listaParole=new LinkedList<RichWord>();
		
		for(String s: inputTextList){
			RichWord r=new RichWord(s);
			if(dizionario.contains(s)){
				r.isCorrect();
			}
			listaParole.add(r);
		}

		return listaParole;
	}
	
}
