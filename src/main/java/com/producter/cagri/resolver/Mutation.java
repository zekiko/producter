package com.producter.cagri.resolver;

import com.producter.cagri.constants.Constants;
import com.producter.cagri.exception.NoSuchPlayerException;
import com.producter.cagri.exception.TeamIsFullException;
import com.producter.cagri.model.EnumPositionType;
import com.producter.cagri.model.Player;
import com.producter.cagri.repository.PlayerRepository;
import com.producter.cagri.repository.PositionRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private PlayerRepository playerRepository;
    private PositionRepository positionRepository;

    public Mutation(PlayerRepository playerRepository, PositionRepository positionRepository) {
        this.playerRepository = playerRepository;
        this.positionRepository = positionRepository;
    }

    public Player newPlayer(String name, String surname, EnumPositionType position) {
        Player player = new Player();
        player.setName(name);
        player.setSurname(surname);
        player.setPosition(positionRepository.findByType(position));

        List<Player> team = playerRepository.findAll();
        if(team.size() == Constants.MAX_TEAM_SIZE){
            throw new TeamIsFullException("Cannot add new player: ", player);
        }

        playerRepository.save(player);

        return player;
    }

    public boolean deletePlayer(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if(!player.isPresent()){
            throw new NoSuchPlayerException("Player cannot found: ", id);
        }
        playerRepository.deleteById(id);
        return true;
    }
}
