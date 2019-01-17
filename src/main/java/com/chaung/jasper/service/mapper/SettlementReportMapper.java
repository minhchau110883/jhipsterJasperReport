package com.chaung.jasper.service.mapper;

import com.chaung.jasper.domain.*;
import com.chaung.jasper.service.dto.SettlementReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SettlementReport and its DTO SettlementReportDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SettlementReportMapper extends EntityMapper<SettlementReportDTO, SettlementReport> {



    default SettlementReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        SettlementReport settlementReport = new SettlementReport();
        settlementReport.setId(id);
        return settlementReport;
    }
}
