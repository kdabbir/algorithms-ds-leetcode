// Task Assignment get maximum duration
// Hard


// You're given an integer k representing a number of workers and an
// array of positive integers representing durations of tasks that must be
// completed by the workers. Specifically, each worker must complete two unique
// tasks and can only work on one task at a time. The number of tasks will always
// be equal to 2k  such that each worker always has exactly two tasks
// to complete. All tasks are independent of one another and can be completed in
// any order. Workers will complete their assigned tasks in parallel, and the
// time taken to complete all tasks will be equal to the time taken to complete
// the longest pair of tasks (see the sample output for an explanation).


// Write a function that returns the optimal assignment of tasks to each worker
// such that the tasks are completed as fast as possible. Your function should
// return a list of pairs, where each pair stores the indices of the tasks that
// should be completed by one worker. The pairs should be in the following
// format: 
// [task1, task2], where the order of task1 and task2  doesn't matter. Your function can return the pairs in any
// order. If multiple optimal assignments exist, any correct answer will be
// accepted.
// Note: you'll always be given at least one worker (i.e.,k  will
// always be greater than 0)

// Sample Input
// k = 3, tasks =  = [1, 3, 5, 3, 1, 4]

// Sample output:
// [
//   [0, 2], // tasks[0] = 1, tasks[2] = 5 | 1 + 5 = 6

//   [4, 5], // tasks[4] = 1, tasks[5] = 4 | 1 + 4 = 5

//   [1, 3],  // tasks[1] = 3, tasks[3] = 3 | 3 + 3 = 6
//   // The fastest time to complete all tasks is 6.


import java.util.*;

class Program {

  public ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) {
   	ArrayList<ArrayList<Integer>> outputTasks = new ArrayList<ArrayList<Integer>>();
		Map<Integer, List<Integer>> durationToIdxMap = genIndexMap(tasks);
		ArrayList<ArrayList<Integer>> tasksIdxArr = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> sortedTasks = tasks;
		Collections.sort(sortedTasks);
		for(int idx = 0; idx < k; idx++) {
			List<Integer> indicesArrTask1 = durationToIdxMap.get(sortedTasks.get(idx));
			int taskidx1 = indicesArrTask1.remove(indicesArrTask1.size() - 1);
			
			int taskDuration2Idx = tasks.size() - 1 - idx;
			List<Integer> indicesArrTask2 = durationToIdxMap.get(sortedTasks.get(taskDuration2Idx));
			int taskidx2 = indicesArrTask2.remove(indicesArrTask2.size() - 1);
			
			ArrayList<Integer> outputPair = new ArrayList<Integer>();
			outputPair.add(taskidx1);
			outputPair.add(taskidx2);
			outputTasks.add(outputPair);
		}
		
    return outputTasks;
  }
	
	public HashMap<Integer, List<Integer>> genIndexMap(ArrayList<Integer> tasks) {
		HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for(int idx = 0; idx < tasks.size(); idx++) {
			int currTask = tasks.get(idx);
			if(map.containsKey(currTask)) {
				map.get(currTask).add(idx);
			} else {
				List<Integer> indicesArr = new ArrayList<Integer>();
				indicesArr.add(idx);
				map.put(currTask, indicesArr);
			}
		}
		return map;
	}
}
//O(nlog(n)) time | O(n)  space - where n is the number of tasks