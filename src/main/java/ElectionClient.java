import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.*;

public class ElectionClient {

    static Election remote_election = null;
    static Map<String, String> voters = new HashMap<String, String>();
    static Scanner in = new Scanner(System.in);

    private static void printMainMenu(){
        System.out.println("Bem vindo ao ElectionServer");
        System.out.println("1 - Server");
        System.out.println("2 - Candidatos");
        System.out.println("3 - Votacao");
        System.out.println("4 - Numero de candidatos");
        System.out.println("5 - Resultado");
        System.out.println("6 - Sair");
    }

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
        System.out.println();
        if (voters.containsKey(voterName)) {
            System.out.println("Bem vindo de volta " + voterName);
        } else {
            voters.put(voterName,UUID.randomUUID().toString());
            System.out.println("Bem vindo " + voterName + " o seu UUID e " + voters.get(voterName));
        }
        System.out.println();
        System.out.println("Candidatos disponiveis:");
        System.out.println();
        printCandidates();
        System.out.println();
        System.out.println("Escolha seu candidato:");
        String candidateName = in.nextLine();
        vote(candidateName, voters.get(voterName));

    }

    private static void results() {
        ;
        try {
            ArrayList <String> candidates = remote_election.candidates();
            for (String candidate : candidates) {
                Result result = result(candidate);
                System.out.println(result.getCandidateName() + "\t-\t" + result.getCandidateVotes());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        String rmi_server = "rmi://localhost:8080/";
        String server_name = "ElectionService";
        Scanner in = new Scanner(System.in);


        try {
            remote_election = (Election) Naming.lookup(rmi_server + server_name);
            System.out.println("Objeto remoto \'"+ server_name + "\' encontrado no servidor.");


            Integer option = -1;
            while (option != 6) {
                printMainMenu();
                System.out.println("Digite sua opcao: ");
                switch (option = in.nextInt()) {
                    case 1:
                        System.out.println("Servidor rodando no endereco: " + rmi_server + server_name);
                        break;
                    case 2:
                        printCandidates();
                        break;
                    case 3:
                        vote();
                        break;
                    case 4:
                        System.out.println(candidates().size() + " candidatos encontrados");
                        break;
                    case 5:
                        results();
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
