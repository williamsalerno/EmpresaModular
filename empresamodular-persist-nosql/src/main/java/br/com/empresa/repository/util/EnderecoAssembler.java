package br.com.empresa.repository.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresawilliam.Endereco;
import br.com.contmatic.empresawilliam.EnderecoType;

public class EnderecoAssembler {

    private EnderecoAssembler() {
    }

    public static Document toDocument(Endereco endereco) {
        if (endereco != null) {
            Document enderecoDoc = new Document("tipoLogradouro", endereco.getTipoLogradouro()).append("nomeLogradouro", endereco.getNomeLogradouro())
                    .append("numeroEndereco", endereco.getNumeroEndereco()).append("cep", endereco.getCep()).append("tipoEndereco", endereco.getTipoEndereco().name());
            return enderecoDoc;
        }
        return null;
    }

    public static List<Document> toDocument(Set<Endereco> enderecos) {
        if (enderecos != null) {
            List<Document> lista = new ArrayList<Document>();
            for(Endereco endereco : enderecos) {
                lista.add(toDocument(endereco));
            }
            return lista;
        }
        return null;

    }

    public static Endereco documentToEndereco(Document document) {
        Endereco endereco = new Endereco();
        if (document == null) {
            return null;
        } else {
            endereco.setTipoLogradouro(document.getString("tipoLogradouro"));
            endereco.setNomeLogradouro(document.getString("nomeLogradouro"));
            endereco.setNumeroEndereco(document.getInteger("numeroEndereco"));
            endereco.setCep(document.getString("cep"));
            endereco.setTipoEndereco(tipoEndereco(document));
            return endereco;
        }
    }

    public static Set<Endereco> toEndereco(List<Document> listaEndereco) {
        Set<Endereco> enderecos = new HashSet<Endereco>();
        if (listaEndereco != null) {
            for(Document doc : listaEndereco) {
                enderecos.add(documentToEndereco(doc));
            }
            return enderecos;
        }

        return null;
    }

    public static EnderecoType tipoEndereco(Document doc) {
        if (doc.get("tipoEndereco").equals(EnderecoType.COMERCIAL.name())) {
            return EnderecoType.COMERCIAL;
        } else {
            return EnderecoType.RESIDENCIAL;
        }
    }
}
