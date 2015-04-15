package Spargrisen;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Klassen är en "Chat Server" som kan lagra och skicka ut meddelande till anslutna klienter
 * @author Stefan Tran
 *
 */

public class Server implements Runnable {
	/**
	 * Nödvändinga instansvariabler deklareras
	 */
	private ServerSocket serverSocket;
	private Thread server = new Thread(this);
	private ArrayList<ClientHandler> alch;
	private User user;
	private ClientHandler clientHandler;


	public Server(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		alch = new ArrayList<ClientHandler>();
		server.start();
	}

	public void run() {
		System.out.println("Server Online");
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				ClientHandler ch = new ClientHandler(socket);
				this.clientHandler = ch;
				alch.add(ch);
				ch.start();
			} catch (IOException e) {
				System.err.println(e);
			}
		}

	}
/**
 * En inre klass som kan hantera de olika klienter som kopplar in sig till servern
 * @author Girondins
 *
 */
	private class ClientHandler extends Thread {
		private Socket socket;
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
/**
 * Konstruktor som tar emot rätt socket från klient
 * @param socket från ansluten klient
 * @throws IOException
 */
		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println("HEJ");
		}
		
/**
 * Metod som startar hantering av inkommande och utgående object
 * 
 */
		public void run() {
			Object object = null;

			try {
				while (true) {

					try {
						object = ois.readObject();
					} catch (ClassNotFoundException e) {
			
						e.printStackTrace();
					}
					
					if (object instanceof User) {
						user = (User) object;
						user.setID(clientHandler.getId());
						
						
						
						
					}else {
						System.out.println("GG");
					}
				}
			} catch (IOException e) {
				System.err.println("fel");
			}
				try {
					oos.close();
					ois.close();
					socket.close();
				} catch (IOException e1) {
					System.out.println("error");
				}
						
		

		}
	}
		
		
		
	
	public static void main(String[] args) throws IOException {
		new Server(3001);

	}
}
