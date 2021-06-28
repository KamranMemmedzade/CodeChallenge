package com.bestcommerce.model;


import com.bestcommerce.model.product.Product;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table
@ToString
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    private String ownerName;

    private String address;

    private String phoneNumber;

    @Column(nullable = false,unique = true)
    private String email;

    @Transient
    private List<Product> productList;

    @Enumerated(EnumType.STRING)
    private MerchantType merchantType;

    public Merchant(
            String name, String password,
            String email, MerchantType merchantType,
            String ownerName,
            String address,
            String phoneNumber) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.merchantType=merchantType;
        this.ownerName=ownerName;
        this.address=address;
        this.phoneNumber=phoneNumber;
    }
}
