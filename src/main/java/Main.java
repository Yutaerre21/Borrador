import java.util.Scanner;

public class Main {
    private JsonCvsConverter converter;
    public Main() {
        converter = new JsonCvsConverter();
    }

    public JsonCvsConverter getConverter() {
        return converter;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Elija una opcion: ");
            System.out.println("1. Convertir de json a csv");
            System.out.println("2. Convertir de csv a json");
            System.out.println("3. Salir");
            String selected = scanner.nextLine();
            try {
                int option = Integer.parseInt(selected);
                if(option > 3 || option < 1){
                    System.out.println("Opcion invalida!");
                    return;
                }
                if(option == 3){
                    System.out.println("Hasta luego!!!");
                    break;
                }
                System.out.println("Ingrese el nombre del archivo");
                String fileName = scanner.nextLine();
                if(option == 1){
                    if(main.getConverter().convertAtoB(new Archivo(fileName),new Archivo("output.csv"))){
                        System.out.println("Convertido exitosamente!");
                        break;
                    }
                }else {
                    if(main.getConverter().convertBtoA(new Archivo(fileName),new Archivo("output.json"))){
                        System.out.println("Convertido exitosamente!");
                        break;
                    }
                }
            }catch (NumberFormatException e){
                System.out.println("Opcion invalida!");
            }
        }
    }

}
