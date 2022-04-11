package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import javax.jws.WebParam;


@Controller

public class UsersController {


    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("userList", userService.allUser());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(name = "id")int id,@ModelAttribute("user") User user) {
        user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editUser(@PathVariable(name = "id")int id,@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.edit(user);
        return modelAndView;
    }



    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView delete(@PathVariable(name = "id")int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.deleteById( id);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute(name = "name") String name, @ModelAttribute(name = "lastname")String lastname) {
        userService.add(new User(0,name, lastname));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}