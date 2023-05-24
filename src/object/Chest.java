package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chest extends parentObject {
    public Chest(){
        name = "Chest";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/chest.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}