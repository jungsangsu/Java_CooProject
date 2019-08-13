package day0619;

public class Array_Test1 {

	
	
	public static void main(String[] args) {
		int[][] recArray = new int[5][5];
		for(int i=0;i<recArray.length;i++) {
			for(int j=0;j<recArray[i].length;j++) {
				for(int i==0||i==recArray.length-1||j==0||j==recArray[i].length-1) {
					recArray[i][j] = 1;
					
				}
			
			}

		}
		for(int i=0;i<recArray.length;i++) {
			for(int j=0;j<recArray[i].length;j++) {
				System.out.println(recArray[i][j]+"");
			}
			System.out.println();
