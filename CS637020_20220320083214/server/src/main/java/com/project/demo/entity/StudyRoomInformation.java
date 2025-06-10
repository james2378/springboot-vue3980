package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *自习室信息：(StudyRoomInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StudyRoomInformation")
public class StudyRoomInformation implements Serializable {

    //StudyRoomInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_room_information_id")
    private Integer study_room_information_id;
   // 商家
   @Basic
    private Integer business;
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
   // 座位数量
   @Basic
    private String number_of_seats;
   // 座位类型
   @Basic
    private String seat_type;
   // 空余座位
   @Basic
    private Integer spare_seats;
   // 每小时价格
   @Basic
    private String hourly_rate;
   // 自习室图片
   @Basic
    private String self_study_room_pictures;
   // 自习室简介
   @Basic
    private String introduction_to_self_study_room;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
