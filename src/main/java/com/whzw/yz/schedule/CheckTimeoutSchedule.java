package com.whzw.yz.schedule;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.whzw.yz.mapper.OrderLogMapper;
import com.whzw.yz.service.SeatOrderService;

/**
 * 没隔5秒检查一次订单是否过期
 * @author wubn
 *
 */
@Component
public class CheckTimeoutSchedule {
	
	@Autowired
	SeatOrderService seatOrderService;
	
	@Autowired
	OrderLogMapper orderLogMappper;

	@Scheduled(fixedRate = 5000)
	@Transactional
	public void checkTimeout() {
		Map<String, Date> orderMap = seatOrderService.getOrderMap();
		Date currentDate = new Date();
		for(String key : orderMap.keySet()) {
			if(orderMap.get(key).before(currentDate)) {
				try {
					seatOrderService.cancelOrder(key);
					System.out.println("订单：" + key + "已超时");
					orderLogMappper.updateStatus(key, "超时");
					//TODO 扣减信用积分	
					
				}catch (Exception e) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					e.printStackTrace();
					throw e;
				}
				
			}
		}
	}
	
//	public static void main(String[] args) {
//		Date date = new Date(2019 - 1900, 8 - 1, 25);
//		System.out.println(date.before(new Date()));
//	}
}
