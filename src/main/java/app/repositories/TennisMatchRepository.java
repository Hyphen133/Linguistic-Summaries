package app.repositories;

import app.data.TennisMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TennisMatchRepository extends JpaRepository<TennisMatch,Long> {
}
