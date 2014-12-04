import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

enum LastButton{NUMBER, OPERATION};

public class GUI extends JFrame implements ActionListener {
	JTextField display;
	JPanel btnPanel;
	Vector<String> numbers;
	private LastButton lastButton;
	private String cur;
	private String prev;
	private String oper="none";
	public GUI(){
		lastButton=LastButton.NUMBER;
		numbers=new Vector<String>();
		display=new JTextField();
		display.setEditable(false);
		btnPanel=new JPanel();
		add(display, BorderLayout.NORTH);
		
		btnPanel.setLayout(new GridLayout(4,4));
		add(btnPanel, BorderLayout.CENTER);
		
		for(int i=1; i<10; i++){
			JButton btn=new JButton(String.valueOf(i));
			btnPanel.add(btn);
			btn.setActionCommand(String.valueOf(i));
			btn.addActionListener(this);
			numbers.add(String.valueOf(i));
		}
		
		JButton zero=new JButton("0");
		numbers.add("0");
		JButton dot=new JButton(".");
		JButton equal=new JButton("=");
		zero.setActionCommand("0");
		zero.addActionListener(this);
		equal.setActionCommand("=");
		equal.addActionListener(this);
		JPanel operations=new JPanel();
		operations.setLayout(new BoxLayout(operations, BoxLayout.PAGE_AXIS));
		
		JButton addBtn=new JButton("+");
		addBtn.setActionCommand("+");
		addBtn.addActionListener(this);
		JButton subBtn=new JButton("-");
		subBtn.setActionCommand("-");
		subBtn.addActionListener(this);
		JButton multBtn=new JButton("*");
		multBtn.setActionCommand("*");
		multBtn.addActionListener(this);
		JButton divBtn=new JButton("/");
		divBtn.setActionCommand("/");
		divBtn.addActionListener(this);
		
		
		JButton sinBtn=new JButton("sin");
		sinBtn.setActionCommand("sin");
		sinBtn.addActionListener(this);
		
		JButton cosBtn=new JButton("cos");
		cosBtn.setActionCommand("cos");
		cosBtn.addActionListener(this);
		
		JButton powBtn=new JButton("pow");
		powBtn.setActionCommand("pow");
		powBtn.addActionListener(this);
		
		operations.add(addBtn);
		operations.add(subBtn);
		operations.add(multBtn);
		operations.add(divBtn);
		operations.add(sinBtn);
		operations.add(cosBtn);
		operations.add(powBtn);
		
		btnPanel.add(zero);
		btnPanel.add(dot);
		btnPanel.add(equal);
		
		add(operations, BorderLayout.EAST);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 GUI app = new GUI();
		 app.setVisible(true);
		 app.setSize(300,300);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		
		if(numbers.contains(command)){
			if(lastButton == LastButton.OPERATION){
				prev=display.getText();
				display.setText("");
			}
			display.setText(display.getText()+command);
			lastButton=LastButton.NUMBER;
		}else{
			lastButton=LastButton.OPERATION;
		}
		
		operation(command);
	}
	
	//Performing operation
	public void operation(String command){
		
		
		if(lastButton == LastButton.NUMBER) return;
		String ans="";
		if(command.equals("=")){
			
			cur=display.getText();
			System.out.println(oper+" "+prev+" "+cur);
			if(oper.equals("sin") || oper.equals("cos")){
				ans=ClientRPC.send(oper+" "+cur);
			}else{
				ans=ClientRPC.send(oper+" "+prev+" "+cur);
			}
				
		
			display.setText(""+ans);
			System.out.println("Answer: "+ans);
		}else{
			oper=command;
		}
		
		//display.setText(Integer.toString(ans));
	}
}

