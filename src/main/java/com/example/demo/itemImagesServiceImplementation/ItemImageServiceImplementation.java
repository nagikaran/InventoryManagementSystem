package com.example.demo.itemImagesServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.itemImagesConstants.ErrorMessages;
import com.example.demo.itemImagesConstants.SuccessMessages;
import com.example.demo.itemImagesModelClass.ItemImage;
import com.example.demo.itemImagesRepositoryInterface.ItemImageRepositoryInterface;
import com.example.demo.itemImagesServiceInterface.ItemImageServiceInterface;

@Service
public class ItemImageServiceImplementation implements ItemImageServiceInterface{
    @Autowired
    ItemImageRepositoryInterface imageRepositoryInterface;
	@Override
	public String saveItemDetails(ItemImage itemImage) {
		// TODO Auto-generated method stub
		if(itemImage!=null) {
			imageRepositoryInterface.save(itemImage);
			return SuccessMessages.SUCCESS_MESSAGE_DATA_SAVED_SUCCESSFULLY;
		}else {
			return ErrorMessages.ERROR_MESSAGE_SOMETHING_WENT_WRONG_WHILE_SAVING_THE_DATA;
		}
		
		
		
	}
	@Override
	public List<ItemImage> getListOfItemImagesDetails() {
		// TODO Auto-generated method stub
		List<ItemImage> itemImageListData=null;
		itemImageListData = imageRepositoryInterface.findAll();
		if(itemImageListData!=null) {
			return itemImageListData;
		}else {
			return itemImageListData;
		}
		
	}
	
	
	
	

}
