package tacos.web.api;

import java.util.List;

import org.springframework.hateoas.Resources;
import tacos.web.api.resource.TacoResource;

public class TacoResources extends Resources<TacoResource> {
  public TacoResources(List<TacoResource> tacoResources) {
    super(tacoResources);
  }
}