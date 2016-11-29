package com.ethoca.shoppingcart.controller;

import com.ethoca.shoppingcart.model.UserSignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Chemcee. M. C on 27-11-2016.
 */

@Controller
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping(method= RequestMethod.GET)
    public String registerCustomer(Model model)
    {
        model.addAttribute("signUpForm", new UserSignUpForm());
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerCustomer(@Valid @ModelAttribute("signUpForm") UserSignUpForm signUpForm, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "signup";

        return "redirect:/";
    }

}
