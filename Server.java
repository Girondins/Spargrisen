package Spargrisen;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

public class Server {

	private ServerSocket serverSocket;
	private int port;
	private ArrayList<Client> userList;

	public Server(int port) {
		this.port = port;
		
	}

	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server Online");
			while (true) {
				Socket socket = serverSocket.accept();
				new ClientHandler(socket).start();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private class ClientHandler extends Thread {
		private ObjectInputStream ois;
		private ObjectOutputStream oos;

		public ClientHandler(Socket socket) {
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			Object object;
			
				try {
					while (true) {
							object = ois.readObject();
							doseUserExist(object);
							
					} 
					} catch (Exception e) {
					e.printStackTrace();
					}		
		}
		
		
		public void doseUserExist(Object object){
			// chek if user exist
		}
		public void registerUser(){
			// register user
		}
		public void getUserHistory(){
			//return user history
			
		}
		

	}

	public void sendMessage(String message) {

	}

	public String getTime() {
		String time;
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.SECOND) < 10) {
			time = "[" + cal.get(Calendar.HOUR_OF_DAY) + ":"
					+ cal.get(Calendar.MINUTE) + ":0"
					+ cal.get(Calendar.SECOND) + "]";
		} else {
			time = "[" + cal.get(Calendar.HOUR_OF_DAY) + ":"
					+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND)
					+ "]";
		}
		return time;
	}

	public static void main(String[] args) {
		Server s = new Server(1515);
		s.run();
	}

}
