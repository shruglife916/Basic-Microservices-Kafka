package com.cogent.inventoryservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Order {
    private String orderId;

    private String name;

    private Date date;

    private int quantity;

}
