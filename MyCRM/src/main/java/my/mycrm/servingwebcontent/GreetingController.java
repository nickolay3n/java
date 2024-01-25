package com.example.servingwebcontent;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/index")
    public String greeting(@RequestParam(name="name",
            required=false,
            defaultValue="World") String name,
                           Model model)//-то куда мы хотим складывать данные которые мы хотим вернуть пользователю
    {
        model.addAttribute("name", name);
        return "index";
    }

}