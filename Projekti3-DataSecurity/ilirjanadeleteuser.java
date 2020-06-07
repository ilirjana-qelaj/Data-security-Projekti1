private static void deleteUser(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, SQLException
	{
	
		java.sql.Connection connection=null;
		Statement stmt=null;
		connection=getConn();
		java.sql.Statement st = connection.createStatement();
		String emri=args[1];
		if(args.length!=2) {
			System.out.println("Numer jo i sakte i argumenteve!");
			System.out.println("Komanda duhet te shkruhet kesisoj: ds delete-user <name>");
		}
		else {
			String queryexsists = ("select * from shfrytezuesit where emri='"+emri+"'");
			ResultSet existsresult = st.executeQuery(queryexsists);
		
			if(existsresult.next())
				{
				String query="DELETE FROM shfrytezuesit WHERE emri='"+emri+"'";
				int rs = st.executeUpdate(query);
				System.out.println(emri+"Eshte larguar nga shfrytezuesit");
				deleteUser1(args);
				}
			else {
				System.out.println(emri+" Nuk ekziston");
				}
			}
		
		}