package com.example.demo.itemImagesRepositoryInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.itemImagesModelClass.ItemImage;

@Repository
public interface ItemImageRepositoryInterface extends JpaRepository<ItemImage, Integer> {

}
