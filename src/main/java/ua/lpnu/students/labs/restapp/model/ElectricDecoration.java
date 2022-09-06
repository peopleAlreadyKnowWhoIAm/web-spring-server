package ua.lpnu.students.labs.restapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import ua.lpnu.students.labs.restapp.model.shared.Usage;

/**
 * Electric decoration class.
 */
@NoArgsConstructor
@Getter
@Setter
@Table("electric-decoration")
public class ElectricDecoration {

    String name = "";
    String material = "";
    Usage usage = Usage.FOR_CHRISTMASS;
    @JsonProperty("amount_avalaible")
    int avalaibleAmount;
    float price;

    @Id
    private long id;
    @JsonProperty("colors")
    String colourOfLights = "";
    int length;
    @JsonProperty("lamps_per_meter")
    int amountLampsPerMeter;
    @JsonProperty("power")
    int powerInWatts;


    /**
     * Constructor without id and type.
     */
    public ElectricDecoration(String name, Usage usage, String material, int avalaibleAmount,
            String colourOfLights, int length, int amountLampsPerMeter, int powerInWatts, float price) {
        this.name = name;
        this.material = material;
        this.usage = usage;
        this.avalaibleAmount = avalaibleAmount;
        this.price = price;
        this.colourOfLights = colourOfLights;
        this.length = length;
        this.amountLampsPerMeter = amountLampsPerMeter;
        this.powerInWatts = powerInWatts;
    }

    ElectricDecoration(String name, String material, Usage usage, int avalaibleAmount, float price,
            long id, String colourOfLights, int length, int amountLampsPerMeter, int powerInWatts) {
        this.name = name;
        this.material = material;
        this.usage = usage;
        this.avalaibleAmount = avalaibleAmount;
        this.price = price;
        this.id = id;
        this.colourOfLights = colourOfLights;
        this.length = length;
        this.amountLampsPerMeter = amountLampsPerMeter;
        this.powerInWatts = powerInWatts;
    }
}
