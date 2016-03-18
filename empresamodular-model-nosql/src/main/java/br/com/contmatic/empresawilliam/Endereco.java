package br.com.contmatic.empresawilliam;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Class Endereco.
 *
 * @author williansalerno
 */
public class Endereco {

    // Constantes

    /** The Constant TAMANHO_NUMERO_DE_ENDERECO_MINIMO. */
    public final static int TAMANHO_NUMERO_DE_ENDERECO_MINIMO = 1;

    /** The Constant TAMANHO_NUMERO_DE_ENDERECO_MAXIMO. */
    public final static int TAMANHO_NUMERO_DE_ENDERECO_MAXIMO = 9999;

    /** The Constant TAMANHO_NOME_DE_LOGRADOURO_MAXIMO. */
    public final static int TAMANHO_NOME_DE_LOGRADOURO_MAXIMO = 30;

    // Variáveis

    /** The numero endereco. */
    @Min(value = TAMANHO_NUMERO_DE_ENDERECO_MINIMO, message = "O número de endereço deve ser, no mínimo, {value}.")
    @Max(value = TAMANHO_NUMERO_DE_ENDERECO_MAXIMO, message = "O número de endereço deve ser, no máximo, {value}.")
    private int numeroEndereco;

    /** The tipo logradouro. */
    @NotNull(message = "O tipo de logradouro deve ser preenchido.")
    @NotEmpty(message = "O tipo de logradouro não pode ficar vazio.")
    @NotBlank(message = "O tipo de logradouro não pode ficar vazio.")
    @Pattern(regexp = "[a-zA-Z]{3,10}", message = "O tipo de logradouro deve conter entre 3 e 10 caracteres, sem números.")
    private String tipoLogradouro;

    /** The nome logradouro. */
    @NotNull(message = "O nome de logradouro deve ser preenchido.")
    @NotEmpty(message = "O nome de logradouro não pode ficar vazio.")
    @NotBlank(message = "O nome de logradouro não pode ficar vazio.")
    @Length(max = TAMANHO_NOME_DE_LOGRADOURO_MAXIMO, message = "O nome de logradouro deve conter entre 1 e {max} caracteres.")
    @Pattern(regexp = "[a-zA-Zá-úÁ-Ú0-9]+", message = "O nome de logradouro deve conter apenas caracteres alfanuméricos.")
    private String nomeLogradouro;

    /** The cep. */
    @NotNull(message = "O CEP deve ser preenchido.")
    @NotEmpty(message = "O CEP não pode ficar vazio.")
    @NotBlank(message = "O CEP não pode ficar vazio.")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos.")
    private String cep;

    /** The tipo endereco. */
    @NotNull(message = "O tipo de endereço deve ser preenchido.")
    private EnderecoType tipoEndereco;

    // getters e setters

    /**
     * Obtém tipo de logradouro.
     *
     * @return the tipo logradouro
     */
    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    /**
     * Determina um valor para tipo de logradouro e chama um método de validação.
     *
     * @param tipoLogradouro the new tipo logradouro
     */
    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    /**
     * Obtém nome de logradouro.
     *
     * @return the nome logradouro
     */
    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    /**
     * Determina um valor para nome de logradouro e chama um método de validação.
     *
     * @param nomeLogradouro the new nome logradouro
     */
    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    /**
     * Obtém número de endereço.
     *
     * @return the numero endereco
     */
    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    /**
     * Determina um valor para número de endereço e chama um método de validação.
     *
     * @param numeroEndereco the new numero endereco
     */
    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    /**
     * Obtém CEP.
     *
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * Determina um valor para CEP e chama um método de validação.
     *
     * @param cep the new cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Obtém tipo de endereço.
     *
     * @return the tipo endereco
     */
    public EnderecoType getTipoEndereco() {
        return tipoEndereco;
    }

    /**
     * Determina um valor para tipo de endereço e chama um método de validação.
     *
     * @param tipoEndereco the new tipo endereco
     */
    public void setTipoEndereco(EnderecoType tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    // Equals, HashCode e toString

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.cep).append(this.numeroEndereco).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Endereco)) {
            return false;
        }
        Endereco outro = (Endereco) obj;
        return new EqualsBuilder().append(this.cep, outro.cep).append(this.numeroEndereco, outro.numeroEndereco).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, MULTI_LINE_STYLE).append("tipo de logradouro: ", this.tipoLogradouro).append("nome de logradouro: ", this.nomeLogradouro)
                .append("número de endereço: ", this.numeroEndereco).append("CEP: ", this.cep).append("Tipo de endereço: ", this.tipoEndereco).build();
    }

}
