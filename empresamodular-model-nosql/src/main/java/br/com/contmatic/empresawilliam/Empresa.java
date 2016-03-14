package br.com.contmatic.empresawilliam;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.caelum.stella.bean.validation.CNPJ;

/**
 * The Class Empresa.
 *
 * @author williansalerno
 */
public class Empresa {

    // Constantes

    /** The Constant TAMANHO_MINIMO_RAZAOSOCIAL. */
    private final static int TAMANHO_MINIMO_RAZAOSOCIAL = 4;

    /** The Constant TAMANHO_MAXIMO_RAZAOSOCIAL. */
    private final static int TAMANHO_MAXIMO_RAZAOSOCIAL = 40;

    /** The Constant TAMANHO_MINIMO_PROPRIETARIO. */
    private final static int TAMANHO_MINIMO_PROPRIETARIO = 2;

    /** The Constant TAMANHO_MAXIMO_PROPRIETARIO. */
    private final static int TAMANHO_MAXIMO_PROPRIETARIO = 50;

    /** The Constant TAMANHO_MINIMO_EMAIL. */
    private final static int TAMANHO_MINIMO_EMAIL = 7;

    /** The Constant TAMANHO_MAXIMO_EMAIL. */
    private final static int TAMANHO_MAXIMO_EMAIL = 50;

    /** The Constant TAMANHO_MINIMO_ENDERECOS. */
    private final static int TAMANHO_MINIMO_ENDERECOS = 2;

    /** The Constant TAMANHO_MINIMO_TELEFONE. */
    private final static int TAMANHO_MINIMO_TELEFONE = 2;

    /** The Constant TAMANHO_MINIMO_SITE. */
    private final static int TAMANHO_MINIMO_SITE = 6;

    /** The Constant TAMANHO_MAXIMO_SITE. */
    private final static int TAMANHO_MAXIMO_SITE = 50;

    // Variáveis

    /** The cnpj. */
    @NotNull(message = "O CNPJ deve ser preenchido.")
    @NotEmpty(message = "O CNPJ não pode ficar vazio.")
    @Pattern(regexp = "\\d{14}", message = "CNPJ inválido. Deve conter 14 dígitos numéricos.")
    @CNPJ(message = "CNPJ inválido.")
    private String cnpj;

    /** The razao social. */
    @NotNull(message = "A razão social deve ser preenchida.")
    @NotEmpty(message = "A razão social não pode ficar vazia.")
    @NotBlank(message = "A razão social não pode ficar vazia.")
    @Pattern(regexp = "[a-zA-Z0-9]", message = "A razão social só pode conter caracteres alfanuméricos.")
    @Length(min = TAMANHO_MINIMO_RAZAOSOCIAL, max = TAMANHO_MAXIMO_RAZAOSOCIAL, message = "A razão social deve conter entre {min} e {max} caracteres.")
    private String razaoSocial;

    /** The proprietario. */
    @NotNull(message = "O nome de proprietário deve ser preenchido.")
    @NotEmpty(message = "O nome de proprietário não pode ficar vazio.")
    @NotBlank(message = "O nome de proprietário não pode ficar vazio.")
    @Pattern(regexp = "[a-zA-Z]", message = "O nome de proprietário só pode conter letras.")
    @Length(min = TAMANHO_MINIMO_PROPRIETARIO, max = TAMANHO_MAXIMO_PROPRIETARIO, message = "O nome de proprietário deve conter entre {min} e {max} caracteres.")
    private String proprietario;

    /** The email. */
    @NotNull(message = "O email deve ser preenchido.")
    @NotEmpty(message = "O email não pode ficar vazio.")
    @NotBlank(message = "O email não pode ficar vazio.")
    @Length(min = TAMANHO_MINIMO_EMAIL, max = TAMANHO_MAXIMO_EMAIL, message = "O email deve conter entre {min} e {max} caracteres.")
    @Email(regexp = "[a-z]+@{1}\\w+\\.com{1}(\\.br)*", message = "O email informado é inválido.")
    private String email;

    /** The enderecos. */
    @NotNull(message = "O endereço deve ser preenchido.")
    @NotEmpty(message = "A empresa deve conter no mínimo 2 endereços.")
    @Size(min = TAMANHO_MINIMO_ENDERECOS, message = "A empresa deve conter no mínimo {min} endereços.")
    @Valid
    private Set<Endereco> enderecos;

    /** The telefones. */
    @NotNull(message = "O telefone deve ser preenchido.")
    @NotEmpty(message = "A empresa deve conter no mínimo 2 telefones.")
    @Size(min = TAMANHO_MINIMO_TELEFONE, message = "A empresa deve conter no mínimo {min} telefones.")
    @Valid
    private Set<Telefone> telefones;

    /** The site. */
    @NotNull(message = "O site deve ser preenchido.")
    @NotEmpty(message = "O site não pode ficar vazio.")
    @NotBlank(message = "O site não pode ficar vazio.")
    @Length(min = TAMANHO_MINIMO_SITE, max = TAMANHO_MAXIMO_SITE, message = "O site deve conter entre {min} e {max} caracteres.")
    @Pattern(regexp = "[a-z]+(\\.com)*\\.br{1}", message = "Site inválido.")
    private String site;

    /** The data de criacao. */
    @NotNull(message = "A data de criação deve ser preenchida.")
    private LocalDate dataDeCriacao;

    /** The data de alteracao. */
    @NotNull(message = "A data de alteração deve ser preenchida.")
    @Future(message = "A data de alteração deve ser posterior à data de criação.")
    private LocalDate dataDeAlteracao;

    // getters e setters

    /**
     * Obtém a razão social.
     *
     * @return the razao social
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * Determina um valor para a razão social e chama um método de validação.
     *
     * @param razaoSocial the new razao social
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * Obtém o CNPJ.
     *
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Determina um valor para o CNPJ e chama um método de validação.
     *
     * @param cnpj the new cnpj
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Gets the proprietario.
     *
     * @return the proprietario
     */
    public String getProprietario() {
        return proprietario;
    }

    /**
     * Determina um valor para proprietário e chama um método de validação.
     *
     * @param proprietario the new proprietario
     */
    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * Obtém email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Determina um valor para email e chama um método de validação.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém uma Seta de endereços.
     *
     * @return the enderecos
     */
    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    /**
     * Determina referências de Endereços e chama um método de validação.
     *
     * @param enderecos the new enderecos
     */
    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    /**
     * Obtém uma Seta de telefones.
     *
     * @return the telefones
     */
    public Set<Telefone> getTelefones() {
        return telefones;
    }

    /**
     * Determina referências de Telefones e chama um método de validação.
     *
     * @param telefones the new telefones
     */
    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    /**
     * Obtém site.
     *
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * Determina um valor para site e chama um método de validação.
     *
     * @param site the new site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Obtém data de criação.
     *
     * @return the data de criacao
     */
    public LocalDate getDataDeCriacao() {
        return dataDeCriacao;
    }

    /**
     * Determina um data de criação, chama um método de validação e depois um método de conversão de leitura da data ao usuário.
     *
     * @param dataDeCriacao the new data de criacao
     */
    public void setDataDeCriacao(LocalDate dataDeCriacao) {
        verificaSeDataDeCriacaoEAnterior(dataDeCriacao);
        verificaSeDataDeCriacaoEPosterior(dataDeCriacao);
        this.dataDeCriacao = dataDeCriacao;
    }

    /**
     * Obtém data de alteração.
     *
     * @return the data de alteracao
     */
    public LocalDate getDataDeAlteracao() {
        return dataDeAlteracao;
    }

    /**
     * Determina um data de alteração, chama um método de validação e depois um método de conversão de leitura da data ao usuário.
     *
     * @param dataDeAlteracao the new data de alteracao
     */
    public void setDataDeAlteracao(LocalDate dataDeAlteracao) {
        verificaSeDataDeAlteracaoEAnteriorACriacao(dataDeAlteracao);
        this.dataDeAlteracao = dataDeAlteracao;
    }

    // Formatação das datas

    /**
     * Converte a data de criação para uma leitura apropriada ao usuário.
     *
     * @param dataDeCriacao the data de criacao
     * @return the string
     */
    public String converteDataDeCriacao(LocalDate dataDeCriacao) {
        checkNotNull(dataDeCriacao, "A data de criação deve ser preenchida.");
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        return dataDeCriacao.toString(dtf);
    }

    /**
     * Converte a data de alteração para uma leitura apropriada ao usuário.
     *
     * @param dataDeAlteracao the data de alteracao
     * @return the string
     */
    public String converteDataDeAlteracao(LocalDate dataDeAlteracao) {
        checkNotNull(dataDeAlteracao, "A data de alteração deve ser preenchida.");
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        return dataDeAlteracao.toString(dtf);
    }

    // verificação das datas
    /**
     * Verifica se data de criacao é anterior à data atual.
     *
     * @param dataDeCriacao the data de criacao
     */
    public void verificaSeDataDeCriacaoEAnterior(LocalDate dataDeCriacao) {
        checkArgument(!dataDeCriacao.isBefore(LocalDate.now()), "Data de criação informada não pode ser anterior à data atual.");
    }

    /**
     * Verifica se data de criacao é posterior à data atual.
     *
     * @param dataDeCriacao the data de criacao
     */
    public void verificaSeDataDeCriacaoEPosterior(LocalDate dataDeCriacao) {
        checkArgument(!dataDeCriacao.isAfter(LocalDate.now()), "Data de criação informada não pode ser posterior à data atual.");
    }

    /**
     * Verifica se data de alteracao é anterior à criação.
     *
     * @param dataDeAlteracao the data de alteracao
     */
    public void verificaSeDataDeAlteracaoEAnteriorACriacao(LocalDate dataDeAlteracao) {
        checkState(dataDeAlteracao.isAfter(getDataDeCriacao()), "A data de alteração deve ser posterior à data de criação.");
    }

    // Equals, HashCode e toString

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.cnpj).toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Empresa)) {
            return false;
        }
        Empresa outra = (Empresa) obj;
        return new EqualsBuilder().append(this.cnpj, outra.cnpj).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, MULTI_LINE_STYLE).append("Razão social: ", razaoSocial).append("Proprietário: ", proprietario).append("CNPJ", cnpj).append("Endereço: ", (enderecos != null) ? enderecos : null)
                .append("Telefone: ", (telefones != null) ? telefones : null).append("Email: ", email).append("Site: ", site)
                .append("Data de criação: ", (converteDataDeCriacao(dataDeCriacao) != null) ? converteDataDeCriacao(dataDeCriacao) : null)
                .append("Data de alteração: ", (converteDataDeAlteracao(dataDeAlteracao) != null) ? converteDataDeAlteracao(dataDeAlteracao) : null).build();
    }

}
