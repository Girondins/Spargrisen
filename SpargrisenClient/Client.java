package SpargrisenClient;



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

import SpargrisenObjekt.AvailableUser;
import SpargrisenObjekt.Category;
import SpargrisenObjekt.RegisterUser;
import SpargrisenObjekt.Tag;
import SpargrisenObjekt.User;
import SpargrisenServer.InputGUI;

public class Client{

	
	private ClientController clientController;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	

	public Client(String ip, int port) throws UnknownHostException, IOException{
			socket = new Socket(ip,port);
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			new ClientStarter().start();
		}
	

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;

	}
	

	public void connect(AvailableUser user) {

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
	
	public void updateUser(User user){
		//Går att använda för att uppdatera server, KASTA INTE
		try {

			oos.writeObject(user);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addCategory(Category category){
		
		try {
			oos.writeObject(category);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void changeCategoryLimit(Category category){
		
		try {
			oos.writeObject(category);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addTag(Tag tag){

		try {
			oos.writeObject(tag);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registerUser(RegisterUser registerUser){
		
		
		try {
			oos.writeObject(registerUser);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	private class ClientStarter extends Thread {
		public void run() {
			Object object;
			while (true) {
				try {
					object = ois.readObject();
					if(object instanceof User) {
						User user = (User) object;
						clientController.setUserInfo(user);
					}else if(object instanceof String){
						String message = (String)object;
						clientController.showMessage(message);
					}

				} catch (IOException | ClassNotFoundException ex) {
					System.err.print(ex);
				}

			}
		}
	}

}