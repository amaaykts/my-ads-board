package controller;

import exceptions.IllegalSymbolException;
import exceptions.LengthFieldsException;
import exceptions.SpaceFieldsException;
import exceptions.ValidateEmailFailException;
import model.Category;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.MyService;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        return "admin.html";
    }

    /**
     * Заведение нового администратора
     */
    @RequestMapping(value = "/admin/signup", method = RequestMethod.POST)
    public String signupRegistration(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "role") String roleName,
            @RequestParam(name = "dateOfBirth") String dateOfBirth,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "repeatPassword") String repeatPassword,
            Model model) {
        System.out.println("/admin/signup");
//        System.out.println(role);
//        System.out.println(name);
        if (password.equals(repeatPassword)) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateOfBirth);
                Role role = (Role) roleService.get(roleName);
                User user = new User(name, surname, email, date, role, password);
                userService.add(user);
                model.addAttribute("success", "Added a new administrator: " + name);

            } catch (
                    LengthFieldsException |
                            IllegalSymbolException |
                            ValidateEmailFailException |
                            SpaceFieldsException |
                            ParseException e) {
                e.printStackTrace();
                model.addAttribute("error", "Incorrect data!");
                return "admin_registration.html";
            }
        } else {
            model.addAttribute("error", "Passwords do not match!");
            return "admin_registration.html"; //Пароли не совпадают
        }

        return "admin_registration.html";
    }

    /**
     * Заведение нового администратора
     */
    @RequestMapping(value = "/admin/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        List<Role> list = getRoles();
        model.addAttribute("list", list);
        return "admin_registration.html";
    }

    /**
     * Заведение новой категории, нужно перекидывать сюда а отсюда на admins redirect
     */
    @RequestMapping(value = "/admin/settings/add_category", method = RequestMethod.POST)
    public String addCategory(@RequestParam(name = "name") String name, Model model) {
        System.out.println("/admin/settings/add_category");

//        TODO Добавляем категорию
        Category category = new Category(name);
        categoryService.add(category);
        model.addAttribute("success", "Added a new category: " + name);
        return "admin_registration.html";
    }

    /**
     * Заведение новой роли, нужно перекидывать сюда а отсюда на admins redirect
     */
    @RequestMapping(value = "/admin/settings/add_role", method = RequestMethod.POST)
    public String addRole(
            @RequestParam(name = "name") String name,
            Model model) {
        System.out.println("/admin/settings/add_role");

//        TODO Добавляем роль
//        Role role = new Role(name);
//        roleService.add(role);
        model.addAttribute("success", "Added a new role: " + name);
        System.out.println(name);
        return "admin_registration.html";
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
