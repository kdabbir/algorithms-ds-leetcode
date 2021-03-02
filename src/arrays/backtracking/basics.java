// Backtracking is an algorithm for finding all solutions by exploring all potential candidates. If the solution candidate turns to be not a solution (or at least not the last one), backtracking algorithm discards it by making some changes on the previous step, i.e. backtracks and then try again.

// Pick a starting point.
// while(Problem is not solved)
//     For each path from the starting point.
//         check if selected path is safe, if yes select it
//         and make recursive call to rest of the problem
//         before which undo the current move.
//     End For
// If none of the move works out, return false, NO SOLUTON.


// Important observation in 46. Permutations.java, we are basically creating a new ArrayList during backtracking adding and removing.