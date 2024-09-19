package com.hard.cegAndranovelona.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hard.cegAndranovelona.function.Function;
import com.hard.cegAndranovelona.modeles.Administrateur;
import com.hard.cegAndranovelona.service.AdministrateurService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Index {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private AdministrateurService administrateurService;
    
    @GetMapping("/loginAdmin")
    public String getLoginAdmin() {
        return "loginAdmin";
    }
    
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/logonAdmin")
    public String logOnAdmin(@RequestParam String  authentification,@RequestParam String password){
        password=Function.hashPassword(password);
        Administrateur administrateur=administrateurService.login(authentification, password);
        if(administrateur==null){
            return "redirect:/loginAdmin?msg=Vous n'etes pas un Administrateur";
        }
        httpSession.setAttribute("admin", administrateur);
        return "redirect:/";
    }

    @GetMapping("/crypte")
    public String cryptage(){
        String mdp="12345";
        String hash=Function.hashPassword(mdp);
        System.out.println(hash);
        return hash;
    }
}

