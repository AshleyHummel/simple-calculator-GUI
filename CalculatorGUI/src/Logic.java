//Logic class calculates user input and returns a total

import java.text.DecimalFormat;

public class Logic 
{
	private double total;
	private DecimalFormat df;
	
	public Logic() {
		total = 0.0;
	}
	
	public void divide(double val1, double val2) {
		total = val1 / val2;
	}
	
	public void multiply(double val1, double val2) {
		total = val1 * val2;
	}
	
	public void subtract(double val1, double val2) {
		total = val1 - val2;
	}	
	
	public void add(double val1, double val2) {
		total = val1 + val2;
	}
	
	public String getTotal() {
		df = new DecimalFormat("0.#");
		return df.format(total);
	}
	
	public void clearAll() {
		total = 0.0;
	}
}
