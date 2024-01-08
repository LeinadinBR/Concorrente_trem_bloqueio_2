/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  Autor: Daniel Nogueira
  Matricula: 201911910
  Inicio...: 14 de Abril de 2021
  Alteracao: 18 de Abril de 2021
  Nome.....: Trem
  Funcao...: Uma classe abstrata que herda de 'Thread' e serve para definir os metodos e atributos comuns aos trens
  =-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public abstract class Trem extends Thread {
  private Sprite imagem;  //objeto de imagem que o trem possui
  protected int x;      //localizacao x do trem
  protected int y;      //localizacao y do trem
  protected int velocidade;  //velocidade do trem

  /* *********************
  * Metodo: Trem
  * Funcao: Construtor de Trem
  * Parametros: int x, int y
  ********************* */
  public Trem(int x, int y){
    this.x = x;
    this.y = y;
  }

  /* *********************
  * Metodo: esperar
  * Funcao: faz a thread do trem dormir por um tempo definido por sua velocidad
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  protected void esperar(){
    try {
      if (velocidade!=0)
        sleep(80/velocidade);
    } 
    catch (InterruptedException e) {}
  }

  //Getters e Setters
  public Sprite getImagem() {
    return imagem;
  }

  public void setImagem(Sprite imagem) {
    this.imagem = imagem;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }


  public int getVelocidade() {
    return velocidade;
  }


  public void setVelocidade(int velocidade) {
    this.velocidade = velocidade;
  }
}
