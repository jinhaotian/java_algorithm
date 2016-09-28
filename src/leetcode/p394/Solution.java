package leetcode.p394;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

public class Solution {

	// Python
	// def decodeString(self, s):
	// while '[' in s:
	// s = re.sub(r'(\d+)\[([a-z]*)\]', lambda m: int(m.group(1)) * m.group(2), s)
	// return s
	// Ruby
	// def decode_string(s)
	// 1 while s.gsub!(/(\d+)\[([a-z]*)\]/) { $2 * $1.to_i }
	// s
	// end
	@Test
	public void test() {
		System.out.println(this.decodeString("sd2[f2[e]g]i"));
	}

	public String decodeString(String s) {
		//String r = this.process(s, 0, s.length());
		String r = this.processStack(s, 0, s.length());
		return r;
	}

	public String process(String s, int iStart, int iEnd) {
		StringBuilder sb = new StringBuilder();
		int start = iStart;
		int end = iEnd;

		int rStart = iStart;
		int rEnd = iEnd;
		int wordStart = iStart;

		for (int i = start; i < end; i++) {
			while (i < end && Character.isLetter(s.charAt(i))) {
				sb.append(s.charAt(i));
				i++;
			}
			rStart = i;

			if (i == end)	break;

			int repeat = 0;
			while (i < end && Character.isDigit(s.charAt(i))) {
				repeat = repeat * 10 + s.charAt(i)-'0';
				i++;
				rEnd = i;
			}

			i++;
			wordStart = i;
			int balance = 1;

			while (balance != 0 && i < end) {
				if (s.charAt(i) == '[')
					balance++;
				if (s.charAt(i) == ']')
					balance--;
				i++;
			}
			i--;
			String temp = process(s, wordStart, i);
			
			for (int j = 0; j < repeat; j++) {
				sb.append(temp);
			}
		}

		return sb.toString();
	}
	
	Stack<String> stack = new Stack<String>();
	Queue<String> queue = new LinkedList<String>();
	Queue<String> rQueue = new LinkedList<String>();
	public String processStack(String s, int iStart, int iEnd) {
		populateToken(s, iStart, iEnd);
		
		String previous=null;
		while(queue.peek()!=null){
			String item= queue.remove();
			if(Character.isDigit(item.charAt(0))) {
				rQueue.add(item);
				if(previous!=null&&item.charAt(0)!='['){
					stack.push("+");
				}
				stack.push("[");
			}else if(item.charAt(0)=='['){
				stack.push("*");
				stack.push("(");
			}else if(Character.isLetter(item.charAt(0))) {
				rQueue.add(item);
				if(previous!=null&&item.charAt(0)!='['){
					stack.push("+");
				}
				
			}else if(item.charAt(0)==']'){
				while(!stack.empty()&&stack.peek().charAt(0)!='('){
					rQueue.add(stack.pop());
				}
			}
			previous = item;
		}
		while(!stack.empty()){
			rQueue.add(stack.pop());
		}
		
		StringBuilder result = new StringBuilder();
		//sd2[f2[e]g]i= sd+(2*
		while(!rQueue.isEmpty()){
			String item = rQueue.remove();
			if(item.charAt(0)!='+'&&item.charAt(0)!='*'){
				stack.push(item);
			}
			if(item.charAt(0)=='+'){
				String op2 = stack.pop();
				String op1 = stack.pop();
				stack.push(op1+op2);
			}
			if(item.charAt(0)=='*'){
				String op2 = stack.pop();
				String op1 = stack.pop();
				StringBuilder sb = new StringBuilder();
				for(int i=0;i<Integer.valueOf(op1);i++){
					sb.append(op2);
				}
				stack.push(sb.toString());
			}
		}
		
		
		return stack.pop().toString();
	}

	private void populateToken(String s, int iStart, int iEnd) {
		for (int i=iStart;i<iEnd; ){
            int dStart=i;
			while(i < iEnd && Character.isDigit(s.charAt(i))){
				i++;
			}
			if(i>dStart) {
				queue.add(s.substring(dStart,i));
			}
			
			if(i < iEnd & s.charAt(i)=='[')  {
				queue.add("[");
				i++;
			}
			
			dStart=i;
			while (i < iEnd && Character.isLetter(s.charAt(i))) {
				i++;
			}
			if(i>dStart){
				queue.add(s.substring(dStart,i));
			}
			
			if(i < iEnd &&s.charAt(i)==']')  {
				queue.add("]");
				i++;
			}
		}
	}
}
