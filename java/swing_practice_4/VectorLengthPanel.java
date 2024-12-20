import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VectorLengthPanel extends JPanel {
    VectorLengthPanel() {
        setLayout(new GridLayout(6, 1));
        setBackground(new Color(170, 170, 170));
        setSize(150, 150);
        
        add(new JLabel("Calculate ||(y1, y2)||.", 0));

        JPanel textInput1 = new JPanel(new GridLayout(1, 2, 20, 20));
        textInput1.setSize(150, 30);
        textInput1.add(new JLabel("y_1 = ", 0));
        JTextField textField1 = new JTextField();
        textField1.setSize(100, 30);
        textInput1.add(textField1);
        add(textInput1);


        JPanel textInput2 = new JPanel(new GridLayout(1, 2, 20, 20));
        textInput2.setSize(150, 30);
        textInput2.add(new JLabel("y_2 = ", 0));
        JTextField textField2 = new JTextField();
        textField2.setSize(100, 30);
        textInput2.add(textField2);
        add(textInput2);
        


        JButton submitButton = new JButton("Click me");
        add(submitButton);


        JLabel answer = new JLabel("Answer: ");
        add(answer);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (textField1.getText() == "" && textField2.getText() == "") {
                    return;
                }

                double y1 = Double.parseDouble(textField1.getText());
                double y2 = Double.parseDouble(textField2.getText());
                
                MathResolver mr = new MathResolver();
                
                double vectorLengthValue = 
                       mr.calculateVectorLength(new double[]{y1, y2});

                String formattedAnswer = String.format("%2.3f", vectorLengthValue);

                answer.setText("Answer: " + formattedAnswer);
                revalidate();
                repaint();
            }
        });
    }

    
}
