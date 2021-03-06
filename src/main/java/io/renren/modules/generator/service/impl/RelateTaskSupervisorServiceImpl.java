package io.renren.modules.generator.service.impl;

import io.renren.modules.generator.dao.*;
import io.renren.modules.generator.dto.RelateTaskSupervisorRspDTO;
import io.renren.modules.sys.dao.SysUserDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.entity.RelateTaskSupervisorEntity;
import io.renren.modules.generator.service.RelateTaskSupervisorService;

import javax.annotation.Resource;


@Service("relateTaskSupervisorService")
public class RelateTaskSupervisorServiceImpl extends ServiceImpl<RelateTaskSupervisorDao, RelateTaskSupervisorEntity> implements RelateTaskSupervisorService {

    @Resource
    RelateTaskSupervisorDao relateTaskSupervisorDao;
    @Resource
    CourseTeacherClassroomDao courseTeacherClassroomDao;
    @Resource
    SysUserDao sysUserDao;
    @Resource
    TaskDao taskDao;
    @Resource
    TeacherDao teacherDao;
    @Resource
    CourseDao courseDao;
    @Resource
    ClassroomDao classroomDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RelateTaskSupervisorEntity> page = this.page(
                new Query<RelateTaskSupervisorEntity>().getPage(params),
                new QueryWrapper<RelateTaskSupervisorEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<RelateTaskSupervisorRspDTO> listItems() {
        List<RelateTaskSupervisorRspDTO> relateTaskSupervisorRspDTOS = new ArrayList<>();
        List<Integer> relateIds = relateTaskSupervisorDao.selectRelateIds();
        for (Integer relateId : relateIds){
            RelateTaskSupervisorRspDTO relateTaskSupervisorRspDTO = new RelateTaskSupervisorRspDTO();
            //??????taskId???userId
            Integer taskId = relateTaskSupervisorDao.selectTaskIdByRelateId(relateId);
            Integer userId = relateTaskSupervisorDao.selectUserIdByRelateId(relateId);
            //
            String userFullName = sysUserDao.selectUserFullnameByUserId(userId);
            //????????????task_id??????teacher_id course_id classroom_id
            Integer unionId = taskDao.selectUnionId(taskId);
            Integer teacherId = courseTeacherClassroomDao.selectTeacherIdByUnionId(unionId);
            Integer courseId = courseTeacherClassroomDao.selectCourseIdByUnionId(unionId);
            Integer classroomId = courseTeacherClassroomDao.selectClassroomIdByUnionId(unionId);
            //?????????????????? ???????????? ????????????
            String teacherName = teacherDao.selectTeacherNameByTeacherId(teacherId);
            String courseName = courseDao.selectCourseNameByCourseId(courseId);
            String classroomNum = classroomDao.selectClassroomNumByClassroomId(classroomId);
            Date time = taskDao.selectTimeByTaskId(taskId);
            //status
            Integer status = relateTaskSupervisorDao.selectStatusByRelateId(relateId);
            String isFinish;
            if (status.equals(0)){
                isFinish="???";
            }else {
                isFinish="???";
            }
            relateTaskSupervisorRspDTO.setRelateTaskSupervisorId(relateId);
            relateTaskSupervisorRspDTO.setIsFinish(isFinish);
            relateTaskSupervisorRspDTO.setTeacherId(teacherId);
            relateTaskSupervisorRspDTO.setTeacherName(teacherName);
            relateTaskSupervisorRspDTO.setCourseId(courseId);
            relateTaskSupervisorRspDTO.setCourseName(courseName);
            relateTaskSupervisorRspDTO.setClassroomId(classroomId);
            relateTaskSupervisorRspDTO.setClassroomNum(classroomNum);
            relateTaskSupervisorRspDTO.setUserId(userId);
            relateTaskSupervisorRspDTO.setUserFullname(userFullName);
            relateTaskSupervisorRspDTO.setTime(time);

            relateTaskSupervisorRspDTOS.add(relateTaskSupervisorRspDTO);

        }
        return relateTaskSupervisorRspDTOS;
    }

}