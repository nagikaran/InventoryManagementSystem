package com.example.demo.itemImagesRepositoryInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.itemImagesModelClass.InventoryManagementModel;
@Repository
public interface InventoryManagementRepositoryInterface extends JpaRepository<InventoryManagementModel, Integer> {

}
