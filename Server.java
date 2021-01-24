//package u3;

import java.net.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;

public class Server {
	public static void main(String argv[]) throws IOException {

		int port_in = 9000;
		int port_out = 9001;
		String host = "localhost";

		ServerSocket ss = new ServerSocket(port_in);
		Socket s = new Socket(port_out);
		InetSocketAddress addr = new InetSocketAddress(host, port_out);
		// CONNECT
		s.connect(addr);

		while (true) {
			// .accept() blockt den thread und wartet auf Client Anfrage -> socket-object,
			// welches von accept zurückgegeben wird demonstriert Verbindung

			try {
				System.out.println("Server is listening to port 9000");
				// client.request("date");
				Socket connection_socket = ss.accept();

				System.out.println("new client connected");
				// neues Connection-Objekt mit Socket versorgen

				// und dessen run-Methode asynchron starten
				// Connection conn = new Connection(connsock).start();

				// returns input stream as bytes.. conversion needed for further use
				InputStream input = connection_socket.getInputStream();
				OutputStream output = connection_socket.getOutputStream();

				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				// reads a line of text
				String answer = "no match";
				String line = reader.readLine();
				System.out.println("Client reqeuest: " + line);
				if (line.contains("Date")) {
					answer = LocalDate.now().toString();
				}

				if (line.contains("Time")) {
					answer = LocalTime.now().toString();
					String date_info = contact_backend_server(connection_socket);
					System.out.println("connection between server and computing server established");
				}

				System.out.println(answer);
				// String html = "<html> hello <html>";
				// byte[] answer_as_bytes = answer.getBytes();
				// output.write(answer_as_bytes);
				PrintWriter writer = new PrintWriter(output, true);
				writer.println("this is a message sent to the client");

				output.flush();
				connection_socket.close();
				ss.close();
			} catch (Exception e) {
				System.out.println(e);
				break;
			}

		}

	}


	public static String contact_backend_server(Socket ss_out) {
		OutputStream output = ss_out.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		writer.println("this is a message sent to the backendserver");

		InputStream input = ss_out.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		// reads a line of text
		String answer = "no match";
		String line = reader.readLine();

		System.out.println(line);
		return line;

	}
}

// class Connection extends Thread {
// private Socket sock;

// Connection(Socket s) {
// sock = s;
// }

// public void run() {
// try {
// // hole die Input- und Output-Streams der Verbindung

// InputStream insock = sock.getInputStream();
// OutputStream outsock = sock.getOutputStream();

// // Text-Reader/Writer drumwickeln
// PrintWriter s_out = new PrintWriter(outsock);
// BufferedReader s_in = new BufferedReader(new InputStreamReader(insock));

// try {

// String request = s_in.readLine();
// String answer = "";
// if (request.equals("Hi")) {
// answer = "Hello, welcome to my first Server with Java";

// }

// if (request.equals("Datum")) {
// answer = LocalDate.now().toString();

// }

// if (request.equals("Time")) {
// answer = LocalTime.now().toString();

// }

// s_out.println(answer);
// s_out.flush();

// } catch (IOException e) {
// System.out.println(e);
// }

// sock.close();
// } catch (IOException e) {
// System.out.println(e);
// }

// }
// }
