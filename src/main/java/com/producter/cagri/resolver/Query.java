package com.producter.cagri.resolver;

import com.producter.cagri.model.Player;
import com.producter.cagri.repository.PlayerRepository;
import com.producter.cagri.repository.PositionRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private PlayerRepository playerRepository;
    private PositionRepository positionRepository;

    public Query(PlayerRepository playerRepository, PositionRepository positionRepository) {
        this.playerRepository = playerRepository;
        this.positionRepository = positionRepository;
    }

    public Iterable<Player> findAllPlayers() {
        return playerRepository.findAll();
    }
}
