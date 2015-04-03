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
	
	public Client(String ip, int port) throws IOException{
		socket = new Socket(ip,port);
		oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
	}
	
	public void setClientController(ClientController clientController){
		this.clientController=clientController;
		
	}
	
	public void sendMessage(String message){
		
	}
	
	public void exit(){
		
	}
}
