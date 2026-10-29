
interface payment {
    public void pay();
}

class zcafe implements payment {

    @Override
    public void pay() {
        System.out.println("paid");
    }

    public void display() {
        System.out.println("Completed");
    }
}

public class inter {
    public static void main(String[] args) {
    }
}