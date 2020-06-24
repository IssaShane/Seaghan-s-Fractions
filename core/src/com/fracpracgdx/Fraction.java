package com.fracpracgdx;

public class Fraction {
	public int num, denom;
	
	public Fraction() {
		this.num = 0;
		this.denom = 1;
	}
	
	public Fraction(String str) {
		char [] chars = str.toCharArray();
		String numStr = new String();
		int i = 0;
		// get numerator
		for (; i < str.length(); i++) {
			if (chars[i] == '/') break;
			else if (chars[i] != ' ') numStr += chars[i];
		}
		int numInt = Integer.parseInt(numStr);
		//System.out.println("numInt: " + Integer.toString(numInt));
		String denomStr = new String();
		// get denominator
		for (; i < str.length(); i++) {
			if (chars[i] != ' ' && chars[i] != '/') denomStr += chars[i];
		}
		int denomInt = 1;
		if (denomStr != null) denomInt = Integer.parseInt(denomStr);
		//System.out.println("denomInt: " + Integer.toString(denomInt));
		this.num = numInt;
		this.denom = denomInt;
	}
	
	public Fraction(int num) {
		this.num = num;
		this.denom = 1;
	}
	
	public Fraction (int num, int denom) {
		this.num = num;
		this.denom = denom;
	}
	
	public Fraction (Fraction other) {
		this.num = other.num;
		this.denom = other.denom;
	}
	
	public boolean equals(Fraction other) {
		BashWrapper libvec = new BashWrapper("frac", "./libvecwrap.sh");
		String output = libvec.runCommand("fracequal " + this.toString() + " " + other.toString());
		System.out.println("frac.equals output: " + output);
		if (output.equals(new String("true"))) return true;
		else return false;
	}
	
	public String toString() { 
		return Integer.toString(this.num) + "/" + Integer.toString(this.denom); 
	}
}
