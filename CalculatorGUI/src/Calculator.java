/*
 * Ashley Hummel
 * Date (created): 2/2/21
 * 
 * A simple java calculator GUI to practice Swing.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class Calculator extends JFrame 
{
	private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;
    private static final int BUTTON_WIDTH = 10;
    private static final int BUTTON_HEIGHT = 15;
    private Color baseColor;
    private Color darkGrey;
    private Color lightGrey;
    private Color orange;
    
	private Font font;
	
	private JTextField inputText;
	private JButton btnBack, btnClear;
	
	public Calculator() {
		font = new Font("Monospaced", Font.BOLD, 18);
		baseColor = new ColorUIResource(46, 44, 41);
		darkGrey = new ColorUIResource(69, 66, 63);
		lightGrey = new ColorUIResource(110, 105, 99);
		orange = new ColorUIResource(217, 150, 35);
		
		setLayout(new BorderLayout(10, 10));
		getContentPane().setBackground(baseColor);
		
		
		JPanel topPanel = new JPanel(new GridLayout(2, 1));
		configurePanel(topPanel);
		
		inputText = new JTextField(20);
		inputText.setForeground(Color.WHITE); //text color
		inputText.setBackground(baseColor);
		inputText.setBorder(BorderFactory.createBevelBorder(0));
		inputText.setPreferredSize(new Dimension(250, 60));
		inputText.setHorizontalAlignment(SwingConstants.RIGHT);
		inputText.setFont(new Font("Monospaced", Font.BOLD, 35)); //enlarge input text size
		
		JPanel deletePanel = new JPanel(new GridLayout(1, 2));
		
		btnBack = new JButton("←");
		btnBack.setBackground(darkGrey);
		configureButton(btnBack);
		btnBack.setFont(new Font("Monospaced", Font.BOLD, 36)); //enlarge arrow
		
		btnClear = new JButton("Clear");
		btnClear.setBackground(darkGrey);
		configureButton(btnClear);
		
		deletePanel.add(btnBack);
		deletePanel.add(btnClear);
		
		topPanel.add(inputText);
		topPanel.add(deletePanel);

		add(topPanel, BorderLayout.PAGE_START);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		JPanel numberPanel = new JPanel(new GridLayout(4, 3));
		configurePanel(numberPanel);
		
		String[] btnValues  = {
			"7", "8", "9",
			"4", "5", "6",
			"1", "2", "3",
			"0", ".", "="
		};
		
		for(int i = 0; i < btnValues.length; i++) {
			JButton btn = new JButton(btnValues[i]);
			btn.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
			
			btn.setBackground(lightGrey);
			if(i == btnValues.length-1) {
				btn.setBackground(orange);
			}
			
			btn.setOpaque(true);
			configureButton(btn);
			
			numberPanel.add(btn);
			
		}
		
		JPanel operationsPanel = new JPanel(new GridLayout(4, 1));
		configurePanel(operationsPanel);
		
		String[] operationValues  = {
				"÷",
				"x",
				"-",
				"+"
			};
		
		for(int i = 0; i < operationValues.length; i++) {
			JButton btn = new JButton(operationValues[i]);
			btn.setPreferredSize(new Dimension(BUTTON_WIDTH-5, BUTTON_HEIGHT));
			
			btn.setBackground(orange);
			configureButton(btn);
			
			operationsPanel.add(btn);
		}
		
		buttonPanel.add(numberPanel);
		buttonPanel.add(operationsPanel);
		
		add(buttonPanel, BorderLayout.CENTER);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Calculator");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);
	}

	private void configureButton(JButton btn) {
		btn.setFont(font);
		btn.setBorder(BorderFactory.createBevelBorder(0));
		btn.setForeground(Color.WHITE); //text color
		btn.setOpaque(true);
		
	}
	
	private void configurePanel(JPanel panel) {
		panel.setBackground(darkGrey);
	}
	
	public static void main(String[] args) 
	{
		new Calculator();
	}

}
