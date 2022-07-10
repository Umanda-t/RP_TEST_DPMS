package com.example.dpms_research_sample;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.text.DecimalFormat;

public class Prediction_1_Model {
    public float GetPrediction(int a1,int a2, int a3,int a4,int a5,float a6,int a7) {
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("F:\\Weka/D_prediction1.arff");
            Instances traindata = source.getDataSet();
            //set class index to last attribute
            traindata.setClassIndex(traindata.numAttributes() - 1);

            //RandomForest
            RandomForest rf = new RandomForest();
            rf.buildClassifier(traindata);


            // load new data set
            ConverterUtils.DataSource source1 = new ConverterUtils.DataSource("F:\\Weka/D_prediction1.arff");
            Instances testdata = source1.getDataSet();
            //set class index to last attribute
            testdata.setClassIndex(testdata.numAttributes() - 1);


            // Create empty instance with three attribute values
            Instance inst = new DenseInstance(8);

// Set instance's values for the attributes

            inst.setValue(testdata.attribute(0), a1);
            inst.setValue(testdata.attribute(1), a2);
            inst.setValue(testdata.attribute(2), a3);
            inst.setValue(testdata.attribute(3), a4);
            inst.setValue(testdata.attribute(4), a5);
            inst.setValue(testdata.attribute(5), a6);
            inst.setValue(testdata.attribute(6), a7);


            inst.setDataset(testdata);


            //call classifyinstance which return adouble value for class
            double predict = rf.classifyInstance(inst);


            Evaluation eval = new Evaluation(traindata);
            eval.evaluateModel(rf, testdata);
            final double error_rate = eval.errorRate();

            double Accuracy = ((1 - error_rate) * 100);
            float Predict_PCT = (float) (predict * Accuracy);
            DecimalFormat df = new DecimalFormat("#.##");
            String pr=df.format(Predict_PCT);
            float PredictPCT = Float.parseFloat(pr);

            if (PredictPCT > 100) {
                return (float) 99.99;
            } else if (PredictPCT < 0) {
                return (float) 0.00;
            } else {
                return PredictPCT;
            }
        }
        catch (Exception e)
        {
        return 404;
        }
    }
}
