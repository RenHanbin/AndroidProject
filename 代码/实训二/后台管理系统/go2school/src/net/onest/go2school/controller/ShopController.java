package net.onest.go2school.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.onest.go2school.entity.Manager;
import net.onest.go2school.entity.Question;
import net.onest.go2school.entity.Shop;
import net.onest.go2school.service.ShopService;

@Controller
@RequestMapping("shop")
public class ShopController {
	@Autowired
	private ShopService shopService;
	
	//获取所有商家信息
	@RequestMapping(value="getShopList",produces="application/json;charset=utf-8")
	public String getShopList(Model model) {
		List<Shop> shopList=shopService.findAllShops();
		model.addAttribute("shopList", shopList);
		model.addAttribute("remark", "getShopList");
		System.out.println("ִ执行了findAllShop");
		return "shops";
	}
	
	//通过店铺的id查找店铺，并将店铺的详细信息返回到店铺详情页
	@RequestMapping(value="findShopById",produces="application/json;charset=utf-8")
	public String findShopById(@RequestParam(value="shopId")Integer shopId,Model model,HttpSession session) {	
		System.out.println("shopId"+shopId);
		Shop shop = shopService.findShopById(shopId);
		session.setAttribute("shop", shop);
		model.addAttribute("shop", shop);
		return "shopProfile";
	}
	
	//修改商家信息  
	@RequestMapping("/updateShopById")
	public String updateShopById(Shop shop,Model model,HttpSession session) {

//		System.out.println("新的shop111111111111111"+shop.toString());
//		Shop shop1 = (Shop)session.getAttribute("shop");
//		shop.setShopId(shop1.getShopId());
		System.out.println("更改商家shopId:"+shop.toString());
		int result=shopService.updateShopById(shop);
		
		String shopMessage="";
		if(result!=0) {
			shopMessage= "修改成功";
		}else {
			shopMessage= "修改失败";
		}
		model.addAttribute("shopMessage",shopMessage);
		model.addAttribute("shop", shop);
		System.out.println("修改商家信息的提示信息："+shopMessage);
		return "shopProfile";
	}
	
	
	//跳转商家
	@RequestMapping("/addShop")
	public String addShop(Model model) {
		String remark="addShop";
		model.addAttribute("remark",remark);
		return "/shopProfile";
	}
	
	
	// 添加商家
		@RequestMapping("/insertShop")
		public String insertShop(Shop shop, Model model, HttpSession session) {
			System.out.println("新的shop111111111111111"+shop.toString());
			System.out.println("添加商家shopId:" + shop.toString());
			int result = shopService.insertShop(shop);
			

			String shopMessage = "";
			if (result != 0) {
				shopMessage = "添加成功";
			} else {
				shopMessage = "添加失败";
			}
			model.addAttribute("shopMessage", shopMessage);
			
			System.out.println("添加商家信息的提示信息：" + shopMessage);
			return "shopProfile";
		}

	
		//搜索商家
	@RequestMapping(value="getLikeShopList",produces="application/json;charset=utf-8")
	public String getLikeQuestionList(@RequestParam("searchstr")String strLikeName,Model model,HttpSession session) {
		List<Shop> shopListLike=shopService.findShopByLike(strLikeName);
		session.setAttribute("search", strLikeName);
		model.addAttribute("shopListLike", shopListLike);
		model.addAttribute("remark", "getLikeShopList");
		System.out.println("ִ执行了findShopByLike"+shopListLike);
		return "shops";
	}
	
	//删除商家
	@RequestMapping(value="deleteShop",produces="application/json;charset=utf-8")
	public String deleteQuestion(@RequestParam("shopId")int shopId,
			@RequestParam("remark1")String remark1,
			HttpSession session,
			Model model) {
		shopService.deleteShopById(shopId);
		if("all".equals(remark1)) {
			List<Shop> shopList=shopService.findAllShops();
			model.addAttribute("shopList", shopList);
			model.addAttribute("remark", "getShopList");
			System.out.println("ִ执行了findAllShops");
		}else if("like".equals(remark1)) {
			String strLikeName=(String) session.getAttribute("search");
			System.out.println("searchsearchsearchsearch"+strLikeName);
			List<Shop> shopListLike=shopService.findShopByLike(strLikeName);
			model.addAttribute("shopListLike", shopListLike);
			model.addAttribute("remark", "getLikeShopList");
			System.out.println("ִ执行了findShopByLike"+shopListLike);
		}
		return "shops";
	}

}
