import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;

public class Perceptron {
    private final double[] weights;
    private final double[][] trainX;
    private final double[][] testX;
    private final int[] yD;

    private final double learningRate = new Random().nextDouble();

    public Perceptron(int N,double[][] trainX, double[][] testX ,int[] yD) {
        weights = DoubleStream.generate(() -> new Random().nextDouble()).limit(N).toArray();
        this.trainX = trainX;
        this.testX = testX;
        this.yD = yD;
    }

    private double addition(double[] x){
        double addition = 0;
        for (int j = 0; j < this.weights.length; j++) {
            addition += this.weights[j] * x[j];
        }
        return addition;
    }


    public void train(){
        int counter = 0;
        double y;
        double error;
        while (counter<this.trainX.length){
            y = addition(trainX[counter]);

            if(y  >= 0){
                y = 1;
            }else{
                y = -1;
            }

            error = this.yD[counter] - y;

            if (error==0){
                counter += 1;
            }else {
                updateWeights(error, counter);
                //System.out.println("a");
                counter = 0;
            }
        }
        System.out.println("Weights : "+Arrays.toString(this.weights));
    }

    public void updateWeights(double error, int counter){
        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i] += this.learningRate * error * this.trainX[counter][i];
        }
    }

    public void evaluate(){
        for (double[] x:this.testX){
            double y = addition(x);

            if(y>=0){
                y=1;
            } else {
                y=-1;
            }
            
            if(y == 1){
                System.out.println("Iris-setosa");
            }else{
                System.out.println("Iris-versicolor");
            }

        }
    }


}