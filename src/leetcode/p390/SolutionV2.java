package leetcode.p390;

import org.junit.Test;

import org.junit.Assert ;

public class SolutionV2 {

	int min, max;
	int result,base;
	boolean forward;

	public int lastRemaining(int n) {
		if (n == 1)
			return 1;
		if (n == 2 || n == 3 || n == 4)
			return 2;

		result = 0;
		base = 1;

		min = 1;
		max = n;
		forward = true;

		while (min < max) {
			System.out.println(String.format("min:%s, max :%s, base:%s,remainder:%s,forward:%b",Integer.toBinaryString(min),Integer.toBinaryString(max),base,result, forward));
			if (forward) {
				forwardElimnate();
			} else {
				backwardElimnate();
			}
		}
		if(min==max){
			result=result+base*max;
		}else{
			result=result+base*0;
			
		}
		return  result;
	}

	public void forwardElimnate() {
		if(min==0){
			result=result+base*1;
			if((max&1)==0){
				max--;
			}
			min=min++;
		}else{
			result=result+base*0;
			if((max&1)==1){
				max--;
			}
			min=min+1;
		}
	    min=min>>1;
		max=max>>1;
		
		base=base<<1;
		forward = false;
	}

	public void backwardElimnate() {
		if((max&1)==0){//even
			result=result+base*1;
			if(min==0){
				min++;
			}
			max--;
		}else{//odd
			result=result+base*0;
			if(min==1){
				min++;
			}
			max--;
		}
	    min=min>>1;
		max=max>>1;
		base=base<<1;
		forward = true;
	}

	
	@Test
	public void test12() {
		int result = this.lastRemaining(12);
		System.out.println(result);
		Assert.assertEquals("Not correct",6, result);
	}

	@Test
	public void test9() {
		int result = this.lastRemaining(9);
		System.out.println(result);
		Assert.assertEquals("Not correct",6, result);
	}

	@Test
	public void test4() {
		int result = this.lastRemaining(4);
		System.out.println(result);
		Assert.assertEquals("Not correct",2, result);
	}

	@Test
	public void test6() {
		int result = this.lastRemaining(6);
		System.out.println(result);
		Assert.assertEquals("Not correct",4, result);
		
	}
	
	@Test
	public void test6102() {
		int result = this.lastRemaining(6102);
		System.out.println(result);
		Assert.assertEquals("Not correct",2008, result);
		
	}
}
