package maven.trabalho02;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1. Listar");
            System.out.println("2. Inserir");
            System.out.println("3. Atualizar");
            System.out.println("4. Excluir");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    listarUsuarios();
                    break;
                case 2:
                    inserirUsuario();
                    break;
                case 3:
                    atualizarUsuario();
                    break;
                case 4:
                    excluirUsuario();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listar();
        for (Usuario usuario : usuarios) {
            System.out.println("Código: " + usuario.getCodigo() + ", Login: " + usuario.getLogin() + ", Sexo: " + usuario.getSexo());
        }
    }

    private static void inserirUsuario() {
        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Sexo (M/F): ");
        String sexo = scanner.nextLine().toUpperCase();

        if (!sexo.equals("M") && !sexo.equals("F")) {
            System.out.println("Erro: Sexo deve ser 'M' ou 'F'.");
            return;
        }

        Usuario usuario = new Usuario(login, senha, sexo); // Código será gerado automaticamente
        usuarioDAO.inserir(usuario);
    }

    private static void atualizarUsuario() {
        System.out.print("Código do usuário a ser atualizado: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Novo Login: ");
        String login = scanner.nextLine();
        System.out.print("Nova Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Novo Sexo: ");
        String sexo = scanner.nextLine();

        Usuario novoUsuario = new Usuario(codigo, login, senha, sexo);
        usuarioDAO.atualizar(codigo, novoUsuario);
        System.out.println("Usuário atualizado com sucesso.");
    }

    private static void excluirUsuario() {
        System.out.print("Código do usuário a ser excluído: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        usuarioDAO.excluir(codigo);
        System.out.println("Usuário excluído com sucesso.");
    }
}