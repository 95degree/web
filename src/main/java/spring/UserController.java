package spring;

import db.DataBase;
import model.User;

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
