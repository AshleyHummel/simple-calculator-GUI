/*
 * Ashley Hummel
 * Date (created): 2/2/21
 * 
 * A simple java calculator GUI to practice Swing.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Calculator implements ActionListener
{
	//declare size constants
	private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 445;
    private static final int BUTTON_WIDTH = 10;
    private static final int BUTTON_HEIGHT = 15;
    private static final int GAP = 5;
    
    //initialize color and font
	private Font font;
	
	private JFrame frame;
	
	private JTextField inputText;
	private JButton btnBack, btnClear;
	
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
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Calculator");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		
		//initialize font and colors
		font = new Font("Monospaced", Font.BOLD, 18);
		
		GridLayout topLayout = new GridLayout(2, 1);
		topLayout.setHgap(GAP);
		topLayout.setVgap(GAP);
		JPanel topPanel = new JPanel(topLayout); //contains input text field AND "delete" buttons
		
		//set property of input textfield
		inputText = new JTextField(20);
		inputText.setForeground(Color.BLACK); //text color
		inputText.setBackground(Color.WHITE);
		inputText.setBorder(BorderFactory.createBevelBorder(0));
		inputText.setPreferredSize(new Dimension(200, 75));
		inputText.setHorizontalAlignment(SwingConstants.RIGHT);
		inputText.setFont(new Font("Monospaced", Font.BOLD, 35)); //enlarge input text size
		topPanel.add(inputText);
		
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
		
		topPanel.add(deleteBtnsPanel);
		
		frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
		
		GridLayout btnsLayout = new GridLayout(1, 2);
		btnsLayout.setHgap(GAP);
		btnsLayout.setVgap(GAP);
		JPanel btnsPanel = new JPanel(btnsLayout); //contains number AND operation buttons
				
		//initialize number panel
		GridLayout numberLayout = new GridLayout(4, 3);
		numberLayout.setHgap(GAP);
		numberLayout.setVgap(GAP);
		JPanel numberPanel = new JPanel(numberLayout);
		
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
			JButton btn = new JButton(btnValues[i]);
			configureButton(btn);
			
			numberPanel.add(btn);
		}
		btnsPanel.add(numberPanel);
		
		//initialize operations panel
		GridLayout operationsLayout = new GridLayout(4, 1);
		operationsLayout.setHgap(GAP);
		operationsLayout.setVgap(GAP);
		JPanel operationsPanel = new JPanel(operationsLayout);
		
		
		//define operation button values
		String[] operationValues  = {
				"÷",
				"x",
				"-",
				"+"
			};
		
		//configure operation buttons using String array operationValues, and add to operations panel
		for(int i = 0; i < operationValues.length; i++) 
		{
			JButton btn = new JButton(operationValues[i]);
			configureButton(btn);
			btn.setBorder(BorderFactory.createBevelBorder(0));
			
			operationsPanel.add(btn);
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) 
	{
		new Calculator();
	}

}