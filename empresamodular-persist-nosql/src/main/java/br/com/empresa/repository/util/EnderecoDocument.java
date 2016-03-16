package br.com.empresa.repository.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresawilliam.Endereco;

public class EnderecoDocument {

    private EnderecoDocument() {
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
}
