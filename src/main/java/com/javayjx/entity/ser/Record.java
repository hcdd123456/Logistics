package com.javayjx.entity.ser;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.javayjx.entity.base.CustomDateTimeSerializer;

@Entity
@Table(name="t_record")
public class Record {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=50)
	private String name; //操作人
	
	@Temporal(TemporalType.TIMESTAMP) 
	private Date createDateTime;//操作时间 
	
	@Column(length=50)
	private String content; //操作内容
	
	@Column(length=10)
	private Integer type;//类型 1订单  2客户   3车辆  4 员工  5仓储 6调度


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Record{" +
				"id=" + id +
				", name='" + name + '\'' +
				", createDateTime=" + createDateTime +
				", content='" + content + '\'' +
				", type=" + type +
				'}';
	}
	
	
}
