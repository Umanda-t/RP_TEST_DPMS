package com.example.dpms_research_sample;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import java.text.DecimalFormat;

public class Prediction_2_Accuracy {
    public float GetAccuracy() {
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("src/main/resources/static/datasets/d520.arff");
            Instances traindata = source.getDataSet();
            //set class index to last attribute
            traindata.setClassIndex(traindata.numAttributes() - 1);

            MultilayerPerceptron m = new MultilayerPerceptron();
            m.buildClassifier(traindata);


            // load new data set
            ConverterUtils.DataSource source1 = new ConverterUtils.DataSource("src/main/resources/static/datasets/d520.arff");
            Instances testdata = source1.getDataSet();
            //set class index to last attribute
            testdata.setClassIndex(testdata.numAttributes() - 1);


            Evaluation eval = new Evaluation(traindata);
            eval.evaluateModel(m, testdata);
            double Accuracy = eval.pctCorrect();
            DecimalFormat df = new DecimalFormat("#.##");
            String a = df.format(Accuracy);
            float AccuracyValue = Float.parseFloat(a);

            return AccuracyValue;

        } catch (Exception e) {
            return 0;
        }
    }

}
