package testing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String name, catchPhrase, bs;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Company company = (Company) obj;
        return Objects.equals(name, company.name) &&
                Objects.equals(catchPhrase, company.catchPhrase) &&
                Objects.equals(bs, company.bs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, catchPhrase, bs);
    }
}
