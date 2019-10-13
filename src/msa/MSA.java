package msa;

import java.util.Random;

public class MSA {

	public static void main(String[] args) {

		answer maxSubarray = null;
		int array[] = createArray();
		
		
		for (int e : array) {
			System.out.print(e + "  ");
		}
		//Note: Array index begins at 0.
		maxSubarray = findMaximumSubarray(array, 0, findHigh(array));
		System.out.println("\n" + maxSubarray.toString());
	}
	
	public static int findHigh(int [] A) {
		int high = A.length - 1;
		return high;
	}
	
	public static answer findMaxCrossingSubarray(int[] A, int low, int mid, int hi) {
		int left_sum = Integer.MIN_VALUE;
		int sum = 0;
		int max_left = 0;
		int max_right = 0;
		for (int i = mid; i>= low; i--) {
			sum = sum + A[i];
			if (sum > left_sum) {
				left_sum = sum;
				max_left = i;
			}
		}
		int right_sum = Integer.MIN_VALUE;
		sum = 0;
		for (int j = mid + 1; j <= hi; j++) {
			sum = sum + A[j];
			if (sum > right_sum) {
				right_sum = sum;
				max_right = j;
			}
		}
		
		return new answer(max_left, max_right, left_sum + right_sum);
	}
	
	public static answer findMaximumSubarray(int[] A, int lowIndex, int hiIndex) {
		
		answer left_sub;
		answer right_sub;
		answer cross_sub;
		
		if (hiIndex == lowIndex) {
			return new answer(A[lowIndex], lowIndex, hiIndex);
		} else {
			int mid = (hiIndex+lowIndex)/2;
			
			left_sub = findMaximumSubarray(A, lowIndex, mid);
			right_sub = findMaximumSubarray(A, mid + 1, hiIndex);
			cross_sub = findMaxCrossingSubarray(A, lowIndex, mid, hiIndex);
			
			
		}
		if (left_sub.sum >= right_sub.sum && left_sub.sum >= cross_sub.sum) {
			return left_sub;
		} else if (right_sub.sum >= left_sub.sum && right_sub.sum >= cross_sub.sum) {
			return right_sub;
		} else  {
			return cross_sub;
		}
	}
	
	public static int[] createArray() {
		
		int[] mainArray = new int[16];
		Random r= new Random();
		
		for (int i = 0; i < mainArray.length; i++) {
			
			int tempNum = (int) (Math.random() * ((32 - (-32) + 1)) + -32);
			mainArray[i] = tempNum;
			
		}
		return mainArray;
	}

}

class answer {
	int sum;
	int low;
	int high;
	
	answer(int lo, int hi, int sums) {
		sum = sums;
		low = lo;
		high = hi;
	}
	
	@Override
	public String toString() {
		String result = "Sum of max subarray=" + sum + ", Low index=" + low + ", High index=" + high;
		return result;
	}

}

