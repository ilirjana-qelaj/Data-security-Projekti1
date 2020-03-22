package mergo;


import java.util.Scanner;

public class program {  
    
    public static void main(String[] args) {  
    	Scanner in =new Scanner(System.in);
		String zgjedhja;
		System.out.println("Enter '1' to count words,");
		System.out.println("Enter '2' to count letters,");
		System.out.println("Enter '3' to count symbols,");
		System.out.println("Enter '4' to count vowels and consonants,");
		System.out.println("Enter '5' to count sentences,");
		System.out.println("Enter '6' to count lines,");
		System.out.println("Enter '0' for all function.");
		System.out.println("Enter a digit:");
		  zgjedhja=in.nextLine(); 
		  for(int i=0;i<zgjedhja.length();i++)
		  {
		  		while(Character.isLetter(zgjedhja.charAt(i))
		  		 && zgjedhja.charAt(i)!='0' 
				 && zgjedhja.charAt(i)!='1' 
				 && zgjedhja.charAt(i)!='2' 
				 && zgjedhja.charAt(i)!='3' 
				 && zgjedhja.charAt(i)!='4' 
				 && zgjedhja.charAt(i)!='5' 
				 && zgjedhja.charAt(i)!='6'){
			 System.out.println("Oops look's like you enter a letter or something else... please Enter '0' for all function ,Enter '1' to count words ,"
				 		+"Enter '2' to count letters,Enter '3' to count symbols,Enter '4' to count vowels and consonants," +"Enter '5' to count sentences,Enter '6' to count lines:");
			 System.out.println("Enter a digit:");
			  zgjedhja=in.nextLine(); 
		 }
		 if (zgjedhja.charAt(i)=='0')
		 {
			 allinone();
		 }
		 if (zgjedhja.charAt(i)=='1')
		 {
			 word();
		 }
		 if (zgjedhja.charAt(i)=='2') {
			 letter();
		 }
		 if (zgjedhja.charAt(i)=='3')
		 {
			 symbol();
		 }
		 if (zgjedhja.charAt(i)=='4')
		 {
			 vowelscons();
		 }
		 if (zgjedhja.charAt(i)=='5')
		 {
			 sentences();
		 }
		 if (zgjedhja.charAt(i)=='6')
		 {
			 lines();
		 }
		  }
    }
    public static void allinone() {
    	Scanner in =new Scanner(System.in);
		String plaintextall;
    	 System.out.println("Enter a Text: ");
		 plaintextall=in.nextLine();
		 System.out.println("The number of lines:"+countLines(plaintextall)); 
		 sentences1(plaintextall);  
	     vowelscons12(plaintextall);  
	     System.out.println("The number of words:"+wordcount(plaintextall));  
	     System.out.println("The number of symbols:"+symbolcount(plaintextall)); 
	      System.out.println("The number of letters:"+lettercount(plaintextall));  
    }

    public static void lines() {
    	Scanner in =new Scanner(System.in);
		String plaintext111;
    	 System.out.println("Enter a Text: ");
		 plaintext111=in.nextLine();
		 System.out.println("The number of lines:"+countLines(plaintext111));   
    }
    
    public static void sentences() {
    	Scanner in =new Scanner(System.in);
		String plaintext11;
    	 System.out.println("Enter a Text: ");
		 plaintext11=in.nextLine();
		 sentences1(plaintext11);   
    }
    public static void vowelscons() {
    	Scanner in =new Scanner(System.in);
		String plaintext1;
    	 System.out.println("Enter a Text: ");
		 plaintext1=in.nextLine();
	     vowelscons12(plaintext1);   
    }
    public static void word() {
    	Scanner in =new Scanner(System.in);
		String plaintext;
    	 System.out.println("Enter a Text: ");
		 plaintext=in.nextLine();
	     System.out.println("The number of words:"+wordcount(plaintext));   
    }
    public static void symbol() {
    	Scanner in =new Scanner(System.in);
		String plaintexttt;
    	 System.out.println("Enter a Text: ");
		 plaintexttt=in.nextLine();
	     System.out.println("The number of symbols:"+symbolcount(plaintexttt));   
    }
    public static void letter() {
    	Scanner in =new Scanner(System.in);
		String plaintextt;
    	System.out.println("Enter a Text: ");
		 plaintextt=in.nextLine();
      System.out.println("The number of letters:"+lettercount(plaintextt));   
    }
   static int wordcount(String plaintext)  
    {  
      int countwords=1;
      int countwordsnull=0;
      if (plaintext.length()==0)
      {
      return countwordsnull;
      }
      for(int i=0;i<plaintext.length();i++)  
      {  
          if (plaintext.charAt(i)==' ' && plaintext.charAt(0)!=' ')
          	countwords++;
      }  
      
        return countwords;  
    } 
    static int lettercount(String plaintextt)  
    {  
      int countletter=0;
      
      for(int i = 0; i < plaintextt.length(); i++) {    
          if(Character.isLetter(plaintextt.charAt(i)))
        	  countletter++;    
      }    
      return countletter;
    }
    static int symbolcount(String plaintexttt)  
    {  
      int countsymbol=0;
      
      
	for(int i = 0; i < plaintexttt.length(); i++) { 
    	  plaintexttt.charAt(i);
    	  		if(plaintexttt.charAt(i)=='~' || 
    	  				plaintexttt.charAt(i)=='`' || plaintexttt.charAt(i)=='!' || plaintexttt.charAt(i)=='@' || 
    	  				plaintexttt.charAt(i)=='#' || plaintexttt.charAt(i)=='$' || 
    	  				plaintexttt.charAt(i)=='%' || plaintexttt.charAt(i)=='^' || plaintexttt.charAt(i)=='&' ||
    	  				plaintexttt.charAt(i)=='*' || plaintexttt.charAt(i)=='(' || plaintexttt.charAt(i)==')' || 
    	  				plaintexttt.charAt(i)=='[' || plaintexttt.charAt(i)==']' || plaintexttt.charAt(i)=='{' || 
    	  				plaintexttt.charAt(i)=='}' || plaintexttt.charAt(i)=='/' || plaintexttt.charAt(i)=='.' ||
    	  				plaintexttt.charAt(i)==',' || plaintexttt.charAt(i)=='=' || plaintexttt.charAt(i)=='+' ||
    	  				plaintexttt.charAt(i)=='-' || plaintexttt.charAt(i)==':' || plaintexttt.charAt(i)==';' || 
    	  				plaintexttt.charAt(i)=='|' || plaintexttt.charAt(i)=='<' || plaintexttt.charAt(i)=='>' ||
    	  				plaintexttt.charAt(i)=='"' || plaintexttt.charAt(i)=='_')
    	  {
        	 
              countsymbol++;
      }    
    }
      return countsymbol;
    }
    public static void vowelscons12(String plaintext1)
    {
    	int vowels=0;
    	int cons=0;
    	for (int i=0;i<plaintext1.length();i++)
    	{
    		if(plaintext1.charAt(i)=='a' || plaintext1.charAt(i)=='e'
    				|| plaintext1.charAt(i)=='i' || plaintext1.charAt(i)=='o' 
    				|| plaintext1.charAt(i)=='u' || plaintext1.charAt(i)=='y'
    				|| plaintext1.charAt(i)=='A' || plaintext1.charAt(i)=='E' 
    				|| plaintext1.charAt(i)=='I' || plaintext1.charAt(i)=='O' 
    				|| plaintext1.charAt(i)=='U' || plaintext1.charAt(i)=='Y' )
    		{
    			vowels++;
    		}
    		else {
    			cons++;
    			}
    	}
    	System.out.println("The number of vowels:" +vowels); 
    	System.out.println("The number of consonants:" +cons);
    }
    public static void sentences1(String plaintext11)
    {
    	int sent=0;
    	for(int i=0;i<plaintext11.length();i++)
    	{
    		if(plaintext11.charAt(i)=='!' || plaintext11.charAt(i)=='?' || plaintext11.charAt(i)=='.')
    			sent++;
    	}
    	System.out.println("The number of sentences:" +sent);
    }
    //Prej internetit//
    static int countLines(String plaintext111){
    	   String[] lines = plaintext111.split("\r\n|\r|\n");
    	   return  lines.length;
    	}
    
}


