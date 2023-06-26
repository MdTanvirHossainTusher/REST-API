package testing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street, suite, city, zipcode;
    private Geo geo;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Address address = (Address) obj;

        return Objects.equals(street, address.street) &&
                Objects.equals(suite, address.suite) &&
                Objects.equals(city, address.city) &&
                Objects.equals(zipcode, address.zipcode) &&
                Objects.equals(geo, address.geo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, suite, city, zipcode, geo);
    }
}
