package com.whzw.yz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.whzw.yz.exception.GlobalException;
import com.whzw.yz.mapper.LoginMapper;
import com.whzw.yz.mapper.StudentMapper;
import com.whzw.yz.pojo.Student;
import com.whzw.yz.pojo.StudentLogin;
import com.whzw.yz.result.CodeMsg;
import com.whzw.yz.result.Result;
import com.whzw.yz.vo.RegisteVo;

/**
 * 注册
 * @author wubn
 *
 */
@Service
@Transactional
public class RegisteService {
	
	@Autowired
	StudentMapper studentMapper;
	
	@Autowired
	LoginMapper loginMapper;
	
	public Boolean do_Registe(RegisteVo registeVo) {
		try {
			String studetnId = studentMapper.getStudentIdById(registeVo.getStudentId());
			if(studetnId != null) {
				throw new GlobalException(CodeMsg.STUDENT_EXIST);
			}
			Student student = new Student();
			student.setStudentId(registeVo.getStudentId());
			student.setName(registeVo.getName());
			student.setCollege(registeVo.getCollege());
			student.setGender(registeVo.getGender());
			student.setPhone(registeVo.getPhone());
			student.setSubject(registeVo.getSubject());
			studentMapper.addStudent(student);
			
			StudentLogin studentLogin = new StudentLogin();
			studentLogin.setStudentId(registeVo.getStudentId());
			studentLogin.setPassword(registeVo.getPassword());
			loginMapper.addStudent(studentLogin);			
			return true;			
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw e;
		}
	}

}
