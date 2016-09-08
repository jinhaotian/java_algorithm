package p395;

import org.junit.Test;

public class Solution {
	
	@Test
	public void test(){
		int r = longestSubstring("bbaaacbd",3);
		System.out.println(r);
	}
    public int longestSubstring(String s, int k) {
        int start =0;
        int end =s.length();
        
        return max(s,k,start,end);
    }
    public int max(String s, int k, int start, int end){
         int max=0;
        
        //End of Loop
        if(end-start<k){
        	return max;
        }
      
        int[] counts = new int['z'-'a'+1];
        for (int i=start;i<end;i++){
            counts[s.charAt(i)-'a']++;
        }
        
        int curr=start;
        int istart=start;
        int iend=istart;
        while(curr<end){
            if (counts[s.charAt(curr)-'a']<k){
                int t = max( s, k,  istart, curr);
                if(t>max) max=t;
                curr++;
                while(curr<end&&counts[s.charAt(curr)-'a']<k){
                	curr++;
                }
                istart=curr;
                iend=istart;
            }else{
                iend++;
                curr++;
            }
            
          
        }
        
        if(istart==start&&iend==end) return iend-istart;
        if(curr==end){
                 int t = max( s, k,  istart, end);
                 if(t>max) max=t;
        }
       
        return max;
    }
}