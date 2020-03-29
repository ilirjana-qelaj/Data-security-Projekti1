import java.util.ArrayList;
import java.util.List;



public class ds {  
    public static void main(String [] args)
    {	
    		while(!args[0].equals("four-square") && !args[0].equals("count") && !args[0].equals("rail-fence"))
    		{
    			System.out.println("-------------Error... Ju keni shtypur nje shkronje&numer tjeter.Provoni perseri-------------");
    	     	break;
    		} 
     	
    		if(args[0].equals("four-square"))
    		{
    		  	foursq(args);	
    		}
    		else if(args[0].equals("count"))
    		{
    		  	leximi(args);	
    		}
    		else if(args[0].equals("rail-fence"))
    		{
    			railFence(args);
    		}
    		    		
    }
    
    
  //===================================================================================================
  //Rail
  //===================================================================================================
    
    public static boolean aEshteNr(String s)
    {
    	boolean rez = true;
    	int gjatesia = s.length();
    	if(gjatesia == 0)
    	{
    		rez = false;
    	}
    	else
    	{
    		for(int j = 0; j<gjatesia; j++)
    		{
    			rez = rez && Character.isDigit(s.charAt(j));
    		}
    	}
    	
    	
    	
		return rez;
    	
    }
    
    
    private static String enkriptoRailFence(String mesazhi, int shiritat, boolean show)
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
				if(show)
				{
					for (int i = 0; i < shkronjat.length; i++) 
					{	
			            for (int j = 0; j < shkronjat[i].length; j++) {
			                cipher = cipher + shkronjat[i][j] + " "; 
					}
					cipher = cipher + "\n";
					}
				}
				else
				{	
				for (int i = 0; i < shkronjat.length; i++) 
		            for (int j = 0; j < shkronjat[i].length; j++) 
		                cipher = cipher + shkronjat[i][j]; 
				}
				
				
			}
			else
			{
				if(gjatesiaM < shiritat)
				{
					System.out.println("Gjatësia e mesazhit tuaj është më e vogël sesa numri i shiritave të zgjedhur!");			
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
					if(show)
					{
						for (int i = 0; i < shkronjat.length; i++) 
						{	
				            for (int j = 0; j < shkronjat[i].length; j++) 
				            {
				                cipher = cipher + shkronjat[i][j] + " "; 
				            }    
				            cipher = cipher + "\n";
						}
						
					}
					else
					{	
					for (int i = 0; i < shkronjat.length; i++) 
			            for (int j = 0; j < shkronjat[i].length; j++) 
			                cipher = cipher + shkronjat[i][j]; 
					}
				}
			 
			}
		}
		else 
		{
			System.out.println("Ju duhet të shkruani një mesazh për enkriptim!");
		}
		return cipher;
	}

	
	private static String dekriptoRailFence(String mesazhi, int shiritat, boolean show)
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
			if(show)
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
			else {
	        for (int i = 0; i < shkronjat.length; i++) 
	            for (int j = 0; j < shkronjat[i].length; j++) 
	                mesazhiDekriptuar = mesazhiDekriptuar + shkronjat[i][j]; 
	        
	        plain = mesazhiDekriptuar.replace("w", "");
			}
		}
		else
		{
			System.out.println("Ju duhet të shkruani një mesazh për enkriptim!");
		}
		return plain;
	}
	
    public static void railFence(String[] args)
    {
    	String veprimi = args[1];
		int shiritat = 1;
		if(aEshteNr(args[2]))
		{
			shiritat = Integer.valueOf(args[2]);
		}
		else
		{
			System.out.println("Argumenti i trete duhet te jete numer qe tregon se ne sa shirita te ndahet mesazhi!");
			System.exit(0);
		}
		String tekstiHyrje = args[3];
		if(veprimi.contentEquals("encrypt"))
		{
			if(args.length == 5)
			{
				if(args[4].equals("show"))
				{
					System.out.println("Teksti i enkriptuar:");
					System.out.println(enkriptoRailFence(tekstiHyrje, shiritat, true ));
				}
				else
				{
					System.out.println("Per te shfaqur ciphertekstin te organizuar ne shirita argumenti i peste(i fundit) duhet te jete show.");
					System.exit(0);
				}
				
			}
			else if(args.length == 4)
			{
				System.out.println("Teksti i enkriptuar:");
				System.out.println(enkriptoRailFence(tekstiHyrje, shiritat, false));
			}
			else
			{
				System.out.println("Komanda duhet te jete kesisoj: ds rail-fence encrypt <rails> <plaintext>");
				System.out.println("ose");
				System.out.println("Komanda duhet te jete kesisoj: ds rail-fence encrypt <rails> <plaintext> show");
				System.exit(0);
				
			}
		}
		else if(veprimi.contentEquals("decrypt"))
		{
			if(args.length == 5)
			{
				if(args[4].equals("show"))
				{
					System.out.println("Teksti i dekriptuar:");
					System.out.println(dekriptoRailFence(tekstiHyrje, shiritat, true ));
				}
				else
				{
					System.out.println("Per te shfaqur ciphertekstin te organizuar ne shirita argumenti i peste(i fundit) duhet te jete show.");
					System.exit(0);
				}
				
			}
			else if(args.length == 4)
			{
				System.out.println("Teksti i dekriptuar:");
				System.out.println(dekriptoRailFence(tekstiHyrje, shiritat, false ));
			}
			else
			{
				System.out.println("Komanda duhet te jete kesisoj: ds rail-fence decrypt <rails> <plaintext>");
				System.out.println("ose");
				System.out.println("Komanda duhet te jete kesisoj: ds rail-fence decrypt <rails> <plaintext> show");
				System.exit(0);
			}
		}
		else
		{
			System.out.println("Veprimi i caktuar permes argumentit te dyte eshte shkruajtur gabim! Veprimet e mundshme jane encrypt dhe decrypt.");
			System.exit(0);
		}
    }
	
	
	
	//===================================================================================================
	//===================================================================================================
	
   
    public static void foursq(String[] args) {
    	
			System.out.println("Teksti:"+args[2]);
			System.out.println("Celesi i pare:"+args[3]);
			System.out.println("Celesi i dyte:"+args[4]);
		if (args[1].equals("encrypt"))
		{
			System.out.println("Teksi i Dekriptuar eshte: "+encryptFS(args[2],args[3],args[4]));
		}
		else if(args[1].equals("decrypt"))
		{
			System.out.println("Teksi i Dekriptuar eshte: "+decryptFS(args[2],args[3],args[4]));

		}
		else {
			System.out.println("Shtypet diqka gabim!!!");
			System.exit(1);
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
	    	//int a='"';
	        input = input.trim().replace("", "").replace("Q", "").toUpperCase();
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
    public static void leximi(String [] args) {  
    	// nese nuk shtypet najnjona prej ktyne --Shtypet diqka gabim
    	System.out.println("Teksti eshte:"+args[2]);
    	while(!args[1].equals("all") && !args[1].equals("word") && !args[1].equals("letter") && !args[1].equals("symbol") && !args[1].equals("vowel") 
    		&& !args[1].equals("sentences") && !args[1].equals("lines"))
    	{
    		System.out.println("Shtypet diqka gabim!!");
    		break;
    	}
    	// nese argumenti i pare osht all ose word... e thirr at klase
		 if (args[1].equals("all"))
		 {
			 allinone(args);
		 }
		 else if (args[1].equals("word"))
		 {
			 wordcount(args);
		 }
		 else if (args[1].equals("letter"))
				 {
			 lettercount(args);
		 }
		 else if (args[1].equals("symbol"))
		 {
			 symbolcount(args);
		 }
		 else if (args[1].equals("vowel"))
		 {
			 vowelscons12(args);
		 }
		 else if (args[1].equals("sentences"))
		 {
			 sentences1(args);
		 }
		 else if (args[1].equals("lines"))
		 {
			 countLines(args);
		 }
		  }
 
    public static void allinone(String [] args) {
    	//i thirr krejt 
		 countLines(args); 
		 sentences1(args);  
	     vowelscons12(args);  
	     wordcount(args);  
	     symbolcount(args); 
	      lettercount(args);  
    }

    public static void wordcount(String [] args)  
    {  
    	//per shkak se kur e shtyp ni tekst 'null' mdilke qe numri i fjaleve eshte 1 per qata osht if args==0 ...
      int countwords=1;
      int countwordsnull=0;
      if (args[2].length()==0)
      {
      System.out.println("Numri i fjaleve eshte:" +countwordsnull );
      System.exit(1);
      }
      for(int i=0;i<args[2].length();i++)  
      {  
    	  // nese ka space ather rrite per njo fjalen
          if (args[2].length()!=0 && args[2].charAt(i)==' ' && args[2].charAt(0)!=' ')
          	countwords++;
      }  
      
        System.out.println("Numri i fjaleve eshte:"+countwords) ;
    } 
    public static void lettercount(String [] args)  
    {  
      int countletter=0;
      
      for(int i = 0; i < args[2].length(); i++) {    
    	  //nese osht shkronje rrite per nje shkronjen
          if(Character.isLetter(args[2].charAt(i)))
        	  countletter++;    
      }    
      System.out.println("Numri i shkronjave eshte:"+countletter);
    }
    public static void symbolcount(String [] args)  
    {  
      int countsymbol=0;
      	for(int i = 0; i < args[2].length(); i++) { 
    	  args[2].charAt(i);
    	  // nese osht najnjana prej atyne simboleve (krejt qato simbole) rrite per njo simbolet 
    	  // kemi mujt me bo edhe 
    	  //if (!Character.isLetter(args[2].charAt(i)) && !Character.isDigit(args[2].charAt(i)) nese nuk osht as shkronje as numer 
    	  // rrite simbolin
    	  		/*if(args[2].charAt(i)=='~' || 
    	  				args[2].charAt(i)=='`' || args[2].charAt(i)=='!' || args[2].charAt(i)=='@' || 
    	  				args[2].charAt(i)=='#' || args[2].charAt(i)=='$' || 
    	  				args[2].charAt(i)=='%' || args[2].charAt(i)=='^' || args[2].charAt(i)=='&' ||
    	  				args[2].charAt(i)=='*' || args[2].charAt(i)=='(' || args[2].charAt(i)==')' || 
    	  				args[2].charAt(i)=='[' || args[2].charAt(i)==']' || args[2].charAt(i)=='{' || 
    	  				args[2].charAt(i)=='}' || args[2].charAt(i)=='/' || args[2].charAt(i)=='.' ||
    	  				args[2].charAt(i)==',' || args[2].charAt(i)=='=' || args[2].charAt(i)=='+' ||
    	  				args[2].charAt(i)=='-' || args[2].charAt(i)==':' || args[2].charAt(i)==';' || 
    	  				args[2].charAt(i)=='|' || args[2].charAt(i)=='<' || args[2].charAt(i)=='>' ||
    	  				args[2].charAt(i)=='"' || args[2].charAt(i)=='_')
    	  			*/
    	  if(!Character.isLetter(args[2].charAt(i)) && !Character.isDigit(args[2].charAt(i)) && args[2].charAt(i)!=' ')
    	  		
    	  {
        	 
              countsymbol++;
      }    
    }
      System.out.println("Numri i simboleve eshte:"+countsymbol);
    }
    public static void vowelscons12(String [] args)
    {
    	int vowels=0;
    	int cons=0;
    	for (int i=0;i<args[2].length();i++)
    	{
    		// nese e shtyp njanen prej ktyne shkronjave rrite vowels (zanoren)
    		// edhe nqofse osht shkronje
    		if (Character.isLetter(args[2].charAt(i))) {
    		if(args[2].charAt(i)=='a' || args[2].charAt(i)=='e'
    				|| args[2].charAt(i)=='i' || args[2].charAt(i)=='o' 
    				|| args[2].charAt(i)=='u' || args[2].charAt(i)=='y'
    				|| args[2].charAt(i)=='A' || args[2].charAt(i)=='E' 
    				|| args[2].charAt(i)=='I' || args[2].charAt(i)=='O' 
    				|| args[2].charAt(i)=='U' || args[2].charAt(i)=='Y' )
    		{
    			vowels++;
    		}
    		else {
    			cons++;
    			}
    	}
    	}
    	System.out.println("Numri i zanoreve eshte:" +vowels); 
    	System.out.println("Numri i bashketingelloreve eshte:" +cons);
    }
    public static void sentences1(String [] args)
	{
    	//logjikisht ni fjali fillon nqofse kemi . ose ! ose ? kshtu qe nqofse kemi najnjo prej ktyne simbolev rritet edhe fjalia 
		int sent=0;
		for(int i=0;i<args[2].length();i++)
		{
			if(args[2].charAt(i)=='!' || args[2].charAt(i)=='?' || args[2].charAt(i)=='.')
				sent++;
		}
		System.out.println("Numri i fjalive eshte:" +sent);
	}
	//Prej internetit//
    //nese dalim nrresht tri me njonen prej ktyne \r\n... rrite rreshtin
    public static void countLines(String [] args){
    	   String[] lines = args[2].split("\r\n|\r|\n");
    	   System.out.println("Numri i rreshtave eshte:"+ lines.length);
    	} 
    }
