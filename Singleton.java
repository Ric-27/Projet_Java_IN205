public class Singleton {

    private static Singleton INSTANCE = null;

    // Singleton Logic
    private Singleton(){}

    public static Singleton getInstance(){
        synchronized(INSTANCE){
            if(INSTANCE == null){
                INSTANCE = new Singleton();
            }
        }
        return INSTANCE;
    }
    protected void finalize() {
        print("finalize()");
    }

    public static void print(Object o) {
        System.out.println(o);
    }
    
    public static void main(String[] args) {
        Singleton s1 = getInstance();
        Singleton s2 = getInstance();
        Singleton s3 = s1;
        print(s1 == s2);
        print(s1 == s3);
        s2 = s3;
        System.gc();
    }
}