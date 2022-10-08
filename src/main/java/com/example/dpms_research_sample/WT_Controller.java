package com.example.dpms_research_sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WT_Controller {

    @Autowired
    private WTRepository wtrepo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/W_Tracker")
    public String W_TrackerPage() {
        return "WT";
    }

    @GetMapping("/WTDU")
    public String WTDUPage(Model model, @CurrentSecurityContext(expression="authentication?.name")String un) {
        un = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);
        WT wt = new WT();
        wt.setUsername(user);
        model.addAttribute("wt", wt);
        return "WTDU";
    }
    @PostMapping("/wt_add_update")
    public String WT_processRegister(@ModelAttribute("wt")WT wt, Model model) {
        wtrepo.save(wt);
        System.out.println(wt);
        float w=wt.getWeight();
        double h=wt.getHeight();
        double h2=h*h;
        double bmi=w/h2;

        model.addAttribute("bmi", bmi);

            if(bmi<18.5)
            {
                model.addAttribute("m", "You are in the underweight range. ");
            }
            else if (bmi>=18.5 && bmi<25.0)
            {
                model.addAttribute("m", "You are in the healthy weight range.");
            }
            else if (bmi>=25.0 && bmi<30.0)
            {
                model.addAttribute("m", "You are in the overweight range.");
            }
            else if (bmi>=30.0)
            {
                model.addAttribute("m", "You are in the obese range. Please try to follow healthy life style and have healthy meals.");
            }


            return "WT_update_success";
    }
}
