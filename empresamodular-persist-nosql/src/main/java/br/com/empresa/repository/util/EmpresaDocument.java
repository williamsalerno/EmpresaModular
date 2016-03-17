package br.com.empresa.repository.util;

import org.bson.Document;

import br.com.contmatic.empresawilliam.Empresa;

public final class EmpresaDocument {
    
    private EmpresaDocument() {
    }

    public static Document toDocument(Empresa empresa){
        if(empresa != null){
        Document empresaDoc = new Document("cnpj", empresa.getCnpj())
                .append("razaoSocial", empresa.getRazaoSocial())
                .append("proprietario", empresa.getProprietario())
                .append("email", empresa.getEmail())
                .append("site", empresa.getSite())
                .append("enderecos", EnderecoDocument.toDocument(empresa.getEnderecos()))
                .append("telefones", TelefoneDocument.toDocument(empresa.getTelefones()))
                .append("dataCriacao", empresa.getDataDeCriacao())
                .append("dataAlteracao", empresa.getDataDeAlteracao());
        return empresaDoc;
        }
        return null;
        
    }
}
