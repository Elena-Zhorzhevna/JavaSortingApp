package java_sorting_app.loaders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileLoader <TYPE> implements Loader<TYPE> {
    public void write(TYPE obs, String fileName){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obs);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public TYPE read(String fileName){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (TYPE)ois.readObject();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
