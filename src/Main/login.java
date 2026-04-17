package Main;

/* Logic for the PIN-based employee login. This object stores accepted PINs as an array of integers
and uses its validatePin method to return whether a PIN is accepted.
 */

public class login {
    final private int[] pin;

    public login(){
        this.pin = new int[] {1234, 4321};
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