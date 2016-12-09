package com.ethoca.shoppingcart.controller;

import com.ethoca.shoppingcart.domain.User;
import com.ethoca.shoppingcart.model.UserSignUpForm;
import com.ethoca.shoppingcart.service.UserService;
import com.ethoca.shoppingcart.utils.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Chemcee. M. C on 08-12-2016.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @RequestMapping(value = "/register",method= RequestMethod.GET)
    public String registerCustomer(Model model)
    {
        model.addAttribute("signUpForm", new UserSignUpForm());
        return "signup";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerCustomer(@Valid @ModelAttribute("signUpForm") UserSignUpForm signUpForm, BindingResult bindingResult)
    {
        User user = null;

        userValidator.validate(signUpForm, bindingResult);

        if(bindingResult.hasErrors())
        {
            return "signup";
        }
        else {
            user = userService.registerNewUserAccount(signUpForm);

            //autologin after successful registration
            userService.autoLogin(signUpForm.getEmail(), signUpForm.getConfirmPassword());
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String returnFormLogin(Model model, String error, String logout)
    {
        if(error != null)
        {
            model.addAttribute("error", "Wrong username/password");
        }
        if(logout != null){
            model.addAttribute("message", "You have been logged out.");
        }

        return "login";
    }

}
