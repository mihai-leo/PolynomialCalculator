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
    private JLabel labels = new JLabel("..");
    private TextField field = new TextField();
    /*             myButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });*/
    public Interface() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSizes(frame);
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
        textField.setBounds(x,x*2,x*11,x);
        result.setBounds(x,x*4,x*11,x*2);
        label1.setBounds(x,x,x*10,x);
        labels.setBounds(x*11,x,x,x);
        //result.setFont(new Font("Arial", Font.PLAIN, 20));
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
        frame.add(labels);
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
    private void allButtonsAction(Operation operation,String string)
    {
        this.operation=operation;
        this.label1.setText(textField.getText());
        input1=new Polynomial(textField.getText());
        textField.setText("");
        labels.setText(string);
    }
    private void equalPress()
    {
        input2=new Polynomial(textField.getText());
        if(operation==Operation.none)
        {
            label1.setText("invalid");
        } else if (operation==Operation.der||operation==Operation.itg) {
            //input1=new Polynomial(textField.getText());
            OneInput oneInput=new OneInput(input1);
            if(operation==Operation.der) {
                oneInput.derivation();
                result.setText(oneInput.getResult().stringPolynomial());
            }
            else {
                oneInput.integration();
                result.setText(oneInput.getResult().stringPolynomial()+"+C");
            }
        }
        else {
            input2=new Polynomial(textField.getText());
            TwoInputs twoInputs=new TwoInputs(input1,input2);
            if (operation==Operation.add)
                twoInputs.addition();
            else if (operation==Operation.sub)
                twoInputs.subtraction();
            else if(operation==Operation.mul)
                twoInputs.multiplication();
            else {
                twoInputs.division();
            }
            if(operation==Operation.div)
                result.setText(twoInputs.getResult().stringPolynomial()+"\n"+"  R: "+twoInputs.getRest().stringPolynomial());
            else
                result.setText(twoInputs.getResult().stringPolynomial());
        }
    }
}
