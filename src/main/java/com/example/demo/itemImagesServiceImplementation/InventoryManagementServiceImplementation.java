package com.example.demo.itemImagesServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.itemImagesConstants.ErrorMessages;
import com.example.demo.itemImagesConstants.SuccessMessages;
import com.example.demo.itemImagesModelClass.InventoryManagementModel;
import com.example.demo.itemImagesRepositoryInterface.InventoryManagementRepositoryInterface;
import com.example.demo.itemImagesServiceInterface.InventoryManagementServiceInterface;
@Service
public class InventoryManagementServiceImplementation implements InventoryManagementServiceInterface{

	@Autowired
	InventoryManagementRepositoryInterface inventoryManagementRepositoryInterface;
	@Override
	public String addProductDetails(InventoryManagementModel inventoryManagementModel) {
		// TODO Auto-generated method stub
		InventoryManagementModel saveData=null;
		if(inventoryManagementModel!=null) {
			saveData = inventoryManagementRepositoryInterface.save(inventoryManagementModel);
			
		}
		if(saveData!=null) {
			return SuccessMessages.SUCCESS_MESSAGE_DATA_SAVED_SUCCESSFULLY;
		}else {
			return ErrorMessages.ERROR_MESSAGE_SOMETHING_WENT_WRONG_WHILE_SAVING_THE_DATA;
		}
		
	}
	@Override
	public String updateTheStatusOfTheProduct(int id) {
		// TODO Auto-generated method stub
		String message=null;
if(inventoryManagementRepositoryInterface.existsById(id)) {
	Optional<InventoryManagementModel> productDetailsById = inventoryManagementRepositoryInterface.findById(id);
	InventoryManagementModel inventoryManagementModel = new InventoryManagementModel();
	
	if(productDetailsById!=null) {
		InventoryManagementModel inventoryManagementModel2 = productDetailsById.get();
		if(inventoryManagementModel2.getQuantity()>0) {
			inventoryManagementModel.setStatus("AVAILABEL");
			message=inventoryManagementModel.getStatus();
			inventoryManagementRepositoryInterface.save(inventoryManagementModel);
			
		}
		else if(inventoryManagementModel2.getQuantity()==0) {
			inventoryManagementModel.setStatus("OUT-OF-STOCK");
			message=inventoryManagementModel.getStatus();
			inventoryManagementRepositoryInterface.save(inventoryManagementModel);
		}
		
	}
}
		
		return message;
	}
	@Override
	public List<InventoryManagementModel> getAllTheInventoryDetails() {
		// TODO Auto-generated method stub
		List<InventoryManagementModel> findAll = inventoryManagementRepositoryInterface.findAll();
		return findAll;
	}
	@Override
	public String deleteAsPerId(int id) {
		// TODO Auto-generated method stub
		if(inventoryManagementRepositoryInterface.existsById(id)) {
			inventoryManagementRepositoryInterface.deleteById(id);
			return SuccessMessages.DATA_DELETED_SUCCESSFULLY;
		}else {
			return ErrorMessages.NO_DATA_FOUND_FOR_THESE_ID;
		}
		
		
		
	}
	@Override
	public int getAllStocks() {
		// TODO Auto-generated method stub
		int stocks=0;
		List<InventoryManagementModel> findAllStocks = inventoryManagementRepositoryInterface.findAll();
		if(findAllStocks!=null) {
			for(InventoryManagementModel forStocks:findAllStocks) {
				stocks+=forStocks.getQuantity();
			}
		}
		
		return stocks;
	}

}
