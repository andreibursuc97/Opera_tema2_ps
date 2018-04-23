package Model;

public class ExporterFactory {

    public static Exporter getExporter(String type)
    {
        switch (type){
            case "Csv":
                return createCsvExporter();
            case "Json":
                return creareJsonExporter();

        }

        throw new IllegalArgumentException();
    }

    public static JsonExporter creareJsonExporter()
    {
        return new JsonExporter();
    }

    public static CsvExporter createCsvExporter()
    {
        return new CsvExporter();
    }
}
