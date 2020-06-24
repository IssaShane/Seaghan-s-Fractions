package com.fracpracgdx;

import java.util.Scanner;

public class FractionInputField extends NumberInputField {
	public FractionInputField(Posn pos) {
		super(pos);
	}
	
	public FractionInputField(Posn pos, String skinfilename) {
		super(pos, skinfilename);
	}
	

	@Override
	public Fraction getContent() {
		char [] chars = this.field.getText().toCharArray();
		String numStr = new String();
		int i = 0;
		// get numerator
		for (; i < this.field.getText().length(); i++) {
			if (chars[i] == '/') break;
			else if (chars[i] != ' ') numStr += chars[i];
		}
		int numInt = Integer.parseInt(numStr);
		//System.out.println("numInt: " + Integer.toString(numInt));
		String denomStr = new String();
		// get denominator
		for (; i < this.field.getText().length(); i++) {
			if (chars[i] != ' ' && chars[i] != '/') denomStr += chars[i];
		}
		int denomInt = Integer.parseInt(denomStr);
		//System.out.println("denomInt: " + Integer.toString(denomInt));
		this.content = new Fraction(numInt, denomInt);
		return this.content;
	}
}
