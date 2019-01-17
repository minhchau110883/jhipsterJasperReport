package com.chaung.jasper.repository;

import com.chaung.jasper.domain.SettlementReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SettlementReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SettlementReportRepository extends JpaRepository<SettlementReport, Long>, JpaSpecificationExecutor<SettlementReport> {

}
