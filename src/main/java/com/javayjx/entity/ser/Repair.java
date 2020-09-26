package com.javayjx.entity.ser;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.javayjx.entity.base.CustomDateSerializer;

/**
 * 维修管理
 * @author Administrator
 *
 */
@Entity
@Table(name="t_repair")
public class Repair {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="carId")
	private Car car; // 车辆id
	
	@Temporal(TemporalType.DATE) 
	private Date createDateTime;//维修 时间 

	@Column(length=10)
	private Integer type;//维修部位   1车身 2轮胎  3零件
	
	@Column(length=10)
	private Integer day;// 维修天数



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}
	
	
	
	
	
}
