package sp.utils;

public class ReflectionUtil {

    public static void main(String args[]) throws Exception {
        //https://codechacha.com/ko/reflection/

        Class clazz = Child.class;
        System.out.println("Class name: " + clazz.getName()); //Class name: test.Child

        Class clazz2 = Class.forName("test.Child");
        System.out.println("Class name: " + clazz2.getName()); //Class name: test.Child



//        Class clazz = Class.forName("test.Child");
//        Constructor constructor = clazz.getDeclaredConstructor();
//        System.out.println("Constructor: " + constructor.getName()); //Constructor: test.Child


    }

    public class Parent {
        private String str1 = "1";
        public String str2 = "2";

        public Parent() {
        }

        private void method1() {
            System.out.println("method1");
        }

        public void method2(int n) {
            System.out.println("method2: " + n);
        }

        private void method3() {
            System.out.println("method3");
        }
    }

    public class Child extends Parent {
        public String cstr1 = "1";
        private String cstr2 = "2";

        public Child() {
        }

        private Child(String str) {
            cstr1 = str;
        }

        public int method4(int n) {
            System.out.println("method4: " + n);
            return n;
        }

        private int method5(int n) {
            System.out.println("method5: " + n);
            return n;
        }

}
