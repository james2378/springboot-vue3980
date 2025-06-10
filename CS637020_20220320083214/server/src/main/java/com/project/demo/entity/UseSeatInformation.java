package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *使用座位信息：(UseSeatInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "UseSeatInformation")
public class UseSeatInformation implements Serializable {

    //UseSeatInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_seat_information_id")
    private Integer use_seat_information_id;
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
   // 座位类型
   @Basic
    private String seat_type;
   // 预约时段
   @Basic
    private String appointment_period;
   // 预约人数
   @Basic
    private Integer number_of_appointments;
   // 管理员确认
   @Basic
    private String administrator_confirmation;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
