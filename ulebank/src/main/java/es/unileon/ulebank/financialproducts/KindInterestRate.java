package es.unileon.ulebank.financialproducts;


public enum KindInterestRate {
	Euribor("euribor"),IRS("irs");
	String name;
	
	KindInterestRate(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;	
	}
}
