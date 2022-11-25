package Element;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class Element {

    
    public static Font getFont(int size){
        Font font = null;
        try{
            InputStream is = Element.class.getResourceAsStream("/font/Mali-Bold.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont((float)size);
        }catch(FontFormatException | IOException e){
        }
        return font;
    }    
}
