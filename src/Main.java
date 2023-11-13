import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Random agende = new Random();
        String[] aplicatii = {"Twos", "Obsidian", "Joplin", "BearApp"};
        for(int i = 0; i <= 3; i++){
                int random = agende.nextInt(3);
                System.out.println(random);
        }

    }
}