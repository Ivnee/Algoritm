public class Exp {
    public float Exp(float num,float n) {
        if(n<0){
            return 1/Exp(num,Math.abs(n));
        }
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return num;
        }
        return num * Exp(num,n-1);
    }
}
