package SpargrisenServer;

import java.io.IOException;

public class TestDriver {
	
	
	
	
	public static void main(String[] args){
	Driver d = new Driver();
	try {
		InputGUI gui = new InputGUI(d);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}



}
