package com.maciej.tacocloudspringinaction.web;

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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    private final UserRepository userRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository, UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        Taco savedTaco = tacoRepository.save(taco);
        order.addDesign(savedTaco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}
