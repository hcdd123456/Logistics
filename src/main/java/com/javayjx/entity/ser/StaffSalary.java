package com.javayjx.entity.ser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author hc
 * @create 2020/9/26 0026 22:21
 */
@Entity
@Table(name="t_staffSalary")
public class StaffSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="staffId")
    private Staff staff;

    @Column(length=50)
    @NotNull(message = "薪水不可为空")
    private Double salary;

    @Column(length=100)
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "StaffSalary{" +
                "id=" + id +
                ", staff=" + staff +
                ", salary=" + salary +
                ", remark='" + remark + '\'' +
                '}';
    }
}
