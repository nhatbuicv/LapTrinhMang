
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class bai04_client extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnhap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bai04_client frame = new bai04_client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public bai04_client() throws UnknownHostException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txtnhap = new JTextField();
		GridBagConstraints gbc_txtnhap = new GridBagConstraints();
		gbc_txtnhap.insets = new Insets(0, 0, 5, 0);
		gbc_txtnhap.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtnhap.gridx = 2;
		gbc_txtnhap.gridy = 0;
		contentPane.add(txtnhap, gbc_txtnhap);
		txtnhap.setColumns(10);
		Socket socket = new Socket("127.0.0.1", 3000);
	    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	    DataInputStream in = new DataInputStream(socket.getInputStream());
		JButton btngui = new JButton("GỬI");
		
		
		GridBagConstraints gbc_btngui = new GridBagConstraints();
		gbc_btngui.insets = new Insets(0, 0, 5, 0);
		gbc_btngui.gridx = 2;
		gbc_btngui.gridy = 1;
		contentPane.add(btngui, gbc_btngui);
		
		JTextArea txthienthi = new JTextArea();
		GridBagConstraints gbc_txthienthi = new GridBagConstraints();
		gbc_txthienthi.fill = GridBagConstraints.BOTH;
		gbc_txthienthi.gridx = 2;
		gbc_txthienthi.gridy = 2;
		contentPane.add(txthienthi, gbc_txthienthi);
		int random = in.readInt();
		String hienthi = String.valueOf(random);
		
		txthienthi.setText(hienthi);
		btngui.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String inputStr = txtnhap.getText().trim();
				if (inputStr.isEmpty()) {
		            txthienthi.setText(txthienthi.getText() + "\nnhập một số!");
		            return;
		        }
		        if (!inputStr.matches("^[0-9]+$")) {
		            txthienthi.setText(txthienthi.getText() + "\nKhông nhập chữ hoặc ký tự đặc biệt!");
		            txtnhap.setText("");
		            return;
		        }
	            
				int number = Integer.parseInt(txtnhap.getText().trim());
	            if (number < 1 || number > 9999) {
	                txthienthi.setText(txthienthi.getText() + "\nSố phải nằm trong khoảng 1-9999!");
	                txtnhap.setText("");
	                return; 
	            }
	            
				try {
					out.writeInt(number);
					out.flush();
					String kq = in.readUTF();
					String kq_hienthi = txthienthi.getText() + "\n" +  kq;
					txthienthi.setText(kq_hienthi);
					int nextRandom = in.readInt();
                    txthienthi.setText(txthienthi.getText() +"\n" + nextRandom);
                    txtnhap.setText("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					
			
			}
		});
	}

}
