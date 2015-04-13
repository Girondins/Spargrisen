package Spargrisen;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	private ClientController clientController;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private User user;
	private String userName;

	public Client(String ip, int port) throws IOException {
		socket = new Socket(ip, port);
		oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		new ClientStarter().start();
	}

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;

	}

	private class ClientStarter extends Thread {
		private Object object;

		public void run() {
			while(true) {
				try{
					object = ois.readObject();
					clientController.newObject(object);
					
				}catch(IOException | ClassNotFoundException ex) {
					System.err.print(ex);
				}
	
			}
		}
	}

	public void setUserName(String userName) {
		this.userName = userName;
		
	}
}