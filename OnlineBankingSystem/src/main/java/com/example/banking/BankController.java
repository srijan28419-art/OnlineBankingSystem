package com.example.banking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.math.BigDecimal;
import java.security.Principal;
@Controller public class BankController {
 private final BankService bank;BankController(BankService bank){this.bank=bank;}
 @GetMapping("/") String home(){return "redirect:/dashboard";}
 @GetMapping("/login") String login(){return "login";}
 @GetMapping("/dashboard") String dashboard(Principal principal,Model model){User user=bank.current(principal.getName());model.addAttribute("user",user);model.addAttribute("transactions",bank.history(user).stream().limit(5).toList());model.addAttribute("page","dashboard");return "dashboard";}
 @GetMapping("/transfer") String transferPage(Principal principal,Model model){model.addAttribute("user",bank.current(principal.getName()));model.addAttribute("page","transfer");return "transfer";}
 @PostMapping("/transfer") String transfer(Principal principal,@RequestParam String destination,@RequestParam BigDecimal amount,@RequestParam(required=false) String description,RedirectAttributes flash){try{bank.transfer(principal.getName(),destination,amount,description);flash.addFlashAttribute("success","Transfer completed successfully.");return "redirect:/transactions";}catch(IllegalArgumentException e){flash.addFlashAttribute("error",e.getMessage());return "redirect:/transfer";}}
 @GetMapping("/transactions") String transactions(Principal principal,Model model){User user=bank.current(principal.getName());model.addAttribute("user",user);model.addAttribute("transactions",bank.history(user));model.addAttribute("page","transactions");return "transactions";}
}
