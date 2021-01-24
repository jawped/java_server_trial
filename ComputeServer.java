import java.net.*;
import java.io.*;

public class ComputeServer {

    public static void main(String argv[]) throws IOException {
        int backend_port = 9001;
        ServerSocket s = new ServerSocket(backend_port);

        while(true) {

            try {
                System.out.println("Starting compute server, waiting for request from Server");
                Socket connection_socket = s.accept();

                
                System.out.println("connection to computing server established");

                InputStream input = connection_socket.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String line = reader.readLine();
                OutputStream output = connection_socket.getOutputStream();
                if (line.contains("date")) {
                    
                    PrintWriter writer = new PrintWriter(output, true);
				    writer.println("this information comes from the computing server");
                }

                output.flush();
                

            } catch (Exception e) {
                System.out.println(e);
            }
           
        }
       
    }

        
}