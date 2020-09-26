package com.javayjx.entity.ser;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.javayjx.entity.base.CustomDateTimeSerializer;

@Entity
@Table(name="t_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=50)
	private String number; // 订单编号
	
	@Column(length=50)
	private String name1; // 寄件人
	@Column(length=50)
	private String address1; //寄件地址
	@Column(length=100)
	private String detail1; //寄件详细
	@Column(length=20)
	private String phone1; //电话
	
	
	@Column(length=50)
	private String name2; // 收件人
	@Column(length=50)
	private String address2; // 收件地址
	@Column(length=100)
	private String detail2; //寄件详细
	@Column(length=20)
	private String phone2; //电话
	
	
	@Column(length=50)
	private String receiver ; // 业务员编号

	@ManyToOne
	@JoinColumn(name="staffId")
	private Staff staff;

	@NotNull(message="价格不能为空！")
	@Column(precision = 10, scale = 2)
	private BigDecimal price;  //价格
	@NotNull(message="物品重量不能为空！")
	@Column(precision = 10, scale = 2)
	private BigDecimal weight;  //物品重量
	@Column(length=100)
	private String remark ; //备注
	@Temporal(TemporalType.TIMESTAMP) 
	private Date createDateTime;//创建时间
	@Column(length=10)
	private Integer state;//判断 是否接单  默认0
	
	
	@Column(precision = 10, scale = 6)
	private BigDecimal p1_lng;//坐标1
	@Column(precision = 10, scale = 6)
	private BigDecimal p1_lat;//坐标1
	@Column(precision = 10, scale = 6)
	private BigDecimal p2_lng;//坐标2
	@Column(precision = 10, scale = 6)
	private BigDecimal p2_lat;//坐标2

	public BigDecimal getP1_lng() {
		return p1_lng;
	}

	public void setP1_lng(BigDecimal p1_lng) {
		this.p1_lng = p1_lng;
	}

	public BigDecimal getP1_lat() {
		return p1_lat;
	}

	public void setP1_lat(BigDecimal p1_lat) {
		this.p1_lat = p1_lat;
	}

	public BigDecimal getP2_lng() {
		return p2_lng;
	}

	public void setP2_lng(BigDecimal p2_lng) {
		this.p2_lng = p2_lng;
	}

	public BigDecimal getP2_lat() {
		return p2_lat;
	}

	public void setP2_lat(BigDecimal p2_lat) {
		this.p2_lat = p2_lat;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
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

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getDetail1() {
		return detail1;
	}

	public void setDetail1(String detail1) {
		this.detail1 = detail1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDetail2() {
		return detail2;
	}

	public void setDetail2(String detail2) {
		this.detail2 = detail2;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
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

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", number='" + number + '\'' +
				", name1='" + name1 + '\'' +
				", address1='" + address1 + '\'' +
				", detail1='" + detail1 + '\'' +
				", phone1='" + phone1 + '\'' +
				", name2='" + name2 + '\'' +
				", address2='" + address2 + '\'' +
				", detail2='" + detail2 + '\'' +
				", phone2='" + phone2 + '\'' +
				", receiver='" + receiver + '\'' +
				", staff=" + staff +
				", price=" + price +
				", weight=" + weight +
				", remark='" + remark + '\'' +
				", createDateTime=" + createDateTime +
				", state=" + state +
				", p1_lng=" + p1_lng +
				", p1_lat=" + p1_lat +
				", p2_lng=" + p2_lng +
				", p2_lat=" + p2_lat +
				'}';
	}
}
