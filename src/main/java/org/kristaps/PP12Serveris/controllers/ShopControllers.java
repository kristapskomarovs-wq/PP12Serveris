package org.kristaps.PP12Serveris.controllers;

import java.util.List;

import org.kristaps.PP12Serveris.models.ShopModel;
import org.kristaps.PP12Serveris.services.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShopControllers {

    private final ShopService shopService;

    @GetMapping("/api/v1/getallautos")
    public ResponseEntity<List<ShopModel>> findAllAutos() {
        return ResponseEntity.ok(shopService.getAllAutos());
    }

    @PostMapping("/api/v1/addauto")
    public ResponseEntity<Void> addAuto(@RequestBody ShopModel shopModel) {
        if (shopService.addAuto(shopModel)) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/v1/updateauto/{id}")
    public ResponseEntity<Void> updateAuto(@PathVariable Long id, @RequestBody ShopModel shopModel) {
        if (shopService.updateAuto(id, shopModel)) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/v1/deleteauto/{id}")
    public ResponseEntity<Void> deleteAuto(@PathVariable Long id) {
        if (shopService.deleteAuto(id)) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}