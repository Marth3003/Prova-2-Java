import classes.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AppProdutos {
    public static void main(String[] args) throws InterruptedException, IOException {
        int opcao;
        Scanner in = new Scanner(System.in);
        List<Produto> produtos = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Consultar produtos");
            System.out.println("3 - Listar produtos cadastrados");
            System.out.println("4 - Vendas por periodo");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); 

                if (opcao == 1) {
                    Produto p = new Produto();
                    try{
                    System.out.println("Digite o codigo do produto");
                    p.setCodigoproduto(in.nextInt());

                    } catch(Exception e){
                        in.nextLine();
                        System.out.println("Código do produto inválido !!! (Apenas números é aceito aqui)");
                        voltarMenu(in);
                        continue;
                    }
                    boolean verif = false;

                    for (Produto produto : produtos) {
                        if(produto.getCodigoproduto() == p.getCodigoproduto()){
                        verif = true;
                        }
                    }
                    if(verif != false){
                        in.nextLine();
                        System.out.println("\nEste código é de algum produto já cadastrado, tente novamente !");
                        voltarMenu(in);
                        continue;
                    }
                    System.out.println("Nome do produto: ");
                    in.nextLine();
                    p.setNome(in.nextLine());
                    try{
                    System.out.println("Quantidade em estoque: ");
                    p.setQtdestoque(in.nextInt());
                    if ( p.getQtdestoque() <= 0){
                        in.nextLine();
                        System.out.println("Quantidade inválida");
                        voltarMenu(in);
                        continue;
                    }
                    }catch(Exception e){
                        in.nextLine();
                        System.out.println("Quantidade inserida inválida");
                        voltarMenu(in);
                        continue;
                    }
                    try{
                    System.out.println("Preço do produto: ");
                    p.setPreco(in.nextInt());
                if (p.getPreco() <= 0){
                    System.out.println("Preço do produto inválido");
                    voltarMenu(in);
                    continue;
                }   
                    in.nextLine();
                    System.out.println("Produto inserido com sucesso !!!");
                    voltarMenu(in);
                    
                }catch(Exception e){
                    in.nextLine();
                    System.out.println("Preço inserido inválido !");
                    voltarMenu(in);
                }
                produtos.add(p);
            }
             else if (opcao == 2) {

                if (produtos.size() == 0){
                    System.out.println("Não existem produtos a serem buscados.");
                    voltarMenu(in);
                    continue;
                }
                System.out.println("Digite o codigo do produto desejado: ");
                int escolha = in.nextInt();
                in.nextLine();

                for (Produto produto : produtos) {
                    if(escolha == produto.getCodigoproduto()){
                        System.out.println("Produto encontrado");
                        System.out.println(produto);
                        voltarMenu(in);
                    }
                    }
                    for (Produto produto : produtos){
                        if(escolha != produto.getCodigoproduto()) {
                            System.out.println("Produto não cadastrado com este código");
                            voltarMenu(in);

                        }
                    }
               }
                else if (opcao == 3) {
                if (produtos.size() == 0){
                    System.out.println("Não existem produtos a serem listados.");
                    voltarMenu(in);
                    continue;
                }
        
                produtos.forEach((produto) -> {
                    System.out.println("Código do produto: " + produto.getCodigoproduto());
                    System.out.println("Nome do produto: " + produto.getNome());
                    System.out.println("Valor da unidade: " + produto.getPreco());
                    System.out.println("Quantidade em Estoque: " + produto.getQtdestoque());
                    System.out.println("________________________________________________");
                });
                voltarMenu(in);
            
            } else if (opcao == 4) {
                
                try{
                System.out.print("Informe a data inicial no formato 'aaaa-mm-dd': ");
                String dtInicial = in.nextLine();

                System.out.print("Informe a data final no formato 'aaaa-mm-dd': ");
                String dtFinal = in.nextLine();

                Venda vendasBt = new Venda();
                vendasBt.getDatesBetween(vendas, dtInicial, dtFinal);
                voltarMenu(in);

            }catch(Exception e){
                System.out.println("ERROR: Não há vendas neste periodo de tempo, ou o formato da data está errado");
                voltarMenu(in);
            }
        }
    
            else if (opcao == 5){
                if (produtos.size() == 0){
                    System.out.println("Não há produtos cadastrados, verifique o estoque");
                    voltarMenu(in);
                    continue;
                }
                Venda v = new Venda();
                Produto p = new Produto();
                
                System.out.println("VENDA REALIZADA NO DIA DE HOJE (DE ACORDO COM O SEU SISTEMA OPERACIONAL)");
                v.setDataVenda(LocalDate.now());
                try{
                System.out.println("Digite o codigo do produto: ");
                int buscarCodigo = in.nextInt();
                for (Produto produto : produtos) {
                    if(buscarCodigo == produto.getCodigoproduto()){
                        p = produto;
                    } else if (buscarCodigo != produto.getCodigoproduto()){
                        in.nextLine();

                        System.out.println("Não há nenhum produto cadastrado com o código informado, favor, pressione '0' para voltar ao menu");
                        break;
                    }
                } 
                }catch(Exception e){
                System.out.println("ERROR: Apenas números é aceito aqui");
                voltarMenu(in);
             }
             try{
                 System.out.println("Digite a quantidade para a venda: ");
                 int quantidadeDigitada = in.nextInt();
                 if(quantidadeDigitada <= p.getQtdestoque()){
                     v.setQuantidadeVendida(quantidadeDigitada);
                     p.removerQuantidadeEstoque(quantidadeDigitada);
                     v.setProdutoVendido(p);
                    }
                    else{
                        System.out.printf("ERROR: Não produtos no estoque o suficiente, há apenas: %s !",p.getQtdestoque());
                        in.nextLine(); 
                        voltarMenu(in);
                        continue;
                    }
                }catch(Exception e){
                    in.nextLine();
                    System.out.println("ERROR: Apenas números é aceito aqui");
                    voltarMenu(in);
            }
                vendas.add(v);
                System.out.println("Venda realizada com sucesso!"); 
                in.nextLine();
                voltarMenu(in);
            }      
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
                voltarMenu(in);
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");
        in.close();
    }


    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
}
