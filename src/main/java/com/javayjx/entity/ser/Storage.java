package com.javayjx.entity.ser;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
/**
 * 仓库
 * @author Administrator
 */
@Entity
@Table(name="t_storage")
public class Storage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="goodsId")
	private Goods goods; // 货物名称id(获取货物存储)

	@ManyToOne
	@JoinColumn(name="warehouseId")
	private Warehouse warehouse;
	
	@Column(length=50)
	private String number; //仓库编号

//	@NotNull(message="仓库名称不能为空！")
	@Column(length=50)
	private String name; //仓库名称
	
	@Column(length=10)
	private Integer type;//仓库型号（1大2中3小）


	@Column(length=10)
	private String num;  //仓库数量/货物存储

	@NotNull(message="货物数量不能为空！")
	@Column(precision = 10, scale = 2)
	private BigDecimal weight;  //仓库重量
	
	@Column(length=10)
	private Integer man;//是否满仓 0没有  1满
	
	@Column(length=100)
	private String remark ; //备注

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Integer getMan() {
		return man;
	}

	public void setMan(Integer man) {
		this.man = man;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "Storage{" +
				"id=" + id +
				", goods=" + goods +
				", warehouse=" + warehouse +
				", number='" + number + '\'' +
				", name='" + name + '\'' +
				", type=" + type +
				", num='" + num + '\'' +
				", weight=" + weight +
				", man=" + man +
				", remark='" + remark + '\'' +
				'}';
	}
}
