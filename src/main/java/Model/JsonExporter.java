package Model;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonExporter implements Exporter {
    @Override
    public void export(int id) {
        BiletOperations biletOperations=new BiletOperations();

        List<BiletEntity> biletEntityList=biletOperations.returneazaBilete(id);
        Gson gson= new Gson();

        try(FileWriter writer=new FileWriter("bileteSpectacol"+id+".json")){

            gson.toJson(biletEntityList,writer);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
