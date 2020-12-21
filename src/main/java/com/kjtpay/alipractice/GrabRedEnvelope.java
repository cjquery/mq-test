package com.kjtpay.alipractice;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Package: com.kjtpay.alipractice
 * @ClassName: GrabRedEnvelope
 * @author: caojiaqi
 * @Date: Created in 2020/12/14 17:01
 * @Description： TODO
 */
public class GrabRedEnvelope {
	//评测题目: 红包算法，给定一个红包总金额和分红包的人数，输出每个人随机抢到的红包数量。
// 要求：
// 1. 每个人都要抢到红包，并且金额随机
// 2. 每个人抢到的金额数不小于1
// 3. 每个人抢到的金额数不超过总金额的30%
// 例如总金额100，人数10，输出【19 20 15 1 25 14 2 2 1 1】
	//最少分得红包数
	private static final double min = 1;
	//最多分得红包数占比
	private static final double percentMax = 0.3;

	public static void allocateMoney(double money, int peopleNum) {
		double minMoney = min;
		double maxMoney = percentMax * money;
		double shareMoney = 0;
		double sum = 0;
		for (int i = 0; i < peopleNum; i++) {
			minMoney = minMoney > money - maxMoney * (peopleNum - i - 1) ? minMoney : (money - maxMoney * (peopleNum - i - 1));
			maxMoney = maxMoney < money - minMoney * (peopleNum - i - 1) ? maxMoney : (money - minMoney * (peopleNum - i - 1));
			shareMoney = new BigDecimal((maxMoney - minMoney) * Math.random() + minMoney).setScale(2, RoundingMode.UP).doubleValue();
			money = money - shareMoney;
			sum += shareMoney;
			System.out.println("第" + (i + 1) + "个人抢到:" + shareMoney + "元");
		}
		System.out.println("要分配的红包总额为:" + sum + "元");
	}
	public static void main(String[] args){
		allocateMoney(100,10);
	}

}
