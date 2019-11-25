package pl.epoint.jzawadka.servletsample.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
    Integer id;
    String name;
    BigDecimal price;
}
