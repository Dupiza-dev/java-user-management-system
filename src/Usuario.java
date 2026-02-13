public class Usuario {
    private static int contadorId = 1;
    private int id;
    private String nome;

    public Usuario(String nome) {
        this.id = contadorId++;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
