package lab6;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;



import java.awt.Container;

public class Calc implements ActionListener{

  JFrame guiFrame;
  JPanel buttonPanel;
  JTextField numberCalc;
  int calcOperation = 0;
  int currentCalc;
  private RPCClient sender = new RPCClient();
  public static void main(String[] args) {
       new Calc();         
  }
  
  public Calc()
  {
      guiFrame = new JFrame();
      
      guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      guiFrame.setSize(300,300);
    
      guiFrame.setLocationRelativeTo(null);
      
      numberCalc = new JTextField();
      numberCalc.setHorizontalAlignment(JTextField.RIGHT);
      numberCalc.setEditable(false);
      
      guiFrame.add(numberCalc, BorderLayout.NORTH);
      
      buttonPanel = new JPanel();
             
      buttonPanel.setLayout(new GridLayout(4,3));   
      guiFrame.add(buttonPanel, BorderLayout.CENTER);
      
      for (int i=1;i<10;i++)
      {
          addButton(buttonPanel, String.valueOf(i));
      }

      JButton addButton = new JButton("+");
      addButton.setActionCommand("+");
      
      OperatorAction subAction = new OperatorAction(1);
      addButton.addActionListener(subAction);
      
      JButton subButton = new JButton("-");
      subButton.setActionCommand("-");
      
      OperatorAction addAction = new OperatorAction(2);
      subButton.addActionListener(addAction);
      
      JButton equalsButton = new JButton("=");
      equalsButton.setActionCommand("=");
      equalsButton.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent event)
          {
        	  
              if (!numberCalc.getText().isEmpty())
              {
            	  
            	  numberCalc.setText(sender.run(numberCalc.getText(), calcOperation, currentCalc));
            	  
              }
          }
      });
      
      buttonPanel.add(addButton);
      buttonPanel.add(subButton);
      buttonPanel.add(equalsButton);
      guiFrame.setVisible(true);  
  }
  
  private void addButton(Container parent, String name)
  {
      JButton but = new JButton(name);
      but.setActionCommand(name);
      but.addActionListener(this);
      parent.add(but);
  }
  
  @Override
  public void actionPerformed(ActionEvent event)
  {
      String action = event.getActionCommand();
      
      numberCalc.setText(action);       
  }
  
  private class OperatorAction implements ActionListener
  {
      private int operator;
      
      public OperatorAction(int operation)
      {
          operator = operation;
      }
      
      public void actionPerformed(ActionEvent event)
      {
          currentCalc = Integer.parseInt(numberCalc.getText()); 
          calcOperation = operator;
      }
  }
}