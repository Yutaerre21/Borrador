import java.io.File;

public class Archivo {
    private File file;
    private Formato formato;
    public Archivo(String path) {
        file = new File(path);
        formato = Formato.getFormat(file.getName());
    }

    public File getFile() {
        return file;
    }

    public Formato getFormato() {
        return formato;
    }
}
