package noteasy.sundo.queryfactory.portfolio.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.portfolio.Portfolio;
import noteasy.sundo.queryfactory.portfolio.factory.PortfolioRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long>, BaseQueryFactory<Portfolio, Long>, PortfolioRepositoryCustom {
}
