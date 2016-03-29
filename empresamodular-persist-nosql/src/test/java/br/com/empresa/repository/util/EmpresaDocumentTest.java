package br.com.empresa.repository.util;

import static br.com.contmatic.empresawilliam.EnderecoType.COMERCIAL;
import static br.com.contmatic.empresawilliam.EnderecoType.RESIDENCIAL;
import static br.com.contmatic.empresawilliam.TelefoneType.CELULAR;
import static br.com.contmatic.empresawilliam.TelefoneType.FIXO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.junit.Test;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.Endereco;
import br.com.contmatic.empresawilliam.Telefone;
import br.com.contmatic.empresawilliam.assembler.EmpresaAssembler;

/**
 * @author William
 *
 */
public class EmpresaDocumentTest {

    private Empresa empresa = new Empresa();
    Set<Endereco> enderecos = new HashSet<Endereco>();
    Set<Telefone> telefones = new HashSet<Telefone>();

    @Test
    public void deve_retornar_valores_corretos() {
        Endereco endereco1 = new Endereco();
        Endereco endereco2 = new Endereco();
        Telefone telefone1 = new Telefone();
        Telefone telefone2 = new Telefone();

        this.empresa.setCnpj("12345678911231");
        this.empresa.setRazaoSocial("Um Nome Qualquer");
        this.empresa.setProprietario("Fulano");
        this.empresa.setEmail("timetrial@gmail.com");
        this.empresa.setSite("www.timetrialfac.com");

        endereco1.setTipoLogradouro("Rua");
        endereco1.setNomeLogradouro("Exemplo");
        endereco1.setNumeroEndereco(100);
        endereco1.setCep("12345678");
        endereco1.setTipoEndereco(COMERCIAL);

        endereco2.setTipoLogradouro("Avenida");
        endereco2.setNomeLogradouro("Teste");
        endereco2.setNumeroEndereco(1000);
        endereco2.setCep("87654321");
        endereco2.setTipoEndereco(RESIDENCIAL);

        this.enderecos.add(endereco1);
        this.enderecos.add(endereco2);

        telefone1.setDdd(11);
        telefone1.setTelefone("12345678");
        telefone1.setTipoTelefone(FIXO);

        telefone2.setDdd(11);
        telefone2.setTelefone("123456789");
        telefone2.setTipoTelefone(CELULAR);

        telefones.add(telefone1);
        telefones.add(telefone2);

        empresa.setEnderecos(enderecos);
        empresa.setTelefones(telefones);

        Document empresaDoc = EmpresaAssembler.toDocument(empresa);
        @SuppressWarnings("unchecked")
        List<Document> enderecoDoc = (List<Document>) empresaDoc.get("enderecos");

        for(Document document : enderecoDoc) {
            if (document.get("tipoLogradouro").equals(endereco1.getTipoLogradouro())) {
                assertEquals(document.get("tipoLogradouro"), endereco1.getTipoLogradouro());
            }
            if (document.get("tipoLogradouro").equals(endereco2.getTipoLogradouro())) {
                assertEquals(document.get("tipoLogradouro"), endereco2.getTipoLogradouro());
            }
            if (document.get("nomeLogradouro").equals(endereco1.getNomeLogradouro())) {
                assertEquals(document.get("nomeLogradouro"), endereco1.getNomeLogradouro());
            }
            if (document.get("nomeLogradouro").equals(endereco2.getNomeLogradouro())) {
                assertEquals(document.get("nomeLogradouro"), endereco2.getNomeLogradouro());
            }
            if (document.get("numeroEndereco").equals(endereco1.getNumeroEndereco())) {
                assertEquals(document.get("numeroEndereco"), endereco1.getNumeroEndereco());
            }
            if (document.get("numeroEndereco").equals(endereco2.getNumeroEndereco())) {
                assertEquals(document.get("numeroEndereco"), endereco2.getNumeroEndereco());
            }
            if (document.get("cep").equals(endereco1.getCep())) {
                assertEquals(document.get("cep"), endereco1.getCep());
            }
            if (document.get("cep").equals(endereco2.getCep())) {
                assertEquals(document.get("cep"), endereco2.getCep());
            }
            if (document.get("tipoEndereco").equals(endereco1.getTipoEndereco())) {
                assertEquals(document.get("tipoEndereco"), endereco1.getTipoEndereco());
            }
            if (document.get("tipoEndereco").equals(endereco2.getTipoEndereco())) {
                assertEquals(document.get("tipoEndereco"), endereco2.getTipoEndereco());
            }

        }
        assertEquals(empresa.getCnpj(), empresaDoc.get("_id"));
        assertEquals(empresa.getRazaoSocial(), empresaDoc.get("razaoSocial"));
        assertEquals(empresa.getProprietario(), empresaDoc.get("proprietario"));
        assertEquals(empresa.getEmail(), empresaDoc.get("email"));
        assertEquals(empresa.getSite(), empresaDoc.get("site"));
    }

    @Test
    public void deve_aceitar_null_se_empresa_e_null() {
        assertNull(EmpresaAssembler.toDocument(null));
    }

}
