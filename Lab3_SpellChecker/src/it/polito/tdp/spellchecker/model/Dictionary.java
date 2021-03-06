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
		
		Collections.sort(dizionario);
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> listaParole=new LinkedList<RichWord>();
		
		for(String s: inputTextList){
			RichWord r=new RichWord(s);
			int start=0;
			int end=dizionario.size();
			int centro=start+end/2;
			while(start+1<end){
				if(r.getParola().compareTo(dizionario.get(centro))>0){
					start=centro;
					centro=(centro+end)/2;
				}
				else if(r.getParola().compareTo(dizionario.get(centro))<0){
					end=centro;
					centro=(centro+start)/2;
				}
				else{
					r.isCorrect();
					break;
				}
			}
			listaParole.add(r);
		}
		return listaParole;
	}
}
