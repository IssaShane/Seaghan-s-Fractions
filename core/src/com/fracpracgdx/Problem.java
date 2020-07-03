package com.fracpracgdx;

import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;

public class Problem {
	Fraction frac1, frac2;
	int lower, upper;
	char op;
	
	public Problem() {
		this.lower = 1;
		this.upper = 5;
		this.op = '+';
		init();
	}
	
	protected void init() {
		this.frac1 = new Fraction(randInRange(), randInRange());
		this.frac2 = new Fraction(randInRange(), randInRange());
	}
	
	public void changeOp(char op) { this.op = op; }
	
	public char getOp() { return this.op; }
	
	public void reload(int difficulty) {
		if (difficulty == 0) {
			this.frac1 = new Fraction(randInRange(), randInRange());
			this.frac2 = new Fraction(randInRange(), this.frac1.denom);
		}
		init();
	}
	
	public boolean checkSoln(Fraction soln) {
		Fraction correctSoln = this.evaluate();
		System.out.println("correctSoln taken");
		if (soln.equals(this.evaluate())) return true;
		else return false;
	}
	
	public Fraction evaluate() {
		//System.out.println("Fraction::evaluate");
		Fraction retval = new Fraction(0);
		BashWrapper libfrac = new BashWrapper("frac", "./libvecwrap.sh");
		//System.out.println("libfrac loaded");
		String output = libfrac.runCommand(this.toCommand());
		//System.out.println("command: " + this.toCommand());
		//System.out.println("command run: " + output);
		retval = new Fraction(output);
		return retval;
	}
	
	private int randInRange() {
		return (int) (Math.random() * (this.upper - this.lower + 1) + this.lower);
	}
	
	public String toString() {
		return this.frac1.toString() + " " + this.op + " " + this.frac2.toString();
	}
	
	public String toCommand() {
		String retval = new String();
		if (this.op == '+') retval += "addfrac " + this.frac1.toString() + " " + this.frac2.toString();
		else if (this.op == '-') retval += "subfrac " + this.frac1.toString() + " " + this.frac2.toString();
		else if (this.op == '/') retval += "divfrac " + this.frac1.toString() + " " + this.frac2.toString();
		else if (this.op == '*') retval += "multfrac " + this.frac1.toString() + " " + this.frac2.toString();
		return retval;
	}
	
	public void setUpperBound(int upper) {
		this.upper = upper;
	}
	
	public void setLowerBound(int lower) {
		this.lower = lower;
	}
}
