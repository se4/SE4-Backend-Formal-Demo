package nju.se4.demo.data.dao;

import nju.se4.demo.data.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @InterfaceName HomeworkDAO
 * @PackageName nju.se4.demo.data.dao
 * @Author sheen
 * @Date 2018/11/4
 * @Version 1.0
 * @Description 作业DAO
 **/
public interface HomeworkDAO extends JpaRepository<Homework,Integer> {
    Homework findHomeworkByName(String name);
}
