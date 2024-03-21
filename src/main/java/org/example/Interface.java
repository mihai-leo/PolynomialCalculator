package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Interface {
    private enum Operation{add,sub,mul,div,der,itg,none};
    private Operation operation;
    Polynomial input1= new Polynomial();
    Polynomial input2= new Polynomial();
    private JButton myButton = new JButton("=");
    private JButton addButton = new JButton("+");
    private JButton divButton = new JButton("/");
    private JButton mulButton = new JButton("x");
    private JButton subButton = new JButton("-");
    private JButton derButton = new JButton("d");
    private JButton itgButton = new JButton("I");
    private JTextField textField =new JTextField("");
    private JLabel result = new JLabel("result");
    private JLabel label1 = new JLabel("...");
    private JLabel label2 = new JLabel("...");
    private JLabel labels = new JLabel("..");


    public Interface() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSizes(frame);//will set the sizes for buttons and labels
            frame.add(myButton);
            frame.add(textField);
            frame.add(addButton);
            frame.add(subButton);
            frame.add(mulButton);
            frame.add(divButton);
            frame.add(derButton);
            frame.add(itgButton);
            frame.add(result);
            frame.add(label1);
            frame.add(label2);
            frame.add(labels);
            myButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    equalPress();
                }
            });
            allButtons(addButton,Operation.add,"+");
            allButtons(subButton,Operation.sub,"-");
            allButtons(divButton,Operation.div,"/");
            allButtons(mulButton,Operation.mul,"*");
            allButtons(derButton,Operation.der,"d");
            allButtons(itgButton,Operation.itg,"I");
        });
    }
    private void setSizes(JFrame frame)
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int W=screenSize.width/4;
        int x=W/13;
        frame.setLayout(null);
        myButton.setBackground(Color.gray);
        addButton.setBackground(Color.gray);
        subButton.setBackground(Color.gray);
        mulButton.setBackground(Color.gray);
        divButton.setBackground(Color.gray);
        derButton.setBackground(Color.gray);
        itgButton.setBackground(Color.gray);
        myButton.setBounds(x,x*13,x*11,x*2);
        addButton.setBounds(x,x*7,x*3,x*2);
        subButton.setBounds(x*5,x*7,x*3,x*2);
        mulButton.setBounds(x*9,x*7,x*3,x*2);
        divButton.setBounds(x,x*10,x*3,x*2);
        derButton.setBounds(x*5,x*10,x*3,x*2);
        itgButton.setBounds(x*9,x*10,x*3,x*2);
        textField.setBounds(x,x,x*11,x);
        result.setBounds(x,x*4,x*11,x*2);
        label1.setBounds(x,x*2,x*10,x);
        label2.setBounds(x,x*3,x*10,x);
        labels.setBounds(x*11,x*2,x,x);
        frame.setSize(W, x*16);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBackground(Color.BLACK);
    }
    private void allButtons(JButton button ,Operation operation,String string)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allButtonsAction(operation,string);
            }
        });

    }
    public boolean isGood(String string)
    {
        if (string.charAt(0)!='-')
        {
            string="+"+string;
        }
        string=string.replaceAll(" ","");
       // String regex="^(([-+]{1}[0-9]*\\.?[0-9]*)(x?)(\\^([0-9]+)))+$";
        String termPattern = "([-+]{1}[0-9]*\\.?[0-9]*)(x?)(\\^([0-9]+))?";
        String regex = "(" + termPattern + "(\\s*" + termPattern + ")*)";
        return Pattern.matches(regex,string);
    }
    private void allButtonsAction(Operation operation,String string)
    {
        if (isGood(textField.getText())) {
            this.operation = operation;
            input1 = new Polynomial(textField.getText());
            label1.setText(input1.stringPolynomial());
            labels.setText(string);
            label2.setText("");
            result.setText("result");
        }
        else
            this.result.setText("invalid input");
        textField.setText("");

    }
    private void equalPress()
    {
        if (operation == Operation.none) {
            result.setText("invalid");
        } else if (operation == Operation.der || operation == Operation.itg) {
           oneInputOperation();
            } else if (isGood(textField.getText())) {
            twoInputOperation();
            }  else
            result.setText("invalid input");
        textField.setText("");
    }
    private void oneInputOperation()
    {
        Operations oneInput = new Operations(input1);
        if (operation == Operation.der) {
            oneInput.derivation();
            result.setText(oneInput.getResult().stringPolynomial());
        } else {
            oneInput.integration();
            result.setText(oneInput.getResult().stringPolynomial() + "+C");
        }
    }
    private void twoInputOperation()
    {

        input2 = new Polynomial(textField.getText());
        label2.setText(input2.stringPolynomial());
        Operations twoInputs = new Operations(input1, input2);
        if (operation == Operation.add)
            twoInputs.addition();
        else if (operation == Operation.sub)
            twoInputs.subtraction();
        else if (operation == Operation.mul)
            twoInputs.multiplication();
        else {
            try {
                twoInputs.division();
            } catch (Exception e) {
                result.setText("No 0 division");
                return;
            }
        }
        if (operation == Operation.div)
            result.setText(twoInputs.getResult().stringPolynomial() + "\n" + "  R: " + twoInputs.getRest().stringPolynomial());
        else
            result.setText(twoInputs.getResult().stringPolynomial());
    }
}
