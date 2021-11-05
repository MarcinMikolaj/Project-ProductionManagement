package project.validator.algorithms;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import project.validator.annotation.Curses;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class CursesConstraint implements ConstraintValidator<Curses, String> {
	
	private boolean isActive;
	private Dictionary[] dictionary;
	
	public void initialize(Curses annotation) {
		
		this.isActive = annotation.isActive();
		this.dictionary = annotation.dictionary();
		
		
	}
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(isActive == false)
			return true;
		
		boolean result = true;
		
		for(Dictionary iterator: this.dictionary) {
			
			if(Dictionary.PL == iterator) 
				result &= plFilter(value);
							
			if(Dictionary.ENG == iterator)
				result &= enFilter(value);
						
		}
		
		return result;
	}
	
	//Słownik dla przekleństw w jezyku PL
	public boolean plFilter(String value) {
		List<String> curses = new ArrayList<String>();
		curses.add("kurde"); curses.add("kurwa");
		
		return generalFilter(value, curses);
	}
	
	//Słownik dla przekleństw w języku ENG
	public boolean enFilter(String value) {
		List<String> curses = new ArrayList<String>();
		curses.add("fuck"); curses.add("twat");
		
		return generalFilter(value, curses);
	}
	
	//Sprawdza czy podana wartość jest przekleństwem
	public boolean generalFilter(String value, List<String> badWords) {
		
		List<String> found = badWords.stream()
				.filter(q -> q.toLowerCase().contains(value))
				.collect(Collectors.toList());
		
		return found.isEmpty();
	}

}
