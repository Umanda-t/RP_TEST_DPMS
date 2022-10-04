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

    @GetMapping("/Perdiction_2")
    public String viewePerdiction_2Page(Model model) {
        model.addAttribute("p2", new Prediction_2());
        return "Perdiction_2";
    }
    @PostMapping("/Get_Prediction_2")
    public String processGet_Prediction_2(@ModelAttribute("p2")Prediction_2 p2,Model model){
         int age=p2.getAge();
         int gender=p2.getGender();
         int polyuria= p2.getPolyuria();
         int polydipsia= p2.getPolydipsia();
         int weight_loss=p2.getWeight_loss();
         int weakness=p2.getWeakness();
         int polyphagia=p2.getPolyphagia();
         int genital_thrush=p2.getGenital_thrush();
         int visual_blurring=p2.getVisual_blurring();
         int itching=p2.getItching();
         int irritability=p2.getIrritability();
        int delayed_healing=p2.getDelayed_healing();
        int partial_paresis=p2.getPartial_paresis();
         int muscle_stiffness=p2.getMuscle_stiffness();
        int alopecia=p2.getAlopecia();
        int obesity=p2.getObesity();

        Prediction_2_Model p=new Prediction_2_Model();
         String result2=p.getPrediction(age,gender,polyuria, polydipsia, weight_loss, weakness,
         polyphagia,genital_thrush,visual_blurring,itching,irritability,delayed_healing,
         partial_paresis,muscle_stiffness,alopecia,obesity);

        Prediction_2_Accuracy a=new Prediction_2_Accuracy();
        float accuracy=a.GetAccuracy();
        if(result2.equals("Positive"))
        {
            String m="According to the answers you have given, you have possibility to have diabetes in the future. " +
                    "You can check how much risk you have from Diabetes Risk Prediction. " +
                    "There is no need to worry about that much but you need to follow healthy life style and eat healthy foods." +
                    " It is great if you can follow proper workout plan. " +
                    "By following healthy life style and eat healthy foods you can reduce the risk.";
            model.addAttribute("m", m);
        }
        else
        {
            String m="According to the answers you have given, you have less possibility to have diabetes in the future. " +
                    "By following healthy life style and eat healthy foods you can reduce the risk.";
            model.addAttribute("m", m);
        }

        model.addAttribute("accuracy", accuracy);
        return "Perdiction_2_Results";

    }
}
