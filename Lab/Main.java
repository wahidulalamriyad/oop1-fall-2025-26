package Lab;

public class Main {
    public static void main(String[] args) {
        var obj = new ClassObject();
        obj.setText("Hello, World!");
        System.out.println("Text after setting: " + obj.text.toUpperCase());
        obj.clearText();
        System.out.println("Text after clearing: " + obj.text);

        var anotherObj = new ClassObject();
        anotherObj.setText("Java Programming");
        System.out.println("Another object's text: " + anotherObj.text.toLowerCase());
    }

    public static int sum(int a, int b) {
        return a + b;
    }
}
