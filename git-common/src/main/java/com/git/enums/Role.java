package com.git.enums;

import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;

/**
 * 角色 枚举
 */
public enum Role {
    // 普通用户
    roleGuest("roleGuest", "guest", "匿名用户"),
    // 部长
    roleLeader("roleLeader", "部长"),
    // 超级管理员
    roleAdmin("roleAdmin", "超级管理员", "系统管理员"),
    // 副值
    roleGroupSlave("roleGroupSlave", "副值", "副值烧烤员"),
    // 总值
    roleGroupLeader("roleGroupLeader", "总值", "值班长"),
    // 正值
    roleGroupMaster("roleGroupMaster", "正值", "主值", "监控"),
    // 监控员
    roleGroupMonitor("roleGroupMonitor", "监控", "监控员"),
    // 见习
    roleGroupTrainee("roleGroupTrainee", "实习", "见习");

    // 系统内部统一名称
    private String roleName;

    // 别名
    private String[] aliases;

    private Role(String roleName, String... aliases) {
        this.setRoleName(roleName); // 角色名
        this.aliases = aliases; // 角色别名
    }

    private boolean isRoleAlias(String roleAlias) {
        return ArrayUtils.contains(aliases, roleAlias);
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    /**
     * 根据别名获取用户角色
     * @param roleAlias 别名
     * @return 角色名
     */
    public static Role getRoleByAlias(String roleAlias) {
        // 方法一:
        for (Role role : Role.values()) {
            if (role.isRoleAlias(roleAlias)) {
                return role;
            }
        }
        
        // 方法二:
        /*Role[] roles = {roleGuest, roleLeader, roleAdmin, roleGroupSlave, roleGroupLeader, roleGroupMaster,
                roleGroupMonitor, roleGroupTrainee};
        for (Role r : roles) {
            if (r.isRoleAlias(roleAlias)) {
                return r;
            }
        }*/
        return roleGuest;
    }
    
    /**
     * 根据角色名获取别名
     * @param role 角色名
     * @return 别名; 譬如:[副值, 副值烧烤员]
     */
    public static String getAliasByRole(Role role) {
        return Arrays.toString(role.aliases);
    }
}
