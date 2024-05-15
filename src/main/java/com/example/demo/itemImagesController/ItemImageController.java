package com.example.demo.itemImagesController;



import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.itemImagesConstants.ErrorMessages;
import com.example.demo.itemImagesConstants.SuccessMessages;
import com.example.demo.itemImagesConstants.UrlsEndPoint;
import com.example.demo.itemImagesModelClass.ItemImage;
import com.example.demo.itemImagesServiceInterface.ItemImageServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = UrlsEndPoint.ITEM_IMAGE_URL)
public class ItemImageController {
	
	@Autowired
	ItemImageServiceInterface imageServiceInterface;
	
	/*End point to save the image in the db*/
	
	@RequestMapping(value = UrlsEndPoint.ITEM_IMAGE_URL_TO_SAVE_THE_IMAGE,method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> saveTheItemDetails(
			@RequestPart(required = true,value = "itemImages")MultipartFile itemImages,
			@RequestPart("imageDetails")String imageDetails) throws JsonMappingException, JsonProcessingException,IOException{
		
		ItemImage itemImage = new ItemImage();
		ObjectMapper objectMapper = new ObjectMapper();
		String message=null;
		ItemImage itemImagesDetailValue = objectMapper.readValue(imageDetails, ItemImage.class);
		if(itemImagesDetailValue!=null) {
			itemImage.setItemName(itemImagesDetailValue.getItemName());
			itemImage.setItemDiscription(itemImagesDetailValue.getItemDiscription());
			itemImage.setItemImage(itemImages.getBytes());
			message=imageServiceInterface.saveItemDetails(itemImage);	
		}
		
		if(message!=null && message.equals(SuccessMessages.SUCCESS_MESSAGE_DATA_SAVED_SUCCESSFULLY)) {
			return ResponseEntity.status(HttpStatus.OK).body(SuccessMessages.SUCCESS_MESSAGE_DATA_SAVED_SUCCESSFULLY);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessages.ERROR_MESSAGE_SOMETHING_WENT_WRONG_WHILE_SAVING_THE_DATA);
		}
		
	}
	
	/*Endpoint To get the Details of the items*/
	@RequestMapping(value = UrlsEndPoint.ITEM_IMAGE_URL_TO_GET_ALL_IMAGE_DETAILS,method = RequestMethod.GET)
	public ResponseEntity<?> getAlltheItemImageDetais(){
		List<ItemImage> itemImagesList =imageServiceInterface.getListOfItemImagesDetails();
		
		if(itemImagesList!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(itemImagesList);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessages.NO_DATA_FOUND);
		}
		
		
		
		
		
	}

}
