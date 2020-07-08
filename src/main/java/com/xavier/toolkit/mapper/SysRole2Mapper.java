package com.xavier.toolkit.mapper;

import com.xavier.toolkit.entity.SysRole1;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface SysRole2Mapper {
    //mapUnderscoreToCamelCase is configured in yaml
    @Select({
            "select id, role_name, enabled, create_by, create_time createTime ",
            "from sys_role ",
            "where id = #{id}"
    })
    SysRole1 selectById(Integer id);

    @Results({
            @Result(property="id", column="id", id=true),
            @Result(property="roleName", column="role_name"),
            @Result(property="enabled", column="enabled"),
            @Result(property="createBy", column="create_by"),
            @Result(property="createTime", column="create_time")
    })
    @Select({"select id, role_name, enabled, create_by, create_time ",
                    "from sys_role where id =#{id}"})
    SysRole1 selectById2(Integer id);
}
