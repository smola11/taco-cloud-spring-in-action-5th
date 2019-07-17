package com.maciej.tacocloudspringinaction.web;

import com.maciej.tacocloudspringinaction.config.DiscountCodeProps;
import com.maciej.tacocloudspringinaction.data.springdata.Ingredient;
import com.maciej.tacocloudspringinaction.data.springdata.Ingredient.Type;
import com.maciej.tacocloudspringinaction.data.springdata.Order;
import com.maciej.tacocloudspringinaction.data.springdata.Taco;
import com.maciej.tacocloudspringinaction.data.springdata.repositories.IngredientRepository;
import com.maciej.tacocloudspringinaction.data.springdata.repositories.TacoRepository;
import com.maciej.tacocloudspringinaction.data.springdata.repositories.UserRepository;
import com.maciej.tacocloudspringinaction.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

    private DiscountCodeProps discountProps;

    public DiscountController(DiscountCodeProps discountProps) {
        this.discountProps = discountProps;
    }

    @GetMapping
    public String displayDiscountCodes(Model model) {

        Map<String, Integer> codes = discountProps.getCodes();
        model.addAttribute("codes", codes);

        return "discountList";
    }
}
