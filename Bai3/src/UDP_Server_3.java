import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDP_Server_3 extends JFrame {

    private JButton agent;

    public UDP_Server_3() {
        setTitle("UDP Server");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agent = new JButton("Agent");
        agent.setBounds(200, 200, 80, 30);
        add(agent);
        setVisible(true);
    }

    public void startServer() throws IOException {
        byte[] bufIn = new byte[1024];
        DatagramSocket socket = new DatagramSocket(3000);
        DatagramPacket dataIn = new DatagramPacket(bufIn, bufIn.length);

        while (true) {
            socket.receive(dataIn);
            String strIn = new String(dataIn.getData(), 0, dataIn.getLength());
            moveButton(strIn);
        }
    }

    private void moveButton(String str) {
        int x = agent.getX();
        int y = agent.getY();
        int step = 10;

        switch (str) {
            case "UP":    y -= step; break;
            case "DOWN":  y += step; break;
            case "LEFT":  x -= step; break;
            case "RIGHT": x += step; break;
        }

        agent.setLocation(x, y);
    }

    public static void main(String[] args) throws IOException {
        UDP_Server_3 server = new UDP_Server_3();
        server.startServer();
    }
}