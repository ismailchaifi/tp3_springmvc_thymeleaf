package ma.enset.hospital.web;

import jakarta.validation.Valid;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.service.HospitalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HospitalServiceImpl service;

    @GetMapping("user/index")
    public String index(Model model,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "4") int size,
                        @RequestParam(value = "keyword", defaultValue = "") String kw) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(kw, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);
        return "patients";
    }

    @GetMapping("admin/delete")
    public String delete(@RequestParam(value = "id") String id,
                         @RequestParam(value = "keyword", defaultValue = "") String keyword,
                         @RequestParam(value = "page", defaultValue = "0") int page) {
        patientRepository.deletePatientById(id);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("admin/formPatients")
    public String formPatients(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @PostMapping(path = "admin/save")
    public String save(@Valid Patient patient,
                       BindingResult bindingResult,
                       @RequestParam(value = "keyword", defaultValue = "") String keyword,
                       @RequestParam(value = "page", defaultValue = "0") int page) {
        if (bindingResult.hasErrors()) return "formPatients";
        service.savePatient(patient);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("admin/edit")
    public String editPatient(Model model,
                              @RequestParam(value = "id") String id,
                              @RequestParam(value = "keyword", defaultValue = "") String keyword,
                              @RequestParam(value = "page", defaultValue = "0") int page) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Produit introuvable")
        );
        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPatient";
    }

}
