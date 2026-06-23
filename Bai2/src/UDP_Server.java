import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDP_Server {

    public static void main(String[] args) throws IOException {

        DatagramSocket socket = new DatagramSocket(3000);

        System.out.println("UDP SERVER STARTED");

        while (true) {
            byte[] bufIn = new byte[1024];

            DatagramPacket dataIn = new DatagramPacket(bufIn, bufIn.length);
            socket.receive(dataIn);
            String strIn = new String(dataIn.getData(), 0, dataIn.getLength());
            System.out.println("Client: " + strIn);
            String strOut;

            switch (strIn.toUpperCase()) {
                case "RED":
                    strOut = "Server : Red";
                    break;

                case "BLUE":
                    strOut = "Server : Blue";
                    break;

                case "WHITE":
                    strOut = "Server : White";
                    break;

                case "ESC":
                    strOut = "Kết thúc !";
                    break;

                default:
                    strOut = "Màu không hợp lệ";
            }

            byte[] bufOut = strOut.getBytes();
            DatagramPacket dataOut = new DatagramPacket(bufOut, bufOut.length, dataIn.getAddress(), dataIn.getPort());
            socket.send(dataOut);

            if (strIn.equalsIgnoreCase("ESC")) {
                break;
            }
        }
        socket.close();
    }
}