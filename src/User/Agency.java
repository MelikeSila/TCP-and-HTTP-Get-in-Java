package User;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.net.*;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Agency extends JPanel implements ActionListener{
	static JFrame frame = new JFrame("EM Travel Agency");
	static JFrame suggestionframe = new JFrame("Sugestion Frame");
	static PrintStream output = null;
	static DataInputStream input = null;
	static ServerSocket MyService = null ;
	static Socket serviceSocket = null;
	static Button search = new Button("SEARCH!");
	static Button accepted = new Button("ACCEPT!");
	static Button rejected = new Button("REJECT!");
	public static void main(String s[]) {
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setContentPane(new UserGui());
		frame.add(search);

		frame.pack();
		frame.setVisible(true);
		
		//OpenNewFrame();
		//take all token value from user. (wanted day, ...)
		String wday = UserGui.dayChoosed;
		String wmounth = UserGui.monthChoosed;
		String wyear = UserGui.yearChoosed;
		String wpassenger = UserGui.passengerChoosed;
		String whotel = UserGui.hotelChoosed;
		String wplane = UserGui.planeChoosed;
		
		
		OpenTravelSocket();
		UserGui.OpenCustomerSocket();
		System.out.println("cþdvlcvf");
		//if butona bastýysa();
			//UserGui.CloseCustomerSocket();
			//hotelServer.OpenHotelSocket();
			//airlineServer.OpenAirSocket();
				//int isavailableairline
				//int isavailableHotel = 
				//int available = Available(isavailablehotel, isavailableairplane);
				//if Available() == true; -->Database güncellicez. --> socketleri kapat hotels and airlines
				//else --> ayýn otelde yakin tarihe bak.
		CloseTravelSocket();
		
	}
	
	public Agency(){
		//search button
				search.setActionCommand("disable");
				search.addActionListener( new ActionListener()
				{
				    public void actionPerformed(ActionEvent e)
				    {
				    	frame.dispose();
				    	//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));   
				    	OpenNewFrame();
				    }
				});
				
				accepted.setActionCommand("accept");
				accepted.addActionListener(this);
				
				rejected.setActionCommand("rejectxasx");
				rejected.addActionListener(this);
	}
	
	public static void OpenTravelSocket(){
		
	    try {
	       MyService = new ServerSocket(6787);
	        }
	        catch (IOException e) {
	           System.out.println(e);
	        }
	    
	    //I create a socket object from the server Socket
	    UserGui.OpenCustomerSocket();	
	    try {
	    	System.out.println("ggg");
	       serviceSocket = MyService.accept();
	       System.out.println("kkk");
	        }
	    catch (IOException e) {
	       System.out.println(e);
	    }
	    
	    //Reach entered data from user
	    try {
	    	System.out.println("girdi OpenCustomerSocket");
	       input = new DataInputStream(serviceSocket.getInputStream());
	    }
	    catch (IOException e) {
	       System.out.println(e);
	    }
	    
	    //to send data to client
	    
	    try {
	       output = new PrintStream(serviceSocket.getOutputStream());
	    }
	    catch (IOException e) {
	       System.out.println(e);
	    }    
	}
	public static void CloseTravelSocket(){
		 //Close input output and socket
	    try {
	        output.close();
	        input.close();
	        serviceSocket.close();
	        MyService.close();
	     } 
	     catch (IOException e) {
	        System.out.println(e);
	     }
	}
	
	public static void OpenNewFrame(){		
		suggestionframe.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		suggestionframe.setContentPane(new UserGui());
		suggestionframe.add(search);

		suggestionframe.pack();
		suggestionframe.setVisible(true);	
		
		suggestionframe.add(accepted);
		suggestionframe.add(rejected);
	}
	public void actionPerformed(ActionEvent e) {
	    if ("disable".equals(e.getActionCommand())) {
	        //b2.setEnabled(false);
	        search.setEnabled(false);
	        accepted.setEnabled(false);
	        //b3.setEnabled(true);
	    } else {
	        //b2.setEnabled(true);
	        search.setEnabled(true);
	        accepted.setEnabled(true);
	        rejected.setEnabled(true);
	        //b3.setEnabled(false);
	    }
	} 
	
}
