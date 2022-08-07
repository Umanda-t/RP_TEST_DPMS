package com.example.dpms_research_sample;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.text.DecimalFormat;

public class Prediction_1_Accuracy {
    public float GetAccuracy() {
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("src/main/resources/static/datasets/d2k.arff");
            Instances traindata = source.getDataSet();
            //set class index to last attribute
            traindata.setClassIndex(traindata.numAttributes() - 1);

            //RandomForest
            RandomForest rf = new RandomForest();
            rf.buildClassifier(traindata);


            // load new data set
            ConverterUtils.DataSource source1 = new ConverterUtils.DataSource("src/main/resources/static/datasets/d2k.arff");
            Instances testdata = source1.getDataSet();
            //set class index to last attribute
            testdata.setClassIndex(testdata.numAttributes() - 1);


            Evaluation eval = new Evaluation(traindata);
            eval.evaluateModel(rf, testdata);
            final double error_rate = eval.errorRate();

            double Accuracy = ((1 - error_rate) * 100);
            DecimalFormat df = new DecimalFormat("#.##");
            String a=df.format(Accuracy);
            float AccuracyValue = Float.parseFloat(a);
            return AccuracyValue;
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
