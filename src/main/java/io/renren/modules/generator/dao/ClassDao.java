package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.ClassEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-09 18:02:48
 */
@Mapper
public interface ClassDao extends BaseMapper<ClassEntity> {
    String selectClassGradeByClassId(@Param("classId") Integer classId);
    String selectClassSpecialityByClassId(@Param("classId") Integer classId);
    String selectClassNameByClassId(@Param("classId") Integer classId);
    List<Integer> selectClassId();
}