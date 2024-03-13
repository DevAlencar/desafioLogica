import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String palavra;
        @SuppressWarnings("resource")
        Scanner ler = new Scanner(System.in);
        System.out.printf("Informe uma palavra:\n");
        palavra = ler.next();

        Input inserir = new Input(palavra);
        int aux = inserir.validation();

        switch (aux) {
            case -1:
                System.out.println("Está correto");
                TrueTest test = new TrueTest(palavra);
                test.createTests(palavra);
                break;
            case -2:
                System.out.println("Está incorreto o uso de parênteses");
                break;
            default:
                System.out.println("O erro está no caractere " + aux);
        }
    }
}
