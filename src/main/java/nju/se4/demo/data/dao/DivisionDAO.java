package nju.se4.demo.data.dao;

import nju.se4.demo.data.entity.Division;
import nju.se4.demo.data.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 10/30/2018
 */
@Component
public interface DivisionDAO extends CrudRepository<Division, Integer> {

    List<Division> findAllByLabor(Group labor);

}
