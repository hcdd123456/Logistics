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
 * 车辆申请
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_car_app")
public class CarApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="carId")
	private Car car; // 车辆id
	
	@ManyToOne
	@JoinColumn(name="staffId")
	private Staff staff; // 驾驶员
	
	
	@Temporal(TemporalType.DATE) 
	private Date createDateTime;//申请日期
	
	@NotNull(message="理由不能为空！")
	@Column(length=200)
	private  String reason;//理由


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

	 
	
	
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "CarApp{" +
				"id=" + id +
				", car=" + car +
				", staff=" + staff +
				", createDateTime=" + createDateTime +
				", reason='" + reason + '\'' +
				'}';
	}
	
	
}
