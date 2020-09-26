package com.javayjx.entity.ser;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hc
 * @create 2020/9/24 0024 14:47
 */
@Entity
@Table(name="t_warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(length = 50)
    @NotBlank(message="仓库名称不能为空！")
    private String warehouseName;

    @Column(length=50)
    private Integer warehouseType;

    @Column(length=50)
    private Integer warehouseModel;


    @Column(length=50)
    @NotBlank(message="仓库容量不能为空！")
    private String warehouseCapacity;

    @Column(length=100)
    private String remark;

    @ManyToOne
    @JoinColumn(name="warehouseId")
    private Warehouse warehouse;


    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseType=" + warehouseType +
                ", warehouseModel=" + warehouseModel +
                ", warehouseCapacity='" + warehouseCapacity + '\'' +
                ", remark='" + remark + '\'' +
                ", warehouse=" + warehouse +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(Integer warehouseType) {
        this.warehouseType = warehouseType;
    }

    public Integer getWarehouseModel() {
        return warehouseModel;
    }

    public void setWarehouseModel(Integer warehouseModel) {
        this.warehouseModel = warehouseModel;
    }

    public String getWarehouseCapacity() {
        return warehouseCapacity;
    }

    public void setWarehouseCapacity(String warehouseCapacity) {
        this.warehouseCapacity = warehouseCapacity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
