package com.kjtpay.nsum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Package: com.kjtpay.nsum
 * @ClassName: Nsum
 * @author: 曹佳琪
 * @Date: Created in 2020/7/20 11:16
 * @Description： 求多数之和的通用方法，两数之和可以使用map
 */
public class Nsum {
	public static List<List<Integer>> nSumTarget(
			List<Integer>  nums, int n, int start, int target) {

		int sz = nums.size();
		List<List<Integer>> res=new ArrayList<>();
		// 至少是 2Sum，且数组大小不应该小于 n
		if (n < 2 || sz < n){
			return res;
		}
		// 2Sum 是 base case
		if (n == 2) {
			// 双指针那一套操作
			int lo = start, hi = sz - 1;
			while (lo < hi) {
				int sum = nums.get(lo) + nums.get(hi);
				int left =  nums.get(lo);
				int right =  nums.get(hi);
				if (sum < target) {
					while (lo < hi && nums.get(lo) == left){
						lo++;
					}
				} else if (sum > target) {
					while (lo < hi && nums.get(hi) == right){
						hi--;
					}

				} else {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(left);
					list.add(right);
					res.add(list);
					while (lo < hi && nums.get(lo) == left){
						lo++;
					}
					while (lo < hi && nums.get(hi) == right){
						hi--;
					}
				}
			}
		} else {
			// n > 2 时，递归计算 (n-1)Sum 的结果
			for (int i = start; i < sz; i++) {
				List<List<Integer>> sub =
						nSumTarget(nums, n - 1, i + 1, target - nums.get(i));
				for (List<Integer> arr : sub) {
					// (n-1)Sum 加上 nums[i] 就是 nSum
					arr.add(nums.get(i));
					res.add(arr);
				}
				while (i < sz - 1 && nums.get(i) == nums.get(i + 1)){
					i++;
				}
			}
		}
		return res;
	}
	public static void main(String[] args){
		 	List<Integer>  nums =new ArrayList<>();
		 	nums.add(1);
		 	nums.add(0);
		 	nums.add(-1);
		 	nums.add(0);
		 	nums.add(-2);
		 	nums.add(2);
		 	System.out.println(nums);
			Collections.sort(nums);
			System.out.println(nums);
			List<List<Integer>> lists = nSumTarget(nums, 4, 0, 0);
			System.out.println(lists);

	}

}
