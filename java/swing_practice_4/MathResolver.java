public class MathResolver {

    public double[] calculateNewPointByGradient(
        double[] oldPoint,
        double eta,
        double[] gradient
    ) {
        double[] newPoint = {0, 0};

        for (int i = 0; i < 2; ++i) {
            newPoint[i] = oldPoint[i] - eta * gradient[i];
        }

        return newPoint;
    }

    public double calculateVectorLength(double[] vector) {
        double vectorLength = 0;

        for (int i = 0; i < 2; ++i) {
            vectorLength += Math.pow(vector[i], 2);
        }

        return Math.sqrt(vectorLength);
    }

    public double solveMSE(double[] predicts, double[] y) {
        double sum = 0;
        for (int i = 0; i < 2; ++i) {
            sum += Math.pow((predicts[i] - y[i]), 2);
        }
        return sum / 4.0;
    }

    // public double calculateOutputLayerDelta(double a, double y) {

    // double dC_da = (a - y) / 2;

        // double delta = dC_da * a * (1 - a);
        // return delta;
    // }




    public double solveTask3(int epochs, double w, double b, double eta, double[] v) {
        double C = 0;

        for (int i = 0; i < epochs; ++i) {
            double a = calculateSigmoid(v[0] * w + b);

            // C = -1 * (v[1] * Math.log(a) + (1 - v[1]) * Math.log(1 - v[1]));
            // C = -1 * (v[1] * Math.log(a) + (Math.log(1 - v[1]) - v[1] * Math.log(1 - v[1])));
            C = -1 * Math.log(a);
        
            double dC_dw = v[0] * a - v[0];
            double dC_db = a - 1;

            w = w - eta * dC_dw;
            b = b - eta * dC_db;
        }

        return C;
    } 


    public double calculateFailureFunctionPrimeByWeight(
        double[][] precedents, double[][][] weights,
        double[][] activatons,
        int layer, int i, int j
    ) {


        double delta = activatons[layer-1][j] * Math.pow(activatons[layer-1][j], 2) * ((weights[layer][j][0] * (activatons[layer][0] - precedents[1][0]) * activatons[layer][0] * (1 - activatons[layer][0])) + weights[layer][j][1] * (activatons[layer][1] - precedents[1][1]) * activatons[layer][1] - Math.pow(activatons[layer][1], 2));


        return delta * precedents[layer][i];
    }

    public double calculateDelta(double a, double y) {
        return (a - y) * a * (1 - a);
    }


    // public static double calcuateNewWeight(
    //               double[][] precedents,
    //               double[][][] weights,
    //               double[][] activations,
    //               int layer,
    //               int from,
    //               int to
    // ) {
    //     double newWeight = 0;

    //     double a = activations[layer][to];

    //     double failureFunction = 2 * (activations[layer+1][from] - )

    //     // double 

    //     //double sigma = a * (1 - a) * (weights[layer][to][from];
    //       //            + )

    //     // double C_W_ij = a * (1 - a) * (weights[layer]);

    //     return newWeight;
    // }


    public double[][] feedForward(
                  double[][] precedents,
                  double[][][] weights,
                  double[][] biases
    ) {
        double[][] activations = {{0, 0}, {0, 0}};
        double layersNum = 2;

        for (int layer = 0; layer < layersNum; ++layer) {
            for (int j = 0; j < 2; ++j) {
                activations[layer][j] += calculateNeuronActivation(
                    layer == 0 ? precedents[0] : activations[layer-1],
                    weights[layer][j],
                    biases[layer][j]
                );
            }
        }

        return activations;
    }


    public double[] feedForward(
           double[] weights,
           double[] biases,
           double[] x,
           int size
    ) {

        double[] result = {0., 0., 0., 0.};

        for (int i = 0; i < size; ++i) {
            int z = 0;
            for (int j = 0; j < size; ++j) {
                z += weights[j] * x[i];
            }
            result[i] = z + biases[i];
        }

        return result;
    }


    public boolean[] calculateActivationsBinary(double[] a) {
        boolean[] result = new boolean[4]; 
        for (int i = 0; i < 4; ++i) {
            result[i] = (a[i] >= 0) ? true : false;
        }
        return result;
    }


    public int calculateIntByBinaryArray(boolean[] bin) {


        int result = 0;

        for (int i = bin.length - 1; i >= 0; --i) {
            result += (bin[i]) ? Math.pow(2, bin.length - i) : 0;
        }


        return result;
    } 


    
    public double calculateNeuronActivation(
        double[] precedent,
        double[] weights,
        double bias
    ) {
        double activation = 0;

        for (int i = 0; i < 2; ++i) {
            activation += weights[i] * precedent[i];
        }

        return this.calculateSigmoid(activation + bias); 
    }
    
    public double calculateSigmoid(double z) {
        return (double) 1 / (1 + Math.pow(2.71828, -1.0 * z));
    }
}
