package vn.edu.iuh.fit.week05.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id")
    private long id;

    private String about;
    private String email;

    @Column(name = "comp_name")
    private String name;

    private String phone;

    @Column(name = "web_url")
    private String webURL;

    @OneToOne
    @JoinColumn(name = "address") // Thay đổi tên cột liên kết với bảng Address
    private Address address;

    public Company() {
    }

    public Company(String about, String email, String name, String phone, String webURL, Address address) {
        this.about = about;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.webURL = webURL;
        this.address = address;
    }
}
