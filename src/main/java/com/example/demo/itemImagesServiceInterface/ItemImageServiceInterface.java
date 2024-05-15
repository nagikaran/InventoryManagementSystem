package com.example.demo.itemImagesServiceInterface;

import java.util.List;

import com.example.demo.itemImagesModelClass.ItemImage;

public interface ItemImageServiceInterface {

	String saveItemDetails(ItemImage itemImage);

	List<ItemImage> getListOfItemImagesDetails();

}
