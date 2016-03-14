package br.com.contmatic.empresawilliam;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * The Class Telefone.
 *
 * @author williamsalerno
 */
public class Telefone {

    // Constantes

    /** The Constant TAMANHO_MINIMO_DDD. */
    private final static int TAMANHO_MINIMO_DDD = 11;

    /** The Constant TAMANHO_MAXIMO_DDD. */
    private final static int TAMANHO_MAXIMO_DDD = 99;

    // Variáveis

    /** The tipo telefone. */
    @NotNull(message = "O tipo de telefone deve ser preenchido.")
    private TelefoneType tipoTelefone;

    /** The ddd. */
    @Range(min = TAMANHO_MINIMO_DDD, max = TAMANHO_MAXIMO_DDD, message = "O número de DDD informado deve ser entre {min} e {max}.")
    private int ddd;

    /** The numero telefone. */
    @NotNull(message = "O número de telefone não pode ser nulo.")
    @NotEmpty(message = "O número de telefone não pode ficar vazio.")
    @NotBlank(message = "O número de telefone não pode ficar vazio.")
    @Pattern.List({ @Pattern(regexp = "\\d{8}", groups = Fixo.class, message = "Para telefone fixo, por favor informar 8 dígitos."),
        @Pattern(regexp = "\\d{9}", groups = Celular.class, message = "Para telefone celular, por favor informar 9 dígitos.") })
    private String telefone;

    // Getters e setters

    /**
     * Obtém tipo de telefone.
     *
     * @return the tipo telefone
     */
    public TelefoneType getTipoTelefone() {
        return tipoTelefone;
    }

    /**
     * Determina um valor para tipo de telefone e chama um método de verificação.
     *
     * @param tipoTelefone the new tipo telefone
     */
    public void setTipoTelefone(TelefoneType tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    /**
     * Obtém DDD.
     *
     * @return the ddd
     */
    public int getDdd() {
        return ddd;
    }

    /**
     * Determina um valor para DDD e chama um método de verificação.
     *
     * @param ddd the new ddd
     */
    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    /**
     * Obtém número de telefone.
     *
     * @return the numero telefone
     */
    public String getTelefoneFixo() {
        return telefone;
    }

    /**
     * Determina um valor para número de telefone e chama um método de verificação.
     *
     * @param telefoneFixo the new telefone fixo
     */
    public void setTelefoneFixo(String telefoneFixo) {
        this.telefone = telefoneFixo;
    }

    /**
     * Gets the telefone celular.
     *
     * @return the telefone celular
     */
    public String getTelefoneCelular() {
        return telefone;
    }

    /**
     * Sets the telefone celular.
     *
     * @param telefoneCelular the new telefone celular
     */
    public void setTelefoneCelular(String telefoneCelular) {
        this.telefone = telefoneCelular;
    }

    // Equals, HashCode e toString

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.ddd).append(this.tipoTelefone).append(this.telefone).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Telefone)) {
            return false;
        }
        Telefone outro = (Telefone) obj;
        return new EqualsBuilder().append(this.ddd, outro.ddd).append(this.tipoTelefone, outro.tipoTelefone).append(this.telefone, outro.telefone).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, MULTI_LINE_STYLE).append("tipo de telefone: ", this.tipoTelefone).append("ddd: ", this.ddd).append("telefone: ", this.telefone).build();
    }

}
