class Result {
 /*
 * Complete the 'flippingMatrix' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts 2D_INTEGER_ARRAY matrix as parameter.
 */
 public static int flippingMatrix(List<List<Integer>> matrix) {
 // Write your code here
 int size = matrix.size()/2;
 int sum = 0;

 for(int i=0;i<size;i++){
 for(int j=0;j<size;j++){
 sum += Math.max(Math.max(matrix.get(i).get(j),
matrix.get(i).get(matrix.size()-1-j)),
 Math.max(matrix.get(matrix.size()-1-i).get(j),
matrix.get(matrix.size()-1-i).get(matrix.size()-1-j)));
 }
 }

 return sum;
 }
}
