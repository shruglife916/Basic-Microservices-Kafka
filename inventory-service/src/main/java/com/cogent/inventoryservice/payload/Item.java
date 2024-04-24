package com.cogent.inventoryservice.payload;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "items"
)
public class Item {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long itemId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;
}
