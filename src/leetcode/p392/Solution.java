package leetcode.p392;

public class Solution {

	
	 public boolean isSubsequence(String s, String t) {
		 if(s.length()==0) return true;
		 
		 int current =0;
		 for(int i=0;i<t.length();i++){
			 if( t.charAt(i)!=s.charAt(current)) continue;
			 current++;
			 if(current>=s.length()) return true;
		 }
		 return false;
	 }
	 
	 public boolean isSubsequence2(String s, String t) {
		 if(s.length()==0) return true;
		 
		 int[] count = new int['z'-'a'+1];
		 for(int j=0;j<s.length();j++){
			 count[s.charAt(j)-'a']= count[s.charAt(j)-'a']+1;
		 }
		 
		 int[][] state =   buildDFS(s);
		 int current =0;
		 for(int i=0;i<t.length();i++){
			 if( count[t.charAt(i)-'a']==0) continue;
			 
			 current = state[t.charAt(i)-'a'][current];
			 if(current>=s.length()) return true;
		 }
		 return false;
	 }
	 
	 public static int[][] buildDFS(String pattern){
		int pl = pattern.length();
		
		int[][] result = new int['z'-'a'+1][pl];
		
		result[pattern.charAt(0)-'a'][0]=1;
		for (int x=0,j=1;j<pl;j++){
			for(int c='a';c<='z';c++){
				result[c-'a'][j] = result[c-'a'][x];
			}
			result[pattern.charAt(j)-'a'][j]=j+1;
			x = result[pattern.charAt(j)-'a'][x];
		}
		return result;
	}
}
