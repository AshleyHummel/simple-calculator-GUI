/*
 * Ashley Hummel
 * Date (created): 2/2/21
 * 
 * A simple java calculator GUI to practice Swing.
 * 
 * CLASSES: Calculator and Logic
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Calculator implements ActionListener
{
	
	Logic logic; //internal calculator
	private String currentOp; //tracks current operation
	private boolean opClicked; //tracks if an operation button should be highlighted
	private double temp1; //holds first value
	private double temp2; //holds second value
	
	//declare size constants
	private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 445;
    private static final int BUTTON_WIDTH = 10;
    private static final int BUTTON_HEIGHT = 15;
    private static final int GAP = 5; //gaps between components/panels
    
    //initialize font
	private Font font;
	
	
	//initialize components
	private JFrame frame;
	
	private JTextField inputText;
	private String text;
	private JButton btnBack, btnClear;
	
	private JPanel numberPanel;
	private JButton[] numbers;
	private JPanel operationsPanel;
	private JButton[] operations;
	
	public Calculator() 
	{
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		 } catch (Exception e) {
		            e.printStackTrace();
		 }
		
		initGUI();
	}
	
	private void initGUI() 
	{
		logic  = new Logic(); //implements calculator logic
		
		//initialize variables
		opClicked = false;
		temp1 = 0.0;
		temp2 = 0.0;
		currentOp = "";
		
		//initialize frame
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Calculator");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		
		//initialize font
		font = new Font("Monospaced", Font.BOLD, 18);
		
		//initialize top panel
		GridLayout topLayout = new GridLayout(2, 1);
		topLayout.setHgap(GAP);
		topLayout.setVgap(GAP);
		JPanel topPanel = new JPanel(topLayout); //contains input text field AND "delete" buttons
		
		//set property of input textfield
		inputText = new JTextField(20);
		inputText.setEditable(false); //user cannot edit text field
		text = "0";
		inputText.setText(text);
		inputText.setForeground(Color.BLACK); //text color
		inputText.setBackground(Color.WHITE);
		inputText.setBorder(BorderFactory.createBevelBorder(0));
		inputText.setPreferredSize(new Dimension(200, 75));
		inputText.setHorizontalAlignment(SwingConstants.RIGHT);
		inputText.setFont(new Font("Monospaced", Font.BOLD, 35)); //enlarge input text size
		topPanel.add(inputText);
		
		//initialize delete button panel
		GridLayout delBtnsLayout = new GridLayout(1, 2);
		delBtnsLayout.setHgap(GAP);
		delBtnsLayout.setVgap(GAP);
		JPanel deleteBtnsPanel = new JPanel(delBtnsLayout); //contains "delete" buttons
		
		//set property of "backspace" (←) button
		btnBack = new JButton("←");
		configureButton(btnBack);
		btnBack.setFont(new Font("Monospaced", Font.BOLD, 36)); //enlarge arrow
		deleteBtnsPanel.add(btnBack);
		
		//set property of "clear" button
		btnClear = new JButton("Clear");
		configureButton(btnClear);
		frame.getContentPane().add(btnClear);
		deleteBtnsPanel.add(btnClear);
		
		topPanel.add(deleteBtnsPanel); //put "backspace" and "clear" buttons on delete button panel
		
		frame.getContentPane().add(topPanel, BorderLayout.PAGE_START); //add delete button panel to main frame
		
		//initialize button panel
		GridLayout btnsLayout = new GridLayout(1, 2);
		btnsLayout.setHgap(GAP);
		btnsLayout.setVgap(GAP);
		JPanel btnsPanel = new JPanel(btnsLayout); //contains number AND operation buttons
				
		//initialize number panel
		GridLayout numberLayout = new GridLayout(4, 3);
		numberLayout.setHgap(GAP);
		numberLayout.setVgap(GAP);
		numbers = new JButton[12];
		numberPanel = new JPanel(numberLayout);
		
		//define number button values
		String[] btnValues  = {
			"7", "8", "9",
			"4", "5", "6",
			"1", "2", "3",
			"0", ".", "="
		};
		
		//configure number buttons using String array btnValues, and add to number panel
		for(int i = 0; i < btnValues.length; i++) 
		{
			numbers[i] = new JButton(btnValues[i]);
			configureButton(numbers[i]);
			
			numberPanel.add(numbers[i]);
		}
		btnsPanel.add(numberPanel);
		
		//initialize operations panel
		GridLayout operationsLayout = new GridLayout(4, 1);
		operationsLayout.setHgap(GAP);
		operationsLayout.setVgap(GAP);
		operations = new JButton[4];
		operationsPanel = new JPanel(operationsLayout);
		
		
		//define operation button values
		String[] operationValues  = {
				"/",
				"x",
				"-",
				"+"
			};
		
		//configure operation buttons using String array operationValues, and add to operations panel
		for(int i = 0; i < operationValues.length; i++) 
		{
			operations[i] = new JButton(operationValues[i]);
			configureButton(operations[i]);
			
			operationsPanel.add(operations[i]);
		}
		btnsPanel.add(operationsPanel);
		
		JPanel content = new JPanel(new BorderLayout(5, 5));
		content.add(topPanel, BorderLayout.NORTH);
		content.add(btnsPanel);
		
		content.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		frame.getContentPane().add(content, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}

	//configure default button settings
	private void configureButton(JButton btn) 
	{
		btn.setFont(font);
		btn.setBorder(BorderFactory.createBevelBorder(0));
		btn.setForeground(Color.BLACK); //text color
		btn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		btn.setOpaque(true);
		btn.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//"equals" button
		if(e.getSource() == numbers[numbers.length-1]) {
			for(int i = 0; i < operations.length; i++) {
				operations[i].setBorder(BorderFactory.createBevelBorder(0)); //set all operation borders back to default
			}
			
		if(currentOp.equals("/")) {
			temp2 = Double.parseDouble(inputText.getText());
			logic.divide(temp1, temp2);
			System.out.println("Divide " + temp1 + " and " + temp2); //TEST
			temp1 = Double.parseDouble(logic.getTotal());
			temp2 = 0;
			System.out.println("\tTemp1: " + temp1 //TEST
					+ "\n\tTemp2: " + temp2
					+ "\n\tTotal: " + logic.getTotal());
		}
		else if(currentOp.equals("x")) {
			temp2 = Double.parseDouble(inputText.getText());
			logic.multiply(temp1, temp2);
			System.out.println("Multiply " + temp1 + " and " + temp2); //TEST
			temp1 = Double.parseDouble(logic.getTotal());
			temp2 = 0;
			System.out.println("\tTemp1: " + temp1 //TEST
					+ "\n\tTemp2: " + temp2
					+ "\n\tTotal: " + logic.getTotal());
		}
		else if(currentOp.equals("-")) {
			temp2 = Double.parseDouble(inputText.getText());
			logic.subtract(temp1, temp2);
			System.out.println("Subtract " + temp1 + " and " + temp2); //TEST
			temp1 = Double.parseDouble(logic.getTotal());
			temp2 = 0;
			System.out.println("\tTemp1: " + temp1 //TEST
					+ "\n\tTemp2: " + temp2
					+ "\n\tTotal: " + logic.getTotal());
		}
		else if(currentOp.equals("+")) {
			temp2 = Double.parseDouble(inputText.getText());
			logic.add(temp1, temp2);
			System.out.println("Add " + temp1 + " and " + temp2); //TEST
			temp1 = Double.parseDouble(logic.getTotal());
			temp2 = 0;
			System.out.println("\tTemp1: " + temp1 //TEST
					+ "\n\tTemp2: " + temp2
					+ "\n\tTotal: " + logic.getTotal());
		}
		else {
			temp1 = Double.parseDouble(inputText.getText());
		}
		
		inputText.setText(logic.getTotal());
		
		currentOp = "=";
		}
		
		//number and decimal buttons
		else {
			for(int index = 0; index < numbers.length-1; index++)
			{
				opClicked = false;
				for(int i = 0; i < operations.length; i++) {
					operations[i].setBorder(BorderFactory.createBevelBorder(0)); //set all operation borders back to default
				}
				
				if(e.getSource() == numbers[index]) {
					//reset everything if a number if pressed after a calculation is completed ("equals" button pressed)
					// --> calculator assumes that the user is starting a new calculation
					
					if(inputText.getText().equals("0")) { //replace 0
						text = "";
					}
					
					text += e.getActionCommand();
				}
				
				inputText.setText(text); //print previous inputs + button clicked
			}
		}
		
		//operation (divide, multiply, add, subtract) buttons
		for(int index = 0; index < operations.length; index++)
		{
			if(e.getSource() == operations[index]) {
				opClicked = true;
				
				if(opClicked) {
					for(int i = 0; i < operations.length; i++) {
						operations[i].setBorder(BorderFactory.createBevelBorder(0)); //set all operation borders back to default
					}
				}
				operations[index].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3)); //highlight current operation
				
				if(currentOp.equals("/")) {
					temp2 = Double.parseDouble(inputText.getText());
					logic.divide(temp1, temp2);
					temp1 = Double.parseDouble(logic.getTotal());
					temp2 = 0;
				}
				else if(currentOp.equals("x")) {
					temp2 = Double.parseDouble(inputText.getText());
					logic.multiply(temp1, temp2);
					temp1 = Double.parseDouble(logic.getTotal());
					temp2 = 0;
				}
				else if(currentOp.equals("-")) {
					temp2 = Double.parseDouble(inputText.getText());
					logic.subtract(temp1, temp2);
					temp1 = Double.parseDouble(logic.getTotal());
					temp2 = 0;
				}
				else if(currentOp.equals("+")) {
					temp2 = Double.parseDouble(inputText.getText());
					logic.add(temp1, temp2);
					temp1 = Double.parseDouble(logic.getTotal());
					temp2 = 0;
				}
				else if(currentOp.equals("=")) {
					//keep temp1 as the previous total value
				}
				else {
					temp1 = Double.parseDouble(inputText.getText());
				}
				
				currentOp = e.getActionCommand();
				text = "";
				inputText.setText(text);
			}
		}
		
		//"backspace" button
		if(e.getSource() == btnBack) {
			if(text.equals("")) { //do nothing if there is no displayed input
				
			}
			else {
				text = text.substring(0, text.length() - 1); //remove most recent input
				inputText.setText(text);
			}
		}
		
		//"clear" button
		if(e.getSource() == btnClear) {
			reset();
			opClicked = false;
		}
	}
	
	private void reset() {
		logic.reset();
		temp1 = 0.0;
		temp2 = 0.0;
		currentOp = "";
		text = "0"; //reset textfield display
		inputText.setText(text);
	}

	public static void main(String[] args) 
	{
		new Calculator();
	}

}