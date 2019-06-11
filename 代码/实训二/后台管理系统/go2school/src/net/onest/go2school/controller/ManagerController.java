package net.onest.go2school.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.onest.go2school.entity.Manager;
import net.onest.go2school.service.ManagerService;

@Controller
@RequestMapping("manager")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	
	//管理员登录
	@RequestMapping("logina")
	public String login(Manager manager,Model model,HttpSession session) {
			
			Manager manager1=managerService.login(manager.getManagerName(),manager.getManagerPassword());			
			if(manager1!=null) {
				model.addAttribute("manager", manager1);
				session.setAttribute("manager", manager1);
				session.setAttribute("managerImg", manager1.getManagerImg());
				return "index";		
			}
				return "redirect:loq";
		
		
	}
	
	
	@RequestMapping("loq")
	public String login2(Model model,HttpSession session) {
		/*String loginMsg="登录失败，请重新登录";
		session.setAttribute("loginMsg", loginMsg);*/
		return "login";
	}
	
	
	
	//修改管理员信息
	@RequestMapping("/updateManager")
	public String updateManager(Manager manager,Model model,HttpSession session) {
		System.out.println("新的manager"+manager.toString());
		Manager manager1=(Manager) session.getAttribute("manager");
		manager.setManagerId(manager1.getManagerId());
		System.out.println("managerId:"+manager1.toString());
		int result=managerService.updateManagerById(manager);
		
		String magMessage="";
		if(result!=0) {
			magMessage= "修改成功";
			session.removeAttribute("manager");
			session.setAttribute("manager",manager );
		}else {
			magMessage= "修改失败";
		}
		model.addAttribute("magMessage",magMessage);
		System.out.println("修改用户信息的提示信息："+magMessage);
		return "index";
	}
	
	//退出登录
	@RequestMapping("/unLogin")
	public String unLogin(HttpSession session) {
		Manager manager=(Manager) session.getAttribute("manager");
		System.out.println("退出登录");
		System.out.println(manager.toString());
		session.removeAttribute("manager");
		return "login";
	}

}
