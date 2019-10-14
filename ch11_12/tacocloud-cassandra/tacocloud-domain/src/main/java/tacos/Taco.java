package tacos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.rest.core.annotation.RestResource;

import com.datastax.driver.core.utils.UUIDs;

import lombok.Data;

@Data
@RestResource(rel = "tacos", path = "tacos")
@Table("tacos") // Persist to "tacos" table in Cassandra
public class Taco {

    // Defines the Partition Key.
    // This specifies that the 'id' property serves as the partition key, used to determine which Cassandra
    // partition(s) each row of taco data will be written to.
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = UUIDs.timeBased();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    // Defines the Clustering Key.
    // Clustering keys are used to determine the ordering of rows within a partition.
    // More specifically, the ordering is set to descending order — therefore, within a given partition, newer rows
    // appear first in the tacos table.
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED,
        ordering = Ordering.DESCENDING)
    private Date createdAt = new Date();

    // Columns that contain collections of data, such as the ingredients column, must be collections of native types
    // (integers, strings, and so on) or must be collections of user-defined types [IngredientUDT].
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @Column("ingredients")
    private List<IngredientUDT> ingredients;

}
