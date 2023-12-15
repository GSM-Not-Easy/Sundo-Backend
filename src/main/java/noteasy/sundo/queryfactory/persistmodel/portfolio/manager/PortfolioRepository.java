package noteasy.sundo.queryfactory.persistmodel.portfolio.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.portfolio.Portfolio;
import noteasy.sundo.queryfactory.persistmodel.portfolio.factory.PortfolioRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long>, BaseQueryFactory<Portfolio, Long>, PortfolioRepositoryCustom {
}
