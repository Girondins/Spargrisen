package Spargrisen;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

public class Server {

	private ServerSocket serverSocket;
	private ArrayList<User> userList;
	private ArrayList<ClientHandler> alch;
	private ClientHandler clientHandler;
	private ObjectInputStream bankOis;
	private Socket bankSocket;
	private LinkedList<String> transactionList;

	public Server(int port, String IP, int bankPort) throws IOException {
		bankSocket = new Socket(IP, bankPort);
		bankOis = new ObjectInputStream(new BufferedInputStream(
				bankSocket.getInputStream()));
		this.serverSocket = new ServerSocket(port);
		alch = new ArrayList<ClientHandler>();

	}

	public void run() {
		System.out.println("Server Online");
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				ClientHandler ch = new ClientHandler(socket);
				this.clientHandler = ch;
				alch.add(ch);
				ch.start();
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
					String transaction = bankOis.readUTF();
					transactionList.add(transaction);
					handleTransaction();
					
					if (object instanceof User) {
						User user = (User) object;
						if (doesUserExist(user) == false) {
							registerUser(user);
						} else
							getUserHistory(user);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void handleTransaction(){
			User user = null;
			String read = transactionList.pop();
			String[] parts = read.split(",");
			String purchaseInfo = parts[1] + parts[2] + parts[3] + parts[4];
			
			for(int i=0; i<userList.size(); i++){
				if(parts[0].equals(userList.get(i).getName())){
					user = userList.get(i);
				}
			}
			
			for(int i=0; i<user.getCategoryList().size(); i++){
				if(user.getCategoryList().getCategoryIndex(i).checkTags(parts[3])){
					user.getCategoryList().getCategoryIndex(i).addPurchase(purchaseInfo);
				}else{
					user.getCategoryList().getCategoryIndex(user.getCategoryList().size()).addPurchase(purchaseInfo);
				}
				
			}
			
		}

		public boolean doesUserExist(User user) throws IOException {
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getName().equals(user.getName())) {
					String response = "Username is Already Taken";
					oos.writeObject(response);
					oos.flush();
					return true;
				}
			}
			return false;

		}

		public void registerUser(User user) throws IOException {
			user.setID(clientHandler.getId());
			userList.add(user);
			String response = "Welcome: " + user.getName() + "\n"
					+ "You have now been registred!";
			oos.writeObject(response);
			oos.flush();
			oos.writeObject(user);
			oos.flush();
		}

		public void getUserHistory(User user) throws IOException {
			User historyUser = null;
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getName().equals(user.getName())) {
					historyUser = userList.get(i);
				}
			}
			oos.writeObject(historyUser);
			oos.flush();
		}

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

	public static void main(String[] args) throws IOException {
		Server s = new Server(1515, "127.0.0.1", 4545);
		s.run();
	}

}
