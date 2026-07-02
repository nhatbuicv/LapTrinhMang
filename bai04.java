package bai01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class bai04 {
	public static void main(String args[]) throws IOException {
		ServerSocket serversocket = new ServerSocket(3000);
		Socket socket = serversocket.accept();
		  DataInputStream in = new DataInputStream(socket.getInputStream());
          DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
          Random rand = new Random();
          while (true) {
              int number = rand.nextInt(9999)+1;
              System.out.println(number);
              out.writeInt(number);
              out.flush();
              int receive = in.readInt();
              if (number == receive) { 
            	  	out.writeUTF("Đúng"); 	
              }
              else {out.writeUTF("Sai");
              }
              out.flush();
              }
            
	}
          
}
