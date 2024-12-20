import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.*;

public class MainFrame extends JFrame {
    MainFrame() {
        super("NN Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 450));
        // this.setMaximumSize(new Dimension(300, 450));
        this.setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();

        SigmoidPanel sigmoidPanel = new SigmoidPanel();
        tabbedPane.addTab("Sigmoid", sigmoidPanel);

        VectorLengthPanel vectorLengthPanel = new VectorLengthPanel();
        tabbedPane.addTab("Len(vector)", vectorLengthPanel);

        FailureFunctionPanel failureFunctionPanel = new FailureFunctionPanel();
        tabbedPane.addTab("Failure func", failureFunctionPanel);

        BinaryActivationsPanel binaryActivationsPanel = new BinaryActivationsPanel();
        tabbedPane.addTab("Bin activs", binaryActivationsPanel);


        add(tabbedPane);
    }
}
