package com.example.healthassistant.controller.view;

import com.example.healthassistant.model.PersonalHealthVitals;
import com.example.healthassistant.service.PersonalHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonalHealthController {
    @Autowired
    PersonalHealthService personalHealthService;

    @GetMapping("/PersonalHealth/{id}")
    public String showPersonalHealth(@PathVariable Long id, Model model){
        Optional<PersonalHealthVitals> personalHealth = personalHealthService.findPersonalHealthByID(id);
        model.addAttribute("personalHealth", personalHealth);
        return "personal";
    }

    @GetMapping("/showNewPersonalHealthForm")
    public String showNewPersonalHealthForm(Model model){
        PersonalHealthVitals personalHealth = new PersonalHealthVitals();
        model.addAttribute("personalHealth", personalHealth);
        return "health-create";
    }

    @PostMapping("/savePersonalHealth")
    public String savePersonalHealth(@ModelAttribute("personalHealth") PersonalHealthVitals personalHealth) {
        personalHealthService.savePersonalHealth(personalHealth);
        return "redirect:/personal";
    }

    @GetMapping("/showFormForUpdate2/{id}")
    public String showFormForUpdate2(@PathVariable(value = "id") long id, Model model) {
        Optional<PersonalHealthVitals> personalHealth = personalHealthService.findPersonalHealthByID(id);
        model.addAttribute("personalHealth", personalHealth);
        return "health-edit";
    }

    @GetMapping("/deletePersonalHealth/{id}")
    public String deletePersonalHealth(@PathVariable (value = "id") long id) {
        this.personalHealthService.deletePersonalHealth(id);
        return "redirect:/personal";
    }
}
