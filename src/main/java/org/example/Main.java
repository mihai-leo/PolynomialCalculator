package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
     /*   SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Swing App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JButton myButton = new JButton("Add");
            TextField fiel2 = new TextField();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            // Get the screen size
            Dimension screenSize = toolkit.getScreenSize();
            int W=screenSize.width;
            int H=screenSize.height;
            myButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            frame.setLayout(null);
            myButton.setBackground(Color.gray);

            myButton.setBounds(W/8,W/6,W/10,W/10);
            frame.add(myButton);
            frame.add(fiel2);

            frame.setSize(W/4, W/3);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });*/
        Polynomial input1= new Polynomial("2x^3+x^2-3x^1");
        Polynomial input2= new Polynomial("4x^2-x^1-3");

        System.out.printf(input1.stringPolynomial()+"\n");
        System.out.printf(input2.stringPolynomial()+"\n");
        System.out.println("\n");
        TwoInputs op =new TwoInputs(input1,input2);
        OneInput op2 =new OneInput(input1);

        op.addition();

        System.out.println(op.getResult().stringPolynomial());
        op.subtraction();
        System.out.println(op.getResult().stringPolynomial());
        op.multiplication();
        System.out.println(op.getResult().stringPolynomial());
        op2.derivation();
        System.out.println(op2.getResult().stringPolynomial());
        op2.integration();
        System.out.println(op2.getResult().stringPolynomial());



    }
}