package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends parentObject {
    public Door(){
        name = "Door";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/door.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
