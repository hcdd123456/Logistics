package com.javayjx.entity.ser;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 货物管理
 * @author Administrator
 */
@Entity
@Table(name="t_goods")
public class Goods {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length=50)
	private String number; //货物编号
	
	@Column(length=50)
	private String name; //货物名称
	
	@Column(length=10)
	private Integer type;//货物类型（1普通 2危货 3急货）
	
	@Column(length=10)
	private Integer num;  //货物数量
	
	
	
	@Column(precision = 10, scale = 2)
	private BigDecimal weight;  //货物重量
	
	@Column(precision = 10, scale = 2)
	private BigDecimal price1;  //货物价格
	
	@Column(precision = 10, scale = 2)
	private BigDecimal price2;  //货物收入
	
	@Column(length=100)
	private String remark ; //备注



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getPrice1() {
		return price1;
	}

	public void setPrice1(BigDecimal price1) {
		this.price1 = price1;
	}

	public BigDecimal getPrice2() {
		return price2;
	}

	public void setPrice2(BigDecimal price2) {
		this.price2 = price2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Goods{" +
				"id=" + id +
				", number='" + number + '\'' +
				", name='" + name + '\'' +
				", type=" + type +
				", num=" + num +
				", weight=" + weight +
				", price1=" + price1 +
				", price2=" + price2 +
				", remark='" + remark + '\'' +
				'}';
	}

	
}
