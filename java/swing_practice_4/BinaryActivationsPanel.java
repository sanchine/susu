import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BinaryActivationsPanel extends JPanel {
    BinaryActivationsPanel() {
        setLayout(new GridLayout(6, 1));
        setBackground(new Color(170, 170, 170));
        setSize(150, 150);
        
        add(new JLabel("Binary num from activs.", 0));

        JPanel textInput1 = new JPanel(new GridLayout(1, 2, 20, 20));
        textInput1.setSize(150, 30);
        textInput1.add(new JLabel("Input x = ", 0));
        JTextField textField1 = new JTextField(10);
        textField1.setSize(100, 30);
        textInput1.add(textField1);
        add(textInput1);


        JPanel textInput2 = new JPanel(new GridLayout(1, 2, 20, 20));
        textInput2.setSize(150, 30);
        textInput2.add(new JLabel("Weights = ", 0));
        JTextField textField2 = new JTextField(10);
        textField2.setSize(100, 30);
        textInput2.add(textField2);
        add(textInput2);


        JPanel textInput3 = new JPanel(new GridLayout(1, 2, 20, 20));
        textInput3.setSize(150, 30);
        textInput3.add(new JLabel("Biases = ", 0));
        JTextField textField3 = new JTextField(10);
        textField3.setSize(100, 30);
        textInput3.add(textField3);
        add(textInput3);
        

        JButton submitButton = new JButton("Click me");
        add(submitButton);


        JLabel answer = new JLabel("Answer: ");
        add(answer);


        submitButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent event) {
                    double[] weights = parseToDoubleArray(textField2.getText());
                    double[] biases = parseToDoubleArray(textField3.getText());
                    double[] x = parseToDoubleArray(textField1.getText());

                    MathResolver mr = new MathResolver();
                    double[] predicts = mr.feedForward(weights, biases, x, 4);

                    boolean[] binary = mr.calculateActivationsBinary(predicts);
                    int result = mr.calculateIntByBinaryArray(binary);
                    answer.setText("Answer: " + Integer.toString(result));

                    revalidate();
                    repaint();
                }
        });
    }

    private static double[] parseToDoubleArray(String str) {
        String[] parts = str.split(", ");
        double[] result = new double[parts.length];
    
        for (int i = 0; i < parts.length; i++) {
            result[i] = Double.parseDouble(parts[i]);
        }
    
        return result;
    }
}
