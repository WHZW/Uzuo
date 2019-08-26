package com.whzw.yz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whzw.yz.mapper.ClroomMapper;
import com.whzw.yz.mapper.SeatMapper;
import com.whzw.yz.mapper.SeatOrderMapper;
import com.whzw.yz.mapper.TableMapper;
import com.whzw.yz.pojo.Clroom;
import com.whzw.yz.pojo.OrderCode;
import com.whzw.yz.pojo.SeatOrder;
import com.whzw.yz.util.OrderCodeUtil;
import com.whzw.yz.vo.seatshow.ClroomInfoVo;
import com.whzw.yz.vo.seatshow.DateTimeClroomIdVo;
import com.whzw.yz.vo.seatshow.SeatStatusVo;

/**
 * 查看图书馆自习室座位状态
 * 
 * @author zzy
 *
 */
@Service
public class SeatShowService {

	@Autowired
	private ClroomMapper clroomMapper;

	@Autowired
	private TableMapper tableMapper;

	@Autowired
	private SeatMapper seatMapper;

	@Autowired
	private SeatOrderMapper seatOrderMapper;

	/**
	 * 获得指定id教室的所有座位状态
	 * 
	 * @param datetimeClroomIdVo
	 * @return
	 */
	public List<SeatStatusVo> getAllSeatsSatus(DateTimeClroomIdVo datetimeClroomIdVo) {
		String clroomId = datetimeClroomIdVo.getClroomId();

		// 获取所有座位的id
		List<String> seatIds = getAllSeatsId(clroomId);

		String orderCode = null;
		SeatOrder seatOrder = null;
		SeatStatusVo seatStatusVo = null;
		List<SeatStatusVo> seatStatusVos = new ArrayList<>();
		// 生成预订信息编码并添加进列表
		for (String sid : seatIds) {
			orderCode = OrderCodeUtil.encode(new OrderCode(datetimeClroomIdVo.getYear(), datetimeClroomIdVo.getMonth(),
					datetimeClroomIdVo.getDay(), datetimeClroomIdVo.getTimeQuantum(), sid));
			seatOrder = seatOrderMapper.findOneByCode(orderCode);
			seatStatusVo = new SeatStatusVo();
			seatStatusVo.setSeatId(sid);
			if (seatOrder == null) {
				seatStatusVo.setStatus(0);
			} else {
				seatStatusVo.setStatus(1);
			}
			seatStatusVos.add(seatStatusVo);
		}

		// 获取所有座位的状态并返回
		return seatStatusVos;
	}

	/**
	 * 获取教室信息对象（教室--》桌子列表--》座位列表）不包含座位状态
	 * 
	 * @param clroomId
	 * @param date
	 * @param timeQuantum
	 * @return
	 */
	public Clroom getClroomInfo(String clroomId) {
		return clroomMapper.findOneById(clroomId);
	}

	/**
	 * 获取教室所有座位的id
	 * 
	 * @param clroomId
	 * @return
	 */
	public List<String> getAllSeatsId(String clroomId) {
		List<String> tables = tableMapper.findAllTablesId(clroomId);
		if (tables == null)
			return null;
		List<String> seatIds = new ArrayList<>();
		for (String tid : tables) {
			// 将查到的seatId全部添加到查询结果集中
			seatIds.addAll(seatMapper.findAllIdByTableId(tid));
		}
		return seatIds;
	}

	/**
	 * 获取所有教室信息
	 * 
	 * @param datetimeClroomIdVo
	 * @return
	 */
	public List<ClroomInfoVo> getAllClroomInfo(DateTimeClroomIdVo datetimeClroomIdVo) {
		// 查找所有教室信息
		List<ClroomInfoVo> clroomInfoVos = clroomMapper.findAllClrooms();
		if (clroomInfoVos == null) {
			return null;
		}
		// 遍历所有教室
		for (ClroomInfoVo clroomInfo : clroomInfoVos) {
			// 为查找座位状态临时类设置教室id
			datetimeClroomIdVo.setClroomId(clroomInfo.getClroomId());
			// 查询座位状态
			List<SeatStatusVo> seatStatus = getAllSeatsSatus(datetimeClroomIdVo);
			if (seatStatus == null) {
				return null;
			}
			// 设置座位数
			clroomInfo.setSeatNum(seatStatus.size());
			int freeNum = 0;
			// 统计空闲座位数
			for (SeatStatusVo ssv : seatStatus) {
				if (ssv.getStatus() == 0)
					freeNum++;
			}
			// 设置空闲座位数
			clroomInfo.setFreeNum(freeNum);
		}
		return clroomInfoVos;
	}

}
