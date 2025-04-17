package fi.haagahelia.watchlist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.watchlist.domain.Account;
import fi.haagahelia.watchlist.domain.AccountRepository;
import fi.haagahelia.watchlist.domain.Signup;
import jakarta.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/signup")
    public String addStudent(Model model) {
        model.addAttribute("signup", new Signup());
        return "signup";
    }

    /**
     * Create new user Check if user already exists & form validation
     *
     * @param signup
     * @param bindingResult
     * @return
     */
    @PostMapping("/signup/save")
    public String save(@Valid @ModelAttribute("signup") Signup signup, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) { // validation errors
            if (signup.getPassword().equals(signup.getPasswordCheck())) { // check password match		
                String pwd = signup.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                Account newAcc = new Account();
                newAcc.setPasswordHash(hashPwd);
                newAcc.setUsername(signup.getUsername());
                newAcc.setRole("USER");
                if (accountRepository.findByUsername(signup.getUsername()) == null) { // Check if user exists
                    accountRepository.save(newAcc);
                } else {
                    bindingResult.rejectValue("username", "err.username", "Username already exists");
                    return "signup";
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
                return "signup";
            }
        } else {
            return "signup";
        }
        return "redirect:/login";
    }

}
