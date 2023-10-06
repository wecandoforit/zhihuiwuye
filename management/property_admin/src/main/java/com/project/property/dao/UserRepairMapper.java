package com.project.property.dao;

import com.project.property.entity.UserComplaint;
import com.project.property.entity.UserRepair;
import com.project.property.entity.UserUnitRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author
 * @Date
 * @Description
 */
@Mapper
public interface UserRepairMapper {

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("ids") String id);

    /**
     * 全量插入
     * @param record
     * @return
     */
    int insert(UserRepair record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(UserRepair record);

    /**
     * 根据主键查询单条数据
     * @param id
     * @return
     */
    UserRepair selectByPrimaryKey(Integer id);

    /**
     * 根据主键选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserRepair record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserRepair record);

    /**
     * 根据条件查询
     * @param userRepair
     * @return
     */
    List<UserRepair> selectDataByParam(UserRepair userRepair);

    /**
     * 查询数据总量
     * @param userRepair
     * @return
     */
    Integer selectDataCount(UserRepair userRepair);

    /**
     * 查询ID和状态是否是未处理
     * @param ids
     * @return
     */
    List<UserRepair> selectByIdAndStatus(@Param("ids") String ids);

    Integer updateInfoByUserId(UserRepair userRepair);
}