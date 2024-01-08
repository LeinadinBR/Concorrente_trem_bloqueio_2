import java.awt.Canvas;
import java.awt.Dimension;

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 15 de Abril de 2021
Alteracao: 18 de Abril de 2021
Nome.....: Display
Funcao...: Tem como funcao exibir a tela de canvas onde o programa sera "desenhado"
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class Display {
  private Canvas tela;

  /* *********************
  * Metodo: Display
  * Funcao: Metodo construtor de Display
  * Parametros: nenhum
  ********************* */
  public Display(){
    tela = new Canvas();
    tela.setSize(new Dimension(1080,450));
    tela.setFocusable(false);
  }

  /* *********************
  * Metodo: criarBufferStrategy
  * Funcao: cria a BufferStrategy do Canvas
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  public void criarBufferStrategy(){
    tela.createBufferStrategy(2);
  }

  //getter de Tela
  public Canvas getTela() {
    return tela;
  }
}
