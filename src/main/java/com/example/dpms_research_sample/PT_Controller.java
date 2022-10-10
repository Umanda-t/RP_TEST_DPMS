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
public class PT_Controller {

    @Autowired
    private PTRepository ptrepo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/P_Tracker")
    public String P_TrackerPage() {
        return "PT";
    }

    @GetMapping("/PTDU")
    public String PTDUPage(Model model, @CurrentSecurityContext(expression="authentication?.name")String un) {
        un = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);
        PT pt = new PT();
        pt.setUsername(user);
        model.addAttribute("pt", pt);
        return "PTDU";
    }

    @PostMapping("/pt_add_update")
    public String PTprocessRegister(@ModelAttribute("pt")PT pt, Model model) {
        ptrepo.save(pt);
        System.out.println(pt);
        float s=pt.getSystolic();
        float d=pt.getDiastolic();

            if(s<120)
            {
                model.addAttribute("m1", "Your Systolic Blood Pressure Level is Normal.");
            }
            else if (s>=120 && s<130)
            {
                model.addAttribute("m1", "You have elevated Systolic Blood Pressure.");
            }
            else if (s>=130 && s<140)
            {
                model.addAttribute("m1", "Your Systolic Blood Pressure is in Stage 1 high blood pressure level ( stage 1 hypertension).");
            }
            else if (s>=140 && s<180)
            {
                model.addAttribute("m1", "Your Systolic Blood Pressure is in Stage 2 high blood pressure level ( stage 2 hypertension).");
            }
            else if (s>=180)
            {
                model.addAttribute("m1", "Your Systolic Blood Pressure level is in Hypertensive crisis. Please get consult your doctor immediately.");
            }


            if(d<80)
            {
                model.addAttribute("m2", "Your Diastolic Blood Pressure Level is Normal.");
            }
            else if (d>=80 && d<90)
            {
                model.addAttribute("m2", "Your Diastolic Blood Pressure is in Stage 1 high blood pressure level ( stage 1 hypertension).");
            }
            else if (d>=90 && d<120)
            {
                model.addAttribute("m2", "Your Diastolic Blood Pressure is in Stage 2 high blood pressure level ( stage 2 hypertension)");
            }
            else if (d>=120)
            {
                model.addAttribute("m2", "Your Diastolic Blood Pressure level is in Hypertensive crisis. Please get consult your doctor immediately");
            }

        return "PT_update_success";
    }


}
