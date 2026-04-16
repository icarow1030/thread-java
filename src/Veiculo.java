public class Veiculo {
    private int id;
    private String cor;
    private String tipo;
    private int idEstacao;
    private int idFuncionario;
    private int posicaoEsteiraFabrica;
    private int posicaoEsteiraLoja;

    public Veiculo(int id, String cor, String tipo, int idEstacao, int idFuncionario) {
        this.id = id;
        this.cor = cor;
        this.tipo = tipo;
        this.idEstacao = idEstacao;
        this.idFuncionario = idFuncionario;
    }

    public void setPosicaoEsteiraFabrica(int posicao) {
        this.posicaoEsteiraFabrica = posicao;
    }

    public void setPosicaoEsteiraLoja(int posicao) {
        this.posicaoEsteiraLoja = posicao;
    }

    @Override
    public String toString() {
        return String.format("Veiculo{id=%d, cor='%s', tipo='%s', idEstacao=%d, idFuncionario=%d, posicaoEsteiraFabrica=%d, posicaoEsteiraLoja=%d}",
            id, cor, tipo, idEstacao, idFuncionario, posicaoEsteiraFabrica, posicaoEsteiraLoja);
    }
}
