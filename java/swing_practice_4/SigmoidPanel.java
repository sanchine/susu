import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SigmoidPanel extends JPanel {
    SigmoidPanel() {
        setLayout(new GridLayout(6, 1));
        setBackground(new Color(170, 170, 170));
        setSize(150, 150);
        
        add(new JLabel("Calculate sigmoid by z.", 0));

        JPanel textInput = new JPanel(new GridLayout(1, 2, 20, 20));
        textInput.setSize(150, 30);
        textInput.add(new JLabel("z = ", 0));
        JTextField textField = new JTextField();
        textField.setSize(100, 30);
        textInput.add(textField);
        add(textInput);


        JButton submitButton = new JButton("Click me");
        submitButton.setSize(50, 10);
        add(submitButton);


        JLabel answer = new JLabel("Answer: ");
        add(answer);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (textField.getText() == "") {
                    add(new JLabel("Empty string"));
                    return;
                }

                // try {
                double z = Double.parseDouble(textField.getText());
                MathResolver mr = new MathResolver();
                answer.setText("Answer: " + Double.toString(mr.calculateSigmoid(z)));
                revalidate();
                repaint();
                // } catch (Exception e) {
                // }
                
            }
        });


    }

    public JPanel getPanel() {
        return this;
    }
}
