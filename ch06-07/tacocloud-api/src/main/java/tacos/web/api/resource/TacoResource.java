package tacos.web.api.resource;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import tacos.Taco;

import java.util.Date;
import java.util.List;

@Relation(value = "taco", collectionRelation = "tacos") // decouples JSON field and resource type class names
public class TacoResource extends ResourceSupport {

    private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();

    @Getter
    private final String name;

    @Getter
    private final Date createdAt;

    @Getter
    private final List<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toResources(taco.getIngredients());
    }
}
