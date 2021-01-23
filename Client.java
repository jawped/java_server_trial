package u3;
import java.net.*;
import java.io.*;


public class Client {
public  void  request (String request ) throws Exception {
	  // SOCKET
	  Socket sock = new Socket ();
	  int port = 9000;
	   String host = "localhost";
	   
	  InetSocketAddress addr = new InetSocketAddress (host, port);
	  // CONNECT
	  sock.connect (addr);

	  // I/O-Streams des Sockets
	  OutputStream outsock = sock.getOutputStream ();
	  InputStream insock = sock.getInputStream ();
	  // bei Bedarf Writer/Reader für Text-Kommunikation drauflegen:
	  PrintWriter s_out = new PrintWriter (outsock);
	  BufferedReader s_in = new BufferedReader(new InputStreamReader(insock));

	  // SEND, RECEIVE
	  // request schreiben
	  s_out.println("date");
	  s_out.flush();
	  // response lesen
	  String answer = s_in.readLine();
	  // verarbeiten / anzeigen / ...
System.out.println(answer);
	  // DISCONNECT / CLOSE
	  sock.close ();

	
}


}
