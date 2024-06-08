package teclag.c20130027.proyecto_desarrollo_android;

public class DatosCompartidos {
    private static DatosCompartidos instance;
    private String tema;

    private DatosCompartidos() {}

    // Singleton para que haya una instancia unica para toda la aplicacion
    public static synchronized DatosCompartidos getInstance() {
        if (instance == null) {
            instance = new DatosCompartidos();
        }
        return instance;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}
