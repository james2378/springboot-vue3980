package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *自习室管理员：(StudyRoomAdministrator)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StudyRoomAdministrator")
public class StudyRoomAdministrator implements Serializable {

    //StudyRoomAdministrator编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_room_administrator_id")
    private Integer study_room_administrator_id;
   // 商家账号
   @Basic
    private String merchant_account_number;
   // 姓名
   @Basic
    private String full_name;
   // 性别
   @Basic
    private String gender;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
