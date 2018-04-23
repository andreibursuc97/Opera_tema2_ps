package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter implements Exporter {
    @Override
    public void export(int id) {
        BiletOperations biletOperations=new BiletOperations();

        List<BiletEntity> biletEntityList=biletOperations.returneazaBilete(id);


        try(FileWriter writer=new FileWriter("bileteSpectacol"+id+".csv")){

            writer.write("id,idSpectacol,numar,rand\n");
            for(BiletEntity biletEntity:biletEntityList)
            {
                writer.write(biletEntity.getId()+","+biletEntity.getIdSpectacol()+","+biletEntity.getNumar()+","+biletEntity.getNumar()+"\n");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
