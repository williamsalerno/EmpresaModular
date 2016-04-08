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

import static br.com.contmatic.empresawilliam.util.ValidationUtil.hasErrors;
import static br.com.six2six.fixturefactory.Fixture.from;
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static java.lang.System.out;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.joda.time.LocalDate.now;
import static org.junit.Assert.assertTrue;
import static org.junit.rules.ExpectedException.none;
import static org.junit.rules.Timeout.seconds;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

/**
 * The Class EmpresaTeste.
 *
 * @author William
 */
@FixMethodOrder(NAME_ASCENDING)
public class EmpresaTeste {

    /** The empresa. */
    private Empresa empresa;

    /** The empresa invalida. */
    private Empresa empresaInvalida;

    /** The data teste ontem. */
    private LocalDate dataTesteOntem;

    /** The thrown. */
    @Rule
    public ExpectedException thrown = none();

    /** The global timeout. */
    public Timeout globalTimeout = seconds(1);

    /**
     * Set up before class.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        loadTemplates("br.com.contmatic.empresawilliam.templates");
    }

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        this.empresa = from(Empresa.class).gimme("empresa_valida");
        this.empresaInvalida = from(Empresa.class).gimme("empresa_invalida");
        this.dataTesteOntem = now().minusDays(1);
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        empresa = null;
    }

    /**
     * Tear down after class.
     */
    @AfterClass
    public static void tearDownAfterClass() {
        out.println("Teste de Empresa terminado.");
    }

    // Testes

    /**
     * Deve_aceitar_cnpj_valido.
     */
    @Test
    public void deve_aceitar_cnpj_valido() {
        assertThat(hasErrors(empresa, null), is(false));
        out.println(empresa.getCnpj());
    }

    /**
     * Nao_deve_aceitar_cnpj_nulo.
     */
    @Test
    public void nao_deve_aceitar_cnpj_nulo() {
        empresa.setCnpj(null);
        assertTrue(hasErrors(empresa, "O CNPJ deve ser preenchido."));
    }

    /**
     * Nao_deve_aceitar_cnpj_vazio.
     */
    @Test
    public void nao_deve_aceitar_cnpj_vazio() {
        empresa.setCnpj("");
        assertTrue(hasErrors(empresa, "O CNPJ não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_cnpj_menor_que_limite.
     */
    @Test
    public void nao_deve_aceitar_cnpj_menor_que_limite() {
        assertTrue(hasErrors(empresaInvalida, "CNPJ inválido. Deve conter 14 dígitos numéricos."));
    }

    /**
     * Nao_deve_aceitar_cnpj_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_cnpj_maior_que_limite() {
        assertTrue(hasErrors(empresaInvalida, "CNPJ inválido. Deve conter 14 dígitos numéricos."));
    }

    /**
     * Deve_aceitar_cnpj_so_com_numeros.
     */
    @Test(timeout = 1000)
    public void deve_aceitar_cnpj_so_com_numeros() {
        assertTrue(hasErrors(empresaInvalida, "CNPJ inválido. Deve conter 14 dígitos numéricos."));
    }

    /**
     * Deve_aceitar_proprietario_valido.
     */
    @Test
    public void deve_aceitar_proprietario_valido() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    /**
     * Nao_deve_aceitar_proprietario_nulo.
     */
    @Test
    public void nao_deve_aceitar_proprietario_nulo() {
        empresa.setProprietario(null);
        assertTrue(hasErrors(empresa, "O nome de proprietário deve ser preenchido."));
    }

    /**
     * Nao_deve_aceitar_proprietario_vazio.
     */
    @Test
    public void nao_deve_aceitar_proprietario_vazio() {
        empresa.setProprietario("");
        assertTrue(hasErrors(empresa, "O nome de proprietário não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_proprietario_em_branco.
     */
    @Test
    public void nao_deve_aceitar_proprietario_em_branco() {
        empresa.setProprietario(" ");
        assertTrue(hasErrors(empresa, "O nome de proprietário não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_proprietario_menor_que_limite.
     */
    @Test
    public void nao_deve_aceitar_proprietario_menor_que_limite() {
        empresa.setProprietario("a");
        assertTrue(hasErrors(empresa, "O nome de proprietário deve conter entre 2 e 50 caracteres."));
    }

    /**
     * Nao_deve_aceitar_proprietario_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_proprietario_maior_que_limite() {
        empresa.setProprietario("123456789112345678921234567893123456789412345678951");
        assertTrue(hasErrors(empresa, "O nome de proprietário deve conter entre 2 e 50 caracteres."));
    }

    /**
     * Deve_aceitar_proprietario_apenas_com_letras.
     */
    @Test
    public void deve_aceitar_proprietario_apenas_com_letras() {
        empresa.setProprietario("123");
        assertTrue(hasErrors(empresa, "O nome de proprietário só pode conter letras."));
    }

    /**
     * Deve_aceitar_razao social_valido.
     */
    @Test
    public void deve_aceitar_razaoSocial_valido() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    /**
     * Nao_deve_aceitar_razao social_nulo.
     */
    @Test
    public void nao_deve_aceitar_razaoSocial_nulo() {
        empresa.setRazaoSocial(null);
        assertTrue(hasErrors(empresa, "A razão social deve ser preenchida."));
    }

    /**
     * Nao_deve_aceitar_razao social_vazio.
     */
    @Test
    public void nao_deve_aceitar_razaoSocial_vazio() {
        empresa.setRazaoSocial("");
        assertTrue(hasErrors(empresa, "A razão social não pode ficar vazia."));
    }

    /**
     * Nao_deve_aceitar_razao social_em_branco.
     */
    @Test
    public void nao_deve_aceitar_razaoSocial_em_branco() {
        empresa.setRazaoSocial(" ");
        assertTrue(hasErrors(empresa, "A razão social não pode ficar vazia."));
    }

    /**
     * Nao_deve_aceitar_razao social_menor_que_limite.
     */
    @Test
    public void nao_deve_aceitar_razaoSocial_menor_que_limite() {
        empresa.setRazaoSocial("exe");
        assertTrue(hasErrors(empresa, "A razão social deve conter entre 4 e 40 caracteres."));
    }

    /**
     * Nao_deve_aceitar_razao social_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_razaoSocial_maior_que_limite() {
        empresa.setRazaoSocial("12345678911234567892123456789312345678941");
        assertTrue(hasErrors(empresa, "A razão social deve conter entre 4 e 40 caracteres."));
    }

    /**
     * Deve_aceitar_email_valido.
     */
    @Test
    public void deve_aceitar_email_valido() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    /**
     * Nao_deve_aceitar_email_nulo.
     */
    @Test
    public void nao_deve_aceitar_email_nulo() {
        empresa.setEmail(null);
        assertTrue(hasErrors(empresa, "O email deve ser preenchido."));
    }

    /**
     * Nao_deve_aceitar_email_vazio.
     */
    @Test
    public void nao_deve_aceitar_email_vazio() {
        empresa.setEmail("");
        assertTrue(hasErrors(empresa, "O email não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_email_em_branco.
     */
    @Test
    public void nao_deve_aceitar_email_em_branco() {
        empresa.setEmail(" ");
        assertTrue(hasErrors(empresa, "O email não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_email_email_menor_que_limite.
     */
    @Test
    public void nao_deve_aceitar_email_email_menor_que_limite() {
        empresa.setEmail("a@a.co");
        assertTrue(hasErrors(empresa, "O email deve conter entre 7 e 50 caracteres."));
    }

    /**
     * Nao_deve_aceitar_email_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_email_maior_que_limite() {
        empresa.setEmail("123456789112345678921234567893123456789412345678951@");
        assertTrue(hasErrors(empresa, "O email deve conter entre 7 e 50 caracteres."));
    }

    /**
     * Nao_deve_aceitar_email_invalido.
     */
    @Test
    public void nao_deve_aceitar_email_invalido() {
        assertTrue(hasErrors(empresaInvalida, "O email informado é inválido."));
    }

    /**
     * Deve_aceitar_lista_de_enderecos_valida.
     */
    @Test
    public void deve_aceitar_lista_de_enderecos_valida() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    /**
     * Nao_deve_aceitar_lista_de_enderecos_nula.
     */
    @Test
    public void nao_deve_aceitar_lista_de_enderecos_nula() {
        empresa.setEnderecos(null);
        assertTrue(hasErrors(empresa, "O endereço deve ser preenchido."));
    }

    /**
     * Nao_deve_aceitar_lista_de_enderecos_vazia.
     */
    @Test
    public void nao_deve_aceitar_lista_de_enderecos_vazia() {
        empresaInvalida.setEnderecos(null);
        assertTrue(hasErrors(empresaInvalida, "A empresa deve conter no mínimo 2 endereços."));
    }

    /**
     * Nao_deve_aceitar_lista_de_enderecos_invalida.
     */
    @Test
    public void nao_deve_aceitar_lista_de_enderecos_invalida() {
        assertTrue(hasErrors(empresaInvalida, "A empresa deve conter no mínimo 2 endereços."));
    }

    /**
     * Deve_aceitar_lista_de_telefones_valida.
     */
    @Test
    public void deve_aceitar_lista_de_telefones_valida() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    /**
     * Nao_deve_aceitar_lista_de_telefones_nula.
     */
    @Test
    public void nao_deve_aceitar_lista_de_telefones_nula() {
        empresa.setTelefones(null);
        assertTrue(hasErrors(empresa, "O telefone deve ser preenchido."));
    }

    /**
     * Nao_deve_aceitar_lista_de_telefones_vazia.
     */
    @Test
    public void nao_deve_aceitar_lista_de_telefones_vazia() {
        empresa.setTelefones(null);
        assertTrue(hasErrors(empresaInvalida, "A empresa deve conter no mínimo 2 telefones."));
    }

    /**
     * Nao_deve_aceitar_lista_de_telefones_invalida.
     */
    @Test
    public void nao_deve_aceitar_lista_de_telefones_invalida() {
        assertTrue(hasErrors(empresaInvalida, "A empresa deve conter no mínimo 2 telefones."));
    }

    /**
     * Deve_aceitar_site_valido.
     */
    @Test
    public void deve_aceitar_site_valido() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    /**
     * Nao_deve_aceitar_site_nulo.
     */
    @Test
    public void nao_deve_aceitar_site_nulo() {
        empresa.setSite(null);
        assertTrue(hasErrors(empresa, "O site deve ser preenchido."));
    }

    /**
     * Nao_deve_aceitar_site_vazio.
     */
    @Test
    public void nao_deve_aceitar_site_vazio() {
        empresa.setSite("");
        assertTrue(hasErrors(empresa, "O site não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_site_em_branco.
     */
    @Test
    public void nao_deve_aceitar_site_em_branco() {
        empresa.setSite(" ");
        assertTrue(hasErrors(empresa, "O site não pode ficar vazio."));
    }

    /**
     * Nao_deve_aceitar_site_invalido.
     */
    @Test
    public void nao_deve_aceitar_site_invalido() {
        assertTrue(hasErrors(empresaInvalida, "Site inválido."));
    }

    /**
     * Nao_deve_aceitar_site_menor_que_limite.
     */
    @Test
    public void nao_deve_aceitar_site_menor_que_limite() {
        empresa.setSite("a.com");
        assertTrue(hasErrors(empresa, "O site deve conter entre 6 e 50 caracteres."));
    }

    /**
     * Nao_deve_aceitar_site_maior_que_limite.
     */
    @Test
    public void nao_deve_aceitar_site_maior_que_limite() {
        empresa.setSite("exemplotesteexemplotesteexemplotesteexemploteste.com");
        assertTrue(hasErrors(empresa, "O site deve conter entre 6 e 50 caracteres."));
    }

    /**
     * Deve_aceitar_data criacao_valido.
     */
    @Test
    public void deve_aceitar_dataCriacao_valido() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    /**
     * Nao_deve_aceitar_data criacao_nulo.
     */
    @Test
    public void nao_deve_aceitar_dataCriacao_nulo() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("A data de criação deve ser preenchida.");
        empresa.setDataDeCriacao(null);
    }

    /**
     * Nao_deve_aceitar_data criacao_posterior.
     */
    @Test
    public void nao_deve_aceitar_dataCriacao_posterior() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Data de criação informada não pode ser posterior à data atual.");
        empresa.setDataDeCriacao(now().plusDays(1));
    }

    /**
     * Nao_deve_aceitar_data criacao_anterior.
     */
    @Test
    public void nao_deve_aceitar_dataCriacao_anterior() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Data de criação informada não pode ser anterior à data atual.");
        empresa.setDataDeCriacao(dataTesteOntem);
    }

    @Test
    public void nao_deve_aceitar_dataAlteracao_sem_dataCriacao() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("A data de criação deve ser preenchida.");
        empresa.setDataDeCriacao(null);
        empresa.setDataDeAlteracao(null);
    }

    @Test
    public void deve_aceitar_dataAlteracao_nulo() {
        empresa.setDataDeAlteracao(null);
    }

    /**
     * Deve_aceitar_data alteracao_posterior_a_criacao.
     */
    @Test
    public void deve_aceitar_dataAlteracao_posterior_a_criacao() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    /**
     * Nao_deve_aceitar_data alteracao_anterior_a_criacao.
     */
    @Test
    public void nao_deve_aceitar_dataAlteracao_anterior_a_criacao() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("A data de alteração deve ser posterior à data de criação.");
        empresa.setDataDeAlteracao(dataTesteOntem);
    }

    /**
     * Deve_aceitar_empresa_nulo.
     */
    @Test
    public void deve_aceitar_empresa_nulo() {
        empresa = new Empresa();
        out.println(empresa);
    }

    /**
     * Nao_deve_aceitar_empresa_nulo.
     */
    @Test
    public void nao_deve_aceitar_empresa_nulo() {
        out.println(empresa);
    }

}
