package org.spring.app.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

public class GameController {

    @GetMapping(value = "/game")
    public String game() {
        return "game";
    }

    @GetMapping(value = "/calculate")
    public ModelAndView getCalculateView(Model model,
                                         @RequestParam(name = "first") BigDecimal firstNumber,
                                         @RequestParam(name = "second") BigDecimal secondNumber
                                         //@RequestParam(name = "operator") Operator operator
    ) {
        double result = 0.0;            //calculatorService.calculate(firstNumber, secondNumber, operator);
        model.addAttribute("calculationResult", result);
        return new ModelAndView("result");
    }
}
