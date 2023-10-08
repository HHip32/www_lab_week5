package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private long id;
    @Column(name = "comp_name", nullable = false)
    private String name;
    @Column(name = "about", length = 2000)
    private String about;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "web_url")
    private String webUrl;
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    private Address address;
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

}
