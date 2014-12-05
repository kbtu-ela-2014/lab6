import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class CalcClient extends JFrame implements ActionListener {
    private JTextField number;
    private JTextField num2;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a1Button;
    private JButton a0Button;
    private JButton a8Button;
    private JButton pointButton;
    private JButton a2Button;
    private JButton a5Button;
    private JButton buttonMinus;
    private JButton buttonAdd;
    private JButton buttonDiv;
    private JButton a9Button;
    private JButton a6Button;
    private JButton a3Button;
    private JButton cButton;
    private JButton sinButton;
    private JButton cosButton;
    private JButton tanButton;
    private JButton eButton;
    private JButton twoButton;
    private JButton nButton;
    private JButton buttonSqrt;
    private JButton xyButton;
    private JPanel mainPanel;
    private JButton buttonMult;
    private JButton squareButton;
    private JButton cubeButton;
    private JButton overButton;
    private JButton buttonEquals;

    private CalcServerIntf calcServerIntf;

    double a = 0;
    double b = 0;
    Object lastButton = null;

    public CalcClient() throws Exception {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calcServerIntf = (CalcServerIntf) Naming.lookup("rmi://localhost/CalcServer");

        buttonAdd.addActionListener(this);
        buttonMinus.addActionListener(this);
        buttonMult.addActionListener(this);
        buttonDiv.addActionListener(this);

        sinButton.addActionListener(this);
        cosButton.addActionListener(this);
        tanButton.addActionListener(this);
        eButton.addActionListener(this);
        cButton.addActionListener(this);
        pointButton.addActionListener(this);

        nButton.addActionListener(this);
        buttonSqrt.addActionListener(this);
        xyButton.addActionListener(this);
        twoButton.addActionListener(this);
        squareButton.addActionListener(this);
        cubeButton.addActionListener(this);
        overButton.addActionListener(this);
        buttonEquals.addActionListener(this);

        a0Button.addActionListener(this);
        a1Button.addActionListener(this);
        a2Button.addActionListener(this);
        a3Button.addActionListener(this);
        a4Button.addActionListener(this);
        a5Button.addActionListener(this);
        a6Button.addActionListener(this);
        a7Button.addActionListener(this);
        a8Button.addActionListener(this);
        a9Button.addActionListener(this);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == buttonEquals) { // Нажата кнопка =
                if (number.getText().equals("")) return;
                b = Double.parseDouble(number.getText());

                System.out.println("a = " + a);
                System.out.println("b = " + b);
                System.out.println("lastButton  = " + lastButton.toString());
                if (lastButton == buttonAdd) {
                    number.setText(Double.toString(calcServerIntf.add(a, b)));
                } else if(lastButton == buttonMinus) {
                    number.setText(Double.toString(calcServerIntf.minus(a, b)));
                } else if(lastButton == buttonMult) {
                    number.setText(Double.toString(calcServerIntf.mult(a, b)));
                } else if(lastButton == buttonDiv) {
                    number.setText(Double.toString(calcServerIntf.div(a, b)));
                } else if (lastButton == xyButton) {
                    number.setText(Double.toString(calcServerIntf.xy(a, b)));
                }

            } else if (e.getSource() == a0Button) {
                number.setText(number.getText() + "0");
            } else if (e.getSource() == a1Button) {
                number.setText(number.getText() + "1");
            } else if (e.getSource() == a2Button) {
                number.setText(number.getText() + "2");
            } else if (e.getSource() == a3Button) {
                number.setText(number.getText() + "3");
            } else if (e.getSource() == a4Button) {
                number.setText(number.getText() + "4");
            } else if (e.getSource() == a5Button) {
                number.setText(number.getText() + "5");
            } else if (e.getSource() == a6Button) {
                number.setText(number.getText() + "6");
            } else if (e.getSource() == a7Button) {
                number.setText(number.getText() + "7");
            } else if (e.getSource() == a8Button) {
                number.setText(number.getText() + "8");
            } else if (e.getSource() == a9Button) {
                number.setText(number.getText() + "9");
            } else if (e.getSource() == cButton) {
            number.setText("");
            } else if (e.getSource() == pointButton) {
            number.setText(number.getText() + ".");
            }

            else {
                if (number.getText().equals("")) return;
                a = Double.parseDouble(number.getText());
                lastButton = e.getSource();
                if (lastButton == buttonSqrt) {
                    number.setText(Double.toString(calcServerIntf.sqrt(a)));
                } else if (lastButton == sinButton) {
                    number.setText(Double.toString(calcServerIntf.sin(a)));
                } else if (lastButton == cosButton) {
                    number.setText(Double.toString(calcServerIntf.cos(a)));
                } else if (lastButton == tanButton) {
                    number.setText(Double.toString(calcServerIntf.tan(a)));
                } else if (lastButton == nButton) {
                    number.setText(Double.toString(calcServerIntf.fact(a)));
                } else if (lastButton == eButton) {
                    number.setText(Double.toString(calcServerIntf.exp(a)));
                } else if (lastButton == squareButton) {
                    number.setText(Double.toString(calcServerIntf.square(a)));
                } else if (lastButton == cubeButton) {
                    number.setText(Double.toString(calcServerIntf.cube(a)));
                } else if (lastButton == overButton) {
                    number.setText(Double.toString(calcServerIntf.over(a)));
                } else if (lastButton == twoButton) {
                    number.setText(Double.toString(calcServerIntf.two(a)));
                } else {
                    number.setText("");
                }
            }
        } catch (RemoteException re) {
            System.out.println("Exception: " + e);
        }
    }

    public static void main(String[] args) throws Exception {
        CalcClient calcClient = new CalcClient();
    }
}
