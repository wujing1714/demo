package com.imooc.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.imooc.demo.entity.WXSessionModel;
import com.imooc.demo.utils.HttpClientUtil;
import com.imooc.demo.utils.IMoocJSONResult;
import com.imooc.demo.utils.JsonUtils;
import com.imooc.demo.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;


@RestController
@RequestMapping("/superadmin")
public class WXLoginController {
	
	@Autowired
	private RedisOperator redis;
	

	@PostMapping("/wxLogin")
	public IMoocJSONResult wxLogin(String code) {

		System.out.println("wxlogin - code: " + code);

//		https://api.weixin.qq.com/sns/jscode2session?
//				appid=APPID&
//				secret=SECRET&
//				js_code=JSCODE&
//				grant_type=authorization_code
		
	String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> param = new HashMap<>();
		param.put("appid", "wxb82a3524d4d73654");
		param.put("secret", "0af0947178dff9a670bf1d1e45fc6338");
		param.put("js_code", code);
		param.put("grant_type", "authorization_code");
		
		String wxResult = HttpClientUtil.doGet(url, param);
		System.out.println(wxResult);
		
		WXSessionModel model = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);
		// 存入session到redis
		redis.set("user-redis-session:" + model.getOpenid(), 
							model.getSession_key(), 
							1000 * 60 * 30);
		
		return IMoocJSONResult.ok();
	}
	
}
