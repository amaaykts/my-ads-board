package controller;

import exceptions.IllegalSymbolException;
import exceptions.LengthFieldsException;
import exceptions.SpaceFieldsException;
import exceptions.ValidateEmailFailException;
import model.Advert;
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

import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    @Qualifier(value = "advertService")
    private MyService advertService;

    @Autowired
    @Qualifier(value = "roleService")
    private MyService roleService;

    @Autowired
    @Qualifier(value = "userService")
    private MyService userService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Advert> adverts = getAdverts();
//        for (Advert a : adverts) {
//            System.out.println(a);
//        }
        System.out.println("/");
        model.addAttribute("adverts", adverts);
        return "index.html";
    }

    @RequestMapping("/404")
    public String badRequest(){
        return "404.html";
    }

    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public String ads(@RequestParam int id, Model model) {

        Advert advert = (Advert) advertService.get(id);
        System.out.println("/ads");
        model.addAttribute("ads", advert);

        model.addAttribute("author", getUser(advert.getId()));
        return "ads.html";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        List<Advert> adverts = getAdverts();
        for (Advert a : adverts) {
            System.out.println(a);
        }
        System.out.println("/signup");
        return "registration.html";
    }

    /**
     * Регистрация пользователя
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupRegistration(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "dateOfBirth") String dateOfBirth,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "repeatPassword") String repeatPassword,
            Model model) {
        if (password.equals(repeatPassword)) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateOfBirth);
                Role role = (Role) roleService.get("user");
                User user = new User(name, surname, email, date, role, password);
                userService.add(user);
            } catch (
                    LengthFieldsException |
                            IllegalSymbolException |
                            ValidateEmailFailException |
                            SpaceFieldsException |
                            ParseException e) {
                e.printStackTrace();
                model.addAttribute("error", "Incorrect data!");
                return "redirect:/";
            }
        } else {
            model.addAttribute("error", "Passwords do not match!");
            return "redirect:/"; //Пароли не совпадают
        }
        return "redirect:/";
    }

    @RequestMapping("/signin")
    public String signin(Model model) {
        List<Advert> adverts = getAdverts();
        for (Advert a : adverts) {
            System.out.println(a);
        }
        System.out.println("/signin");
        return "index";
    }

    private List<Advert> getAdverts() {
        List<Advert> advertList = new ArrayList<Advert>();
        for (Object o : advertService.list()) {
            advertList.add((Advert) o);
        }
        return advertList;
    }

    private User getUser(int advId) {
        List<User> users = new ArrayList<User>();
        User user = null;
        for (Object o : userService.list()) {
            users.add((User) o);
        }
        for (User user1 : users) {
            List<Advert> adverts = user1.getAdverts();
            for (Advert advert : adverts) {
                if (advert.getId() == advId) {
                    user = user1;
                }
            }
        }
        return user;
    }
}
