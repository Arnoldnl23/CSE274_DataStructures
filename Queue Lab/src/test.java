
public class test {

    public static void main(String[] args) {
        
        int[] test = {4,0,7,21,12};
        int dmin = Integer.MAX_VALUE;
        
        for ( int i = 0; i < test.length - 1; i++) {
            for ( int j = 0; j < test.length - 1; j++) {
                if ( i != j && Math.abs(test[i] - test[j]) < dmin ) {
                    dmin = Math.abs(test[i] - test[j]);
                }
            }
        }
        
        System.out.println(dmin);
    }

}
