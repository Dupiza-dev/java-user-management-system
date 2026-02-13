import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        do {
            System.out.println("\n=================================");
            System.out.println("    SISTEMA DE USUÁRIOS");
            System.out.println("===================================");
            System.out.println("Total cadastrados: " + usuarios.size());
            System.out.println("-----------------------------------");
            System.out.println("1. Castrar usuário");
            System.out.println("2. Listar usuário");
            System.out.println("3. Buscar usuário por ID");
            System.out.println("4. Remover usuário por ID");
            System.out.println("0. Sair");
            System.out.println("------------------------------------");
            System.out.println("Escolja uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarUsuario(scanner);
                        break;
                    case 2:
                        listarUsuarios();
                        break;
                    case 3:
                        buscarUsuario(scanner);
                        break;
                    case 4:
                        removerUsuario(scanner);
                        break;
                    case 0:
                        System.out.println("\nEncerrando sistema...");
                        break;
                    default:
                        System.out.println("\nOpção inválida.");
                }

            } catch (Exception e) {
                System.out.println("\nErro: Digite apenas números.");
                scanner.nextLine();
            }

        } while (opcao != 0);
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.println("Digite o nome do usuário: ");
        String nome = scanner.nextLine();


        Usuario novoUsuario = new Usuario(nome);
        usuarios.add(novoUsuario);

        salvarUsuarioEmArquivo(novoUsuario);
        System.out.println("usuário cadastrado com sucesso!");
        System.out.println("ID gerado: " + novoUsuario.getId());
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() + "- Nome:" + usuario.getNome());
            }
        }
    }

    private static void buscarUsuario(Scanner scanner) {
        System.out.println("Digite o ID para buscar: ");
        try {
            int idBusca = scanner.nextInt();
            scanner.nextLine();

            for (Usuario usuario : usuarios) {
                if (usuario.getId() == idBusca) {
                    System.out.println("Usuário encontrado:");
                    System.out.println("ID: " + usuario.getId());
                    System.out.println("Nome: " + usuario.getNome());
                    return;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro: Digite apenas números.");
            scanner.nextLine();
        }

        System.out.println("ID não encontrado.");
    }

    private static void salvarUsuarioEmArquivo(Usuario usuario) {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("usuarios.txt", true);
            writer.write(usuario.getId() + ";" + usuario.getNome() + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar no arquivo.");
        }
    }

    private static void reescreverArquivo() {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("usuarios.txt"); // sem true

            for (Usuario usuario : usuarios) {
                writer.write(usuario.getId() + ";" + usuario.getNome() + "\n");
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar arquivo.");
        }
    }


    private static void removerUsuario(Scanner scanner) {
        System.out.println("Digite o ID para remover: ");
        try {
            int idRemover = scanner.nextInt();
            scanner.nextLine();

            boolean removido = usuarios.removeIf(usuario -> usuario.getId() == idRemover);
            if (removido) {
                reescreverArquivo();
                System.out.println("Usuário removido com sucesso");
            } else {
                System.out.println("ID não encontrado.");
            }

        } catch (Exception e) {
            System.out.println("Erro: Digite apenas números.");
            scanner.nextLine();
        }
    }
}



