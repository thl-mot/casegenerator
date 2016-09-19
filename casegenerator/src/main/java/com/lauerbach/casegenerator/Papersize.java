package com.lauerbach.casegenerator;

public class Papersize {

	double w,h;
	
	public static final Papersize A8 = new Papersize( 74, 52);
	public static final Papersize A7 = new Papersize( 105, 74);
	public static final Papersize A6 = new Papersize( 148, 105);
	public static final Papersize A5 = new Papersize( 210, 148);
	public static final Papersize A4 = new Papersize( 297, 210);
	public static final Papersize A3 = new Papersize( 420, 297);
	public static final Papersize A2 = new Papersize( 594, 420);
	public static final Papersize A1 = new Papersize( 841, 594);
	public static final Papersize A0 = new Papersize( 1189, 841);
	
	public Papersize(double w, double h) {
		super();
		this.w = w;
		this.h = h;
	}

}
