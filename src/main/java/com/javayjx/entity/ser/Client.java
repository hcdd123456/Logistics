package com.javayjx.entity.ser;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "t_client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length=11)
	private  String number;//客户编号
	@NotBlank(message="姓名不能为空！")
	@Column(length=11)
	private  String trueName;//姓名
	@NotBlank(message="电话不能为空！")
	@Column(length=11)
	private  String phone;//电话
	@Column(length=10)
	private Integer sex;// 1男  2女
	
	
	@Column(length=100)
	private  String remark;//备注
	
	@Column(length=10)
	private Integer age;// 1男  2女
	@Column(length=100)
	private String address;// 地址
	
	
	@Column(length=10)
	private Integer transaction;//交易次数
	
	@Column(length=10)
	private Integer type;// 1普通，2星级


	public Integer getTransaction() {
		return transaction;
	}
	public void setTransaction(Integer transaction) {
		this.transaction = transaction;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

 

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", number='" + number + '\'' +
				", trueName='" + trueName + '\'' +
				", phone='" + phone + '\'' +
				", sex=" + sex +
				", remark='" + remark + '\'' +
				", age=" + age +
				", address='" + address + '\'' +
				", transaction=" + transaction +
				", type=" + type +
				'}';
	}


}
