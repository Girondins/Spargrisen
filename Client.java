package Spargrisen;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client{

	
	private ClientController clientController;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private User user;

	

	public Client(String ip, int port, String name) throws UnknownHostException, IOException{
			
			socket = new Socket(ip,port);
			System.out.println("e");
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			System.out.println("De");
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			System.out.println("FUck");
			user = new User(name); 
			new ClientStarter().start();
		}
	

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;

	}
	

	public void connect() {

		try {
			oos.writeObject(user);
			oos.flush();
			System.out.println("Fred");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	private class ClientStarter extends Thread {
		public void run() {
			Object object;
			while (true) {
				try {
					object = ois.readObject();
					clientController.newObject(object);

				} catch (IOException | ClassNotFoundException ex) {
					System.err.print(ex);
				}

			}
		}
	}

}