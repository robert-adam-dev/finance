package br.com.adamcast.finance.repository;

import br.com.adamcast.finance.model.Despesa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends MongoRepository<Despesa, String> {
    List<Despesa> findByDescricaoContainingIgnoreCase(String descricao);
}
