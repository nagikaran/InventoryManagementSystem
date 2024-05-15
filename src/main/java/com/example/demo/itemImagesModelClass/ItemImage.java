package com.example.demo.itemImagesModelClass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemImage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    int itemId;
	private String itemName;
	private String itemDiscription;
	@Lob
	@Column(name = "item_image",columnDefinition = "longblob")
	private byte[] itemImage;

}
