package com.example.dpms_research_sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Prediction_Controller {
    @GetMapping("/Perdiction_1")
    public String viewePerdiction_1Page(Model model) {
        model.addAttribute("p1", new Prediction_1());
        return "Perdiction_1";
    }
    @PostMapping("/Get_Prediction_1")
    public String processGet_Prediction_1(@ModelAttribute("p1")Prediction_1 p1,Model model)  {
         int Pregnancies=p1.getPregnancies();
         int Glucose=p1.getGlucose();
         int BloodPressure=p1.getBloodPressure();
         int SkinThickness=p1.getSkinThickness();
         int Insulin=p1.getInsulin();
        int Age= p1.getAge();
         float BMI= p1.getBMI();

         Prediction_1_Model p=new Prediction_1_Model();
         float result1=p.GetPrediction(Pregnancies,Glucose,BloodPressure,SkinThickness,Insulin,BMI,Age);
         Prediction_1_Accuracy a=new Prediction_1_Accuracy();
         float accuracy=a.GetAccuracy();
        model.addAttribute("result1", result1);
        model.addAttribute("accuracy", accuracy);
         return "Perdiction_1_Results";

    }
}
