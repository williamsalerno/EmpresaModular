package br.com.empresa.repository;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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

    public void saveEmpresa(Document empresa) {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.insertOne(empresa);
        } finally {
            mongoClient.close();
        }
    }

    public void updateEmpresa(Document empresa, String campo, String valor, String novoValor) {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateOne(new Document(campo, valor), new Document("$set", new Document(campo, novoValor)));
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

}
