package coffee.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CoffeeErrorController implements ErrorController {

    private static final String ERROR_MAPPING = "/error";

    @RequestMapping(value = ERROR_MAPPING)
    public String error() {
        return "errorPage.html";
    }

    @Override
    public String getErrorPath() {
        return ERROR_MAPPING;
    }
}