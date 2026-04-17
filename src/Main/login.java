package Main;

public class login {
    private int[] pin;

    public login(){
        final int[] pin = {1234, 5678};
        this.pin = pin;
    }
    public boolean validatePin(int inputPin){
        for (int p : pin) {
            if (p == inputPin) {
                return true;
            }
        }
        return false;
    }
}
// test comment

//A.J.