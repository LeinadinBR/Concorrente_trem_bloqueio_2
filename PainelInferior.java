import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
  Autor: Daniel Nogueira
  Matricula: 201911910
  Inicio...: 15 de Abril de 2021
  Alteracao: 18 de Abril de 2021
  Nome.....: PainelInferior
  Funcao...: Classe que herda de 'JPanel' e serve para conter os sliders que controlam a velocidade dos trens
  =-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
public class PainelInferior extends JPanel {
  /**
   *
   */
  private static final long serialVersionUID = 1L;    //numero serial herdado de 'JPanel'
  private JSlider trem1, trem2;         //objetos de 'JSlider' 
  private JLabel label1, label2;        //labels indicando que trem o slider modifica

  /* *********************
  * Metodo: PainelInferior
  * Funcao: Construtor de PainelInferior
  * Parametros: nenhum
  ********************* */
  public PainelInferior() {
    inicializar();
    this.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    this.add(label1);
    this.add(trem1);
    this.add(label2);
    this.add(trem2);
    this.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
  }

  /* *********************
  * Metodo: inicializar
  * Funcao: inicializa os objetos da classe
  * Parametros: nenhum
  * Retorno: void
  ********************* */
  private void inicializar(){
    trem1 = new JSlider(0,10,1);
    trem2 = new JSlider(0,10,1);

    label1 = new JLabel("Velocidade do trem 1");
    label2 = new JLabel("Velocidade do trem 2");
  }

  //metodos getters e setters
  public JSlider getTrem1() {
    return trem1;
  }

  public void setTrem1(JSlider trem1) {
    this.trem1 = trem1;
  }

  public JSlider getTrem2() {
    return trem2;
  }

  public void setTrem2(JSlider trem2) {
    this.trem2 = trem2;
  }
}
