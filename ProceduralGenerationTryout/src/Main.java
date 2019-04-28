public class Main {

    public static void main(String[] args) {
        AreaManager man = new AreaManager();
        man.saveImage(man.generateImage(100, 100));
    }

    /** OPTIMIZATION: Size im generation from one side to the other: it is only nessesary to check 4 tiles :)*/
}
