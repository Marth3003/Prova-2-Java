package classes;

public class Produto {
    private String Nome;
    private int Codigoproduto;
    private int Qtdestoque;
    private int Preco;

    public Produto(){}

    public Produto(int Codigoproduto, String Nome, int Preco, int Qtdestoque){
        this.Codigoproduto = Codigoproduto;
        this.Nome = Nome;
        this.Preco = Preco;
        this.Qtdestoque = Qtdestoque;
    }
    
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public int getCodigoproduto() {
        return Codigoproduto;
    }
    public void setCodigoproduto(int codigoproduto) {
        Codigoproduto = codigoproduto;
    }
    public int getQtdestoque() {
        return Qtdestoque;
    }
    public void setQtdestoque(int qtdestoque) {
        Qtdestoque = qtdestoque;
    }
    public int getPreco() {
        return Preco;
    }
    public void setPreco(int preco) {
        Preco = preco;
    }
    public String getValor() {
        return null;
    }
    public String toString() {
        return String.format(" Código do produto: %d\n Nome do produto: %s\n Valor do produto: %d\n Quantidade no estoque: %d\n_____________________________________",getCodigoproduto(), getNome(), getPreco(), getQtdestoque());
    }
    public int removerQuantidadeEstoque(int quantidade) {
        return Qtdestoque = Qtdestoque - quantidade;
    }

    public boolean setNome(int nomeBuscado) {
        return false;
    } 
}