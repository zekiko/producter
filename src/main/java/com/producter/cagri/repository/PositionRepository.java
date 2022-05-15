package com.producter.cagri.repository;

import com.producter.cagri.model.EnumPositionType;
import com.producter.cagri.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByType(EnumPositionType type);
}

