import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Evento {
    public enum TipoEvento {
        FEIRA,
        WORKSHOP,
        SEMINARIO
    }

    private int codigo;
    private String nome;
    private TipoEvento tipoEvento;
    private Date dataInicial;
    private Date dataFinal;
    private List<Inscricao> inscricoes;
    private List<Sala> salas;

    public Evento(int codigo, String nome, TipoEvento tipoEvento, Date dataInicial, Date dataFinal) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipoEvento = tipoEvento;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.inscricoes = new ArrayList<>();
        this.salas = new ArrayList<>();
    }

    public void adicionarInscricao(Inscricao inscricao) {
        this.inscricoes.add(inscricao);
    }

    public void adicionarSala(Sala sala) {
        this.salas.add(sala);
    }

    public Sala getSalaPorCapacidade(int capacidade) {
        for (int i = 0; i < salas.size(); i++) {
            Sala sala = salas.get(i);

            if (sala.getLotacaoMaxima() <= capacidade) {
                return sala;
            }
        }
        return null;
    }

    public List<Inscricao> getInscricoesPorTipo(String tipoInscricao) {
        List<Inscricao> incricoes = new ArrayList<>();
            
        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao inscricao = inscricoes.get(i);
            
            if (inscricao.getCategoria() == inscricao.getCategoria()) {
                incricoes.add(inscricao);
            }
        }

        return incricoes;
    }

    public double calcularReceitaTotal() {
        double receitaTotal = 0.0;

        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao inscricao = inscricoes.get(i);
            receitaTotal += inscricao.getPrecoInscricao();
        }

        return receitaTotal;
    }

    public int capacidadeTotalDoEvento() {
        int capacidadeTotal = 0;

        for (int i = 0; i < salas.size(); i++) {
            Sala sala = salas.get(i);
            capacidadeTotal += sala.getLotacaoMaxima();
        }

        return capacidadeTotal;
    }

    public List<Sala> getSalasPorLocalizacao(String localizacao) {
        List<Sala> salasLocalizacao = new ArrayList<>();

        for (int i = 0; i < salas.size(); i++) {
            Sala sala = salas.get(i);

            if (sala.getLocalizacao().equalsIgnoreCase(localizacao)) {
                salasLocalizacao.add(sala);
            }
        }

        return salasLocalizacao;
    }

    public void removerInscricaoPorId(int idInscricao) {
        for (int i = 0; i < inscricoes.size(); i++) {
            Inscricao inscricao = inscricoes.get(i);

            if (inscricao.getId() == idInscricao) {
                inscricoes.remove(i);
                break;
            }
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoEvento() {
        if (tipoEvento == TipoEvento.FEIRA)
            return "Feira";
        else if (tipoEvento == TipoEvento.WORKSHOP)
            return "Workshop";
        else
            return "Seminario";
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public int getQuantidadeSalas() {
        return salas.size();
    }

    public int getQuantidadeInscricoes() {
        return inscricoes.size();
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public boolean removerSala(Sala sala) {
        return salas.remove(sala);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", tipoEvento=" + tipoEvento +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", inscricoes=" + inscricoes.size() +
                ", salas=" + salas.size() +
                '}';
    }
}