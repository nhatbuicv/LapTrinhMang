import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Client {
    private JButton buttonRed;
    private JButton buttonBlue;
    private JButton buttonWhite;
    private JTextArea textAreaResult;
    private JPanel mainPanel;
    private JButton buttonEsc;
    private DatagramSocket socket;


    public UDP_Client() {
        try {
            socket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }

        buttonRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendColor("RED");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendColor("BLUE");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendColor("WHITE");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonEsc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendColor("ESC");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    void sendColor(String color) throws IOException {
            byte[] bufOut = color.getBytes();
            DatagramPacket dataOut = new DatagramPacket(bufOut, bufOut.length, InetAddress.getByName("localhost"), 3000);
            socket.send(dataOut);

            byte[] bufIn = new byte[1024];
            DatagramPacket dataIn = new DatagramPacket(bufIn, bufIn.length);
            socket.receive(dataIn);
            String data = new String(dataIn.getData(), 0, dataIn.getLength());
            textAreaResult.setText(data);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UDP CLIENT");

        UDP_Client form = new UDP_Client();

        frame.setContentPane(form.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 300);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
