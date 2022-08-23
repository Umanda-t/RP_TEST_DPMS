package com.example.dpms_research_sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dpms_research_sample.CustomUserDetails;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class BST_Controller {
    @Autowired
    private BSTRepository bstrepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BST_Service service;

    @GetMapping("/BS_Tracker")
    public String BS_TrackerPage() {
        return "BST";
    }
    @GetMapping("/BSTDU")
    public String BSTDUPage(Model model, @CurrentSecurityContext(expression="authentication?.name")String un) {
        un = ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);
        BST bst = new BST();
        bst.setUsername(user);
        model.addAttribute("bst", bst);
       return "BSTDU";
    }

    @PostMapping("/add_update")
    public String processRegister(@ModelAttribute("bst")BST bst,Model model) {
        bstrepo.save(bst);
        System.out.println(bst);
        float BS=bst.getBloodsugar();
        String P=bst.getPeriod();
        if(P.equals("Before Breakfast")||P.equals("Before Lunch")||P.equals("Before Dinner"))
        {
            if(BS<=69.99)
          {
              model.addAttribute("m", "Your Blood Sugar Level is very low.");
          }
            else if (BS>=70.00 && BS<=99.99)
          {
              model.addAttribute("m", "Your Blood Sugar Level is normal.");
          }
            else if (BS>=100 && BS<=125.99)
          {
              model.addAttribute("m", "Your Blood Sugar Level is bit high.");
          }
            else if (BS>=126.00)
          {
              model.addAttribute("m", "Your Blood Sugar Level is very high. Please try to follow healthy life style and have healthy meals.");
          }
        }
        else
        {
            if(BS<=69.99)
            {
                model.addAttribute("m", "Your Blood Sugar Level is very low.");
            }
            else if (BS>=70.00 && BS<140.00)
            {
                model.addAttribute("m", "Your Blood Sugar Level is normal.");
            }
            else if (BS>=140.00 && BS<200.00)
            {
                model.addAttribute("m", "Your Blood Sugar Level is bit high.");
            }
            else if (BS>=200.00)
            {
                model.addAttribute("m", "Your Blood Sugar Level is very high. Please try to follow healthy life style and have healthy meals.");
            }
        }
        return "update_success";
    }

    @GetMapping("/BSTAR")
    public String BSTAllRecordsPage(Model model,@CurrentSecurityContext(expression="authentication?.name")String un) {
        un = ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);
        int count=bstrepo.Findcount(user);
        if (count==0)
         {
             return "redirect:/BSTDU";
         }
         else{
             List<BST> listBST = bstrepo.search(user);
             float sum=bstrepo.FindSum(user);
             float avg=sum/count;

        model.addAttribute("listBST", listBST);
        model.addAttribute("avg", avg);

            if(avg<126.00)
            {
                model.addAttribute("m", "Your Average Blood Sugar Level is normal");
            }
            else
            {
                model.addAttribute("m", "Your Average Blood Sugar Level is high. Please try to follow healthy life style and have healthy meals.");
            }
        return "/BSTAR";
         }
    }
    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/BSTAR";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEditBTSPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_BST");
        BST bst = service.get(id);
        mav.addObject("bst", bst);

        return mav;
    }
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("bst") BST bst) {
        service.save(bst);

        return "redirect:/BSTAR";
    }

}
