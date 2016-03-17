package br.com.empresa.repository;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.util.ValidationUtil;
import br.com.empresa.repository.util.EmpresaDocument;

public class EmpresaRepository {

    private String host;
    private int port;
    private String db;
    public static final String COLLECTION = "empresa";
    private MongoClient mongoClient;

    public EmpresaRepository(String host, int port, String db) {
        this.host = host;
        this.port = port;
        this.db = db;
    }

    public void saveEmpresa(Empresa empresa) {
        try {
            Document empresaDoc = EmpresaDocument.toDocument(empresa);
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.insertOne(empresaDoc);
        } finally {
            mongoClient.close();
        }
    }

    public void updateEmpresa(Empresa empresa) {
        try {
//            if (ValidationUtil.hasErrors(empresa)) {
//                throw new Exception("Foi encontrado erro em Empresa.");
//            }
            Document empresaDoc = EmpresaDocument.toDocument(empresa);
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateOne(new Document("cnpj", empresa.getCnpj()), new Document("$set", empresaDoc));
        } finally {
            mongoClient.close();
        }
    }

    public void updateEmpresas(Document empresa, String campo, String valorAtual, String novoValor) {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateMany(new Document(campo, valorAtual), new Document("$set", new Document(campo, novoValor)));
        } finally {
            mongoClient.close();
        }
    }

    public void removeEmpresas(Document empresa, String campo, String valorAtual, String novoValor) {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateMany(new Document(campo, valorAtual), new Document("$set", new Document(campo, novoValor)));
        } finally {
            mongoClient.close();
        }
    }

}
