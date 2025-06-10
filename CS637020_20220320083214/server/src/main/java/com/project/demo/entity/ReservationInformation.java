package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *预约信息：(ReservationInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ReservationInformation")
public class ReservationInformation implements Serializable {

    //ReservationInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_information_id")
    private Integer reservation_information_id;
   // 用户
   @Basic
    private Integer user;
   // 姓名
   @Basic
    private String full_name;
   // 联系方式
   @Basic
    private String contact_information;
   // 自习室名称
   @Basic
    private String name_of_study_room;
   // 自习室类型
   @Basic
    private String study_room_type;
   // 区域
   @Basic
    private String region;
   // 开放时间
   @Basic
    private String opening_hours;
   // 管理员电话
   @Basic
    private String administrator_telephone;
   // 地址
   @Basic
    private String address;
   // 每小时价格
   @Basic
    private String hourly_rate;
   // 座位类型
   @Basic
    private String seat_type;
   // 预约时段
   @Basic
    private String appointment_period;
   // 预约人数
   @Basic
    private Integer number_of_appointments;
   // 使用时长
   @Basic
    private Integer service_duration;
   // 费用
   @Basic
    private String cost;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
