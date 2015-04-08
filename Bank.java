package Spargrisen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Bank implements Runnable {

	private Thread thread = new Thread(this);
	private ServerSocket serverSocket;
	private ArrayList<String> transactionList = new ArrayList<String>();

	public Bank(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		thread.start();
	}

	public void run() {
		System.out.println("The Bank is now open");
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				ClientHandler ch = new ClientHandler(socket);
				ch.start();
			}
		} catch (IOException e) {
		}
	}

	private class ClientHandler extends Thread {
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		private int count = -1;

		public ClientHandler(Socket socket) {
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				while (true) {
					String purchase = ois.readUTF();
					addPurchase(purchase);
					sendPurchaseInfo();

				}
			} catch (IOException e) {
			}
		}

		public void addPurchase(String purchase) {
			transactionList.add(purchase);
			count++;
		}

		public void sendPurchaseInfo() throws IOException {
			oos.writeObject(transactionList.get(count));
			oos.flush();
		}

	}
	
	public static void main(String [] args) throws IOException{
		new Bank(4545);
	}

}
