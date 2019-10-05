package anec.vip.springbootdemo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class loginpage {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model m, HttpSession session){
       if ( !StringUtils.isEmpty(username) && "123".equals(password)){
            session.setAttribute("user",username);
           return "redirect:/main.html";
       }else {
           m.addAttribute("msg","账号密码错误");
           return "index";
       }
    }

    @GetMapping("/loginout/{user}")
    public String loginout(@PathVariable("user") String user,HttpSession s){
        s.removeAttribute("user");
        return "redirect:/";
    }


}
