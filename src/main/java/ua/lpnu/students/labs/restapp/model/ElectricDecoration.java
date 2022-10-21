package ua.lpnu.students.labs.restapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;


/**
 * Electric decoration class.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("electric-decoration")
public class ElectricDecoration {

    String name = "";
    String description = "";
    @Column("amountAvalaible")
    int amountAvalaible;
    float price;

    @Id
    private long id;
    String colors = "";
    int length;
    int power;

    @MappedCollection(idColumn = "decorationId", keyColumn = "numInList")
    List<ImageId> imageIds;

    /**
     * Constructor without id and type.
     */
    public ElectricDecoration(String name, String description, int avalaibleAmount,
            String colourOfLights, int length, int powerInWatts, float price,List<ImageId> imageIds) {
        this.name = name;
        this.description = description;
        this.amountAvalaible = avalaibleAmount;
        this.price = price;
        this.colors = colourOfLights;
        this.length = length;
        this.power = powerInWatts;
        this.imageIds = imageIds;
    }
}
