package net.onest.go2school.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.onest.go2school.entity.User;
import net.onest.go2school.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//得到全部用户
	@RequestMapping("getUserList")
	public String getUser(Model model) {
		List<User> users=userService.findAllUsers();
		model.addAttribute("users", users);
		System.out.println("ִ执行了findAllUser");
		return "userList";
	}
	
	
	/*//通过用户的id查找用户，并将用户的详细信息返回到用户详情页
	@RequestMapping("findUserById")
	public String findUserById(@RequestParam(value="userId") Integer userId,Model model) {	
		System.out.println("userId"+userId);
		User user=userService.findUserById(userId);
		model.addAttribute("user", user);
		return "userProfile";
	}*/
	
	//通过用户的id查找用户，并将用户的详细信息返回到用户详情页
		@RequestMapping("findUserById")
		public String findUserById(@RequestParam(value="userId") Integer userId,Model model,HttpSession session) {	
			System.out.println("userId"+userId);
			User user=userService.findUserById(userId);
			User userFans=userService.findUserById1(userId);
			model.addAttribute("user", user);
//			model.addAttribute("userFan", userFans);
			session.setAttribute("userFan", userFans);
			session.setAttribute("userAtten", user.getUserAtten());
			List<User> users=userService.findfollowListById(userId);
			List<User> follows=new ArrayList<User>();
			for(User u:users) {	 
				follows.add(userService.findUserById2(u.getUserAtten()));
			}
//			model.addAttribute("follows", follows);
			session.setAttribute("follows", follows);
			System.out.println("follows"+follows);
			return "userProfile";
		}

	
	//修改用户信息
	@RequestMapping("updateUser")
	public String updateUser(User user,Model model) {
		
		System.out.println("userId="+user.getUserId());
		int result=userService.updateUser(user);
		String userLog="";
		if(result!=0) {
			userLog="修改成功";
		}else {
			userLog="修改失败";
		}
		model.addAttribute("user",user);
		model.addAttribute("userLog",userLog);
		return "userProfile";
	}
	
}
