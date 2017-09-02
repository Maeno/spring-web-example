package org.maeno.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.maeno.example.domain.Account;

@Mapper
public interface AccountMapper {

    Account auth(@Param("searchKey") String searchKey,
                 @Param("password") String password);

    Account loadAccount(String searchKey);
}
