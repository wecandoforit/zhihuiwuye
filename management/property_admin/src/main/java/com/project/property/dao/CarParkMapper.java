package com.project.property.dao;

import com.project.property.entity.CarPark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author
 * @Date
 * @Description
 */
@Mapper
public interface CarParkMapper {

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 全量插入
     * @param record
     * @return
     */
    int insert(CarPark record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(CarPark record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    CarPark selectByPrimaryKey(Integer id);

    /**
     * 根据主键选择性更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CarPark record);

    /**
     * 根据主键更新信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(CarPark record);

    /**
     * 查询数据总量
     * @param carPark   查询条件
     * @return
     */
    Integer selectDataCount(CarPark carPark);

    /**
     * 根据条件查询
     * @param carPark   查询条件
     * @return
     */
    List<CarPark> selectDataByParam(CarPark carPark);

    List<CarPark> selectInfoByUserName(@Param("userName") String userName);
}