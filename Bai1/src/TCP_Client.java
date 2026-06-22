import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Client {
    public static void main(String[] args) throws IOException {
        System.out.println("CONNECTING TO SERVER ...");

        Socket socket = new Socket("localhost", 9555);

        System.out.println("CONNECTED TO SERVER");

        DataInputStream in = new DataInputStream(socket.getInputStream());

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("Nhap lenh (TIME/DATE/NOW/EXIT): ");
            String command = sc.nextLine();

            out.writeUTF(command);
            out.flush();

            String response = in.readUTF();

            System.out.println("Server: " + response);

            if (command.equalsIgnoreCase("EXIT")) {
                socket.close();
                System.out.println("CLIENT STOPPED");
                break;
            }
        }
    }
}
