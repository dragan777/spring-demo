package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;
    private final TemplateEngine templateEngine;
    @Autowired
    public PersonController(PersonService personService, TemplateEngine templateEngine) {
        this.personService = personService;
        this.templateEngine = templateEngine;
    }
    @PostMapping
    private void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }
    @GetMapping
    private List<Person> getAllPersons(){
        return personService.getPersons();
    }


    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping(path = "/send")
    public int sendEmail(@RequestBody Person person) {

        /*SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("draganvelkovski77@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");*/

        Context context = new Context();
        context.setVariable("message", "tesdasda");
        String content = templateEngine.process("mailTemplate", context);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("testt75432@gmail.com");
            messageHelper.setTo("draganvelkovski77@gmail.com");
            messageHelper.setSubject("Sample mail subject");
            messageHelper.setText(content, true);
        };

        javaMailSender.send(messagePreparator);
        return 1;

    }
}
