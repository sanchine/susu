import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class FailureFunctionPanel extends JPanel {
    FailureFunctionPanel() {
        setLayout(new GridLayout(4, 1));
        setBackground(new Color(170, 170, 170));
        setSize(150, 350);
        
        JLabel title = new JLabel("Calculate loss.", 0);
        title.setSize(150, 100);
        add(title);

        String[][] inputData = {
            {"epochs", "2"},
            {"w", "1.9"},
            {"b", "-1"},
            {"eta", "0.7"},
            {"x", "0.5"},
            {"y", "1"},
        };

        Map<String, Double> dataMap = new HashMap<>();

        dataMap.put("epochs", 2.);
        dataMap.put("w", 1.9);
        dataMap.put("b", -1.0);
        dataMap.put("eta", 0.7);
        dataMap.put("x", 0.5);
        dataMap.put("y", 1.0);

        
        String[] columnNames = {"Names", "Values"};

        JTable dataTable = new JTable(inputData, columnNames);
        dataTable.setSize(100, 500);
        add(dataTable);


        JButton submitButton = new JButton("Click me");
        submitButton.setMinimumSize(new Dimension(150, 50));
        add(submitButton);

        JLabel answer = new JLabel("Answer: ");
        add(answer);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MathResolver mr = new MathResolver();

                double epochs = dataMap.get("epochs");
                double w = dataMap.get("w");
                double b = dataMap.get("b");
                double eta = dataMap.get("eta");
                double x = dataMap.get("x");
                double y = dataMap.get("y");

                double C = mr.solveTask3((int) epochs, w, b, eta, new double[]{x, y});
                String formattedAnswer = String.format("%.2f", C);
                answer.setText("Answer: " + formattedAnswer);
            }
        });

        dataTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent event) {
                // if (event.getType() == TableModelEvent.UPDATE && event.getColumn() == 0)
                if (event.getType() == TableModelEvent.UPDATE && event.getColumn() != 0) {
                    int row = event.getLastRow();
                    int column = event.getColumn();
                    
                    String value = (String) dataTable.getModel().getValueAt(row, column);

                    String key = (String) dataTable.getModel().getValueAt(row, column - 1);
                    System.out.println(key);
                    dataMap.put(key, Double.parseDouble(value));
                }
            }
        });
    }
}
