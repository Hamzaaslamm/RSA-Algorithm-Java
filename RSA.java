
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
public class RSA{
	    public static void main(String[] args){
	    	
	    	
	    Scanner sc= new Scanner(System.in);
	    
	    System.out.print("Enter the size of array greater than 1: ");
	    int size = sc.nextInt();
	    while(size <=1){
		    System.out.print("Enter the size of array greater than 1: ");
		    size = sc.nextInt();
	    }//while

	    int[] array = new int[size];
	    for(int i = 0; i < size; i++){
	    System.out.print("Enter prime number for index [" + i + "] :  ");
	    int number = sc.nextInt();
	    if(isPrime(number)) {
	    array[i] = number;
	    }//if
	    
	    while(!(isPrime(number))){
	    System.out.print(number + " is not a prime number \nEnter Prime number at index [ " + i + " ]:  ");
	    number= sc.nextInt();
	    if(isPrime(number)) {
	    array[i] = number;
	    }//if
	    }//while
	    }//for
	     System.out.println("__________________________________\n");
	    System.out.print("Step1: Prime Numbers{ ");
	    for(int i = 0; i < size; i++){
	    System.out.print(array[i] + ",");
	    }//for
	    System.out.println("}");
// Step 1 Select largest prime numbers P and Q.
	    int p = array[0];   
        int q = array[1];   
        	if(p<q){
        		int temp=p;
                p=q;
                q=temp;
        	}//if
                
        for(int i=2; i<size; i++){
            if (array[i] > p){
				q = p;
				p = array[i];
			}else if (array[i] > q && array[i] != p){
				q = array[i];
			}//else-if
        }//for
        System.out.println("P = "+ p);
        System.out.println("Q = "+ q);
        System.out.println("__________________________________");

//Step 2 n = p*q
        
	        int n = p * q;
	        System.out.println("Step 2 : The Value of n =" + n);
	    System.out.println("__________________________________\n");    
//Step 3 Euler's Totiont
	    
	        int eulerTotiont = (p - 1) * (q - 1);
	        System.out.println("Step 3: Euler Totiont = " + eulerTotiont);
	    System.out.println("__________________________________");

	    
	        int e;
//Step 4 choose value of e. eg. 1<e<eulerTotiont(n) & gcd (eulerTotiont(n),e) = 1
	        for (e = 2; e < eulerTotiont; e++){
	            if (gcd(e, eulerTotiont) == 1){
	                break;
	            }//if
	        }//for
	        System.out.println("Step 4: Value of e = " + e);
	    System.out.println("__________________________________");   

//Step 5 choose value of d. eg. d = e^-1 mod(eulerTotion)

	        int d = 0;
		       int i;
	        for (i = 0; i <= 9; i++){
	            int x = 1 + (i * eulerTotiont);
	            if (x % e == 0){
	            	d = x / e;
	                break;
	            }//if
	        }//for
	        System.out.println("Step 5: Value of d = " + d);
	        
	    System.out.println("__________________________________\n");
	        System.out.println("Public Key[" + e + "," + n +"]");
	        System.out.println("Private Key[" + d + "," + n +"]");

	    System.out.println("__________________________________\n");    
//Encryption	        
        
	        System.out.print("Enter Message =");
	        String letter= sc.next();	       
	        int [] position= findPosition(letter);
	        String text = "";
	        for(int j = 0 ;j< position.length; j++){
	        text = text + position[j];	       
	        }
	        int num = Integer.parseInt(text);
	        System.out.println("Plain Text into Number= " + num);
	     System.out.println("");

	     System.out.println("\n_______Encryption Cipher_______");
	     
	        long cipher = (long) ((Math.pow(num,e)) % n);
	        System.out.println("Encrypted Text = " + cipher);
		System.out.println("__________________________________");
//Decryption	        	        

		System.out.println("\n_______ Decryption Message_______");
	        
	        long plain = (long) (Math.pow(cipher, d) % n);
	        System.out.println("Power Output = " + Math.pow(cipher,d));

	        System.out.println("Decrypted Text = " + plain);
	    System.out.println("__________________________________");
	       
//	     
	    }//main
	 
	    public static  boolean isPrime(int num){  
	    	if(num <= 1){
	    		return false;
	    	}
	    	for(int i=2;i<=num/2;i++){
	    		if((num%i)==0)
	    			return  false;
	    	}
	    	return true;
	    }//isPrime	    
	    
	    public static int gcd(int e, int eulerTotiont){
	        if (e == 0){
	            return eulerTotiont;
	        }else{
	            return gcd(eulerTotiont % e, e);
	        }}//gcd-recursive Method
	    
	    public static int[] findPosition(String inputLetter){
	    int [] position = new int [inputLetter.length()];
	    for(int i =0; i<inputLetter.length(); i++){
	    char text_charcter= Character.toLowerCase(inputLetter.charAt(i));
	    int Value= (int)text_charcter;
	    position[i]= (int) (Value-96)-1;
	    }
	    return position;
	    }//findPosition
	}//class