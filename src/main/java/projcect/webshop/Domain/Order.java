package projcect.webshop.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name= "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToOne
    Bucket bucket;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    Date dateOfCreation;

    @Column
    String message;

    @Column(nullable = false)
    String adress;

    @ManyToOne
    Person owner;

    @Column
    boolean finish;
}
