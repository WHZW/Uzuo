package com.whzw.yz.service;



import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.whzw.yz.exception.GlobalException;
import com.whzw.yz.mapper.OrderLogMapper;
import com.whzw.yz.mapper.SeatOrderMapper;
import com.whzw.yz.pojo.OrderLog;
import com.whzw.yz.pojo.SeatOrder;
import com.whzw.yz.result.CodeMsg;
import com.whzw.yz.vo.OrderVo;

/**
 * 座位预约
 * @author wubn
 *
 */
@Service
public class SeatOrderService {
	
	@Autowired
	SeatOrderMapper seatOrderMapper;
	@Autowired
	OrderLogMapper orderLogMapper;
	
	private Map<String, Date> orderMap;

	@PostConstruct
	private void init() {
		orderMap = new ConcurrentHashMap<>();
		//创建守护线程，查看订单是否超时
		/*Thread timeCountThread = new Thread(new Runnable() {			
			@Override
			public void run() {
				while(true) {
					for(String key : orderMap.keySet()) {
						Date timeoutDate = orderMap.get(key);
						Date currentDate = new Date();
						if (timeoutDate.before(currentDate)) {
							//删除订单记录
							mapper.deleteOrder(key);
							//修改日志
							//todo
							
						}
					}
					try {
						//5秒扫描一次
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		timeCountThread.setDaemon(true);
		timeCountThread.start();*/
		
	}

	/**
	 * 预定座位，判断session中的令牌与cookie中的令牌是否一致
	 * @param orderVo
	 * @param request
	 * @return
	 */
	public boolean order(OrderVo orderVo, HttpServletRequest request) {
//		Cookie[] cookies = request.getCookies();
//		String cooikeValue = getCookieValue(cookies);
//		if(cooikeValue == null) {
//			throw new GlobalException(CodeMsg.SESSION_ERROR);
//		}
//		HttpSession session = request.getSession();
//		String token = (String) session.getAttribute("token");
//		if(!cooikeValue.equals(token)) {
//			throw new GlobalException(CodeMsg.SESSION_ERROR);
//		}
//		String studentId = (String) session.getAttribute("studentId");
		String studentId = "20164545";//测试用
		String orderCode = orderVo.getYear() + orderVo.getMonth() + 
				orderVo.getDay() + orderVo.getTimeQuantem() + orderVo.getSeatId();
		if(seatOrderMapper.getIrderCode(orderCode) != null) {
			throw new GlobalException(CodeMsg.SEAT_ALREDY_ORDERED);
		}
		createOrder(orderVo, studentId);
		return true;
	}

	/**
	 * 创建预定记录
	 * @param orderVo
	 * @param studentId 
	 */
	@Transactional
	private Boolean createOrder(OrderVo orderVo, String studentId) {
		String orderCode = String.valueOf(orderVo.getYear()) + String.valueOf(orderVo.getMonth()) + 
				String.valueOf(orderVo.getDay()) + String.valueOf(orderVo.getTimeQuantem()) + orderVo.getSeatId();
		System.out.println(orderCode);
		SeatOrder seatOrder = new SeatOrder();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		seatOrder.setOrderTime(new Date());
		Date date = new Date(orderVo.getYear() - 1900, orderVo.getMonth(), orderVo.getDay());
		seatOrder.setDate(date);
		seatOrder.setStudentId(studentId);
		seatOrder.setSeatId(orderVo.getSeatId());
		seatOrder.setTimeQuantun(orderVo.getTimeQuantem());
		seatOrder.setOrderCode(orderCode);
		//写入预定记录
		seatOrderMapper.addOrder(seatOrder);
//		//记录超时时间
//		Date timeoutDate = getTimeoutDate(date, orderVo.getTimeQuantem());
//		orderMap.put(seatOrder.getId(), timeoutDate);
		
		//写入日志
		OrderLog orderLog = new OrderLog();
		orderLog.setSeatId(orderVo.getSeatId());
		orderLog.setStudentId(studentId);
		orderLog.setOrderId(seatOrder.getId());		
		orderLog.setOrderTime(seatOrder.getOrderTime());
		orderLogMapper.addLog(orderLog);
		
		return true;
	}

	/**
	 * 计算超时时间
	 * @param date
	 * @param timeQuantem
	 * @return
	 */
	private Date getTimeoutDate(Date date, char timeQuantem) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取cookie
	 * @param cookies
	 * @return
	 */
	private String getCookieValue(Cookie[] cookies) {
		for (Cookie cookie : cookies) {
			if(cookie.getName() == "token") {
				return cookie.getValue();
			}
		}
		return null;
	}
}
