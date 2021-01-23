package u3;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;


	public class Server
	{
	  public static void main (String argv [])  throws IOException
	  {
	    int port = 9000;
	    ServerSocket ss = new ServerSocket (port);

	    while (true) {
	      // bearbeite die Verbindung asynchron
	      Socket connsock = ss.accept();
	      // neues Connection-Objekt mit Socket versorgen 
	      // und dessen run-Methode asynchron starten
	      new Connection (connsock).start ();
	  
	      ss.close();
	    }
	    }
	  
	  }
	


	class Connection extends Thread {
	  private Socket sock;

	  Connection (Socket s)
	  {
	    sock = s;
	  }
	
	  public
	  void run ()
	  {
	    try {
	      // hole die Input- und Output-Streams der Verbindung
	    
	    	InputStream insock = sock.getInputStream ();
	      OutputStream outsock = sock.getOutputStream ();

	      
	      // Text-Reader/Writer drumwickeln
	      PrintWriter s_out = new PrintWriter (outsock);
	      BufferedReader s_in = new BufferedReader(new InputStreamReader(insock));


	      try {
	    	 
	    	  
	          String request = s_in.readLine();
	          String answer = "" ;
	          if (request.equals("Hi")) {
	        	   answer = "Hello, welcome to my first Server with  Java" ;
	
				
			}
	          
	          
	          if (request.equals("Datum")) {
	        	   answer = LocalDate.now().toString(); 
	
				
			}
	          
	          if (request.equals("Time")) {
	        	  answer = LocalTime.now().toString(); 
	
				
			}

	          
	          
	          s_out.println(answer);
	          s_out.flush();
	          
	       } catch (IOException e) {
	    	  System.out.println(e);
	      }
	      
	      sock.close ();
	    } catch (IOException e) {
	    	 System.out.println(e);
	      }
	      
	      

	    
	  }}
	    
	  
	
	



