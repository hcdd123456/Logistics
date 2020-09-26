package com.javayjx.dao.ser;

import com.javayjx.entity.ser.CarDis;
import com.javayjx.entity.ser.KeyBoard;
import com.javayjx.entity.ser.KeyBoardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeyBoardDao extends JpaRepository<KeyBoard,Integer>,JpaSpecificationExecutor<KeyBoard> {




}
