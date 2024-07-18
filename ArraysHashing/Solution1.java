package ArraysHashing;
import java.util.*;
class Solution1 {

    //Contains Duplicate 
    public boolean containsDuplicate(int[] nums){
        Set<Integer> uniques = new HashSet();
        for (int i = 0; i < nums.length; i++){
            if (uniques.contains(nums[i])){
                return true; 
            }
        }
        return false; 
    }

    //Valid Anagram
    public boolean isAnagram(String s, String t){
        if (s.length() != t.length()){
            return false; 
        }

        int[] store = new int[26];

        for (int i = 0; i < s.length(); i++){
            store[s.charAt(i) - 'a']++;
            store[t.charAt(i) - 'a']--;
        }

        for (int n : store){
            if (n != 0){
                return false; 
            }
        }

        return true; 
    }

    //Two Sum
    public int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> prevMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++){
            int num = nums[i];
            int diff = target - num; 
            if (prevMap.containsKey(diff)){
                return new int[]{prevMap.get(diff), i};
            }
            prevMap.put(num, i);
        }

        return new int[]{};
    }

    //Group Anagrams
    public List<List<String>> groupAnagram(String[] strs){
        Map<String, List<String>> res = new HashMap<>();

        for (String s : strs){
            int[] count = new int[26];

            for (char c : s.toCharArray()){
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++){
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();

            res.putIfAbsent(key, new ArrayList<>());
            res.get(key).add(s);
        }
        return new ArrayList<>(res.values());
    }

    //Top K Frequent Elements 
    public int[] topKFrequent(int[] nums, int k){
        int[] arr = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
            (a, b) -> (a.getKey() - b.getKey())
        );

        for (Map.Entry<Integer, Integer> it : map.entrySet()){
            pq.add(it);
            if (pq.size() > k){
                pq.poll();
            }
        }

        int i = k; 
        while (!pq.isEmpty()){
            arr[--i] = pq.poll().getKey();
        }
        return arr; 
    }

    //Encode and Decode Strings 
    public String encode(List<String> strs){
        StringBuilder encodedString = new StringBuilder();
        for (String str : strs){
            encodedString.append(str.length()).append("#").append(str);
        }
        return encodedString.toString();
    }

    public List<String> decode(String str){
        List<String> list = new ArrayList<>();
        int i = 0; 
        while (i < str.length()){
            int j = i; //use j to get the substring length 

            while (str.charAt(j) != '#'){
                j++;
            }
            int length = Integer.valueOf(str.substring(i, j));
            i = j + length + 1; //move i to the new starter 
            list.add(str.substring(j + 1, i));
        }
        return list; 
    }


    //Product of Array Except Self 

    //Valid Sudoku

    //Longest Consecutive Sequence 


}