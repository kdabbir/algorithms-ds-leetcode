// 135. Candy: https://leetcode.com/problems/candy/
// Hard

// There are N children standing in a line. Each child is assigned a rating value.

// You are giving candies to these children subjected to the following requirements:

// Each child must have at least one candy.
// Children with a higher rating get more candies than their neighbors.
// What is the minimum candies you must give?

// Example 1:

// Input: [1,0,2]
// Output: 5
// Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
// Example 2:

// Input: [1,2,2]
// Output: 4
// Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
//              The third child gets 1 candy because it satisfies the above two conditions.




import java.util.*;

public class Solution {
    public int candy(int[] ratings) {
        int[] candyCount = new int[ratings.length];
        Arrays.fill(candyCount, 1);

        //we do not need to find local minimums actually 
		//we can mimic the left & right exapnsions from local mins by
		//traversing the given array forward and backward & applying mins condition accordingly

		//traversing in forward direction
		//mimicing right expansion from localmins
		//starting from one because arr[0] has no previous element

        
        for(int idx = 1; idx < ratings.length; idx++) {
            //store the maximum
				//we can also write rewards[i] = Math.max(rewards[i],rewards[i-1]+1)
				//but its not necessary cause previously stored value is always 1 ,,and incremented value will
				//always greater than 1
            if(ratings[idx] > ratings[idx - 1]) {
                candyCount[idx] = 1 + candyCount[idx - 1];
            }
        }
        		//travesring in backward direction
		//mimicing left expansion from local min point
        //statrting from arr.length-2 beacause arr[arr.length-1] has no next element to compare
        
        for(int idx = ratings.length -2; idx >=0; idx--) {
            if(ratings[idx] > ratings[idx + 1]) {
                 candyCount[idx] = Math.max(candyCount[idx], candyCount[idx + 1] + 1);
            }
        }
        
        int minCandies = 0;
        for(int idx = 0; idx < candyCount.length; idx++) {
            minCandies += candyCount[idx]; 
        }
        return minCandies;
    }
}

// Alternate problem description
// Min Rewards


// Imagine that you're a teacher who's just graded the final exam in a class. You
// have a list of student scores on the final exam in a particular order (not
// necessarily sorted), and you want to reward your students. You decide to do so
// fairly by giving them arbitrary rewards following two rules:

// 1) All students must receive at least one reward.

// 2) Any given student must receive strictly more rewards than an adjacent
// student (a student immediately to the left or to the right) with a lower
// score and must receive strictly fewer rewards than an adjacent student with
// a higher score.


// Write a function that takes in a list of scores and returns the minimum number
// of rewards that you must give out to students to satisfy the two rules.

// You can assume that all students have different scores; in other words, the
// scores are all unique.
// Input:  = [8, 4, 2, 1, 3, 6, 7, 9, 5]
// Output: 25 // you would give out the following rewards: [4, 3, 2, 1, 2, 3, 4, 5, 1]

//Time Complexity O(n) | Space Complexity O(n)
class AC
{
	public static void main(String[] args) 
	{
		int[] arr = new int[]{8,4,2,1,3,6,7,9,5};
		int result = getMinRewards(arr);
		System.out.println("Minimum Reward is "+result);
		
	}
	public static int getMinRewards(int[] arr)
	{
		int[] rewards = new int[arr.length];
		for(int i = 0 ; i < arr.length ; i++)
		{
			rewards[i]=1;
		}

		//we do not need to find local minimums actually 
		//we can mimic the left & right exapnsions from local mins by
		//traversing the given array forward and backward & applying mins condition accordingly

		//traversing in forward direction
		//mimicing right expansion from localmins
		//starting from one because arr[0] has no previous element

		for(int i = 1 ; i < arr.length ; i++)
		{
			if(arr[i-1]<arr[i])
			{
				//store the maximum
				//we can also write rewards[i] = Math.max(rewards[i],rewards[i-1]+1)
				//but its not necessary cause previously stored value is always 1 ,,and incremented value will
				//always greater than 1
				rewards[i] = rewards[i-1]+1;
			}
		}
		//travesring in backward direction
		//mimicing left expansion from local min point
		//statrting from arr.length-2 beacause arr[arr.length-1] has no next element to compare
		for(int i = arr.length-2 ; i >=0 ; i--)
		{
           if(arr[i+1]<arr[i])
           {
           	rewards[i]=Math.max(rewards[i],rewards[i+1]+1);
           }
		}
		System.out.println(Arrays.toString(rewards));
		return sum(rewards);
	}
	public static int sum(int[] arr)
	{
		int sum = 0;
		for(int num : arr)
		{
			sum += num;
		}
		return sum;
	}
}



// Naive

//Time Complexity O(n^2) | Space Complexity O(n)
class B
{
	public static void main(String[] args) 
	{
		//scores of individual student
		int[] arr = new int[]{8,4,2,1,3,6,7,9,5};
		int result = getMinRewards(arr);
		System.out.println("Minimum Reward is "+result);
		
	}
	public static int getMinRewards(int[] arr)
	{
		//to store rewards of individual student
		int[] rewards = new int[arr.length];
		//intialize rewards with 1 //as atleast  1 reward is the minimum for any student
		for(int i = 0 ; i < rewards.length ; i++)
		{
           rewards[i] = 1;
		}

        //iterarte the array using i (forward direction)
		for(int i = 1 ; i < arr.length ; i++) //starts from 1 as arr[0] has no previous element
		{
			//j is used to iterate array backwards
			int j = i-1;
			//if current element is greater than previous element
			if(arr[i]>arr[j])
			{
				//reward of current element is 1 greater than reward of previous element
				rewards[i] = rewards[j]+1;
			}
			//if currenet element is lesser than previous element 
			//go till begin of array from here till elements get increase while moving towards begining
			else
			{
				while(j>=0 && arr[j]>arr[j+1])
				{
					rewards[j] = Math.max(rewards[j],rewards[j+1]+1);
					j--;
				}
			}
		}
		//System.out.println(Arrays.toString(rewards));

		//return the sum of rewards array
		return sum(rewards);
	}
	public static int sum(int[] arr)
	{
		int sum = 0;
		for(int num : arr)
		{
			sum +=num;
		}
		return sum;
	}
}

// Finding Local Minimums and Start expanding in both direction
//Time Complexity O(n) | Space Complexity O(n)
class A
{
	public static void main(String[] args) 
	{
		int[] arr = new int[]{8,4,2,1,3,6,7,9,5};
		int result = getMinRewards(arr);
		System.out.println("Minimum Reward is "+result);
		
	}
	public static int getMinRewards(int[] arr)
	{
		//intialize a  reward array with all 1s
		int[] rewards = new int[arr.length];
		for(int i = 0 ; i < rewards.length ; i++)
		{
			rewards[i]=1;
		}
		//get all the local minimums in the array
		ArrayList<Integer> localMinPoints = getLocalMinPoints(arr);
		//System.out.println(localMinPoints.toString());
		//for every local min point index in array start expanding towards left and right 
		//while expanding check that numbers in original array is increasing 
		//if so then increment by 1 in each step towards left & right
		//and start storing the maximum of (previously stored value,Incremented Value)
		for(int localMin : localMinPoints)
		{
			//exapnd towards left
			int left = localMin-1;
			//expand towards right
			int right = localMin+1;
            
            //keep expanding till begin of array or till forthcoming element is greater than its one previous element holds true
			while(left >=0 && arr[left]>arr[left+1]  )
			{//store the maximum
				
				rewards[left] = Math.max(rewards[left],rewards[left+1]+1);
				//decerement left 
				left--;
			}
			//keep expanding till end of array or till next element is greater than previous holds true
			while(right < arr.length&&arr[right]>arr[right-1]  )
			{

				//store the maximum
				//we can also write rewards[right] = Math.max(rewards[right],rewards[right-1]+1)
				//but its not necessary cause previously stored value is always 1 ,,and incremented value will
				//always greater than 1
				rewards[right] = rewards[right-1]+1;
				//keep traversing
				right++;
			}

		}
		//System.out.println(Arrays.toString(rewards));
		//return the sum of rewards array
		return sum(rewards);
	}

	//returns all the local minimum point index of an array
	public static ArrayList<Integer> getLocalMinPoints(int[] arr)
	{
		//lcoalminpoint  is thos index whoose valu is smaller than its adjacent values
		ArrayList<Integer> localMinPoints = new ArrayList<Integer>();
		if(arr.length == 0)
		{
			return localMinPoints;
		}
		if(arr.length == 1)
		{
			localMinPoints.add(arr[0]);
			return localMinPoints;
		}

		for(int i = 0; i < arr.length ; i++)
		{
			//for first element of array ,just check with next element(it has one adjacent element)
			if(i==0 && arr[i+1]>arr[i])
			{
				localMinPoints.add(i);
			}
			//for last element of array just check with prev element (it has one adjacent element)
			if(i==arr.length-1 && arr[i-1]>arr[i])
			{
				localMinPoints.add(i);
			}
			//if aove condition is not statisfied for first & last element of array
			//then when first & last element comes just skip them
			if( i == 0 || i == arr.length-1)
			{
				continue;
			}

			//for every interio elements of array ,check for local min points
			//check the condition for both of its adjacent elements
			if(arr[i]<arr[i+1] && arr[i]<arr[i-1])
			{
				localMinPoints.add(i);
			}
		}
		return localMinPoints;
	}

	//returns sum of elements of an array
	public static int sum(int[] arr)
	{
		int sum = 0;
		for(int num : arr)
		{
			sum += num;
		}
		return sum;
	}
}
