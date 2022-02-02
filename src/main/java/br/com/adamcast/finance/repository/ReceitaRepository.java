package br.com.adamcast.finance.repository;

import br.com.adamcast.finance.model.Receita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends MongoRepository<Receita, String> {
    List<Receita> findByDescricaoContainingIgnoreCase(String descricao);
}
