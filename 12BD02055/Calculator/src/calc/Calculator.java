/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import java.io.IOException;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;
import java.util.UUID;

import java.awt.ComponentOrientation;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aibek
 */
public class Calculator extends javax.swing.JFrame {

    /**
     * Creates new form Calculator
     */
    String prev="0", cur="0", operation;
    boolean isOperation = false;
    private Connection connection;
	private Channel channel;
	private String requestQueueName = "rpc_queue";
	private String replyQueueName;
	private QueueingConsumer consumer;
        static Calculator client;

    public Calculator() {
        
        initComponents();
        try {
            connect();
        } catch (IOException ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton10 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        edittext = new javax.swing.JTextPane();
        cancel = new javax.swing.JButton();
        divide = new javax.swing.JButton();
        multiply = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        factorial = new javax.swing.JButton();
        sign = new javax.swing.JButton();
        backspace = new javax.swing.JButton();
        minus = new javax.swing.JButton();
        plus = new javax.swing.JButton();
        power = new javax.swing.JButton();
        equals = new javax.swing.JButton();

        jButton10.setText("1");
        jButton10.setSize(new java.awt.Dimension(100, 30));

        jButton17.setText("1");
        jButton17.setSize(new java.awt.Dimension(100, 30));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        edittext.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        edittext.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jScrollPane1.setViewportView(edittext);

        cancel.setText("C");
        cancel.setSize(new java.awt.Dimension(100, 30));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        divide.setText("/");
        divide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divideActionPerformed(evt);
            }
        });

        multiply.setText("*");
        multiply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplyActionPerformed(evt);
            }
        });

        btn7.setText("7");
        btn7.setSize(new java.awt.Dimension(100, 30));
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setText("8");
        btn8.setSize(new java.awt.Dimension(100, 30));
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setText("9");
        btn9.setSize(new java.awt.Dimension(100, 30));
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btn4.setText("4");
        btn4.setSize(new java.awt.Dimension(100, 30));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setText("5");
        btn5.setSize(new java.awt.Dimension(100, 30));
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setText("6");
        btn6.setSize(new java.awt.Dimension(100, 30));
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn1.setText("1");
        btn1.setSize(new java.awt.Dimension(100, 30));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setText("2");
        btn2.setSize(new java.awt.Dimension(100, 30));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setText("3");
        btn3.setSize(new java.awt.Dimension(100, 30));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn0.setText("0");
        btn0.setSize(new java.awt.Dimension(100, 30));
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        factorial.setText("n!");
        factorial.setSize(new java.awt.Dimension(100, 30));
        factorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                factorialActionPerformed(evt);
            }
        });

        sign.setText("-1");
        sign.setSize(new java.awt.Dimension(100, 30));
        sign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signActionPerformed(evt);
            }
        });

        backspace.setText("<-");
        backspace.setSize(new java.awt.Dimension(100, 30));
        backspace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backspaceActionPerformed(evt);
            }
        });

        minus.setText("-");
        minus.setSize(new java.awt.Dimension(100, 30));
        minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusActionPerformed(evt);
            }
        });

        plus.setText("+");
        plus.setSize(new java.awt.Dimension(100, 30));
        plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusActionPerformed(evt);
            }
        });

        power.setText("x^y");
        power.setSize(new java.awt.Dimension(100, 30));
        power.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerActionPerformed(evt);
            }
        });

        equals.setBackground(new java.awt.Color(238, 118, 33));
        equals.setText("=");
        equals.setSize(new java.awt.Dimension(100, 30));
        equals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equalsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(power, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(plus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sign, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(factorial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equals, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(divide, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(multiply, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(backspace, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(divide, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(multiply, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backspace, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(power, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(factorial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sign, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equals, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public String call(String message, String type) throws Exception {     
	String response = null;
	String corrId = UUID.randomUUID().toString();
	
	BasicProperties props = new BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();
	String str = type+message;
        BufferedImage image;
	channel.basicPublish("", requestQueueName, props, str.getBytes());
	while (true) {
	  QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	  if (delivery.getProperties().getCorrelationId().equals(corrId)) {
	    response = new String(delivery.getBody(),"UTF-8");
	    break;
	  }
	}
	
	return response; 
	}

public void close() throws Exception {
	connection.close();
	}
    public void connect() throws IOException{
        ConnectionFactory factory = new ConnectionFactory();
	factory.setHost("localhost");
	connection = factory.newConnection();
	channel = connection.createChannel();
	
	replyQueueName = channel.queueDeclare().getQueue(); 
	consumer = new QueueingConsumer(channel);
	channel.basicConsume(replyQueueName, true, consumer);
    }
    public void numberPressed(String number){
        if(isOperation){
        edittext.setText("");
        isOperation = false;
        }
        String str=edittext.getText();
        if(str.equals("0"))edittext.setText("");
        edittext.setText(edittext.getText().toString()+number);
    }
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
        numberPressed("1");
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
        numberPressed("2");
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
        numberPressed("3");
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
       numberPressed("4");
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
      numberPressed("5");
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        numberPressed("6");
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        numberPressed("7");
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        numberPressed("8");
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        numberPressed("9");
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        numberPressed("0");
    }//GEN-LAST:event_btn0ActionPerformed

    private void backspaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backspaceActionPerformed
        isOperation=false;
        String str = edittext.getText();
        System.out.print(str);
        
        
        if(str.length()==1) edittext.setText("");
        else if(str!=null && !str.equals("")){
        edittext.setText(str.substring(0,str.length()-1));
        }
        
        str= edittext.getText();
        if(str.equals(""))
            edittext.setText("0");
    }//GEN-LAST:event_backspaceActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        isOperation=false;
        edittext.setText("0");
        prev="0";
        cur="0";
        
    }//GEN-LAST:event_cancelActionPerformed

    private void factorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_factorialActionPerformed
       operation="!";
       cur = edittext.getText();
        
        try {
            String ans = call(cur, operation);
            edittext.setText(ans);
        } catch (Exception ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        prev=cur;
    }//GEN-LAST:event_factorialActionPerformed

    private void signActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signActionPerformed
        String str = edittext.getText().toString();
        Integer integer = Integer.parseInt(str);
        integer*=(-1);
        edittext.setText(integer.toString());
    }//GEN-LAST:event_signActionPerformed

    private void plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusActionPerformed
        prev = edittext.getText();
        operation="+";
        isOperation = true;
    }//GEN-LAST:event_plusActionPerformed

    private void divideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divideActionPerformed
        prev = edittext.getText();
        operation="/";
        isOperation = true;
    }//GEN-LAST:event_divideActionPerformed

    private void multiplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplyActionPerformed
        prev = edittext.getText();
        operation="*";
        isOperation = true;
    }//GEN-LAST:event_multiplyActionPerformed

    private void minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusActionPerformed
        prev = edittext.getText();
        operation="-";
        isOperation = true;
    }//GEN-LAST:event_minusActionPerformed

    private void equalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equalsActionPerformed
        cur = edittext.getText();
        String str = prev+"||"+cur;
        try {
            String ans = call(str, operation);
            edittext.setText(ans);
        } catch (Exception ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        prev=cur;
    }//GEN-LAST:event_equalsActionPerformed

    private void powerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerActionPerformed
        prev = edittext.getText();
        operation="^";
        isOperation = true;
    }//GEN-LAST:event_powerActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                client = new Calculator();
                client.setVisible(true);
                
                
            }
            
        });
        
        
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backspace;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton cancel;
    private javax.swing.JButton divide;
    private javax.swing.JTextPane edittext;
    private javax.swing.JButton equals;
    private javax.swing.JButton factorial;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton minus;
    private javax.swing.JButton multiply;
    private javax.swing.JButton plus;
    private javax.swing.JButton power;
    private javax.swing.JButton sign;
    // End of variables declaration//GEN-END:variables
}
