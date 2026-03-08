package org.kristaps.PP12Serveris.repository;

import org.kristaps.PP12Serveris.models.ShopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopModel, Long> {

}
