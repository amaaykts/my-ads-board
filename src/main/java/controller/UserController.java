package controller;

import exceptions.LengthFieldsException;
import model.Advert;
import model.Category;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.MyService;

@Controller
public class UserController {
    @Autowired
    @Qualifier(value = "categoryService")
    private MyService categoryService;

    @Autowired
    @Qualifier(value = "advertService")
    private MyService advertService;

    @Autowired
    @Qualifier(value = "userService")
    private MyService userService;

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String add() {
        System.out.println("/user/add");
        return "add_ads.html";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addAds(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "nameUser") String userName,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "price") String price,
            @RequestParam(name = "category") String categoryName,
            Model model) {
        System.out.println("/user/add");
        Category category = (Category) categoryService.get(categoryName);
        try {
            Advert advert = new Advert(name, description, category, Double.valueOf(price));
            advertService.add(advert);
            User user = (User) userService.get(userName);
            user.getAdverts().add(advert);
            userService.update(user);

        } catch (LengthFieldsException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error");
            return "add_ads.html";
        }
        return "add_ads.html";
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
