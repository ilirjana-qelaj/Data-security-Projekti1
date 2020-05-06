package ds;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.stream.Stream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;



public class ds { 
public static void main(String [] args) throws IOException, InterruptedException
    {	   		
    		switch (args[0]) {
    	      case "four-square":
    	    	  foursq(args);
    	        break;
    	      case "count":
    	    	  leximi(args);
    	        break;
    	      case "rail-fence":
    	    	  railFence(args);
    	        break;
    	      case "create-user":
    	        createUser(args);
    	        break;
	      case "delete-user":
    	        deleteUser(args);
    	        break;
	      case "export-key":
    	        exportKey(args);
    	        break;
    	      case "import-key":
      	        importKey(args);
    	        break;		
    	      default:
    	    	  System.out.println("Keni shkruajtur komande te gabuar!");
      	     	
    	    }
   
    		    		
    }
		 //===================================================================================================
    //Create User
    //===================================================================================================
    
    
    public static void createUser(String[] args)
    {
    	if(args.length != 2)
		{
			System.out.println("Numer jo i sakte i argumenteve!");
			System.out.println("Komanda duhet te shkruhet kesisoj: ds create-user <name>");
		}
		else
		{
			try {
				KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
				keyPairGenerator.initialize(512);
				KeyPair keyPair = keyPairGenerator.generateKeyPair();
				
				keyPair.getPublic();
				keyPair.getPrivate();
				
				
				
				KeyFactory kf = KeyFactory.getInstance("RSA");
				RSAPrivateCrtKeySpec ks;

				try {
					ks = kf.getKeySpec(
					    keyPair.getPrivate(), RSAPrivateCrtKeySpec.class);
								
					String cPrivat ="<RSAKeyValue> \n"
						    + "<Modulus>" +  String.valueOf(ks.getModulus()) + "</Modulus>\n"
						    + "<Exponent>" + String.valueOf( ks.getPublicExponent()) + "</Exponent>\n"
						    + "<P>" + String.valueOf( ks.getPrimeP()) + "</P>\n"
						    + "<Q>" + String.valueOf( ks.getPrimeQ()) + "</Q>\n"
						    + "<DP>" + String.valueOf( ks.getPrimeExponentP()) + "</DP>\n"
						    + "<DQ>" + String.valueOf( ks.getPrimeExponentQ()) + "</DQ>\n"
						    + "<InverseQ>" + String.valueOf( ks.getCrtCoefficient()) + "</InverseQ>\n"
						    + "<D>" + String.valueOf( ks.getPrivateExponent()) + "</D>\n"
						    + "</RSAKeyValue>";
							
										
						String cPublik = "<RSAKeyValue> \n"
								+ "<Modulus>" + String.valueOf( ks.getModulus()) + "</Modulus>\n"
										+ "<Exponent>" + String.valueOf( ks.getPublicExponent()) + "</Exponent>\n"
												+ "</RSAKeyValue>";
					
					
					String emri = args[1];
					
					String pathPri = "C:/Users/hp/OneDrive/Desktop/keypairs/"+emri+".xml";
					String pathPub = "C:/Users/hp/OneDrive/Desktop/keypairs/"+emri+".pub.xml";
					
					File qelesiPerKrijimPub = new File(pathPub);
					File qelesiPerKrijimPri = new File(pathPri);
					boolean existsPu = qelesiPerKrijimPub.exists();
					boolean existsPr = qelesiPerKrijimPri.exists();
					
					if(existsPu || existsPr)
					{
						System.out.println(" Gabim celesi '"+args[1]+"' ekziston paraprakisht!");
					}
					else {
					try {
						ruajXML(cPrivat, pathPri);
						ruajXML(cPublik, pathPub);
						System.out.println("Eshte krijuar celesi publik '"+emri+".pub.xml");
						System.out.println("Eshte krijuar celesi privat '" +emri+".xml");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					}

					
					
					
				} catch (InvalidKeySpecException e) {
					
					e.printStackTrace();
				}
				
				
				//System.out.println(publicKey);
				//System.out.println(privateKey);
				
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			}
		}
    }
     
     //===================================================================================================
    //Delete User
    //===================================================================================================
    
    
    public static void deleteUser(String[] args)
    {
    	if(args.length != 2)
		{
			System.out.println("Numer jo i sakte i argumenteve!");
			System.out.println("Komanda duhet te shkruhet kesisoj: ds delete-user <name>");
		}
		else
		{
			String fajlliPu = "C:/Users/hp/OneDrive/Desktop/keypairs/"+args[1]+".pub.xml";
			String fajlliPr = "C:/Users/hp/OneDrive/Desktop/keypairs/"+args[1]+".xml";
			File qelesiPerFshirjePu = new File(fajlliPu);
			File qelesiPerFshirjePr = new File(fajlliPr);
			
			
			boolean existsPu = qelesiPerFshirjePu.exists();
			boolean existsPr = qelesiPerFshirjePr.exists();
			
			if(existsPu || existsPr)
			{
				if(existsPu)
				{
					if (qelesiPerFshirjePu.isFile())
					{	
					qelesiPerFshirjePu.delete();
					System.out.println("Eshte larguar celesi publik '" +fajlliPu +"'");
					}
					else
					{
						System.out.println(" Useri '"+args[1]+"' nuk eshte fajll!");
					}
						
				}
				if(existsPr)
				{
					if (qelesiPerFshirjePr.isFile())
					{	
					qelesiPerFshirjePr.delete();
					System.out.println("Eshte larguar celesi privat '" +fajlliPr +"'");
				    }
					else
					{
						System.out.println(" Useri '"+args[1]+"' nuk eshte fajll!");
					}
						
				}
			}
			else
			{
				System.out.println(" Gabim celesi '"+args[1]+"' nuk ekziston!");
			}
			
		}
    }
    
	public static void ruajXML(String celesi, String path) throws IOException 
	{
	     
	    FileWriter fileWriter = new FileWriter(path);
	    fileWriter.write(celesi);
	    fileWriter.close();
	}
	//===================================================================================================
    //Export Key
    //===================================================================================================
    public static void exportKey(String[] args)
    {
    	
    	if(args.length == 3)
		{
    		Scanner sc;
			if(args[1].equals("public"))
			{
				String fajlliPerEksportimPub = "C:\\Users\\SAMIRISOFT\\Desktop\\ds"+args[2]+".pub.xml";
				File filePu = new File(fajlliPerEksportimPub);
				boolean existsPu = filePu.exists();
				if(existsPu)
				{
					try {
						sc = new Scanner(filePu);
						while (sc.hasNextLine()) 
							System.out.println(sc.nextLine());
					} catch (FileNotFoundException e) {
						
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("Celesi publik '"+args[2]+".pub.xml' nuk ekziston!");
				}
			}
			else if(args[1].equals("private"))
			{
				String fajlliPerEksportimPri = "C:\\Users\\SAMIRISOFT\\Desktop\\ds"+args[2]+".xml";
		    	File filePri = new File(fajlliPerEksportimPri);
				boolean existsPr = filePri.exists();
				
				if(existsPr)
				{
					try {
						sc = new Scanner(filePri);
						while (sc.hasNextLine()) 
							System.out.println(sc.nextLine());
					} catch (FileNotFoundException e) {
						
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("Celesi privat '"+args[2]+".xml' nuk ekziston!");
				}
			}
			else
			{
				System.out.println("Argumenti i dytë eshte shkruajtur gabim, argumenti i dyte duhet te jete public ode private!");
			}
		}
    	else if(args.length == 4)
    	{
    		if(args[1].equals("public"))
			{
    			
    			String fajlliPerEksportimPub = "C:\\Users\\SAMIRISOFT\\Desktop\\ds"+args[2]+".pub.xml";
				File filePu = new File(fajlliPerEksportimPub);
				boolean existsPu = filePu.exists();
				
				if(existsPu)
				{
					boolean pathValid = isPathValid(args[3]);
					if(pathValid)
					{
						FileInputStream instream = null;
		    			FileOutputStream outstream = null;
		    		 
		    		    	try{
		    		    	    File infile =new File(fajlliPerEksportimPub);
		    		    	    File outfile =new File(args[3]+".pub.xml");
		    		 
		    		    	    instream = new FileInputStream(infile);
		    		    	    outstream = new FileOutputStream(outfile);
		    		 
		    		    	    byte[] buffer = new byte[1024];
		    		 
		    		    	    int length;
		    		    	   
		    		    	    while ((length = instream.read(buffer)) > 0){
		    		    	    	outstream.write(buffer, 0, length);
		    		    	    }

		    		    	    instream.close();
		    		    	    outstream.close();
		    		    	    
		    		    	    System.out.println("Celesi publik u ruajt ne fajllin '"+args[3]+".pub.xml'");
		    		    	    
		    		    	}catch(IOException ioe){
		    		    		ioe.printStackTrace();
		    		    	 }
					}
					else
					{
						System.out.println("Shtegu i dhënë është i gabuar!");
					}
				}
				else
				{
					System.out.println("Celesi publik '"+args[2]+".pub.xml' nuk ekziston!");
				}
				
			}
    		else if(args[1].equals("private"))
			{
    			String fajlliPerEksportimPri = "C:\\Users\\SAMIRISOFT\\Desktop\\ds"+args[2]+".xml";
				File filePu = new File(fajlliPerEksportimPri);
				boolean existsPr = filePu.exists();
				
				if(existsPr)
				{
					boolean pathValid = isPathValid(args[3]);
					if(pathValid)
					{
						FileInputStream instream = null;
		    			FileOutputStream outstream = null;
		    		 
		    		    	try{
		    		    	    File infile =new File(fajlliPerEksportimPri);
		    		    	    File outfile =new File(args[3]+".xml");
		    		 
		    		    	    instream = new FileInputStream(infile);
		    		    	    outstream = new FileOutputStream(outfile);
		    		 
		    		    	    byte[] buffer = new byte[1024];
		    		 
		    		    	    int length;
		    		    	   
		    		    	    while ((length = instream.read(buffer)) > 0){
		    		    	    	outstream.write(buffer, 0, length);
		    		    	    }

		    		    	    instream.close();
		    		    	    outstream.close();
		    		    	    
		    		    	    System.out.println("Celesi privat u ruajt ne fajllin '"+args[3]+".xml'");
		    		    	    
		    		    	    
		    		    	}catch(IOException ioe){
		    		    		ioe.printStackTrace();
		    		    	 }
					}
					else
					{
						System.out.println("Shtegu i dhënë është i gabuar!");
					}
				}
				else
				{
					System.out.println("Celesi privat '"+args[2]+".xml' nuk ekziston!");
				}
			}
    		else
    		{
    			System.out.println("Argumenti i dytë eshte shkruajtur gabim, argumenti i dyte duhet te jete public ode private!");
    		}
    	}
		else
		{
			
			System.out.println("Numer jo i sakte i argumenteve!");
			System.out.println("Komanda duhet te shkruhet kesisoj:  ds export-key <public|private> <name>");
			System.out.println("ose");
			System.out.println("Komanda duhet te shkruhet kesisoj:  ds export-key <public|private> <name> [file]");
			
			
		}
    	
    }
    
    public static boolean isPathValid(String path) {

        try {

            Paths.get(path);

        } catch (InvalidPathException ex) {
            return false;
        }

        return true;
    }
	
	
    //===================================================================================================
    //Import Key
    //===================================================================================================
    
    public static void importKey(String[] args) throws IOException, InterruptedException
    {
    	if(args.length==3)
    	{
    		
    		String fajlliPerRuajtje = args[1];
			File fileRu = new File(fajlliPerRuajtje);
			boolean exists = fileRu.exists();
			
			if(!exists)
			{
				if(args[2].startsWith("http:") || args[2].startsWith("https:"))
				{
					if(existsUrl(args[2]))
					{						
						HttpClient client = HttpClient.newHttpClient();
				        HttpRequest request = HttpRequest.newBuilder()
				                .uri(URI.create(args[2]))
				                .build();

				        HttpResponse<String> response = client.send(request,
				                HttpResponse.BodyHandlers.ofString());
				        
				        String[] lines = response.body().split("\r\n|\r|\n");
				        
				        //System.out.println(lines.length);
				        if(lines.length==4)
				        {
				        FileWriter myWriter = new FileWriter(args[1]+".pub.xml");
				        myWriter.write(response.body());
				        myWriter.close();
				        }
				        else if(lines.length==10)
				        {
				        FileWriter myWriter = new FileWriter(args[1]+".xml");
				        myWriter.write(response.body());
				        myWriter.close();
				        
				        FileWriter myWriter2 = new FileWriter(args[1]+".pub.xml");
				        for(int i=0;i<3;i++)
				        {	
				        myWriter2.append(lines[i]+"\n");
				        }
				        myWriter2.append("</RSAKeyValue>");
				        myWriter2.close();
				        }
				        else
				        {
				        	System.out.println("Fajlli i specifikuar per importim nuk permban te dhenat ne teresi!");
				        }
					}
					else
					{
						System.out.println("Url e dhene nuk ekziston!");
					}
				}
				else
				{
					boolean pathValid = isPathValid(args[2]);
					if(pathValid)
					{
						if(args[2].endsWith(".xml"))
						{	
		    		    	try{
		    		    		String fajlliPerImportim = args[2];
		    		    	    File infile =new File(fajlliPerImportim);
		    		    	    File outfile = null;
		    		    	    
		    		    	    FileInputStream instream = new FileInputStream(infile);
    		    	    		
		    		 
		    		    	    byte[] buffer = new byte[1024];
		    		 
		    		    	    int length;
		    		    	    
		    		    	    if(infile.exists())
		    		    	    {
		    		    	    	if(numeroRreshtat(fajlliPerImportim)==4)
			    		    	    {
		    		    	    		outfile =new File(args[1]+".pub.xml");
		    		    	    		FileOutputStream outstream = new FileOutputStream(outfile);
		    		    	    						    		    	   
				    		    	    while ((length = instream.read(buffer)) > 0){
				    		    	    	outstream.write(buffer, 0, length);
				    		    	    }

				    		    	    instream.close();
				    		    	    outstream.close();
				    		    	    
				    		    	    System.out.println("Celesi publik u ruajt ne fajllin '"+args[1]+".pub.xml'");
			    		    	    }
		    		    	    	else if(numeroRreshtat(fajlliPerImportim)==10)
		    		    	    	{
		    		    	    		outfile =new File(args[1]+".xml");
		    		    	    		FileOutputStream outstream = new FileOutputStream(outfile);
		    		    	    		
				    		    	   
				    		    	    while ((length = instream.read(buffer)) > 0){
				    		    	    	outstream.write(buffer, 0, length);
				    		    	    }

				    		    	    instream.close();
				    		    	    outstream.close();
				    		    	    
				    		    	    System.out.println("Celesi privat u ruajt ne fajllin '"+args[1]+".xml'");
				    		    	    
				    		    	    String[] celesiKomplet = readLineByLineJava(args[2]).split("\r\n|\r|\n");
		    		    	  
				    		    	    FileWriter myWriter2 = new FileWriter(args[1]+".pub.xml");
								        for(int i=0;i<3;i++)
								        {	
								        myWriter2.append(celesiKomplet[i]+"\n");
								        }
								        myWriter2.append("</RSAKeyValue>");
								        myWriter2.close();
				    		    	    

				    		    	    
				    		    	    System.out.println("Celesi publik u ruajt ne fajllin '"+args[1]+".pub.xml'");
				    		    	    
				    		    	    
				    		    	    
				    		           
		    		    	    		
		    		    	    	}
		    		    	    	else
		    		    	    	{
		    		    	    		System.out.println("Fajlli i specifikuar per importim nuk permban te dhenat ne teresi!");
		    		    	    	}
		    		    	    }
		    		 
		    		    	}
		    		    	    
		    		    	    
		    		    	catch(IOException ioe){
		    		    		ioe.printStackTrace();
		    		    	 }
						}
						else
						{
							System.out.println("Gabim: Fajlli i dhene nuk eshte celes valid!");
						}
					}
					else
					{
						System.out.println("Shtegu i dhënë është i gabuar!");
					}
				}
				
			}
			else
			{
				System.out.println("Celesi '"+args[1]+"' ekziston paraprakisht!");
			}
    	}
		else
		{
			
			System.out.println("Numer jo i sakte i argumenteve!");
			System.out.println("Komanda duhet te shkruhet kesisoj:  ds import-key <name> <path>");
			
		}
    	
    }
    
    private static String readLineByLineJava(String filePath) 
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
 
    public static int numeroRreshtat(String fajlli)
    {
    	int linenumber = 0;
    	try{

    		File file =new File(fajlli);


    		    FileReader fr = new FileReader(file);
    		    LineNumberReader lnr = new LineNumberReader(fr);

    		    

    	            while (lnr.readLine() != null){
    	        	linenumber++;
    	            }


    	            lnr.close();    		

    	}catch(IOException e){
    		e.printStackTrace();
    	}
		return linenumber;
    }
    
    public static boolean existsUrl(String url1)
    {
    	URL url;
    	HttpURLConnection huc;
    	int responseCode;
    	boolean ekziston=true;
		try {
			url = new URL(url1);
			huc = (HttpURLConnection) url.openConnection();
	    	responseCode = huc.getResponseCode();
	    	
	    	if (responseCode != 404) {
	        	ekziston=true;
	        	} else {
	        	ekziston=false;
	        	}
	    	
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    	return ekziston;
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
