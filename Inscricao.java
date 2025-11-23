public class Inscricao {
    public enum CategoriaIncricao {
        ESTUDANTE,
        PROFISSIONAL,
        VIP
    }

    private int id;
    private String nome;
    private CategoriaIncricao categoria;
    private double precoInscricao;
    private int cpf;
    private String cargo;
    private String instituicao;

    public Inscricao(int id, String nome, CategoriaIncricao categoria, double precoInscricao, int cpf, String cargo, String instituicao) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoInscricao = precoInscricao;
        this.cpf = cpf;
        this.cargo = cargo;
        this.instituicao = instituicao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        if (categoria == CategoriaIncricao.ESTUDANTE)
            return "Estudante";
        else if (categoria == CategoriaIncricao.PROFISSIONAL)
            return "Profissional";
        else
            return "VIP";
    }

    public double getPrecoInscricao() {
        return precoInscricao;
    }

    public int getCpf() {
        return cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Inscricao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria=" + getCategoria() +
                ", precoInscricao=" + precoInscricao +
                ", cpf=" + cpf +
                ", cargo='" + cargo + '\'' +
                ", instituicao='" + instituicao + '\'' +
                '}';
    }
}
