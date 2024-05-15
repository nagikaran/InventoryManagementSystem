package com.example.demo.itemImagesServiceInterface;

import java.util.List;

import com.example.demo.itemImagesModelClass.InventoryManagementModel;

public interface InventoryManagementServiceInterface {

	String addProductDetails(InventoryManagementModel inventoryManagementModel);

	String updateTheStatusOfTheProduct(int id);

	List<InventoryManagementModel> getAllTheInventoryDetails();

	String deleteAsPerId(int id);

	int getAllStocks();

}
