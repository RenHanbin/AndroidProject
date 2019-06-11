package net.onest.go2school.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.onest.go2school.dao.ShopMapper;
import net.onest.go2school.entity.Question;
import net.onest.go2school.entity.Shop;
import net.onest.go2school.entity.User;
import net.onest.go2school.service.ShopService;
import net.onest.go2school.service.UserService;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopMapper shopMapper;
	
	public List<Shop> findAllShops(){
		return shopMapper.findAllShops();
	}
	
	public Shop findShopById(Integer shopId) {
		return shopMapper.findShopById(shopId);
	}
	
	public int updateShopById(Shop shop){
		return shopMapper.updateShopById(shop);
	};

	public List<Shop> findShopByLike(String strLikeName) {
		return shopMapper.findShopByLike(strLikeName);
	};
	public int deleteShopById(Integer shopId) {
		return shopMapper.deleteShopById(shopId);
	};
	public int insertShop(Shop shop) {
		return shopMapper.insertShop(shop);
	};
}
