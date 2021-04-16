package edu.wctc.streetfood;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class MenuController {
    private IceCreamItem[] IceCreamItemArray;

    @PostConstruct
    private void initMenuData() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            IceCreamItemArray = mapper.readValue(Paths.get("IceCreamItem.json").toFile(), IceCreamItem[].class);
        } catch (IOException e) {
            e.printStackTrace();
            IceCreamItemArray = new IceCreamItem[0];
        }
    }

    @RequestMapping("/credits")
    public String showCredits() {
        return "credits";
    }

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("/menu")
    public String showMenu(Model model) {
        model.addAttribute("stuff", IceCreamItemArray);
        return "menu";
    }
}
