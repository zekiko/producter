package com.producter.cagri.resolver;

import com.producter.cagri.model.Player;
import com.producter.cagri.model.Position;
import com.producter.cagri.repository.PositionRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class PlayerResolver implements GraphQLResolver<Player> {
    private PositionRepository positionRepository;

    public PlayerResolver(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position getPosition(Player player) {
        return positionRepository.findById(player.getPosition()
                        .getId())
                .orElseThrow(null);
    }
}
