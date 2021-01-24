//package u3;

import java.net.*;

import java.io.*;

public class Client {

	public static void main(String argv[]) throws IOException {
		Client client = new Client();
		try {
			client.request("Time");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void request(String req) throws Exception {
		// SOCKET
		Socket s = new Socket();
		int port = 9000;
		String host = "localhost";

		InetSocketAddress addr = new InetSocketAddress(host, port);
		// CONNECT
		s.connect(addr);

		// I/O-Streams des Sockets

		// bei Bedarf Writer/Reader für Text-Kommunikation drauflegen:

		// SEND, RECEIVE
		// request schreiben

		OutputStream output = s.getOutputStream();

		PrintWriter writer = new PrintWriter(output, true);
		writer.println(req);

		output.flush();
		// response lesen
		InputStream input = s.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		String input_str = reader.readLine();
		// verarbeiten / anzeigen / ...
		System.out.println(input_str);

		// DISCONNECT / CLOSE
		s.close();

	}
}
