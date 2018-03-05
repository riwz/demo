package org.springframework.samples.app.person;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.app.person.Person;
import org.springframework.samples.app.person.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
class PersonController {

    private static final String VIEWS_PERSON_CREATE_OR_UPDATE_FORM = "persons/createOrUpdatePersonForm";

    @Autowired
    public PersonRepository persons;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/persons/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> entity) {
        Person person = new Person();
        entity.put("person", person);
        return VIEWS_PERSON_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/persons/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Person person) {
        this.persons.save(person);
        return "redirect:/persons/" + person.getId();
    }

    @GetMapping("/persons/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("person", new Person());
        return "persons/findPersons";
    }

    @GetMapping("/persons")
    public String processFindForm(Person person, Map<String, Object> model, BindingResult result) {
//        if (person.getLastName() == null) {
//            person.setLastName("");
//        }
        Collection<Person> results = this.persons.findByLastName(person.getLastName());

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "persons/findPersons";
        } else
            if (results.size() == 1) {
            person = results.iterator().next();
            return "redirect:/persons/" + person.getId();
        } else {
            model.put("selections", results);
            return "persons/personsList";
        }
    }

    @RequestMapping(value = "/persons/{personId}/edit", method = RequestMethod.GET)
    public String initUpdatePersonForm(@PathVariable("personId") UUID personId, Model entity) {
        Person person = this.persons.findById(personId);
        entity.addAttribute(person);
        return VIEWS_PERSON_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/persons/{personId}/edit", method = RequestMethod.POST)
    public String processUpdatePersonForm(@Valid Person person, BindingResult result, @PathVariable("personId") UUID personId) {
        person.setId(personId);
        this.persons.save(person);
        return "redirect:/persons/{personId}";
    }

    @RequestMapping("/persons/{personId}")
    public ModelAndView showPerson(@PathVariable("personId") UUID personId) {
        ModelAndView mav = new ModelAndView("persons/personDetails");
        mav.addObject(this.persons.findById(personId));
        return mav;
    }
}
