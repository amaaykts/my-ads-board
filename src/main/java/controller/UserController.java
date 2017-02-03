package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @RequestMapping("/user/add")
    public String add() {
        System.out.println("/user/add");
        return "index";
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    public String edit(@RequestParam int id, Model model) {
        System.out.println("/user/edit");
        return "index";
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public String delete(@RequestParam int id, Model model) {
        System.out.println("/user/delete");
        return "index";
    }

    @RequestMapping(value = "/user/settings", method = RequestMethod.GET)
    public String settings(@RequestParam int username, Model model) {
        System.out.println("/user/settings");
        return "index";
    }
}
