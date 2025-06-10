package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *信誉值：(ReputationValue)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ReputationValue")
public class ReputationValue implements Serializable {

    //ReputationValue编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reputation_value_id")
    private Integer reputation_value_id;
   // 用户
   @Basic
    private Integer user;
   // 信誉分
   @Basic
    private String credit_score;
   // 备注
   @Basic
    private String remarks;
   // 扣分详情
   @Basic
    private String deduction_details;
   // 加分详情
   @Basic
    private String bonus_details;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
