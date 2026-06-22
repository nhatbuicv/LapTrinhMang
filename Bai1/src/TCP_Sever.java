import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TCP_Sever {
    public static void main(String[] args) throws IOException {
        System.out.println("WAITING CONNECTION ...");

        ServerSocket serverSocket = new ServerSocket(9555);

        Socket socket = serverSocket.accept();

        System.out.println("CLIENT CONNECTED");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        while(true){
            String dataIn = in.readUTF();
            System.out.println("Client : " + dataIn);

            switch (dataIn.toUpperCase()) {

                case "TIME":
                    out.writeUTF(LocalTime.now().withNano(0).toString());
                    break;

                case "DATE":
                    out.writeUTF(LocalDate.now().toString());
                    break;

                case "NOW":
                    out.writeUTF(LocalDateTime.now().withNano(0).toString());
                    break;

                case "EXIT":
                    out.writeUTF("GOODBYE");
                    socket.close();
                    serverSocket.close();
                    System.out.println("SERVER STOPPED");
                    return;

                default:
                    out.writeUTF("INVALID COMMAND");
            }

            out.flush();
        }
    }
}