package com.axe.roomdome.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\'J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\tH\'J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u000e"}, d2 = {"Lcom/axe/roomdome/dao/UserDao;", "", "deleteUser", "", "user", "Lcom/axe/roomdome/bean/User;", "getUserById", "id", "getUsers", "Lio/reactivex/Single;", "", "insertUser", "", "updateUser", "app_debug"})
public abstract interface UserDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM user")
    public abstract io.reactivex.Single<java.util.List<com.axe.roomdome.bean.User>> getUsers();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM user where id=:id")
    public abstract com.axe.roomdome.bean.User getUserById(int id);
    
    @androidx.room.Insert()
    public abstract long insertUser(@org.jetbrains.annotations.NotNull()
    com.axe.roomdome.bean.User user);
    
    @androidx.room.Update()
    public abstract int updateUser(@org.jetbrains.annotations.NotNull()
    com.axe.roomdome.bean.User user);
    
    @androidx.room.Delete()
    public abstract int deleteUser(@org.jetbrains.annotations.NotNull()
    com.axe.roomdome.bean.User user);
}