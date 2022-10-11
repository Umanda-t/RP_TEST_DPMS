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
public class PT_Controller {

    @Autowired
    private PTRepository ptrepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PT_Service pservice;

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

    @GetMapping("/PTAR")
    public String viewPTARPage(Model model,@CurrentSecurityContext(expression="authentication?.name")String un){
        un = ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);
        int count=ptrepo.PFindcount(user);
        if (count==0)
        {
            return "redirect:/PTDU";
        }
        else {
            return PTAllRecordsPage(model, 1, un);
        }
    }


    @GetMapping("/ppage/{pageNum}")
    public String PTAllRecordsPage(Model model, @PathVariable(name = "pageNum") int pageNum, String un) {

        un = ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);
        int S_count=ptrepo.PFindcount(user);
        int D_count=ptrepo.PDFindcount(user);

        float S_sum=ptrepo.PFindSum(user);
        float D_sum=ptrepo.PDFindSum(user);

        float avg1=S_sum/S_count;

        float avg2=D_sum/D_count;

        model.addAttribute("avg1", avg1);
        model.addAttribute("avg2", avg2);

        Page<PT> page =  pservice.pfindlist(pageNum,user);
        List<PT> listPT =page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listPT", listPT);

        return "/PTAR";

    }
    @GetMapping("/pdelete/{id}")
    public String pdeleteRecord(@PathVariable(name = "id") int id) {
        pservice.pdelete(id);
        return "redirect:/PTAR";
    }
    @GetMapping("/pedit/{id}")
    public ModelAndView pshowEditPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("PT_edit");
        PT pt = pservice.pget(id);
        mav.addObject("pt", pt);

        return mav;
    }
    @PostMapping("/psave")
    public String psaveProduct(@ModelAttribute("pt") PT pt) {
        pservice.psave(pt);

        return "redirect:/PTAR";
    }

    @GetMapping("/PT_pdf")
    public void p_exportToPDF(HttpServletResponse response, @CurrentSecurityContext(expression="authentication?.name")String un) throws DocumentException, IOException {
        un = ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
        User user= userRepo.findByUsername(un);

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=PT_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<PT> listPT = pservice.plistAll(user);

        PT_PDFExporter exporter = new PT_PDFExporter(listPT);
        exporter.export(response);

    }
}
