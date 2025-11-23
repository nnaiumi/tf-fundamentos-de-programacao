import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sistema {
    private List<Sala> todasSalas;
    private List<Evento> eventosCadastrados;
    private List<Inscricao> todasInscricoes;

    public Sistema() {
        this.todasSalas = new ArrayList<>();
        this.eventosCadastrados = new ArrayList<>();
    }

    public void adicionarNovaSala(Sala sala) {
        this.todasSalas.add(sala);
    }

    public void adicionarNovoEvento(Evento evento) {
        this.eventosCadastrados.add(evento);
    }

    public void adicionarNovaInscricao(Inscricao inscricao, Evento evento) {
        this.todasInscricoes.add(inscricao);
        evento.adicionarInscricao(inscricao);
    }

    public void removerInscricao(Inscricao inscricao, Evento evento) {
        this.todasInscricoes.remove(inscricao);
        evento.removerInscricaoPorId(inscricao.getId());
    }

    public void atualizarValorDiariaSala(Sala sala, double novoValor) {
        sala.setValorDiaria(novoValor);
    }

    public boolean adicionarNovaSalaEvento (Evento evento, Sala sala) {
        if (salaDisponivel(sala.getId(), evento.getDataInicial(), evento.getDataFinal()) && (getTodasSalas().contains(sala))) {
            evento.adicionarSala(sala);
            return true;
        }

        return false;
    }

    public void cadastrarEvento(Evento evento) {
        this.eventosCadastrados.add(evento);
    }

    public List<Evento> getEventosCadastrados() {
        return eventosCadastrados;
    }

    public List<Sala> getTodasSalas() {
        return todasSalas;
    }

    public List<Inscricao> getTodasInscricoes() {
        return todasInscricoes;
    }

    public List<Inscricao> getInscricoesDoEvento(int codigoEvento) {
        for (int i = 0; i < eventosCadastrados.size(); i++) {
            Evento evento = eventosCadastrados.get(i);
            if (evento.getCodigo() == codigoEvento) {
                return evento.getInscricoes();
            }
        }
        return new ArrayList<>();
    }

    public Sala getSalaPorId(int id) {
        for (int i = 0; i < todasSalas.size(); i++) {
            Sala sala = todasSalas.get(i);
            if (sala.getId() == id) {
                return sala;
            }
        }
        return null;
    }

    public boolean salaDisponivel(int salaid, Date dataInicio, Date dataFim) {
        Sala sala = getSalaPorId(salaid);
        return getSalasDisponiveisPorHorario(dataInicio, dataFim).contains(sala);
    }

    public List<Sala> getSalasDisponiveisPorHorario(Date dataInicio, Date dataFim) {
        List<Sala> salasLivres = new ArrayList<>(todasSalas);

        for (Evento evento : eventosCadastrados) {
            for (int i = 0; i < evento.getSalas().size(); i++) {
                Sala salaEvento = evento.getSalas().get(i);
                
                if (salasLivres.contains(salaEvento)) {
                    salasLivres.remove(salaEvento);
                }
            }
        }
        return salasLivres;
    }

    public List<Evento> getEventosPorTipo(String tipoEvento) {
        List<Evento> eventosDoTipo = new ArrayList<>();

        for (int i = 0; i < eventosCadastrados.size(); i++) {
            Evento evento = eventosCadastrados.get(i);

            if (evento.getTipoEvento() == tipoEvento) {
                eventosDoTipo.add(evento);
            }
        }

        return eventosDoTipo;
    }

    public List<String> getEventosParaODia(Date data) {
        List<String> eventosNoDia = new ArrayList<>();

        for (int i = 0; i < eventosCadastrados.size(); i++) {
            Evento evento = eventosCadastrados.get(i);

            if (!data.before(evento.getDataInicial()) && !data.after(evento.getDataFinal())) {
                eventosNoDia.add(evento.toString());
            }
        }

        return eventosNoDia;
    }

    public List<Evento> getEventosPorLocalizacao(String localizacao) {
        List<Evento> eventosNaLocalizacao = new ArrayList<>();

        for (int i = 0; i < eventosCadastrados.size(); i++) {
            Evento evento = eventosCadastrados.get(i);

            evento.getSalasPorLocalizacao(localizacao);

            if (!evento.getSalasPorLocalizacao(localizacao).isEmpty()) {
                eventosNaLocalizacao.add(evento);
            }
        }

        return eventosNaLocalizacao;
    }

    public void removerInscricoesUsuario(int idInscricao) {
        for (int i = 0; i < eventosCadastrados.size(); i++) {
            Evento evento = eventosCadastrados.get(i);
            evento.removerInscricaoPorId(idInscricao);
        }
    }

    public List<String> getRelatorioEventos() {
        List<String> relatorio = new ArrayList<>();

        for (int i = 0; i < eventosCadastrados.size(); i++) {
            Evento evento = eventosCadastrados.get(i);
            relatorio.add(evento.toString() + " | Receita Total: R$ " + evento.calcularReceitaTotal());
        }

        return relatorio;
    }

    public List<String> getCapacidadeTotalEventos() {
        List<String> capacidades = new ArrayList<>();

        for (int i = 0; i < eventosCadastrados.size(); i++) {
            Evento evento = eventosCadastrados.get(i);
            capacidades.add(evento.toString() + " | Capacidade Total: " + evento.capacidadeTotalDoEvento() + " pessoas");
        }

        return capacidades;
    }

    public List<Inscricao> getInscricoesPorTipo(int eventoCodigo, String tipoInscricao) {
        for (int i = 0; i < eventosCadastrados.size(); i++) {
            Evento evento = eventosCadastrados.get(i);
            if (evento.getCodigo() == eventoCodigo) {
                return evento.getInscricoesPorTipo(tipoInscricao);
            }
        }
        return new ArrayList<>();
        
    }

    public boolean removerSalaEvento(int eventoCodigo, int salaId) {
        Sala sala = getSalaPorId(salaId);

        for (int i = 0; i < eventosCadastrados.size(); i++) {
            Evento evento = eventosCadastrados.get(i);
            if (evento.getCodigo() == eventoCodigo) {
                return evento.removerSala(sala);
            }
        }
        return false;
    }
}
