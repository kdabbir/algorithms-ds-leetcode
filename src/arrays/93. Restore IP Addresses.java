// 93. Restore IP Addresses: https://leetcode.com/problems/restore-ip-addresses/
// Medium

// Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can return them in any order.

// A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses. 

 

// Example 1:

// Input: s = "25525511135"
// Output: ["255.255.11.135","255.255.111.35"]
// Example 2:

// Input: s = "0000"
// Output: ["0.0.0.0"]
// Example 3:

// Input: s = "1111"
// Output: ["1.1.1.1"]
// Example 4:

// Input: s = "010010"
// Output: ["0.10.0.10","0.100.1.0"]
// Example 5:

// Input: s = "101023"
// Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 

// Constraints:

// 0 <= s.length <= 3000
// s consists of digits only.

// Using backtracking

class Solution {
  public List<String> restoreIpAddresses(String s) {
      List<String> ipAddresses = new ArrayList<String>();
      dfs(ipAddresses, "", s, 0);
      return ipAddresses;
  }
  
  public void dfs(List<String> ipAddresses, String path, String str, int pos) {
      if(pos == 4 || str.length() == 0){
          if(pos == 4 && str.length() == 0) {
              ipAddresses.add(path.substring(1)); //Since initial path will have "" + "." and we need to escape the initial dot
          }
          return;
      }
      
      for(int idx = 1; idx <=(str.charAt(0) == '0' ? 1: 3) && idx <= str.length(); idx++){ // Here i is going from 1->3 if i doesn't start with 0. Else, 0 -> 1 to handle leading zeroes.
          String currPart = str.substring(0, idx); // here we take from 0 -> idx
          if(Integer.parseInt(currPart) <= 255) { 
              dfs(ipAddresses, path + "." + currPart, str.substring(idx), pos + 1); // here we take rest of the string i.e idx -> Str.length
          }
      }
  }
  
  
}

// Naive solution.
class Solution {
  public List<String> restoreIpAddresses(String s) {
      int len = s.length();
      List<String> ipaddresses = new ArrayList<String>();
      for(int first = 1; first < 4 && first < len - 2; first++) {
          for(int second = first + 1; second < 4 + first && second < len - 1; second++) {
              for(int third = second + 1; third < 4 + second && third < len; third++){
                  String firstStr = s.substring(0, first), secondStr = s.substring(first, second), thirdStr = s.substring(second, third), fourthStr = s.substring(third, len);
                  if(isValidIPAddress(firstStr) && isValidIPAddress(secondStr) && isValidIPAddress(thirdStr) && isValidIPAddress(fourthStr)) {
                      ipaddresses.add(firstStr + '.' + secondStr + '.' + thirdStr + '.' + fourthStr);
                  }
                  
              }
              
          }
      }
      return ipaddresses;
  }
  
  public boolean isValidIPAddress(String s) {            
      if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
          return false;
      return true;
      
      // Alternate way of validation

      // int parseVal = Integer.parseInt(ip);

      // if(parseVal > 255) 
      //     return false;
      // return ip.length() == Integer.toString(parseVal).length(); // Check for leading zeros.

  }
}