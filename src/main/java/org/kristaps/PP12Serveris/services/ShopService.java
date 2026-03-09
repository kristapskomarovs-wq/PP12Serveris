package org.kristaps.PP12Serveris.services;

import java.util.List;

import org.kristaps.PP12Serveris.models.ShopModel;
import org.kristaps.PP12Serveris.repository.ShopRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    // public ShopService(ShopRepository shopRepository) {
    // this.shopRepository = shopRepository;
    // }

    public List<ShopModel> getAllAutos() {
        return shopRepository.findAll();
    }

    public boolean addAuto(ShopModel shopModel) {
        ShopModel newAuto = shopRepository.save(shopModel);
        return newAuto.getId() != null;
    }
}
