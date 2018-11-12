package com.example.axon.demo.controllers;

import javax.servlet.http.HttpServletResponse;

import com.example.axon.demo.commands.CreateProductCommand;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.ConcurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: axondemo
 * @description: 商品控制器
 * @author: Xiangchun Zeng
 *
 * @create: 2018-10-26 15:53
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private CommandGateway commandGateway;

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public void create(@PathVariable(value = "id") String id,
					@RequestParam(value = "name", required = true) String name,
					@RequestParam(value = "price", required = true) long price,
					@RequestParam(value = "stock", required = true) int stock, HttpServletResponse response) {

		LOGGER.debug("Adding Product [{}] '{}' {}x{}", id, name, price, stock);

		try {
			// multiply 100 on the price to avoid float number
			CreateProductCommand command = new CreateProductCommand(id, name, price * 100, stock);
			commandGateway.sendAndWait(command);
			response.setStatus(HttpServletResponse.SC_CREATED); // Set up the 201 CREATED response
			return;
		} catch (CommandExecutionException cex) {
			LOGGER.warn("Add Command FAILED with Message: {}", cex.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			if (null != cex.getCause()) {
				LOGGER.warn("Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
				if (cex.getCause() instanceof ConcurrencyException) {
					LOGGER.warn("A duplicate product with the same ID [{}] already exists.", id);
					response.setStatus(HttpServletResponse.SC_CONFLICT);
				}
			}
		}
	}
}
