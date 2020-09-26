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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.javayjx.entity.base.CustomDateSerializer;

/**
 * 员工管理表
 * @author Administrator
 */
@Entity
@Table(name="t_staff")
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length=50)
	@NotBlank(message = "姓名不能为空！")
	private String name; // 姓名
	@Column(length=10)
	private Integer age;//年龄
	@Column(length=50)
	private String address; //地址
	@Column(length=20)
	private String phone; //电话
	
	
	@Temporal(TemporalType.DATE) 
	private Date createDateTime;//入职时间 
	@Column(length=10)
	private Integer state;//  工作状态  正常=1  0=离职  2 退休
	@Column(length=100)
	private String remark ; //备注
	
	
	@Column(precision = 10, scale = 2)
	private BigDecimal wages;  //工资
	@Column(length=100)
	private String sfz ; //身份证

	@Column(length=10)
	private Integer amount;


	public BigDecimal getWages() {
		return wages;
	}

	public void setWages(BigDecimal wages) {
		this.wages = wages;
	}

	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Staff{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", address='" + address + '\'' +
				", phone='" + phone + '\'' +
				", createDateTime=" + createDateTime +
				", state=" + state +
				", remark='" + remark + '\'' +
				", wages=" + wages +
				", sfz='" + sfz + '\'' +
				", amount=" + amount +
				'}';
	}


}
