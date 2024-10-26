import javax.swing.*;

public class NetworkCalculatorApp {
    public static void main(String[] args) {
        JFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        MathResolver mr = new MathResolver();

        double[][] precedents = {
            {5, 3},    // (x1, x2)
            {0.5, 0.9} // (y1, y2)
        };

        double[][][] weights = {
            {
                {0.3,  // w2_11
                -0.5}, // w2_12
                {-0.2, // w2_21
                0.4},  // w2_22
            },
            {
                {-0.1, // w2_11
                0.6},  // w3_12
                {0.1,  // w3_21
                -0.3}  // w3_22
            }
        };

        double[][] biases = {
            {0.2, -0.3}, // (b2_w1, b2_w2)
            {-0.5, 0.2}  // (b3_w1, b3_w2)
        };




        // double[][] predicts = mr.feedForward(precedents, weights, biases);
        // double MSE = mr.solveMSE(predicts[1], precedents[1]);
        // double failure = mr.calculateFailureFunctionPrimeByWeight(precedents, weights, predicts, 1, 0, 1);
        // System.out.println(weights[1][1][0] - (-0.1) * failure);
        
        // for (int i = 0; i < 2; ++i) {
        //     System.out.println(predicts[i][1]);
        // }






        // double[] oldPoint = {9.0, 9.0};
        // double[] vector = mr.calculateNewPointByGradient(oldPoint, 0.4, new double[]{133.0, 96.0});
        // double vectorLength = mr.calculateVectorLength(vector);
        // System.out.println(vectorLength);






        // for (int i = 0; i < 2; ++i) {
        //     System.out.println(vector[i]);
        // }
        // System.out.println(vectorLength);



        // double[][] predicts = mr.feedForward(precedents, weights, biases);


        // // double a = mr.calculateNeuronActivation(new double[]{0.55, 0.475}, new double[]{0.1, -0.3}, 0.2);
        // double a = mr.calculateNeuronActivation(new double[]{0.55, 0.475}, new double[]{0.1, -0.3}, 0.2);
        // double failure = mr.calculateFailureFunctionPrimeByWeight(precedents, weights, predicts, 1, 0, 0);
        // System.out.println(a);
        // System.out.println(failure);


    }
}