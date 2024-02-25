package atech.reg.backend.buh.table.all;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TableAllRepository extends JpaRepository<TableAllEntity, Long> {
            // TableAllEntity findByInputDateAndContractorAndInitiatorAndDestinationAndSum(
            //             Date inputDate,
            //             ContractorEntity contractor,
            //             InitiatorEntity initiator,
            //             String destination,
            //             Double sum);

            @Query("SELECT t FROM TableAllEntity t " +
                        "WHERE t.inputDate BETWEEN :inputDateFrom AND :inputDateTo " +
                        "AND t.contractor.id IN :contractorIds " +
                        "AND t.initiator.id IN :initiatorIds " +
                        "AND t.destination LIKE %:destination% " +
                        "AND t.sum BETWEEN :sumFrom AND :sumTo " +
                        "AND t.number BETWEEN :numberFrom AND :numberTo " +
                        "AND ((:copyDateNull = true AND t.copyDate IS NULL) OR (t.copyDate BETWEEN :copyDateFrom AND :copyDateTo)) " +
                        "AND ((:origDateNull = true AND t.origDate IS NULL) OR (t.origDate BETWEEN :origDateFrom AND :origDateTo)) " +
                        "AND ((:mainDateNull = true AND t.date IS NULL) OR (t.date BETWEEN :mainDateFrom AND :mainDateTo)) " +
                        "AND t.title LIKE %:title% " +
                        "AND t.mark.id IN :markIds " +
                        "AND t.status.id IN :statusIds")
        List<TableAllEntity> getFilteredTest(
                        Date inputDateFrom, Date inputDateTo,
                        List<Long> contractorIds,
                        List<Long> initiatorIds,
                        String destination,
                        Double sumFrom, Double sumTo,
                        Double numberFrom, Double numberTo,
                        Date copyDateFrom, Date copyDateTo, boolean copyDateNull,
                        Date origDateFrom, Date origDateTo, boolean origDateNull,
                        Date mainDateFrom, Date mainDateTo, boolean mainDateNull,
                        String title,
                        List<Long> markIds, List<Long> statusIds);
}
