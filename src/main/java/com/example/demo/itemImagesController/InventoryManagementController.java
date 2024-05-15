package com.example.demo.itemImagesController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.itemImagesConstants.ErrorMessages;
import com.example.demo.itemImagesConstants.SuccessMessages;
import com.example.demo.itemImagesConstants.UrlsEndpointForInventoryManagement;
import com.example.demo.itemImagesModelClass.InventoryManagementModel;
import com.example.demo.itemImagesServiceInterface.InventoryManagementServiceInterface;

@RestController
@RequestMapping(value = UrlsEndpointForInventoryManagement.INVENTORY_MANAGEMENT_URL)
public class InventoryManagementController {
	
	@Autowired
	InventoryManagementServiceInterface inventoryManagementServiceInterface;
	
	/*first End point to add the item in the data base*/
	@RequestMapping(value = UrlsEndpointForInventoryManagement.ADD_PRODUCT_URL,method = RequestMethod.POST)
	public ResponseEntity<String> addProductDetails(@RequestBody InventoryManagementModel inventoryManagementModel){
		String message=null;
		if(inventoryManagementModel!=null) {
			message = inventoryManagementServiceInterface.addProductDetails(inventoryManagementModel);
		}
		if(message.equals(SuccessMessages.SUCCESS_MESSAGE_DATA_SAVED_SUCCESSFULLY)) {
			return ResponseEntity.status(HttpStatus.OK).body(SuccessMessages.SUCCESS_MESSAGE_DATA_SAVED_SUCCESSFULLY);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessages.ERROR_MESSAGE_SOMETHING_WENT_WRONG_WHILE_SAVING_THE_DATA);
		}
	}
	
	/*second end point update the staus of the inventory commonly used status are
	 * Available,out-of-stock lets go with these two*/
	@RequestMapping(value = UrlsEndpointForInventoryManagement.UPDATE_PRODUCT_STATUS+"/{id}",method = RequestMethod.PUT)
	public ResponseEntity<String> updateTheStatusOfProductInInventory(@PathVariable int id){
		String status=null;
		status=inventoryManagementServiceInterface.updateTheStatusOfTheProduct(id);
		
		if(status!=null && !status.equals("")) {
			
			return ResponseEntity.status(HttpStatus.OK).body(status);
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessages.NO_DATA_FOUND_FOR_THESE_ID);
		}
		
		
	}
	
	/*create api to fetch all the information*/
	@RequestMapping(value = UrlsEndpointForInventoryManagement.GET_ALL_LIST_OF_INVENTORY,method = RequestMethod.GET)
	public ResponseEntity<?> getAllTheInventoryDetails(){
		List<InventoryManagementModel> inventoryManagementModels =inventoryManagementServiceInterface.getAllTheInventoryDetails();
		if(inventoryManagementModels!=null && !inventoryManagementModels.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(inventoryManagementModels);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessages.NO_DATA_FOUND);
		}
		
		
		
		
	}
	
	
	@RequestMapping(value=UrlsEndpointForInventoryManagement.DELETE_THE_INVENTORY_AS_PER_ID+"/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAsPerId(@PathVariable int id){
		String message=inventoryManagementServiceInterface.deleteAsPerId(id);
		if(message!=null && message.equals(SuccessMessages.DATA_DELETED_SUCCESSFULLY)) {
			return ResponseEntity.status(HttpStatus.OK).body(SuccessMessages.DATA_DELETED_SUCCESSFULLY);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessages.NO_DATA_FOUND_FOR_THESE_ID);
		}
		
		
	}
	
	/*last end point to get the stock of the inventory.*/
	@RequestMapping(value=UrlsEndpointForInventoryManagement.STOCK_OF_INVENTORY,method = RequestMethod.GET)
	public ResponseEntity<String> getAllTheStocks(){
		
		int stockDetails=inventoryManagementServiceInterface.getAllStocks();
		if(stockDetails!=0) {
			return ResponseEntity.status(HttpStatus.OK).body("Total Stocks = "+stockDetails);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body("Total Stocks = "+stockDetails);
		}
		
		
		
	}
	

}
