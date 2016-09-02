package leetcode.p386;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class Solution {

	@Test
	public void test() {
		int n=568;
		List<Integer> ret = this.lexicalOrder(n);
		for(Integer p :ret){
			System.out.println(p);
		}
	}
	
	int max;
	List<Integer> result;
	
	
	public List<Integer> lexicalOrder(int n) {
		result = new ArrayList<Integer>(n+1);
		this.max=n;
		for(int i=1;i<10;i++){
			this.processR(0,i);
			
		}
		return result;
	}
	
	private void processR(int base,int m){
		 int value =base*10+m;
		 if(value > this.max) return;
	 	 result.add(value);
	 	 for(int i=0;i<10;i++){
	 		 this.processR(value, i);;
	 	 }
	 	
	
	}

	private int getDigit(int n) {
		int d = 0;
		while (n > 0) {
			n = n / 10;
			d++;
		}
		return d;
	}
}
