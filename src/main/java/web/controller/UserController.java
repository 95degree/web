package web.controller;

import web.db.DataBase;
import web.domain.User;
import web.spring.annotation.Controller;
import web.spring.annotation.GetMapping;
import web.spring.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String getUser() {
        return "index.html";
    }

    @GetMapping("/user/form")
    public String userForm(){
        return "user/form.html";
    }

    @PostMapping("/user")
    public String createUser(User user) {
        DataBase.addUser(user);
        return "redirect:/";
    }
}
