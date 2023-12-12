package noteasy.sundo.queryfactory.persistmodel.portfolio.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.portfolio.Portfolio;
import noteasy.sundo.queryfactory.persistmodel.portfolio.factory.PortfolioQueryFactoryNeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioPersistenceManager extends JpaRepository<Portfolio, Long>, BaseQueryFactory<Portfolio, Long>, PortfolioQueryFactoryNeed {
}
