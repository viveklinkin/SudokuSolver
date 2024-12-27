/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [][] board = new int[9][9];
		Map<Integer, Set<Integer>> possR = new HashMap<>();
        Map<Integer, Set<Integer>> possC = new HashMap<>();
        Map<Integer, Set<Integer>> possS = new HashMap<>();
        for(int i = 0; i < 9; i++){
            Set<Integer> setR = new HashSet<Integer>();
            Set<Integer> setC = new HashSet<Integer>();
            Set<Integer> setS = new HashSet<Integer>();
            
            for(int j = 1; j <= 9; j++ ){
                setR.add(j);
                setC.add(j);
                setS.add(j);
            }
            
            possR.put(i, setR);
            possC.put(i, setC);
            possS.put(i, setS);
            
        }
        for(int i = 0; i < 9; i++){
            String s = sc.nextLine();
            for(int j = 0; j < 9; j++){
                if(s.charAt(j) != '_'){
                    board[i][j] = (int)s.charAt(j) - (int)'0';   
                    Set<Integer> setR = possR.get(i);
                    Set<Integer> setC = possC.get(j);
                    Set<Integer> setS = possS.get(((i/3)*3) + (j/3));
                    
                    setR.remove(board[i][j]);
                    setC.remove(board[i][j]);
                    setS.remove(board[i][j]);
                }
                else{
                    board[i][j] = -1;    
                }
            }
        }
        
        solve(board, possR, possC, possS);
	}
	
	public static void solve(int [][]board, Map<Integer, Set<Integer>> possR, Map<Integer, Set<Integer>> possC, Map<Integer, Set<Integer>> possS){
	    for(int i = 0; i < 9; i++){
	        for(int j = 0; j < 9; j++){
	            if(board[i][j] == -1){
	                Set<Integer> setR = possR.get(i);
	                Set<Integer> setC = possC.get(j);
	                Set<Integer> setS = possS.get(((i/3)*3) + (j/3));
	                
	                for(int k = 1; k <= 9; k++){
	                    if(setR.contains(k) && setC.contains(k) && setS.contains(k)){
	                        setR.remove(k);
	                        setC.remove(k);
	                        setS.remove(k);
	                        board[i][j] = k;
	                        solve(board, possR, possC, possS);
	                        setR.add(k);
	                        setC.add(k);
	                        setS.add(k);
	                    }
	                }
	                board[i][j] = -1;
	                return;
	            }
	        }
	    }
	   System.out.println("Solution:");
        for(int i = 0; i < 9; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < 9; j++){
                sb.append(board[i][j] + " ");
            }
            System.out.println(sb.toString());
	    }
	}
	
}
