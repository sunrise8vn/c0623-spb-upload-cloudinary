package com.cg.model.dto.product;

import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateReqDTO {

    private String title;
    private BigDecimal price;
    private String unit;
    private String description;

    private MultipartFile file;

    public Product toProduct(ProductAvatar productAvatar) {
        return new Product()
                .setTitle(title)
                .setPrice(price)
                .setUnit(unit)
                .setDescription(description)
                .setAvatar(productAvatar)
                ;
    }

}
