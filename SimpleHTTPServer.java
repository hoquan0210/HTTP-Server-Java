
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author Alli Ho
 */
public class SimpleHTTPServer {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {
            System.out.println("Listening for connection on port 8080 ....");
            while (true) {
                try (Socket socket = server.accept()) {
                    Date today = new Date();
                    String httpResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nContent-Length: " + today.toString().length() + "\r\n\r\n" + today;
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(httpResponse.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
