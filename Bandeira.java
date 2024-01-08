import java.awt.Graphics;

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 08 de Maio de 2021
Alteracao: 09 de Maio de 2021
Nome.....: Bandeira
Funcao...: Cria as bandeiras para o protocolo de bandeiras
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class Bandeira {
  
  private Sprite imagemAtual;
  private Sprite imagens[] = new Sprite[3];
  private int x, y;
  private int valorImagemAtual=0;

  public Bandeira(int x, int y){
    imagens[0] = new Sprite("res/bandeira_cima.png");
    imagens[1] = new Sprite("res/bandeira_meio.png");
    imagens[2] = new Sprite("res/bandeira_baixo.png");
    imagemAtual = imagens[0];
    this.x = x;
    this.y = y;
  }
  
  public void renderizar(Graphics g){
    g.drawImage(imagemAtual.getSprite(), x, y, null);
  }

  /* *********************
  * Metodo: mudaEstado
  * Funcao: muda a imagem da bandeira
  * Parametros: int i
  * Retorno: void
  ********************* */
  public void mudarEstado(int i){
    switch (i){
      case 0:
        valorImagemAtual = 0;
        imagemAtual = imagens[valorImagemAtual];
        break;
      case 1:
        valorImagemAtual = 1;
        imagemAtual = imagens[valorImagemAtual];
        break;
      case 2:
        valorImagemAtual = 2;
        imagemAtual = imagens[valorImagemAtual];
        break;
      default:
        break;
    }
  }

  //get e set
  public Sprite getImagemAtual() {
    return imagemAtual;
  }

  public void setImagemAtual(Sprite imagem) {
    this.imagemAtual = imagem;
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

  public Sprite[] getImagens() {
    return imagens;
  }

  public void setImagens(Sprite[] imagens) {
    this.imagens = imagens;
  }

  public int getValorImagemAtual() {
    return valorImagemAtual;
  }

  public void setValorImagemAtual(int valorImagemAtual) {
    this.valorImagemAtual = valorImagemAtual;
  } 
}
