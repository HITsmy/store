package com.hit.store.mapper;

import com.hit.store.entity.User;

import java.util.Date;

/**
 * 对应用户类的mapper层
 */

public interface UserMapper {
    /**
     *
     * @param user
     * @return 通过返回值反应插入结果
     */
    Integer insert(User user);

    /**
     *
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return 通过返回值反应更改结果
     */
    Integer updatePassword(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     *
     * @param user user充当数据传输中介，
     * 其中uid、phone、email、gender、modifiedUser、modifiedTime会被用到
     *
     * @return 通过返回值反应更改结果
     */
    Integer updateInfo(User user);

    /**
     *
     * @param user user充当数据传输中介，
     * 其中uid, avatar, modifiedUser,modifiedTime会被用到
     * @return 通过返回值反应更改结果
     */
    Integer updateAvatar(User user);

    /**
     *
     * @param user
     * @return 通过返回值反应删除结果
     */
    Integer delete(User user);

    /**
     *
     * @param username
     * @return 返回查询到的用户实体
     */
    User findByUsername(String username);

    /**
     *
     * @param uid
     * @return 返回查询到的用户实体
     */
    User findByUid(Integer uid);


}
