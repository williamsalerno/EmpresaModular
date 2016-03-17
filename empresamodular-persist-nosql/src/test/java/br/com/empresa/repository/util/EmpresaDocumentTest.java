package br.com.empresa.repository.util;

import static br.com.contmatic.empresawilliam.EnderecoType.COMERCIAL;
import static br.com.contmatic.empresawilliam.EnderecoType.RESIDENCIAL;
import static br.com.contmatic.empresawilliam.TelefoneType.CELULAR;
import static br.com.contmatic.empresawilliam.TelefoneType.FIXO;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.junit.Test;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.Endereco;
import br.com.contmatic.empresawilliam.Telefone;

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

        this.empresa.setCnpj("12345678911234");
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

        Document empresaDoc = EmpresaDocument.toDocument(empresa);
        List<Document> listaEnderecos = EnderecoDocument.toDocument(enderecos);

        assertEquals(empresa.getCnpj(), empresaDoc.get("cnpj"));
        assertEquals(empresa.getRazaoSocial(), empresaDoc.get("razaoSocial"));
        assertEquals(empresa.getProprietario(), empresaDoc.get("proprietario"));
        assertEquals(empresa.getEmail(), empresaDoc.get("email"));
        assertEquals(empresa.getSite(), empresaDoc.get("site"));

        List<String> lista = new ArrayList<String>();

        if (empresaDoc.get("enderecos").equals(listaEnderecos)) {

        }
    }

    @Test
    public void deve_aceitar_null_se_empresa_e_null() {
        assertNull(EmpresaDocument.toDocument(null));
    }

}
