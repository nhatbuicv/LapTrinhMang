import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Client_3 extends JFrame {

    private JPanel mainPanel;
    private JButton buttonLeft;
    private JButton buttonUp;
    private JButton buttonDown;
    private JButton buttonRight;

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private final int SERVER_PORT = 3000;

    public UDP_Client_3() throws Exception {

        socket = new DatagramSocket();
        serverAddress = InetAddress.getByName("localhost");

        setContentPane(mainPanel);

        buttonUp.addActionListener(e -> send("UP"));
        buttonDown.addActionListener(e -> send("DOWN"));
        buttonLeft.addActionListener(e -> send("LEFT"));
        buttonRight.addActionListener(e -> send("RIGHT"));

        setTitle("UDP Client");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void send(String command) {
        try {
            byte[] buf = command.getBytes();
            DatagramPacket packet =
                    new DatagramPacket(buf, buf.length, serverAddress, SERVER_PORT);
            socket.send(packet);
            System.out.println("Đã gửi: " + command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new UDP_Client_3();
    }
}