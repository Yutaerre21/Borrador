
public abstract class FormatConverter{
    private Formato formatoA;
    private Formato formatoB;
    public FormatConverter(Formato formatoA,Formato formatoB) {
        this.formatoA = formatoA;
        this.formatoB = formatoB;
    }

    public abstract boolean convertAtoB(Archivo a, Archivo b);

    public abstract boolean convertBtoA(Archivo b,Archivo a);

    public Formato getFormatoA() {
        return formatoA;
    }

    public Formato getFormatoB() {
        return formatoB;
    }
}
