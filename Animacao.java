/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  Autor: Daniel Nogueira
  Matricula: 201911910
  Inicio...: 15 de Abril de 2021
  Alteracao: 18 de Abril de 2021
  Nome.....: Animacao
  Funcao...: Classe que serve para fazer a animacao dos sprites dos trens
  =-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class Animacao {
  private int imagemAtual=0;      //int que indica qual imagem do array eh a atual
  private Sprite[] imagens = new Sprite[3];    //array de 'Sprite' que tem as imagens para a animacao
  private TickAnimacao tickAnimacao;      //objeto da classe 'TickAnimacao' que serve de temporizador
  private Trem trem;                //objeto referencia ao trem em que a animacao ocorre
  private boolean isRunning = false;    //boolean para definir se a animacao comecou ou nao

  /* *********************
  * Metodo: Animacao
  * Funcao: Construtor de animacao
  * Parametros: String caminho1, String caminho2, String caminho3
  ********************* */
  public Animacao(String path1, String path2, String path3, Trem trem){
    imagens[0] = new Sprite(path1);
    imagens[1] = new Sprite(path2);
    imagens[2] = new Sprite(path3);

    this.trem = trem;

    tickAnimacao = new TickAnimacao();
  }

  /* *********************
  * Metodo: ligarTickAnimacao
  * Funcao: inicia a thread tickAnimacao que altera os sprites
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  public void ligarTickAnimacao(){
    tickAnimacao.start();
    isRunning = true;
  }

  //Getters e setters--------------------------//
  public Sprite getImagem(){
    return imagens[imagemAtual];
  }

  public boolean isRunning() {
    return isRunning;
  }

  public void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  } 
  //-------------------------------------------//
  
  /*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  Autor: Daniel Nogueira
  Matricula: 201911910
  Inicio...: 15 de Abril de 2021
  Alteracao: 18 de Abril de 2021
  Nome.....: TickAnimacao
  Funcao...: Classe criada para ajudar a classe 'Animacao' na questao do tempo de troca de sprites
  =-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
  class TickAnimacao extends Thread {
    /* *********************
    * Metodo: run
    * Funcao: metodo que sera responsavel pelo algoritmo de 'TickAnimacao'
    * Parametros: nenhum
    * Retorno: void
    ********************* */
    @Override
    public void run(){
      if (trem!=null){
        while (true){
          try {
            sleep(350);
            if (imagemAtual<2){
              imagemAtual++;
            }
            else {
              imagemAtual = 0;
            }
            trem.setImagem(imagens[imagemAtual]);
          }
          catch(Exception e){
          }
        }
      }
    }
  }
}
