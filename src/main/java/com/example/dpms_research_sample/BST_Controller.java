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
    public String processRegister(@ModelAttribute("bst")BST bst) {
        bstrepo.save(bst);
        System.out.println(bst);
        return "update_success";
    }

    @GetMapping("/BSTAR")
    public String BSTAllRecordsPage(Model model,@CurrentSecurityContext(expression="authentication?.name")String un) {
        un = ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);
        List<BST> listBST = bstrepo.search(user);
       // List<BST> listBST = service.listAll(u);
        model.addAttribute("listBST", listBST);

        return "/BSTAR";
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
