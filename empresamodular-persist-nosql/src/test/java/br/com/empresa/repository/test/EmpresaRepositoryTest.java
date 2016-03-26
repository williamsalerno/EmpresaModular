package br.com.empresa.repository.test;

import static br.com.contmatic.empresawilliam.EnderecoType.COMERCIAL;
import static br.com.contmatic.empresawilliam.EnderecoType.RESIDENCIAL;
import static br.com.contmatic.empresawilliam.TelefoneType.CELULAR;
import static br.com.contmatic.empresawilliam.TelefoneType.FIXO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.Endereco;
import br.com.contmatic.empresawilliam.Telefone;
import br.com.empresa.repository.EmpresaRepository;

public class EmpresaRepositoryTest {

	private Empresa empresa = new Empresa();
	private Empresa empresaUpdate = new Empresa();
	private Empresa empresaFind = new Empresa();
	Set<Endereco> endereco = new HashSet<Endereco>();
	Set<Telefone> telefone = new HashSet<Telefone>();
	List<String> pesquisa = new ArrayList<String>();

	@Before
	public void setUp() {
		Endereco endereco1 = new Endereco();
		Endereco endereco2 = new Endereco();
		Telefone telefone1 = new Telefone();
		Telefone telefone2 = new Telefone();

		endereco1.setTipoLogradouro("Rua");
		endereco1.setNomeLogradouro("Exemplo");
		endereco1.setNumeroEndereco(100);
		endereco1.setCep("12345678");
		endereco1.setTipoEndereco(COMERCIAL);

		endereco2.setTipoLogradouro("Avenida");
		endereco2.setNomeLogradouro("Teste");
		endereco2.setNumeroEndereco(999);
		endereco2.setCep("87654321");
		endereco2.setTipoEndereco(RESIDENCIAL);

		this.endereco.add(endereco1);
		this.endereco.add(endereco2);

		telefone1.setDdd(11);
		telefone1.setTelefone("12345678");
		telefone1.setTipoTelefone(FIXO);

		telefone2.setDdd(11);
		telefone2.setTelefone("123456789");
		telefone2.setTipoTelefone(CELULAR);

		telefone.add(telefone1);
		telefone.add(telefone2);

		this.empresa.setCnpj("62726723000120");
		this.empresa.setRazaoSocial("teste");
		this.empresa.setProprietario("exemplo");
		this.empresa.setEmail("exemplo@gmail.com");
		this.empresa.setSite("exemplo.com.br");
		this.empresa.setEnderecos(endereco);
		this.empresa.setTelefones(telefone);
		this.empresa.setDataDeCriacao(LocalDate.now());

		this.empresaUpdate.setCnpj("72218452000155");
		this.empresaUpdate.setRazaoSocial("TESTE2");
		this.empresaUpdate.setProprietario("TESTE");
		this.empresaUpdate.setEmail("teste@gmail.com");
		this.empresaUpdate.setSite("teste.com.br");
		this.empresaUpdate.setEnderecos(endereco);
		this.empresaUpdate.setTelefones(telefone);
		this.empresaUpdate.setDataDeCriacao(empresa.getDataDeCriacao());
		this.empresaUpdate.setDataDeAlteracao(LocalDate.now().plusDays(20));

		this.empresaFind.setProprietario("exemplo");
		this.empresaFind.setRazaoSocial("teste");
	}

	@Test
	public void deve_incluir_empresa() {
		EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
		repository.saveEmpresa(this.empresa);
	}

	@Test
	public void deve_atualizar_empresa() {
		EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
		repository.updateEmpresa(this.empresa, this.empresaUpdate);
	}

	@Test
	public void deve_atualizar_empresas() {
		EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
		repository.updateEmpresas(this.empresa, this.empresaUpdate);
	}

	@Test
	public void deve_remover_empresa() {
		EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
		repository.removeEmpresa(this.empresa);
	}

	@Test
	public void deve_remover_empresas() {
		EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
		repository.removeEmpresas(this.empresa);
	}

	@Test
	public void deve_buscar_todas_as_empresas() {
		EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
		System.out.println(repository.buscaEmpresa(this.empresa));
	}

	@Test
	public void deve_buscar_empresa_por_campos_predeterminados() {
		EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
		System.out.println(repository.buscaEmpresaPor(empresaFind));
	}

	@Test
	public void deve_buscar_empresa_por_lista_de_campos() {
		EmpresaRepository repository = new EmpresaRepository("localhost", 27017, "empresa");
		pesquisa.add("proprietario");
		System.out.println(repository.buscaEmpresaPor(pesquisa));
	}

}
