private static void createUser(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, SQLException{
		Scanner in=new Scanner(System.in);
		java.sql.Connection connection=null;
		Statement stmt=null;
		Statement exists=null;
		connection=getConn();
		java.sql.Statement st = connection.createStatement();
		String emri=args[1];
		//System.out.println("U konektuam");
		if(args.length != 2)
		{
			System.out.println("Numer jo i sakte i argumenteve!");
			System.out.println("Komanda duhet te shkruhet kesisoj: ds create-user <name>");
		}
		else {
			String queryexsists = ("select * from shfrytezuesit where emri='"+emri+"'");
			ResultSet existsresult = st.executeQuery(queryexsists);
			if(existsresult.next())
				{
					System.out.println(emri+" ekziston paraprakisht");
				}
			else {
				System.out.println("Jepni fjalekalimin:");
				String pw=in.nextLine();
				System.out.println("Perseritni fjalekalimin:");
				String pw1=in.nextLine();
					if(checkifPassword(pw,pw1)) {
						String passwordToHash = pw;
						  
						String securePassword = hashFjalkalimin(passwordToHash);
						String query = "insert into shfrytezuesit (emri, password) values ('"+emri+"', '"+securePassword+"')";
						int rs = st.executeUpdate(query);
						System.out.println("Eshte krijuar shfrytezuesi '"+emri+"'");
						createUser1(args);
		    
					}	
				}
			}
		}