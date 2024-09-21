package com.example.demo.mapper;

import com.example.demo.model.TestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

@Mapper
public interface TestMapper {
    TestEntity getOneTest(@Param("id") long id);
    void updateProcess(@Param("pcs") String pcs);

    @Select("select * from looking.test")
    Collection<TestEntity> findAll();

    Collection<TestEntity> findAllXml();
}