package com.producter.cagri;

import com.producter.cagri.exception.GraphQLErrorAdapter;
//import com.producter.cagri.graphql.Mutation;
//import com.producter.cagri.graphql.Query;
import com.producter.cagri.model.EnumPositionType;
import com.producter.cagri.model.Player;
import com.producter.cagri.model.Position;
import com.producter.cagri.repository.PlayerRepository;
import com.producter.cagri.repository.PositionRepository;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class PlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerApplication.class, args);
	}

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

	@Bean
	public CommandLineRunner init(PlayerRepository playerRepository, PositionRepository positionRepository) {
		return (args) -> {
			Position position = new Position(EnumPositionType.C);
			positionRepository.save(position);
			positionRepository.save(new Position(EnumPositionType.PF));
			positionRepository.save(new Position(EnumPositionType.PG));
			positionRepository.save(new Position(EnumPositionType.SF));
			positionRepository.save(new Position(EnumPositionType.SG));

			playerRepository.save(new Player("cagri", "zeki", position));
		};
	}

}
