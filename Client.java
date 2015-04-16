package Spargrisen;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class Client extends Observable{

	
	private ClientController clientController;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private User user;

	

	public Client(String ip, int port, String name, RegisterGUI rg) throws UnknownHostException, IOException{
			rg.addObserver(new ObserverImp());
			socket = new Socket(ip,port);
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
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
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void makePurchase(String purchase){
		try{
			oos.writeObject(purchase);
			oos.flush();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private class ObserverImp implements Observer{

		public void update(Observable o, Object arg) {
			String purchase = (String)arg;
			makePurchase(purchase);
			
		}
		
	}
	

	private class ClientStarter extends Thread {
		public void run() {
			Object object;
			while (true) {
				try {
					object = ois.readObject();
					System.out.println(object.toString());
					clientController.newObject(object);

				} catch (IOException | ClassNotFoundException ex) {
					System.err.print(ex);
				}

			}
		}
	}

}