package ArraysHashing;
import java.util.*;
class Solution1 {

    //Contains Duplicate 
    public boolean containsDuplicate(int[] nums){
        //Set<> interface 
        Set<Integer> uniques = new HashSet<>();
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
        //class HashMap<K, V> extends AbstractMap<K, V>
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
        //interface Map<K, V>
        Map<String, List<String>> res = new HashMap<>();

        for (String s : strs){
            int[] count = new int[26];

            for (char c : s.toCharArray()){
                count[c - 'a']++;
            }

            //StringBuilder is asynchronized so it's faster than synchronized StringBuffer
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

        //interface Map.Entry<K, V>
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
            (a, b) -> (a.getKey() - b.getKey()) //lambda compareTo()
        );

        for (Map.Entry<Integer, Integer> it : map.entrySet()){
            //Set<Entry<Integer, Integer>> java.util.HashMap.entrySet()
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
        //interface List<E>
        List<String> list = new ArrayList<>();
        int i = 0; 
        while (i < str.length()){
            int j = i; //use j to get the substring length 

            while (str.charAt(j) != '#'){
                j++;
            }
            //Integer java.lang.Integer.valueOf(String s)
            int length = Integer.valueOf(str.substring(i, j));
            i = j + length + 1; //move i to the new starter 
            list.add(str.substring(j + 1, i));
        }
        return list; 
    }


    //Product of Array Except Self 
    public int[] productExceptSelf(int[] nums){
        int[] arr = new int[nums.length]; //array for product except self 
        int right = 1, left = 1; 

        for (int i = 0; i < nums.length; i++){
            arr[i] = left; //arr[i] is the product of the range [0, i) 
            left *= nums[i]; //left multiply current nums[i]
        }

        for (int i = nums.length - 1; i >= 0; i--){
            arr[i] *= right;  // multiply the right side product except self 
            right *= nums[i]; // right multiply current nums[i]
        }

        return arr; 
    }

    public int[] productExceptSelfNumsAsPrefix(int[] nums){
        int[] output = new int[nums.length];
        output[0] = 1;
        for (int i = 0; i < nums.length - 1; i++){
            output[i + 1] = output[i] * nums[i];
        }
        for (int i = nums.length - 2; i >= 0; i--){
            output[i] = nums[i + 1] * output[i];
            nums[i] *= nums[i + 1];
        }
        return output; 
    }


    //Valid Sudoku
    public boolean isValidSudoku(char[][] board){
        Set<Character> rowSet = null;
        Set<Character> colSet = null;

        for (int i = 0; i < 9; i++){
            //reinitialize the sets so we don't carry over found characters from the previous run 
            rowSet = new HashSet<>();
            colSet = new HashSet<>();
            for (int j = 0; j < 9; j++){
                char r = board[i][j];
                char c = board[j][i];
                if (r != '.'){
                    if (rowSet.contains(r)){
                        return false; 
                    } else {
                        rowSet.add(r);
                    }
                }
                if (c != '.'){
                    if (colSet.contains(c)){
                        return false; 
                    } else {
                        colSet.add(c);
                    }
                }

            }
        }

        //block
        //loop controls advance by 3 each time to jump through the boxes 
        for (int i = 0; i < 9; i += 3){
            for (int j = 0; j < 9; j += 3){
                if (!checkBlock(i, j, board)){
                    return false; 
                }
            }
        }

        return true; 
    }
    public boolean checkBlock(int idxI, int idxJ, char[][] board){
        Set<Character> blockSet = new HashSet<>();
        int rows = idxI + 3; 
        int cols = idxJ + 3; 

        //check the block set 1-9
        for (int i = idxI; i < rows; i++){
            for (int j = idxJ; j < cols; j++){
                if (board[i][j] == '.'){
                    continue; 
                }
                if (blockSet.contains(board[i][j])){
                    return false; 
                }
                blockSet.add(board[i][j]);
            }
        }
        return true; 
    }

    //Longest Consecutive Sequence 
    

}