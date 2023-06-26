package testing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Geo {

    private String lat, lng;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Geo geo = (Geo) obj;

        return Objects.equals(lat, geo.lat) &&
                Objects.equals(lng, geo.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
