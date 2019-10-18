package tacos.ingredientclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tacos.ingredientclient.Ingredient;

// The @FeignClient annotation specifies that any methods you declare
// in this interface will make requests against the service whose name is ingredient-service.

@FeignClient("ingredient-service")
public interface IngredientClient {

    @GetMapping("/ingredients/{id}")
    Ingredient getIngredient(@PathVariable("id") String id);

    @GetMapping("/ingredients")
    Iterable<Ingredient> getAllIngredients();

}
