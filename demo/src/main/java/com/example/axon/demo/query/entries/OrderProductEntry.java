package com.example.axon.demo.query.entries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @program: axondemo
 * @description: 商品订单查询入口
 * @author: Xiangchun Zeng
 * @create: 2018-10-26 16:13
 **/
@Entity
public class OrderProductEntry {

	@Id
	@GeneratedValue
	private Long jpaId;

	private String id;

	@Column
	private String name;

	@Column
	private long price;

	@Column
	private int amount;

	public OrderProductEntry() {
	}

	public OrderProductEntry(String id, String name, long price, int amount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getJpaId() {
		return jpaId;
	}

	public void setJpaId(Long jpaId) {
		this.jpaId = jpaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
