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
		return new Fraction(this.field.getText());
	}
}
