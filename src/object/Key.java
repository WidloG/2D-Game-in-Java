package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Key extends parentObject {
    public Key(){
        name = "Key";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/key.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
