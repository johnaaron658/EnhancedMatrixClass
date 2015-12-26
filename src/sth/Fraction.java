package sth;

/** This java class was retrieved from http://www.cs.berkeley.edu/~jrs/61bf98/labs/lab2/Fraction.java**/
/** Modified on 12/26/2015 **/

/** MODIFICATIONS: 
 * 
 * -Constructor for doubles
 * -toString modification to print out in lowest terms using the gcd function
 * -private static class for gcd (using Euclidean Algorithm)
 * -function for comparing Fractions: 'equal to'
 * -functions for getting the numerator and denominator
 * -function for Fraction inverse
 * -functions for subtraction, multiplication, and division
 */

/** This Fraction class is applicable to all rational numbers**/

class Fraction {
	
	  /* private fields within a Fraction.           */ 
  private int numerator;
  private int denominator;
	  
  /** Constructs a Fraction n/d. 
   *  @param n is the numerator, assumed non-negative.
   *  @param d is the denominator, assumed positive.
   */
	
  Fraction(int n, int d) {
    numerator = n; 
    denominator = d;
  }

  /** Constructs a Fraction n/1. 
   *  @param n is the numerator, assumed non-negative.
   */
  public Fraction(int n) {
    this(n,1);
  }

  /** Constructs a Fraction 0/1. 
   */
  public Fraction() {
    numerator = 0;
    denominator = 1;
  }
  
  //Constructor for double types
  public Fraction(double x){
	  String y = Double.toString(x);
	  String z = y.substring(y.indexOf('.')+1); //String of numbers after the decimal point
	  
	  if(x>=0) //for non-negative numbers
		  numerator = Integer.parseInt(y.substring(0,y.indexOf('.')))*power(10,z.length()) + 
				  	  Integer.parseInt(z);
	  
	  else     //for negative numbers
		  numerator = Integer.parseInt(y.substring(0,y.indexOf('.')))*power(10,z.length()) - 
			  	  Integer.parseInt(z); 
	  
	  denominator = power(10,z.length());
	
  }
 

  /** Converts this fraction to a string format: "numerator/denominator." 
   *  Fractions are printed in reduced form (part of your assignment is 
   *  to make this statement true).  
   *  @return a String representation of this Fraction.
   */
  public String toString()   {
    int thisGcd = gcd(numerator, denominator); 
    if(denominator/thisGcd!=1)
    	return (numerator/thisGcd + "/" + denominator/thisGcd);
    else
    	return String.valueOf(numerator/thisGcd);
  }

  /** Calculates and returns the double floating point value of a fraction.
   *  @return a double floating point value for this Fraction.
   */
  public double evaluate() {
      double n = numerator;	// convert to double
      double d = denominator;	
      return (n / d);		
    }
  
  /*STUFF*/
  
  public int toInt(){
	  int thisGcd = gcd(numerator,denominator);
	  if(denominator/thisGcd == 1)
		  return numerator;
	  return numerator/denominator;
  }
  
  //Reduces fraction to lowest terms
  public Fraction reduced(){
	  int GCD = gcd(this.numerator,this.denominator);
	  return new Fraction(this.numerator/GCD,this.denominator/GCD);
  }
  
  /*GETTERS*/
  
  //Returns numerator
  public int getNum(){
	  return numerator;
  }
  
  //Rerturns denominator
  public int getDen(){
	  return denominator;
  }
  
  /*FRACTION COMPARISON*/
  
  public boolean isEqualTo(Fraction x){
	  if(this.reduced().getNum() == x.reduced().getNum() &&
		 this.reduced().getDen() == x.reduced().getDen())
		  return true;
	  return false;
  }
  
  /**FRACTION OPERATIONS **/
  
  public Fraction inverse (){
	  return new Fraction(this.denominator,this.numerator);
  }
  
  public Fraction times (Fraction f2){
	  return new Fraction(numerator*f2.numerator,denominator*f2.denominator);
  }
  
  public Fraction times (int factor){
	  return new Fraction(numerator*factor,denominator);
  }
  
  public Fraction over (Fraction f2){ 
	  return this.times(f2.inverse());
  }

  public Fraction plus (Fraction f2) {
	  return new Fraction((numerator * f2.denominator) +  (f2.numerator * denominator), (denominator * f2.denominator));    
  }
 
  public Fraction minus (Fraction f2){
	  return this.plus(f2.times(new Fraction(-1)));
  }
  
  public Fraction raisedTo(int n){
	  Fraction prod = new Fraction(1);
	  for(int i = 0; i < n; i++)
		  prod = prod.times(this);
	  return prod;
  }

  /*EXTRA STUFF*/
  private static int gcd (int x, int y) {
		int a,b,r,t;
		x = Math.abs(x);	//Both values are assumed to be positive
		y = Math.abs(y);
		
		if(x == 0) return y;
		if(y == 0) return x;
		
	    if(x > y){
	    	a = x;
	    	b = y;
	    }
	    else if(x < y){
	    	a = y;
	    	b = x;
	    }
	    else
	    	return x;
	    
	    /*Using the Euclidean Algorithm a = qb + r
	     *a - dividend
	     *b - divisor
	     *q - quotient
	     *r - remainder 
	     *t - temporary*/
	    
	    for(t = b,
	    	r = a%b;
	    		r!=0; 
	    	a = b,
	    	b = r, 
	    	t = b, 
	    	r = a%b);

	    return t;
	  }
  
  //only works for positive exponents
  private static int power(int base, int exponent){
	  if(exponent<0){
		  System.out.println("Cannot do this shit.");
		  return 0;
	  }
	  int product =1;
	  for(int i = 0 ; i < exponent; product*=base, i++);
	  return product;
  }
 
}

/** MODIFIED BY: JOHN AAORN ALCOSEBA **/
