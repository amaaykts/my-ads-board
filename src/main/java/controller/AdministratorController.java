package controller;

import model.Category;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.MyService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdministratorController {

    @Autowired
    @Qualifier(value = "userService")
    private MyService userService;

    @Autowired
    @Qualifier(value = "categoryService")
    private MyService categoryService;

    @Autowired
    @Qualifier(value = "roleService")
    private MyService roleService;

    /**
     * Список пользователей, новые категории, новые роли
     */
    @RequestMapping("/admin")
    public String users(Model model) {
        model.addAttribute("users", getUsers());
        model.addAttribute("categories", getCategory());
        model.addAttribute("roles", getRoles());
        System.out.println("/admin");
        return "index";
    }

    /**
     * Заведение нового администратора
     */
    @RequestMapping("/admin/signup")
    public String signup(Model model) {
        System.out.println("/admin/signup");
        return "index";
    }

    /**
     * Заведение новой категории, нужно перекидывать сюда а отсюда на admins redirect
     */
    @RequestMapping("/admin/settings/add_category")
    public String addCategory(Model model) {
        System.out.println("/admin/settings/add_category");
        return "index";
    }

    /**
     * Заведение новой роли, нужно перекидывать сюда а отсюда на admins redirect
     */
    @RequestMapping("/admin/settings/add_role")
    public String addRole(Model model) {
        System.out.println("/admin/settings/add_role");
        return "index";
    }

    private List<Role> getRoles() {
        List<Role> roles = new ArrayList<Role>();
        for (Object o : roleService.list()) {
            roles.add((Role) o);
        }
        return roles;
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
