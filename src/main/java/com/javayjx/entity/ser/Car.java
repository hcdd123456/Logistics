package com.javayjx.entity.ser;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.javayjx.entity.base.CustomDateSerializer;


@Entity
@Table(name = "t_car")
public class Car {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="车牌号码不能为空！")
	@Column(length=11)
	private String number;//车牌号码
	@NotNull(message="颜色不能为空！")
	@Column(length=11)
	private  String color;//颜色
	@NotNull(message="车辆类型不能为空！")
	@Column(length=11)
	private  String type;//车辆类型
	@NotNull(message="存储仓库不存在！！！")
	@Column(precision = 10, scale = 2)
	private BigDecimal weight;  //车辆载重
	
	@Temporal(TemporalType.DATE) 
	private Date createDateTime;//使用时间
	
	@Column(length=10)
	private Integer state;//判断  是否维修   默认=0  维修=1
	
	@Column(length=10)
	private Integer weixiu;// 1已维修， 0未维修
	
	@Column(length=100)
	private  String remark;//备注


	public Integer getWeixiu() {
		return weixiu;
	}

	public void setWeixiu(Integer weixiu) {
		this.weixiu = weixiu;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Car{" +
				"id=" + id +
				", number='" + number + '\'' +
				", color='" + color + '\'' +
				", type='" + type + '\'' +
				", weight=" + weight +
				", createDateTime=" + createDateTime +
				", state=" + state +
				", weixiu=" + weixiu +
				", remark='" + remark + '\'' +
				'}';
	}
	
	
	
}
