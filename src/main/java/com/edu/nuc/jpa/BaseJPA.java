package com.edu.nuc.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by macbookair on 2018/4/9.
 */
@NoRepositoryBean
public interface BaseJPA<T,ID extends Serializable> extends JpaRepository<T,ID> ,JpaSpecificationExecutor<T>,Serializable {
}
