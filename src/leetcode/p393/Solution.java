package leetcode.p393;

import org.junit.Test;

import junit.framework.Assert;

public class Solution {

	@Test
	public void test() {
		int[] input = {197,130,1};
		Assert.assertTrue(validUtf8(input));
	}
	
	@Test
	public void test2() {
		int[] input = {235, 140, 4};
		Assert.assertFalse(validUtf8(input));
	}
	
	@Test
	public void test3() {
		int[] input = {145};
		Assert.assertFalse(validUtf8(input));
	}
	
	@Test
	public void test4(){
		int[] input = {197,130,1};
		Assert.assertTrue(validUtf8(input));
	}
	

	public static int bits = 0b0000000000000000000000001000_0000;

	public boolean validUtf8(int[] data) {
		boolean result = true;
		for (int i = 0; i < data.length; i++) {
			System.out.println("P1:"+ Integer.toBinaryString(data[i]));
			int t = data[i] & bits;
			if (t == 0) continue;
		
			int b = 0;
			while (t != 0){
				b++;
				t =data[i] << b & bits;
			}
			
			if(b==1) return false;
			
			for (int x = 1; x < b; x++) {
				i++;
				System.out.println("P2:"+ Integer.toBinaryString(data[i]));
				if (i >= data.length) return false;
				t = (~data[i]) & bits;
				if (t != 0) return false;
				t = (data[i] << 1) & bits;
				if (t != 0) return false;
		    }
		}
		return result;
	}
}
