package net.onest.go2school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.onest.go2school.entity.Question;
import net.onest.go2school.entity.Shop;

public interface ShopMapper {
	
	public List<Shop> findAllShops();

	public Shop findShopById(Integer shopId);

	public int updateShopById(Shop shop);
	

	public List<Shop> findShopByLike(String strLikeName);
	public int deleteShopById(Integer shopId);
	
	public int insertShop(Shop shop);
}
