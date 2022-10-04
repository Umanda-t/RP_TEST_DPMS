package com.example.dpms_research_sample;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class Prediction_2_Model {

    public String getPrediction(int a1, int a2, int a3, int a4, int a5, int a6,
                                int a7, int a8, int a9, int a10, int a11, int a12,
                                int a13, int a14, int a15, int a16)
    {
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


            // Create empty instance with three attribute values
            Instance inst = new DenseInstance(17);

// Set instance's values for the attributes

            inst.setValue(testdata.attribute(0), a1);
            inst.setValue(testdata.attribute(1), a2);
            inst.setValue(testdata.attribute(2), a3);
            inst.setValue(testdata.attribute(3), a4);
            inst.setValue(testdata.attribute(4), a5);
            inst.setValue(testdata.attribute(5), a6);
            inst.setValue(testdata.attribute(6), a7);
            inst.setValue(testdata.attribute(7), a8);
            inst.setValue(testdata.attribute(8), a9);
            inst.setValue(testdata.attribute(9), a10);
            inst.setValue(testdata.attribute(10), a11);
            inst.setValue(testdata.attribute(11), a12);
            inst.setValue(testdata.attribute(12), a13);
            inst.setValue(testdata.attribute(13), a14);
            inst.setValue(testdata.attribute(14), a15);
            inst.setValue(testdata.attribute(15), a16);

            inst.setDataset(testdata);
            double predict = m.classifyInstance(inst);
            String predictvalue = testdata.classAttribute().value((int) predict);
            return predictvalue;
        }
        catch (Exception e)
        {
            return "error";
        }
    }



}
