package com.example.axon.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.axon.demo.commands.CreateOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: axondemo
 * @description: 订单控制器
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 15:50
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private HttpServletResponse response;

	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestBody(required = true) JSONObject input) {
		LOGGER.info(input.toJSONString());

		int responseCode = HttpServletResponse.SC_BAD_REQUEST;

		if (input.containsKey("username") && input.containsKey("products")) {
			String username = input.getString("username");
			JSONArray products = input.getJSONArray("products");
			if (!StringUtils.isEmpty(username) && products.size() > 0) {
				Map<String, Integer> map = new HashMap<>();
				CreateOrderCommand command = new CreateOrderCommand(username, map);
				for (Object each:products) {
					JSONObject o = (JSONObject)each;
					if (!o.containsKey("id") || !o.containsKey("number"))
						return;
					map.put(o.getString("id"), o.getInteger("number"));
				}
				commandGateway.sendAndWait(command);
				responseCode = HttpServletResponse.SC_CREATED;
			}
		}
		response.setStatus(responseCode);
	}
}
