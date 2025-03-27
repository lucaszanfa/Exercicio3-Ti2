package maven.trabalho02;

public class Usuario {
    private int codigo;
    private String login;
    private String senha;
    private String sexo;

    // Construtor sem o campo "codigo"
    public Usuario(String login, String senha, String sexo) {
        this.login = login;
        this.senha = senha;
        this.sexo = sexo;
    }

    // Construtor com o campo "codigo"
    public Usuario(int codigo, String login, String senha, String sexo) {
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
        this.sexo = sexo;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
