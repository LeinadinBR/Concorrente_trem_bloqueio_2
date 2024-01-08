import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor: Daniel Nogueira
Matricula: 201911910
Inicio...: 09 de Maio de 2021
Alteracao: 09 de Maio de 2021
Nome.....: FrameInicial
Funcao...: Tela para escolher o protocolo
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class FrameInicial extends JPanel{
  private JButton[] buttons = new JButton[4];
  
  public FrameInicial(){
    inicializar();
    this.add(buttons[0]);
    this.add(buttons[1]);
    this.add(buttons[2]);
    this.add(buttons[3]);
  }

  private void inicializar(){
    buttons[0] = new JButton("Protocolo de Bandeiras");
    buttons[1] = new JButton("Variavel de Bloqueio");
    buttons[2] = new JButton("Estrita Alternacia");
    buttons[3] = new JButton("Solucao de Peterson");
  }

  /* *********************
  * Metodo: acaoDosBotoes
  * Funcao: seta as acoes dos botoes
  * Parametros: Pista pista, JFrame frame
  * Retorno: void
  ********************* */
  public void acaoDosBotoes(Pista pista, JFrame frame){
    buttons[0].addActionListener(e -> {
      pista.setTipoDeProtocolo("bandeiras");
      frame.setVisible(false);
    });
    buttons[1].addActionListener(e -> {
      pista.setTipoDeProtocolo("sinal");
      frame.setVisible(false);
    });
    buttons[2].addActionListener(e -> {
      pista.setTipoDeProtocolo("alternancia");
      frame.setVisible(false);
    });
    buttons[3].addActionListener(e -> {
      pista.setTipoDeProtocolo("peterson");
      frame.setVisible(false);
    });
  }
}
