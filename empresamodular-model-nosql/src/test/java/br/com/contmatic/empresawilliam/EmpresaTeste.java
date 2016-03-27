package br.com.contmatic.empresawilliam;

import static br.com.contmatic.empresawilliam.util.ValidationUtil.hasErrors;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

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
import org.junit.runners.MethodSorters;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * @author William
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTeste {

    private Empresa empresa;
    private Empresa tarefa;
    private Empresa empresaInvalida;
    private LocalDate dataTesteOntem;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    public Timeout globalTimeout = Timeout.seconds(1);

    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.empresawilliam.templates");
    }

    @Before
    public void setUp() {
        this.empresa = Fixture.from(Empresa.class).gimme("empresa_valida");
        this.empresaInvalida = Fixture.from(Empresa.class).gimme("empresa_invalida");
        this.dataTesteOntem = LocalDate.now().minusDays(1);
        this.tarefa = Fixture.from(Empresa.class).gimme("tarefa");
    }

    @After
    public void tearDown() {
        empresa = null;
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Teste de Empresa terminado.");
    }

    // Testes

    /**
     * 
     */
    @Test
    public void deve_aceitar_cnpj_valido() {
        assertThat(hasErrors(empresa, null), is(false));
        System.out.println(empresa.getCnpj());
    }

    @Test
    public void nao_deve_aceitar_cnpj_nulo() {
        empresa.setCnpj(null);
        assertTrue(hasErrors(empresa, "O CNPJ deve ser preenchido."));
    }

    @Test
    public void nao_deve_aceitar_cnpj_vazio() {
        empresa.setCnpj("");
        assertTrue(hasErrors(empresa, "O CNPJ não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_cnpj_menor_que_limite() {
        assertTrue(hasErrors(empresaInvalida, "CNPJ inválido. Deve conter 14 dígitos numéricos."));
    }

    @Test
    public void nao_deve_aceitar_cnpj_maior_que_limite() {
        assertTrue(hasErrors(empresaInvalida, "CNPJ inválido. Deve conter 14 dígitos numéricos."));
    }

    @Test(timeout = 1000)
    public void deve_aceitar_cnpj_so_com_numeros() {
        assertTrue(hasErrors(empresaInvalida, "CNPJ inválido. Deve conter 14 dígitos numéricos."));
    }

    @Test
    public void deve_aceitar_proprietario_valido() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_proprietario_nulo() {
        empresa.setProprietario(null);
        assertTrue(hasErrors(empresa, "O nome de proprietário deve ser preenchido."));
    }

    @Test
    public void nao_deve_aceitar_proprietario_vazio() {
        empresa.setProprietario("");
        assertTrue(hasErrors(empresa, "O nome de proprietário não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_proprietario_em_branco() {
        empresa.setProprietario(" ");
        assertTrue(hasErrors(empresa, "O nome de proprietário não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_proprietario_menor_que_limite() {
        empresa.setProprietario("a");
        assertTrue(hasErrors(empresa, "O nome de proprietário deve conter entre 2 e 50 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_proprietario_maior_que_limite() {
        empresa.setProprietario("123456789112345678921234567893123456789412345678951");
        assertTrue(hasErrors(empresa, "O nome de proprietário deve conter entre 2 e 50 caracteres."));
    }

    @Test
    public void deve_aceitar_proprietario_apenas_com_letras() {
        empresa.setProprietario("123");
        assertTrue(hasErrors(empresa, "O nome de proprietário só pode conter letras."));
    }

    @Test
    public void deve_aceitar_razaoSocial_valido() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_razaoSocial_nulo() {
        empresa.setRazaoSocial(null);
        assertTrue(hasErrors(empresa, "A razão social deve ser preenchida."));
    }

    @Test
    public void nao_deve_aceitar_razaoSocial_vazio() {
        empresa.setRazaoSocial("");
        assertTrue(hasErrors(empresa, "A razão social não pode ficar vazia."));
    }

    @Test
    public void nao_deve_aceitar_razaoSocial_em_branco() {
        empresa.setRazaoSocial(" ");
        assertTrue(hasErrors(empresa, "A razão social não pode ficar vazia."));
    }

    @Test
    public void nao_deve_aceitar_razaoSocial_menor_que_limite() {
        empresa.setRazaoSocial("exe");
        assertTrue(hasErrors(empresa, "A razão social deve conter entre 4 e 40 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_razaoSocial_maior_que_limite() {
        empresa.setRazaoSocial("12345678911234567892123456789312345678941");
        assertTrue(hasErrors(empresa, "A razão social deve conter entre 4 e 40 caracteres."));
    }

    @Test
    public void deve_aceitar_email_valido() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_email_nulo() {
        empresa.setEmail(null);
        assertTrue(hasErrors(empresa, "O email deve ser preenchido."));
    }

    @Test
    public void nao_deve_aceitar_email_vazio() {
        empresa.setEmail("");
        assertTrue(hasErrors(empresa, "O email não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_email_em_branco() {
        empresa.setEmail(" ");
        assertTrue(hasErrors(empresa, "O email não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_email_email_menor_que_limite() {
        empresa.setEmail("a@a.co");
        assertTrue(hasErrors(empresa, "O email deve conter entre 7 e 50 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_email_maior_que_limite() {
        empresa.setEmail("123456789112345678921234567893123456789412345678951@");
        assertTrue(hasErrors(empresa, "O email deve conter entre 7 e 50 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_email_invalido() {
        assertTrue(hasErrors(empresaInvalida, "O email informado é inválido."));
    }

    @Test
    public void deve_aceitar_lista_de_enderecos_valida() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_lista_de_enderecos_nula() {
        empresa.setEnderecos(null);
        assertTrue(hasErrors(empresa, "O endereço deve ser preenchido."));
    }

    @Test
    public void nao_deve_aceitar_lista_de_enderecos_vazia() {
        empresaInvalida.setEnderecos(null);
        assertTrue(hasErrors(empresaInvalida, "A empresa deve conter no mínimo 2 endereços."));
    }

    @Test
    public void nao_deve_aceitar_lista_de_enderecos_invalida() {
        assertTrue(hasErrors(empresaInvalida, "A empresa deve conter no mínimo 2 endereços."));
    }

    @Test
    public void deve_aceitar_lista_de_telefones_valida() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_lista_de_telefones_nula() {
        empresa.setTelefones(null);
        assertTrue(hasErrors(empresa, "O telefone deve ser preenchido."));
    }

    @Test
    public void nao_deve_aceitar_lista_de_telefones_vazia() {
        empresa.setTelefones(null);
        assertTrue(hasErrors(empresaInvalida, "A empresa deve conter no mínimo 2 telefones."));
    }

    @Test
    public void nao_deve_aceitar_lista_de_telefones_invalida() {
        assertTrue(hasErrors(empresaInvalida, "A empresa deve conter no mínimo 2 telefones."));
    }

    @Test
    public void deve_aceitar_site_valido() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_site_nulo() {
        empresa.setSite(null);
        assertTrue(hasErrors(empresa, "O site deve ser preenchido."));
    }

    @Test
    public void nao_deve_aceitar_site_vazio() {
        empresa.setSite("");
        assertTrue(hasErrors(empresa, "O site não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_site_em_branco() {
        empresa.setSite(" ");
        assertTrue(hasErrors(empresa, "O site não pode ficar vazio."));
    }

    @Test
    public void nao_deve_aceitar_site_invalido() {
        assertTrue(hasErrors(empresaInvalida, "Site inválido."));
    }

    @Test
    public void nao_deve_aceitar_site_menor_que_limite() {
        empresa.setSite("a.com");
        assertTrue(hasErrors(empresa, "O site deve conter entre 6 e 50 caracteres."));
    }

    @Test
    public void nao_deve_aceitar_site_maior_que_limite() {
        empresa.setSite("exemplotesteexemplotesteexemplotesteexemploteste.com");
        assertTrue(hasErrors(empresa, "O site deve conter entre 6 e 50 caracteres."));
    }

    @Test
    public void deve_aceitar_dataCriacao_valido() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_dataCriacao_nulo() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("A data de criação deve ser preenchida.");
        empresa.setDataDeCriacao(null);
    }

    @Test
    public void nao_deve_aceitar_dataCriacao_posterior() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Data de criação informada não pode ser posterior à data atual.");
        empresa.setDataDeCriacao(LocalDate.now().plusDays(1));
    }

    @Test
    public void nao_deve_aceitar_dataCriacao_anterior() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Data de criação informada não pode ser anterior à data atual.");
        empresa.setDataDeCriacao(dataTesteOntem);
    }

    @Test
    public void nao_deve_aceitar_dataAlteracao_nulo() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("A data de alteração deve ser preenchida.");
        empresa.setDataDeAlteracao(null);
    }

    @Test
    public void deve_aceitar_dataAlteracao_posterior_a_criacao() {
        assertThat(hasErrors(empresa, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_dataAlteracao_anterior_a_criacao() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("A data de alteração deve ser posterior à data de criação.");
        empresa.setDataDeAlteracao(dataTesteOntem);
    }

    @Test
    public void deve_aceitar_empresa_nulo() {
        empresa = new Empresa();
        System.out.println(empresa);
    }

    @Test
    public void nao_deve_aceitar_empresa_nulo() {
        System.out.println(empresa);
    }

    // TAREFA=====================================================================================================================================
    @Test
    public void nao_deve_aceitar_caracteres_invalidos() {
        assertTrue(hasErrors(tarefa, "Existem Apelidos de Empresas contendo caracteres não permitidos pelo sistema operacional"));
    }

    @Test
    public void deve_aceitar_caracteres_validos() {
        empresa.setApelido("a_");
        assertThat(hasErrors(empresa, null), is(false));
    }

    @Test
    public void nao_deve_aceitar_mais_que_maximo_caracteres() {
        empresa.setApelido("abcdefghi");
        assertTrue(hasErrors(tarefa, "Existem Apelidos de Empresas contendo caracteres não permitidos pelo sistema operacional"));
    }

    @Test
    public void nao_deve_aceitar_menos_que_minimo_caracteres() {
        empresa.setApelido(" ");
        assertTrue(hasErrors(tarefa, "Existem Apelidos de Empresas contendo caracteres não permitidos pelo sistema operacional"));
    }

}
