package com.example.dpms_research_sample;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class WT_Controller {

    @Autowired
    private WTRepository wtrepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WT_Service wservice;

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

    @GetMapping("/WTAR")
    public String viewWTARPage(Model model,@CurrentSecurityContext(expression="authentication?.name")String un){
        un = ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);
        int count=wtrepo.WFindcount(user);
        if (count==0)
        {
            return "redirect:/WTDU";
        }
        else {
            return WTAllRecordsPage(model, 1, un);
        }
    }

    @GetMapping("/wpage/{pageNum}")
    public String WTAllRecordsPage(Model model, @PathVariable(name = "pageNum") int pageNum, String un) {

        un = ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);
        int count=wtrepo.WFindcount(user);


        float sum=wtrepo.WFindSum(user);
        float avg=sum/count;


        model.addAttribute("avg", avg);



        Page<WT> page =  wservice.findlist(pageNum,user);
        List<WT> listWT =page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listWT", listWT);

        return "/WTAR";

    }

    @GetMapping("/wdelete/{id}")
    public String deleteRecord(@PathVariable(name = "id") int id) {
        wservice.wdelete(id);
        return "redirect:/WTAR";
    }
    @GetMapping("/wedit/{id}")
    public ModelAndView showEditBTSPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("WT_edit");
        WT wt = wservice.wget(id);
        mav.addObject("wt", wt);

        return mav;
    }

    @PostMapping("/wsave")
    public String saveProduct(@ModelAttribute("wt") WT wt) {
        wservice.wsave(wt);

        return "redirect:/WTAR";
    }

    @GetMapping("/WT_pdf")
    public void exportToPDF(HttpServletResponse response, @CurrentSecurityContext(expression="authentication?.name")String un) throws DocumentException, IOException {
        un = ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=WT_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<WT> listWT = wservice.wlistAll(user);

        WT_PDFExporter exporter = new WT_PDFExporter(listWT);
        exporter.export(response);

    }
}
