package controller;

import model.Advert;
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
public class MainController {
    @Autowired
    @Qualifier(value = "advertService")
    private MyService advertService;

    @RequestMapping("/")
    public String index() {
        List<Advert> adverts = getAdverts();
        for (Advert a : adverts) {
            System.out.println(a);
        }
        System.out.println("/");
        return "index";
    }

    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public String ads(@RequestParam int id, Model model) {
        List<Advert> adverts = getAdverts();
        for (Advert a : adverts) {
            System.out.println(a);
        }
        System.out.println("/ads");
        return "index";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        List<Advert> adverts = getAdverts();
        for (Advert a : adverts) {
            System.out.println(a);
        }
        System.out.println("/signup");
        return "index";
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
}
