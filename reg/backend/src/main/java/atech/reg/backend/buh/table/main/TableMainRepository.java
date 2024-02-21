package atech.reg.backend.buh.table.main;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import atech.reg.backend.buh.contractor.ContractorEntity;
import atech.reg.backend.buh.initiator.InitiatorEntity;

@Repository
public interface TableMainRepository extends JpaRepository<TableMainEntity, Long> {

        TableMainEntity findByInputDateAndContractorAndInitiatorAndDestinationAndSum(
                        Date inputDate,
                        ContractorEntity contractor,
                        InitiatorEntity initiator,
                        String destination,
                        Double sum);

        // "AND (:about IS NULL OR t.about IN :about) " +
        // "AND (:mark IS NULL OR t.mark IN :mark) " +
        // "AND (:status IS NULL OR t.status IN :status)")

        @Query("SELECT t FROM TableMainEntity t " +
                        "WHERE t.inputDate BETWEEN :inputDateFrom AND :inputDateTo " +
                        "AND t.contractor.id IN :contractorIds " +
                        "AND t.initiator.id IN :initiatorIds " +
                        "AND t.destination LIKE %:destination% " +
                        "AND t.sum BETWEEN :sumFrom AND :sumTo " +
                        "AND t.sumClosing BETWEEN :sumClosingFrom AND :sumClosingTo " +
                        "AND ((:copyDateNull = true AND t.copyDate IS NULL) OR (t.copyDate BETWEEN :copyDateFrom AND :copyDateTo)) " +
                        "AND ((:origDateNull = true AND t.origDate IS NULL) OR (t.origDate BETWEEN :origDateFrom AND :origDateTo)) " +
                        "AND t.title LIKE %:title% " +
                        "AND t.about.id IN :aboutIds " +
                        "AND t.mark.id IN :markIds " +
                        "AND t.status.id IN :statusIds")
        List<TableMainEntity> getFilteredTest(
                        Date inputDateFrom, Date inputDateTo,
                        List<Long> contractorIds,
                        List<Long> initiatorIds,
                        String destination,
                        Double sumFrom, Double sumTo,
                        Double sumClosingFrom, Double sumClosingTo,
                        Date copyDateFrom, Date copyDateTo, boolean copyDateNull,
                        Date origDateFrom, Date origDateTo, boolean origDateNull,
                        String title,
                        List<Long> aboutIds, List<Long> markIds, List<Long> statusIds);

}
