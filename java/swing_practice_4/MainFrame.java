import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;




public class MainFrame extends JFrame {
    MainFrame() {
        super("Main frame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.setLayout(new GridLayout(2, 2));

        Color[] COLORS = {new Color(170, 170, 170), new Color(200, 200, 200)};





        MathResolver mr = new MathResolver();

        JPanel sigmoidPanel = new JPanel();
        sigmoidPanel.setLayout(new FlowLayout());
        sigmoidPanel.setBackground(COLORS[0]);

        JLabel taskLabel = new JLabel("Calculate sigmoid by z = ");
        JTextField potentialValueTF = new JTextField(10);
        JButton calculateBtn = new JButton("Click");

        sigmoidPanel.add(taskLabel);
        sigmoidPanel.add(potentialValueTF);
        sigmoidPanel.add(calculateBtn);

        add(sigmoidPanel);








        JPanel vectorLengthPanel = new JPanel();
        vectorLengthPanel.add(new JLabel("Calculate ||(y1, y2)|| : "));
        JTextField vectorLengthFirstTF = new JTextField(4);
        JTextField vectorLengthSecondTF = new JTextField(4);
        JButton calculateVectorLengthButton = new JButton("Click");
        vectorLengthPanel.setBackground(COLORS[1]);
        vectorLengthPanel.add(new JLabel("y1 = "));
        vectorLengthPanel.add(vectorLengthFirstTF);
        vectorLengthPanel.add(new JLabel("y2 = "));
        vectorLengthPanel.add(vectorLengthSecondTF);
        vectorLengthPanel.add(calculateVectorLengthButton);

        add(vectorLengthPanel);






        JPanel failureFunctionPanel = new JPanel();
        failureFunctionPanel.setBackground(COLORS[0]);
        this.addComponentToPanel(failureFunctionPanel, new JLabel("Calculate failure function value\nEnter origins:\n"));
        
        String[][] failureData = {
            {"epochs", "2"},
            {"w", "1.9"},
            {"b", "-1"},
            {"eta", "0.7"},
            {"x", "0.5"},
            {"y", "1"},
        };

        Map<String, Double> failureDataMap = new HashMap<>();

        failureDataMap.put("epochs", 2.);
        // failureDataMap.put("w", 0.);
        // failureDataMap.put("b", 0.);
        // failureDataMap.put("eta", 0.);
        // failureDataMap.put("x", 0.);
        // failureDataMap.put("y", 0.);
        failureDataMap.put("w", 1.9);
        failureDataMap.put("b", -1.0);
        failureDataMap.put("eta", 0.7);
        failureDataMap.put("x", 0.5);
        failureDataMap.put("y", 1.0);

        
        String[] columnNames = {"Names", "Values"};

        JTable failureDataTable = new JTable(failureData, columnNames);

        this.addComponentToPanel(failureFunctionPanel, failureDataTable);

        JButton calculateFailureFunctionButton = new JButton("Click to solve");
        this.addComponentToPanel(failureFunctionPanel, calculateFailureFunctionButton);


        add(failureFunctionPanel);










        JPanel prevTestPanel = new JPanel(new GridLayout(2, 1));

        JPanel binaryNeuronPanel = new JPanel();

        JTextField weightsTF = new JTextField(8);
        JTextField biasesTF = new JTextField(8);
        JTextField inputDataTF = new JTextField(8);


        JButton calculateBinaryNeuronButton = new JButton("Click me");

        addComponentToPanel(binaryNeuronPanel, new JLabel("Input x"));
        addComponentToPanel(binaryNeuronPanel, inputDataTF);



        addComponentToPanel(binaryNeuronPanel, new JLabel("Weights"));
        addComponentToPanel(binaryNeuronPanel, weightsTF);

        addComponentToPanel(binaryNeuronPanel, new JLabel("Biases"));
        addComponentToPanel(binaryNeuronPanel, biasesTF);

        
        addComponentToPanel(prevTestPanel, binaryNeuronPanel);

        addComponentToPanel(binaryNeuronPanel, calculateBinaryNeuronButton);



        add(prevTestPanel);




        calculateBinaryNeuronButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                double[] weights = parseToDoubleArray(weightsTF.getText());
                double[] biases = parseToDoubleArray(biasesTF.getText());
                double[] x = parseToDoubleArray(inputDataTF.getText());

                double[] predicts = mr.feedForward(weights, biases, x, 4);

                boolean[] binary = mr.calculateActivationsBinary(predicts);
                int result = mr.calculateIntByBinaryArray(binary);

                addComponentToPanel(binaryNeuronPanel, new JLabel(Integer.toString(result)));
            }
        });

        
        failureDataTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent event) {
                if (event.getType() == TableModelEvent.UPDATE) {
                    int row = event.getLastRow();
                    int column = event.getColumn();
                    
                    String value = (String) failureDataTable.getModel().getValueAt(row, column);

                    String key = (String) failureDataTable.getModel().getValueAt(row, column - 1);
                    System.out.println(key);
                    failureDataMap.put(key, Double.parseDouble(value));
                }
            }
        });


        calculateFailureFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                double epochs = failureDataMap.get("epochs");
                double w = failureDataMap.get("w");
                double b = failureDataMap.get("b");
                double eta = failureDataMap.get("eta");
                double x = failureDataMap.get("x");
                double y = failureDataMap.get("y");

                double C = mr.solveTask3((int) epochs, w, b, eta, new double[]{x, y});

                System.out.println(C);
                addComponentToPanel(failureFunctionPanel, new JLabel(Double.toString(C)));
            }
        });

        calculateVectorLengthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                double x1 = Double.parseDouble(vectorLengthFirstTF.getText());
                double x2 = Double.parseDouble(vectorLengthSecondTF.getText());
                
                
                double vectorLengthValue = 
                       mr.calculateVectorLength(new double[]{x1, x2});



                addComponentToPanel(
                    vectorLengthPanel,
                    new JLabel(Double.toString(vectorLengthValue))
                );

                updateFrameComponents();
            }
        });

        calculateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String text = potentialValueTF.getText();
                
                double potentialValue = Double.parseDouble(text);

                double sigmoidValue = mr.calculateSigmoid(potentialValue);



                addComponentToPanel(sigmoidPanel, new JLabel(Double.toString(sigmoidValue)));

                updateFrameComponents();
            }
        });
    }


    private void updateFrameComponents() {
        this.revalidate();
        this.repaint();
    }

    public double getDoubleFromTextField(JTextField textField) {
        return Double.parseDouble(textField.getText());
    }

    
    private void addComponentToPanel(JPanel panel, Component component) {
        panel.add(component);
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
