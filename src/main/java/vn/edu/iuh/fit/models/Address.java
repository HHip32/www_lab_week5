package vn.edu.iuh.fit.models;


import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id")
    private long id;
    @Column(name = "city", length = 20)
    private String city;
    @Column(name = "zipcode", length = 7)
    private String zipCode;
    @Column(name = "street", length = 150)
    private String street;
    @Column(name = "number", length = 20)
    private String number;
    @Column(name = "country", length = 30)
    private CountryCode country;

    public Address(String city, String zipCode, String street, String number, CountryCode country) {
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.country = country;
    }
}
