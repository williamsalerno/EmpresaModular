/******************************************************************************
 * Produto: Gestor de Empresas                                                *
 * Contmatic Phoenix © Desde 1986                                             *
 * Tecnologia em Softwares de Gestão Contábil, Empresarial e ERP              *
 * Todos os direitos reservados.                                              *
 *                                                                            *
 *                                                                            *
 *    Histórico:                                                              *
 *          Data        Programador              Tarefa                       *
 *          ----------  -----------------        -----------------------------*
 *   Autor  31/03/2016  william.salerno          Classe criada.        	      *
 *                                                                            *
 *   Comentários:                                                             *
 *                                                                            *
 *                                                                            *
 *                                                                            *
 *                                                                            *
 *****************************************************************************/
package br.com.contmatic.empresawilliam;

/**
 * The Enum TelefoneType.
 *
 * @author williansalerno
 */
public enum TelefoneType {

                          /** The celular. */
                          CELULAR("Celular", 9),
                          /** The fixo. */
                          FIXO("Fixo", 8);

    /** The descricao. */
    private String descricao;

    /** The tamanho. */
    private int tamanho;

    /**
     * Instantiates a new telefone type.
     *
     * @param descricao the descricao
     * @param tamanho the tamanho
     */
    private TelefoneType(String descricao, int tamanho) {
        this.descricao = descricao;
        this.tamanho = tamanho;
    }

    /**
     * Gets the descricao.
     *
     * @return the descricao
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Gets the tamanho.
     *
     * @return the tamanho
     */
    public int getTamanho() {
        return this.tamanho;
    }

}
