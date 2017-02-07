package controller;

import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import exceptions.LengthFieldsException;
import exceptions.ValidateEmailFailException;
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

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Список пользователей, новые категории, новые роли
     */
    @RequestMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", getUsers());
        System.out.println("users");
        return "personal.html";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String add(Model model) {
        System.out.println("/user/add");
        List<User> users = getUsers();
        model.addAttribute("users", users);
        List<Category> categories = getCategory();
        model.addAttribute("categories", categories);
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
        return "redirect:/";
    }


    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    public String edit(@RequestParam int id, Model model) {
        System.out.println(id);
        System.out.println("/user/edit");
        Advert advert = (Advert) advertService.get(id);
        model.addAttribute("advert", advert);
        return "advert_edit.html";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String update(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String description,
            Model model) {
        Advert advert = (Advert) advertService.get(id);
        advert.setName(name);
        advert.setDescription(description);
        advertService.update(advert);

        return "redirect:/";
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public String delete(@RequestParam int id, Model model) {
        advertService.delete(id);
//        System.out.println(id);
//        System.out.println("/user/settings/delete");
        return "admin.html";
    }


    @RequestMapping(value = "/user/settings", method = RequestMethod.GET)
    public String settings(@RequestParam String username, Model model) {
        System.out.println("/user/settings");
        User user = (User) userService.get(username);
        model.addAttribute("user", user);
        return "user.html";
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        for (Object o : userService.list()) {
            users.add((User) o);
        }
        return users;
    }

    private List<Category> getCategory() {
        List<Category> categories = new ArrayList<Category>();
        for (Object o : categoryService.list()) {
            categories.add((Category) o);
        }
        return categories;
    }
}
