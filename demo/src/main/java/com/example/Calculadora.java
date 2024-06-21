package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {
	
	private static double redondea(double d) {
		return (new BigDecimal(d)).setScale(16, RoundingMode.HALF_UP).doubleValue();
	}
	
	public static double add(double a, double b) {
		return redondea(a + b);
	}
	
	public double add2(double a, double b) {
		return redondea(a + b);
	}
	
	public static double div(double a, double b) {
		if(b == 0) throw new ArithmeticException();
		return redondea(a / b);
	}
	
	public static int div(int a, int b) {
		return a / b;
	}
}