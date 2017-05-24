package com.java1234.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.java1234.entity.Blog;
import com.java1234.entity.BlogType;
import com.java1234.entity.Blogger;
import com.java1234.entity.Link;
import com.java1234.service.BlogService;
import com.java1234.service.BlogTypeService;
import com.java1234.service.BloggerService;
import com.java1234.service.LinkService;
import com.java1234.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * ����ԱϵͳController��
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

	@Resource
	private BloggerService bloggerService;
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@Resource
	private BlogService blogService;
	
	@Resource
	private LinkService linkService;
	
	/**
	 * ˢ��ϵͳ����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refreshSystem")
	public String refreshSystem(HttpServletResponse response,HttpServletRequest request)throws Exception{
		ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
		Blogger blogger=bloggerService.find(); // ��ѯ������Ϣ
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		List<BlogType> blogTypeCountList=blogTypeService.countList(); // ��ѯ��������Լ����͵�����
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		
		List<Blog> blogCountList=blogService.countList(); // �������ڷ����ѯ����
		application.setAttribute("blogCountList", blogCountList);
		
		List<Link> linkList=linkService.list(null); // ��ȡ������������
		application.setAttribute("linkList", linkList);
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
