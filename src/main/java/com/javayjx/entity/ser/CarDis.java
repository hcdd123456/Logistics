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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.javayjx.entity.base.CustomDateSerializer;

/**
 * #派遣车辆
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_car_dis")
public class CarDis {	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="carId")
	private Car car; // 车辆id
	
	@Temporal(TemporalType.DATE) 
	private Date createDateTime;// 日期
	
	@NotNull(message="说明不能为空！")
	@Column(length=200)
	private  String remark;//说明
	
	@ManyToOne
	@JoinColumn(name="staffId")
	private Staff staff; // 驾驶员

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "CarDis{" +
				"id=" + id +
				", car=" + car +
				", createDateTime=" + createDateTime +
				", remark='" + remark + '\'' +
				", staff=" + staff +
				'}';
	}
	

	
	
	
	
}
