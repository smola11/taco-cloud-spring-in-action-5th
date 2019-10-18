package tacos.ingredientclient.resttemplate;

import java.util.Arrays;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tacos.ingredientclient.Ingredient;

@Service
@Conditional(NotFeignAndNotWebClientCondition.class)
public class IngredientServiceClient {

    private RestTemplate restTemplate;

    public IngredientServiceClient(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject(
            "http://ingredient-service/ingredients/{id}", Ingredient.class, ingredientId);
    }

    public Iterable<Ingredient> getAllIngredients() {
        Ingredient[] ingredients = restTemplate.getForObject(
            "http://ingredient-service/ingredients", Ingredient[].class);
        return Arrays.asList(ingredients);
    }
}
