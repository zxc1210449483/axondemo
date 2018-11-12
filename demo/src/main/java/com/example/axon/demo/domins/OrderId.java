package com.example.axon.demo.domins;

import java.io.Serializable;

import org.axonframework.common.Assert;
import org.axonframework.common.IdentifierFactory;

/**
 * @program: axondemo
 * @description: 订单IdPOJO
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 15:35
 **/
public class OrderId implements Serializable {

	private static final long serialVersionUID = -4163440749566043686L;

	private final String identifier;

	private final int hashCode;

	public OrderId() {
		this.identifier = IdentifierFactory.getInstance().generateIdentifier();
		this.hashCode = identifier.hashCode();
	}

	public OrderId(String identifier) {
		Assert.notNull(identifier, () -> "Identifier may not be null");
		this.identifier = identifier;
		this.hashCode = identifier.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderId)) return false;

		OrderId orderId = (OrderId) o;

		return identifier.equals(orderId.identifier);
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}

	@Override
	public String toString() {
		return this.identifier;
	}

	public String getIdentifier() {
		return identifier;
	}
}
