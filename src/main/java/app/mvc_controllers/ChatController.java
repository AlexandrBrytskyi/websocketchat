package app.mvc_controllers;

import app.exceptions.UserAlreadyExistsException;
import app.exceptions.WrongUserCredentialsException;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/chat/")
public class ChatController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "logined", method = RequestMethod.POST)
    public String login(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String pass, Model model, RedirectAttributes redirectAttributes) {
        try {
            User logged = userService.login(userName, pass);
            redirectAttributes.addFlashAttribute("user", logged);
            return "redirect:chat";
        } catch (WrongUserCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String pass, Model model, RedirectAttributes redirectAttributes) {
        try {
            User registered = userService.register(userName, pass);
            redirectAttributes.addFlashAttribute("user", registered);
            redirectAttributes.addAttribute("error", "Ok," + registered.getName()+", now login :)");
            return "redirect:login";
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@ModelAttribute(name = "error") String errorMessage, Model model) {
        model.addAttribute("error", errorMessage);
        return "login";
    }


    @RequestMapping(value = "chat", method = RequestMethod.GET)
    public String openChatRoom(Model model, @ModelAttribute(name = "user") User user) {
        if (null == user || user.getName() == null) return "login";
        model.addAttribute("user", user);
        return "chat";
    }

}
