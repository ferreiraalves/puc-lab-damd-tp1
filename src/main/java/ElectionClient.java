import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.*;

public class ElectionClient {

    static Election remote_election = null;
    static Map<String, String> voters = new HashMap<String, String>();
    static Scanner in = new Scanner(System.in);

    private static void vote(String name, String id) {
        for (int i = 0; i <= 5; i++){    // executa retry 5 vezes em caso de falha
            try {
                remote_election.vote(name, id);
                break;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<String> candidates() {
        try {
            return remote_election.candidates();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Result result(String name) {
        try {
            return remote_election.result(name);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void printCandidates() {
        ArrayList <String> candidates = candidates();
        for (String candidate : candidates){
            System.out.println(candidate);
        }
    }

    private static void vote() {
        System.out.println("Digite seu nome: ");
        String voterName = in.nextLine();
        if (voters.containsKey(voterName)) {
            System.out.println("Welcome back " + voterName);
        } else {
            voters.put(voterName,UUID.randomUUID().toString());
            System.out.println("Welcome " + voterName + " your UUID is " + voters.get(voterName));
        }

    }



    public static void main(String[] args) {
        String rmi_server = "rmi://localhost:8080/";
        String server_name = "ElectionService";
        Scanner in = new Scanner(System.in);
        Menu menu = new Menu();

        try {
            remote_election = (Election) Naming.lookup(rmi_server + server_name);
            System.out.println("Objeto remoto \'"+ server_name + "\' encontrado no servidor.");


            Integer option = -1;
            while (option != 6) {
                menu.printMainMenu();
                System.out.println("Digite sua opcao: ");
                switch (option = in.nextInt()) {
                    case 1:
                        System.out.println("Servidor rodando no endereco: " + rmi_server + server_name);
                        break;
                    case 2:
                        printCandidates();
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        break;
                }
                System.out.println();


            }

        } catch (MalformedURLException e) {
            System.out.println("URL \'" + rmi_server + server_name + "\' mal formatada.");
        } catch (RemoteException e) {
            System.out.println("Erro na invocao remota.");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("Objeto remoto \'" + server_name + "\' nao esta disponivel.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
