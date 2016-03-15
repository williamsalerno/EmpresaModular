package br.com.empresa.repository.util;

import org.bson.Document;

import br.com.contmatic.empresawilliam.Empresa;

public final class EmpresaDocument {
    
    private EmpresaDocument() {
    }

    public static Document toDocument(Empresa empresa){
        Document empresaDoc = new Document("razaoSocial", empresa.getRazaoSocial())
                .append("cnpj", empresa.getCnpj())
                .append("proprietario", empresa.getProprietario())
                .append("email", empresa.getEmail())
                .append("site", empresa.getSite())
                .append("enderecos", EnderecoDocument.toDocument(empresa.getEnderecos()))
//                .append("telefones", empresa.getTelefones())
                .append("dataCriacao", empresa.getDataDeCriacao())
                .append("dataAlteracao", empresa.getDataDeAlteracao());
        return empresaDoc;
        
    }
}
