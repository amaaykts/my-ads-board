package controller;

import model.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

        System.out.println("index");
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
