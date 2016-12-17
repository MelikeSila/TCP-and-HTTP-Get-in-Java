package User;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.text.*;

public class UserGui extends Agency{
	static JFrame frame;
	JLabel jlbResult;
	static String datePattern_Current;
	static String dayChoosed;
	static String monthChoosed;
	static String yearChoosed;
	static String passengerChoosed;
	static String hotelChoosed;
	static String planeChoosed;
	static DataInputStream input;
	static DataOutputStream output;//burda bir sýkýntý var. diger yerlerde putput yok zaten
	static Socket MyClient;
	static String[] hotel_data = new String[6];
	String[] airplane_data = new String[6];
	public UserGui() {
		System.out.println("userGui");
		//I took the current dates.
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
		String dateString = formatter.format(today);
		 String[] arr = dateString.split(" ");
		 int day_current;
		 int mounth_current;
		 int year_current;
		 int x = 0;
		 for ( String ss : arr) {
			 if(x == 0){
				 day_current = Integer.parseInt(ss);
			 }else if(x == 1){
				 mounth_current = Integer.parseInt(ss);
			 }else{
				 year_current = Integer.parseInt(ss);
			 }		       
		  }
		
		 //I define the dates.
		String [] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String [] mounths = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		String [] years = {"2016","2017","2018","2019","2020","2021"};
		String [] passengers = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String [] hotels = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String [] planes = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		
		
		//I will use when I connect the code with socket
		dayChoosed = days [0];
		monthChoosed = mounths[0];
		yearChoosed = years[0];
		passengerChoosed = passengers[0];
		hotelChoosed = hotels[0];
		planeChoosed = planes[0];
		
		JLabel datelable = new JLabel("Date: ");
		JComboBox dayList = new JComboBox(days);
		JComboBox mounthList = new JComboBox(mounths);
		JComboBox yearList = new JComboBox(years);
		JLabel passengerlable = new JLabel("Passenger numbers: ");
		JComboBox passengerList = new JComboBox(passengers);
		JLabel   hotellable = new JLabel("Choose Hotel: ");
		JComboBox hotelList = new JComboBox(hotels);
		JLabel   planelable = new JLabel("ChoosePlane");
		JComboBox planeList = new JComboBox(planes);
		
		
		dayList.setAlignmentX(Component.LEFT_ALIGNMENT);
		dayList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbDays = (JComboBox) e.getSource();
				String selected = (String) jcmbDays.getSelectedItem();
				dayChoosed = selected;
				//setdayChoosed(dayChoosed);
			}
		});

		mounthList.setAlignmentX(Component.LEFT_ALIGNMENT);
		mounthList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbDays = (JComboBox) e.getSource();
				String selected = (String) jcmbDays.getSelectedItem();
				monthChoosed = selected;
			}
		});
		
		yearList.setAlignmentX(Component.LEFT_ALIGNMENT);
		yearList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbDays = (JComboBox) e.getSource();
				String selected = (String) jcmbDays.getSelectedItem();
				yearChoosed = selected;
			}
		});
		
		passengerList.setAlignmentX(Component.LEFT_ALIGNMENT);
		passengerList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbDays = (JComboBox) e.getSource();
				String selected = (String) jcmbDays.getSelectedItem();
				passengerChoosed = selected;
			}
		});
		
		
		hotelList.setAlignmentX(Component.LEFT_ALIGNMENT);
		hotelList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbDays = (JComboBox) e.getSource();
				String selected = (String) jcmbDays.getSelectedItem();
				hotelChoosed = selected;
			}
		});
		
		planeList.setAlignmentX(Component.LEFT_ALIGNMENT);
		planeList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbDays = (JComboBox) e.getSource();
				String selected = (String) jcmbDays.getSelectedItem();
				planeChoosed = selected;
			}
		});
		
		//set the data as array
		hotel_data [0] = hotelChoosed;
		hotel_data [1] = yearChoosed;
		hotel_data [2] = monthChoosed;
		hotel_data [3] = dayChoosed;
		hotel_data [4] = passengerChoosed;
		hotel_data [5] = "-1";
		airplane_data [0] = planeChoosed;
		airplane_data [1] = yearChoosed;
		airplane_data [2] = monthChoosed;
		airplane_data [3] = dayChoosed;
		airplane_data [4] = passengerChoosed;
		airplane_data [5] = "-1";
		
		//patternList.setEditable(true); //yazilabilir
		// Create the UI for displaying result
		JLabel jlbResultHeading = new JLabel("Current Date/Time",JLabel.LEFT);
		jlbResult = new JLabel(" ");
		jlbResult.setForeground(Color.black);
		jlbResult.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.black), BorderFactory
						.createEmptyBorder(5, 5, 5, 5)));
		
		// Lay out everything
		JPanel jpnDate = new JPanel();
		jpnDate.setLayout(new BoxLayout(jpnDate, BoxLayout.Y_AXIS));
		jpnDate.add(datelable);
		jpnDate.add(dayList);
		jpnDate.add(mounthList);
		jpnDate.add(yearList);
		
		JPanel jpnPassenger = new JPanel();
		jpnPassenger.setLayout(new BoxLayout(jpnPassenger, BoxLayout.Y_AXIS));
		jpnPassenger.add(passengerlable);
		jpnPassenger.add(passengerList);
		
		JPanel jpnHotel = new JPanel();
		jpnHotel.setLayout(new BoxLayout(jpnHotel, BoxLayout.Y_AXIS));
		jpnHotel.add(hotellable);
		jpnHotel.add(hotelList);
		
		JPanel jpnPlane = new JPanel();
		jpnPlane.setLayout(new BoxLayout(jpnPlane, BoxLayout.Y_AXIS));
		jpnPlane.add(planelable);
		jpnPlane.add(planeList);
		
		JPanel jpnResults = new JPanel();
		jpnResults.setLayout(new GridLayout(0, 1));
		jpnResults.add(jlbResultHeading);
		jpnResults.add(jlbResult);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		jpnDate.setAlignmentX(Component.LEFT_ALIGNMENT);
		jpnPassenger.setAlignmentX(Component.LEFT_ALIGNMENT);
		jpnHotel.setAlignmentX(Component.LEFT_ALIGNMENT);
		jpnPlane.setAlignmentX(Component.LEFT_ALIGNMENT);
		jpnResults.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		add(jpnDate);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(jpnPassenger);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(jpnHotel);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(jpnPlane);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(jpnResults);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		showDateinLabel("dd MM yyyy");
	} // constructor
	/** Formats and displays today's date. */
	public void showDateinLabel(String x) {
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(x);
		try {
			String dateString = formatter.format(today);
			jlbResult.setForeground(Color.black);
			jlbResult.setText(dateString);
		} catch (IllegalArgumentException e) {
			jlbResult.setForeground(Color.red);
			jlbResult.setText("Error: " + e.getMessage());
		}
	}
	//SOCKET
	public static void OpenCustomerSocket(){
		System.out.println("userGui");
		System.out.println("userGui");
		MyClient = null;
	    try {
	           MyClient = new Socket("localhost", 6787);
	    }
	    catch (IOException e) {
	        System.out.println(e);
	    }
	    
	    
	  //Data input stream
	    input = null;
	    try {
	       input = new DataInputStream(MyClient.getInputStream());
	       System.out.println("input basilsin: "+ input);
	    }
	    catch (IOException e) {
	       System.out.println(e);
	    }	    
	    
	    //OUTPUT
	    try {
	       output = new DataOutputStream(MyClient.getOutputStream());
	       System.out.println("vlkfdsnvçö");
	    }
	    catch (IOException e) {
	       System.out.println(e);
	    }
	}
	
	public static void CloseCustomerSocket(){
		//Close input outputs and socket
	    try {
	           output.close();
	           input.close();
	       MyClient.close();
	    } 
	    catch (IOException e) {
	       System.out.println(e);
	    }
	    
	}
	
	//String getdayChoosed(){ return dayChoosed; }
	//void setdayChoosed(String x){ this.dayChoosed = x; }
	
}
