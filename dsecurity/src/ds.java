import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ds {  
    public static void main(String [] args)
    {	
    	Scanner in =new Scanner(System.in);
    	System.out.println("Shtypni '1' ose 'f' per ta provuar komanden -Four Square-,");
     	System.out.println("Shtypni '2' ose 'c' per ta provuar komanden -Count-,");
     	System.out.println("Shtypni '3' ose 'r' per ta provuar komanden -Rail Fence-.");
     	System.out.println("Shtypni nje shkronje ose nje numer:");
    	String lex=in.nextLine();
    	for(int i=0;i<lex.length();i++)
    	{
    		while(lex.charAt(i)!='1' && lex.charAt(i)!='2' && lex.charAt(i)!='3' && lex.charAt(i)!='f' && lex.charAt(i)!='c' && lex.charAt(i)!='r')
    		{
    			System.out.println("-------------Error... Ju keni shtypur nje shkronje&numer tjeter.Provoni perseri-------------");
    	     	System.out.println("Shtypni nje shkronje ose nje numer:");
    	    	lex=in.nextLine();
    		}
    		if(lex.charAt(i)=='1' || lex.charAt(i)=='f')
    		{
    		  	foursq(args);	
    		}
    		if(lex.charAt(i)=='2' || lex.charAt(i)=='c')
    		{
    		  	leximi();	
    		}
    		if(lex.charAt(i)=='3' || lex.charAt(i)=='r')
    		{
    		  	rail(args);	
    		}		
    }
    }
    public static void foursq(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.print("Jepni nje tekst: ");
		String plaintext=input.nextLine();
		System.out.print("Jepni çelesin e pare:");
		String key1=input.nextLine();
		System.out.print("Jepni çelesin e dyte:");
		String key2=input.nextLine();
		System.out.print("Shtypni 'e' per Enkriptim ose 'd' per dekriptim :");
		String choose=input.nextLine();
		if(choose.equals("e"))
		{
			System.out.println("Teksi i Enkriptuar eshte: "+encryptFS(plaintext,key1,key2));

		}
	    
	        else
		{
			System.out.println("Teksi i Dekriptuar eshte: "+decryptFS(plaintext,key1,key2));

		}

	
		
	}
	 private static final char[] ALFABETI = "ABCDEFGHIJKLMNOPRSTUVWXYZ".toCharArray();

	    private static final char[][] ALFABETI_KATROR = new char[5][5];

	    //bllok statik per te populluar matricen alfabeti katror
	    static {
	        int x = 0, y = 0;
	        for (char c : ALFABETI) {
	            ALFABETI_KATROR[x][y] = c;
	            x++;
	            if (x == 5) {
	                x = 0;
	                y++;
	            }
	        }
	    }


	    private static String clean(String input) {
	        input = input.trim().replace(" ", "").replace("Q", "").toUpperCase();
	        StringBuilder clean = new StringBuilder();
	        for (char c : input.toCharArray()) {
	            if (Character.getType(c) == Character.UPPERCASE_LETTER) {
	                clean.append(c);
	            }
	        }
	        return clean.toString();
	    }


	     //Gjeneron nje table 5*5 te qelesave per fjalen kyqe te specifikuar
	    private static char[][] generateKeyTable(String keyword) {
	        keyword = clean(keyword);
	        char[][] keyTable = new char[5][5];
	        List<Character> used = new ArrayList<Character>();
	        int x = 0, y = 0;
	        for (char c : keyword.toCharArray()) {
	            if (!used.contains(c)) {
	                keyTable[x][y] = c;
	                used.add(c);
	                x++;
	                if (x == 5) {
	                    x = 0;
	                    y++;
	                    if (y == 5) {
	                        return keyTable;
	                    }
	                }
	            }
	        }
	        for (char c : ALFABETI) {
	            if (!used.contains(c)) {
	                keyTable[x][y] = c;
	                x++;
	                if (x == 5) {
	                    x = 0;
	                    y++;
	                    if (y == 5) {
	                        return keyTable;
	                    }
	                }
	            }
	        }
	        return keyTable;
	    }


	    //ndan stringun ne stringje dy-shkronjeshe
	    private static String[] split(String plaintext) {
	        if (plaintext.length() % 2 != 0) {
	            plaintext = plaintext + "X";
	        }
	        String[] pairs = new String[plaintext.length() / 2];//array i stringjeve dyshkronjeshe
	        int count = 0;
	        for (int i = 0; i < (plaintext.length() / 2); i++) {
	            pairs[i] = plaintext.substring(count, count + 2);
	            count = count + 2;
	        }
	        return pairs;
	    }

	    // enkriptimi i plaintekstit 
	    public static String encryptFS(String plaintext, String keyword1, String keyword2) {
	        plaintext = clean(plaintext);
	        String[] pairs = split(plaintext);
	        char[][] keytable1 = generateKeyTable(keyword1);
	        char[][] keytable2 = generateKeyTable(keyword2);
	        char first, second;
	        int xFirst = 0, yFirst = 0, xSecond = 0, ySecond = 0;
	        StringBuilder ciphertext = new StringBuilder();
	        for (String s : pairs) {
	            first = s.charAt(0);
	            second = s.charAt(1);
	            for (int y = 0; y < 5; y++) {
	                for (int x = 0; x < 5; x++) {
	                    if (ALFABETI_KATROR[x][y] == first) {
	                        xFirst = x;
	                        yFirst = y;
	                    } else if (ALFABETI_KATROR[x][y] == second) {
	                        xSecond = x;
	                        ySecond = y;
	                    }
	                }
	            }
	            ciphertext.append(keytable1[xSecond][yFirst]).append(keytable2[xFirst][ySecond]);
	        }
	        return ciphertext.toString();
	    }

	    //dekriptimi
	    public static String decryptFS(String ciphertext, String keyword1, String keyword2) {
	        String[] pairs = split(ciphertext);
	        char[][] keytable1 = generateKeyTable(keyword1);
	        char[][] keytable2 = generateKeyTable(keyword2);
	        char first, second;
	        int xFirst = 0, yFirst = 0, xSecond = 0, ySecond = 0;
	        StringBuilder plaintext = new StringBuilder();
	        for (String s : pairs) {
	            first = s.charAt(0);
	            second = s.charAt(1);
	            for (int y = 0; y < 5; y++) {
	                for (int x = 0; x < 5; x++) {
	                    if (keytable1[x][y] == first) {
	                        xFirst = x;
	                        yFirst = y;
	                    } else if (keytable2[x][y] == second) {
	                        xSecond = x;
	                        ySecond = y;
	                    }
	                }
	            }
	            plaintext.append(ALFABETI_KATROR[xSecond][yFirst]).append(ALFABETI_KATROR[xFirst][ySecond]);
	        }
	        return plaintext.toString();
	    }
    public static void leximi() {  
    	Scanner in =new Scanner(System.in);
		String zgjedhja;
		System.out.println("Shtypni '1' per te numruar fjalet,");
		System.out.println("Shtypni '2' per te numruar shkronjat,");
		System.out.println("Shtypni '3' per te numruar simbolet,");
		System.out.println("Shtypni '4' per te numruar zanoret dhe bashketingelloret,");
		System.out.println("Shtypni '5' per te numruar fjalit,");
		System.out.println("Shtypni '6' per te numruar rreshtat,");
		System.out.println("Shtypni '0' per te provuar te gjitha funksionet.");
		System.out.println("Shtyp nje numer:");
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
			 System.out.println("-------------Error... Ju keni shtypur nje shkronje&numer tjeter.Provoni perseri-------------");
				System.out.println("Shtypni '1' per te numruar fjalet," +"Shtypni '2' per te numruar shkronjat,");
				System.out.println("Shtypni '3' per te numruar simbolet,"+"Shtypni '4' per te numruar zanoret dhe bashketingelloret,");
				System.out.println("Shtypni '5' per te numruar fjalit,"+"Shtypni '6' per te numruar rreshtat,");
				System.out.println("Shtypni '0' per te provuar te gjitha funksionet.");
				System.out.println("Shtyp nje numer:");
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
    	 System.out.println("Jepni nje tekst: ");
		 plaintextall=in.nextLine();
		 System.out.println("Numri i rreshtave eshte:"+countLines(plaintextall)); 
		 sentences1(plaintextall);  
	     vowelscons12(plaintextall);  
	     System.out.println("Numri i fjaleve eshte:"+wordcount(plaintextall));  
	     System.out.println("Numri i simboleve eshte:"+symbolcount(plaintextall)); 
	      System.out.println("Numri i shkronjave eshte:"+lettercount(plaintextall));  
    }

    public static void lines() {
    	Scanner in =new Scanner(System.in);
		String plaintext111;
    	 System.out.println("Jepni nje tekst: ");
		 plaintext111=in.nextLine();
		 System.out.println("Numri i rreshtave eshte:"+countLines(plaintext111));   
    }
    
    public static void sentences() {
    	Scanner in =new Scanner(System.in);
		String plaintext11;
    	 System.out.println("Jepni nje tekst:");
		 plaintext11=in.nextLine();
		 sentences1(plaintext11);   
    }
    public static void vowelscons() {
    	Scanner in =new Scanner(System.in);
		String plaintext1;
    	 System.out.println("Jepni nje tekst:");
		 plaintext1=in.nextLine();
	     vowelscons12(plaintext1);   
    }
    public static void word() {
    	Scanner in =new Scanner(System.in);
		String plaintext;
    	 System.out.println("Jepni nje tekst:");
		 plaintext=in.nextLine();
	     System.out.println("Numri i fjaleve eshte:"+wordcount(plaintext));   
    }
    public static void symbol() {
    	Scanner in =new Scanner(System.in);
		String plaintexttt;
    	 System.out.println("Jepni nje tekst: ");
		 plaintexttt=in.nextLine();
	     System.out.println("Numri i simboleve eshte:"+symbolcount(plaintexttt));   
    }
    public static void letter() {
    	Scanner in =new Scanner(System.in);
		String plaintextt;
    	System.out.println("Jepni nje tekst:");
		 plaintextt=in.nextLine();
      System.out.println("Numri i shkronjave eshte:"+lettercount(plaintextt));   
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
    	System.out.println("Numri i zanoreve eshte:" +vowels); 
    	System.out.println("Numri i bashketingelloreve eshte:" +cons);
    }
    public static void sentences1(String plaintext11)
	{
		int sent=0;
		for(int i=0;i<plaintext11.length();i++)
		{
			if(plaintext11.charAt(i)=='!' || plaintext11.charAt(i)=='?' || plaintext11.charAt(i)=='.')
				sent++;
		}
		System.out.println("Numri i fjalive eshte:" +sent);
	}
	//Prej internetit//
    static int countLines(String plaintext111){
    	   String[] lines = plaintext111.split("\r\n|\r|\n");
    	   return  lines.length;
    	}
    public static void rail(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Shkruani mesazhin për enkriptim/dekriptim:");
    	String mesazhi = sc.nextLine();
    	
    	System.out.print("Jepni numrin e shiritave<rails>:");
    	int railsNr = sc.nextInt();
    	
    	System.out.println("Zgjedhni veprimin (1-për Enkriptim, 2-për Dekriptim):");
    	int veprimi = sc.nextInt();
    	
    	System.out.println("Zgjedhni formen e tekstit ne dalje (1-per rresht, 2-per shirita):");
    	int show = sc.nextInt();
    	
    	if(veprimi == 1)
    	{
    		System.out.println("Mesazhi i enkriptuar: ");
    		System.out.print(enkriptoRailFence(mesazhi, railsNr, show));
    ;	}
    	else if(veprimi == 2)
    	{
    		System.out.println("Mesazhi i dekriptuar: ");
            System.out.print(dekriptoRailFence(mesazhi, railsNr, show));
    	}
    	else
    	{
    		System.out.println("Kontrolloni komandën e dhënë për veprimin e zgjedhur!");
    	}
    	
    	

    	}
    	
    	private static String enkriptoRailFence(String mesazhi, int shiritat, int show)
    	{
    		String mesazhiEn = mesazhi.replaceAll("\\s+", "");
    		int gjatesiaM = mesazhiEn.length();
    		String cipher ="";
    		if(gjatesiaM > 0)
    		{	
    			int plotpjestimi = gjatesiaM % shiritat;
    			if(plotpjestimi == 0)
    			{
    				int kolonat = gjatesiaM/shiritat;
    				String[][] shkronjat = new String[shiritat][kolonat];
    				int shkronjaRadhes = 0; 
    				for(int i = 0; i< shiritat; i++)
    				{
    					for(int j = 0; j < kolonat; j++)
    					{
    						shkronjat[i][j]=String.valueOf(mesazhiEn.charAt(shkronjaRadhes));
    						shkronjaRadhes = shkronjaRadhes + shiritat;
    						if(j==kolonat-1)
    						{
    							shkronjaRadhes = i+1;
    						}
    						
    					}
    				}
    				//System.out.println("Mesazhi i enkriptuar është:");
    				if(show ==2)
    				{
    					for (int i = 0; i < shkronjat.length; i++) 
    					{	
    			            for (int j = 0; j < shkronjat[i].length; j++) {
    			                cipher = cipher + shkronjat[i][j] + " "; 
    					}
    					cipher = cipher + "\n";
    					}
    				}
    				else if(show ==1)
    				{	
    				for (int i = 0; i < shkronjat.length; i++) 
    		            for (int j = 0; j < shkronjat[i].length; j++) 
    		                cipher = cipher + shkronjat[i][j]; 
    				}
    				else
    				{
    					System.out.print("Gabim gjatë zgjedhjes së formatit të tekstit në dalje!");
    				}
    				
    				
    			}
    			else
    			{
    				if(gjatesiaM < shiritat)
    				{
    					JOptionPane.showMessageDialog(null, "Gjatësia e mesazhit tuaj është më e vogël sesa numri i shiritave të zgjedhur!");			
    				}
    				else
    				{
    					
    					String mesazhiPlotesuar = mesazhiEn + "w".repeat(shiritat - plotpjestimi);
    					int kolonat = mesazhiPlotesuar.length()/shiritat;
    					
    					String[][] shkronjat = new String[shiritat][kolonat]; 
    					int shkronjaRadhes = 0;
    					for(int i = 0; i< shiritat; i++)
    					{
    						for(int j = 0; j < kolonat; j++)
    						{
    							shkronjat[i][j]=String.valueOf(mesazhiPlotesuar.charAt(shkronjaRadhes));
    							shkronjaRadhes = shkronjaRadhes + shiritat;
    							if(j==kolonat-1)
    							{
    								shkronjaRadhes = i+1;
    							}
    							
    						}
    					}
    					//JOptionPane.showMessageDialog(null, "Mesazhi i enkriptuar është:");
    					if(show ==2)
    					{
    						for (int i = 0; i < shkronjat.length; i++) 
    						{	
    				            for (int j = 0; j < shkronjat[i].length; j++) {
    				                cipher = cipher + shkronjat[i][j] + " "; 
    						}
    						cipher = cipher + "\n";
    						}
    					}
    					else if(show ==1)
    					{	
    					for (int i = 0; i < shkronjat.length; i++) 
    			            for (int j = 0; j < shkronjat[i].length; j++) 
    			                cipher = cipher + shkronjat[i][j]; 
    					}
    					else
    					{
    						System.out.print("Gabim gjatë zgjedhjes së formatit të tekstit në dalje!");
    					}
    				}
    			 
    			}
    		}
    		else 
    		{
    			JOptionPane.showMessageDialog(null, "Ju duhet të shkruani një mesazh për enkriptim!");
    		}
    		return cipher;
    	}

    	
    	private static String dekriptoRailFence(String mesazhi, int shiritat, int show)
    	{
    		String mesazhiDe = mesazhi.replaceAll("\\s+", "");
    		int gjatesiaM = mesazhiDe.length();
    		String plain="";
    		if(gjatesiaM > 0)
    		{
    			int rreshtat = gjatesiaM/shiritat;
    			String[][] shkronjat = new String[rreshtat][shiritat]; 
    			int shkronjaRadhes = 0;  
    			for(int i = 0; i< rreshtat; i++)
    			{
    				for(int j = 0; j < shiritat; j++)
    				{
    					shkronjat[i][j]=String.valueOf(mesazhiDe.charAt(shkronjaRadhes));
    					shkronjaRadhes = shkronjaRadhes + rreshtat;
    					if(j==shiritat-1)
    					{
    						shkronjaRadhes = i+1; 
    					}
    					
    				}
    			}
    			
    			String mesazhiDekriptuar="";
    			if(show==2)
    			{
    				for (int i = 0; i < shkronjat.length; i++) 
    				{	
    		            for (int j = 0; j < shkronjat[i].length; j++)
    		            {
    		                mesazhiDekriptuar = mesazhiDekriptuar + shkronjat[i][j] + " ";
    		            }
    		            mesazhiDekriptuar = mesazhiDekriptuar + "\n";
    				}
    				plain = mesazhiDekriptuar.replace("w", "");
    			}
    			else if(show==1){
    	        for (int i = 0; i < shkronjat.length; i++) 
    	            for (int j = 0; j < shkronjat[i].length; j++) 
    	                mesazhiDekriptuar = mesazhiDekriptuar + shkronjat[i][j]; 
    	        
    	        plain = mesazhiDekriptuar.replace("w", "");
    			}
    			else
    			{
    				System.out.print("Gabim gjatë zgjedhjes së formatit të tekstit në dalje!");
    			}
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(null, "Ju duhet të shkruani një mesazh për enkriptim!");
    		}
    		return plain;
    	}

}


