import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;

class Calculator extends JFrame {
	// Constructor
	public Calculator() {
		setTitle("Easy Calculator");   
		setBounds(500, 500, 300, 150);

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	JPanel contentPane = new JPanel();
		setContentPane(contentPane);
    	contentPane.setLayout(new GridLayout(2, 5));
		
		// Create two panels
    	JPanel pane1 = new JPanel();
    	contentPane.add(pane1);
    	JPanel pane2 = new JPanel();
    	contentPane.add(pane2);
		
		// Five components in pane1
    	final JTextField num1 = new JTextField("", JLabel.CENTER);
		final JLabel op1 = new JLabel("", JLabel.CENTER);
		final JTextField num2 = new JTextField("", JLabel.CENTER);
		JLabel op2 = new JLabel("=", JLabel.CENTER);
		final JLabel ans = new JLabel("");
		
		// Set border
		op1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 210, 240)));
		op2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 210, 240))); 		
		ans.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 210, 240))); 

		num1.setPreferredSize(new Dimension(50,50));
		op1.setPreferredSize(new Dimension(50,50));
		num2.setPreferredSize(new Dimension(50,50));
		op2.setPreferredSize(new Dimension(50,50));
		ans.setPreferredSize(new Dimension(60,50));

		JButton button1 = new JButton("+");
		JButton button2 = new JButton("-");
		JButton button3 = new JButton("*");
		JButton button4 = new JButton("/");
		JButton button5 = new JButton("OK");

		button1.setPreferredSize(new Dimension(50,50));
		button2.setPreferredSize(new Dimension(50,50));
		button3.setPreferredSize(new Dimension(50,50));
		button4.setPreferredSize(new Dimension(50,50));
		button5.setPreferredSize(new Dimension(60,50));

    	pane1.add(num1);
		pane1.add(op1);
		pane1.add(num2);
		pane1.add(op2);
		pane1.add(ans);

		pane2.add(button1);
		pane2.add(button2);
		pane2.add(button3);
		pane2.add(button4);
		pane2.add(button5);

		button1.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
            	op1.setText("+");
			}
		});

		button2.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
            	op1.setText("-");
			}
		});

		button3.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
            	op1.setText("*");
			}
		});

		button4.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
            	op1.setText("/");
			}
		});

		button5.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
            	String strnum1 = num1.getText().toString();
				String strnum2 = num2.getText().toString();
				if ( strnum1.equals("") || strnum2.equals("") ) {
					return;
				}

				Double number1 = Double.valueOf(strnum1).doubleValue();
				Double number2 = Double.valueOf(strnum2).doubleValue();
				String op = op1.getText().toString();
				Double answer = 0.0;
				if ( op.equals("") ) {
					return;
				} else if ( op.equals("+") ) {
					answer = number1 + number2;
				} else if ( op.equals("-") ) {
					answer = number1 - number2;
				} else if ( op.equals("*") ) {
					answer = number1 * number2;
				} else if ( op.equals("/") ) {
					answer = number1 / number2;
				} 
				
				ans.setText(answer.toString());
			}
		});

    	setVisible(true);
  	}
	
  	public static void main(String[] args) {
    	JFrame calculator = new Calculator();
  	}

}
