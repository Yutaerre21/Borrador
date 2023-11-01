public enum Formato {
    JSON(".json"),
    CSV(".csv");
    private String extension;
    Formato(String extension){
        this.extension = extension;
    }

    public static Formato getFormat(String fileName){
        for (Formato formato : Formato.values()) {
            if(fileName.endsWith(formato.extension)){
                return formato;
            }
        }
        return null;
    }
}
