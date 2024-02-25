package atech.reg.backend.buh.table.all;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import atech.reg.backend.Tools;
import atech.reg.backend.buh.contractor.ContractorEntity;
import atech.reg.backend.buh.contractor.ContractorService;
import atech.reg.backend.buh.dropdown.BuhDropdownService;
import atech.reg.backend.buh.dropdown.data.BuhDropdownStatusEntity;
import atech.reg.backend.buh.initiator.InitiatorEntity;
import atech.reg.backend.buh.initiator.InitiatorService;
import jakarta.servlet.http.HttpSession;

@Service
public class TableAllService {

    @Autowired
    private TableAllRepository repo;
    @Autowired
    private Tools tools;
    @Autowired
    private BuhDropdownService dropdownService;
    @Autowired
    private ContractorService contractorService;
    @Autowired
    private InitiatorService initiatorService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private boolean allow(HttpSession session) {
        try {
            @SuppressWarnings("unchecked")
            List<Long> groupIds = (List<Long>) session.getAttribute("groups");
            boolean allow = (groupIds.contains(1L) || groupIds.contains(2L));
            return allow;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TableAllEntity> get(String data) {
        try {
            // System.out.println("\n\n\nINPUTARRAY:\n" + data);
            JsonNode json = objectMapper.readTree(data);

            Date inputDateFrom = tools.stringToDate(json.get("inputDate").get("from").asText());
            Date inputDateTo = tools.stringToDate(json.get("inputDate").get("to").asText());
            List<Long> contractorIds = objectMapper.convertValue(json.get("contractor"),
                    new TypeReference<List<Long>>() {
                    });
            if (contractorIds.size() == 0)
                contractorIds = contractorService.getAllIds();
            List<Long> initiatorIds = objectMapper.convertValue(json.get("initiator"), new TypeReference<List<Long>>() {
            });
            if (initiatorIds.size() == 0)
                initiatorIds = initiatorService.getAllIds();
            String destination = json.get("destination").asText();
            Double sumFrom = json.get("sum").get("from").asDouble();
            Double sumTo = json.get("sum").get("to").asDouble();
            Double numberFrom = json.get("number").get("from").asDouble();
            Double numberTo = json.get("number").get("to").asDouble();
            Date copyDateFrom = tools.stringToDate(json.get("copyDate").get("from").asText());
            Date copyDateTo = tools.stringToDate(json.get("copyDate").get("to").asText());
            boolean copyDateNull = json.get("copyDate").get("isNull").asBoolean();
            Date origDateFrom = tools.stringToDate(json.get("origDate").get("from").asText());
            Date origDateTo = tools.stringToDate(json.get("origDate").get("to").asText());
            boolean origDateNull = json.get("origDate").get("isNull").asBoolean();
            Date mainDateFrom = tools.stringToDate(json.get("mainDate").get("from").asText());
            Date mainDateTo = tools.stringToDate(json.get("mainDate").get("to").asText());
            boolean mainDateNull = json.get("mainDate").get("isNull").asBoolean();
            String title = json.get("title").asText();
            List<Long> markIds = objectMapper.convertValue(json.get("mark"), new TypeReference<List<Long>>() {
            });
            if (markIds.size() == 0)
                markIds = dropdownService.getAllIds("mark");
            List<Long> statusIds = objectMapper.convertValue(json.get("status"), new TypeReference<List<Long>>() {
            });
            if (statusIds.size() == 0)
                statusIds = dropdownService.getAllIds("status");

            // StringBuilder sb = new StringBuilder("\n\n\nRECIEVED DATA:\n");
            // sb.append("inputDateFrom: ").append(inputDateFrom).append("\n");
            // sb.append("inputDateTo: ").append(inputDateTo).append("\n");
            // sb.append("contractor: ").append(contractorIds).append("\n");
            // sb.append("initiator: ").append(initiatorIds).append("\n");
            // sb.append("destination: ").append(destination).append("\n");
            // sb.append("sumFrom: ").append(sumFrom).append("\n");
            // sb.append("sumTo: ").append(sumTo).append("\n");
            // sb.append("numberFrom: ").append(numberFrom).append("\n");
            // sb.append("numberTo: ").append(numberTo).append("\n");
            // sb.append("copyDateFrom: ").append(copyDateFrom).append("\n");
            // sb.append("copyDateTo: ").append(copyDateTo).append("\n");
            // sb.append("copyDateNull: ").append(copyDateNull).append("\n");
            // sb.append("origDateFrom: ").append(origDateFrom).append("\n");
            // sb.append("origDateTo: ").append(origDateTo).append("\n");
            // sb.append("origDateNull: ").append(origDateNull).append("\n");
            // sb.append("mainDateFrom: ").append(mainDateFrom).append("\n");
            // sb.append("mainDateTo: ").append(mainDateTo).append("\n");
            // sb.append("mainDateNull: ").append(mainDateNull).append("\n");
            // sb.append("title: ").append(title).append("\n");
            // sb.append("mark: ").append(markIds).append("\n");
            // sb.append("status: ").append(statusIds).append("\n");
            // System.out.println(sb.toString());

            List<TableAllEntity> entity = repo.getFilteredTest(
                    inputDateFrom, inputDateTo,
                    contractorIds,
                    initiatorIds,
                    destination,
                    sumFrom, sumTo,
                    numberFrom, numberTo,
                    copyDateFrom, copyDateTo, copyDateNull,
                    origDateFrom, origDateTo, origDateNull,
                    mainDateFrom, mainDateTo, mainDateNull,
                    title,
                    markIds, statusIds);
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TableAllEntity getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public boolean insert(String data) {
        try {
            System.out.println("\n\n\nINSERT!\n\nRAW DATA:\n" + data);
            JsonNode json = objectMapper.readTree(data);

            // DATE
            Date inputDate = new Date();
            System.out.println("inputDate: " + inputDate);
            Date copyDate = tools.stringToDate(json.get("copyDate").asText());
            System.out.println("copyDate: " + copyDate);
            Date origDate = tools.stringToDate(json.get("origDate").asText());
            System.out.println("origDate: " + origDate);
            Date date = tools.stringToDate(json.get("date").asText());
            System.out.println("date: " + date);

            // ENTITY
            ContractorEntity contractor = contractorService.insert(json.get("contractor").asText());
            System.out.println("contractor: " + contractor.getName());
            InitiatorEntity initiator = initiatorService.insert(json.get("initiator").asText());
            System.out.println("initiator: " + initiator.getName());
            BuhDropdownStatusEntity status = dropdownService.getStatusById(json.get("status").get("id").asLong());
            System.out.println("status: " + status.getText());

            // STRING
            String destination = json.get("destination").asText();
            System.out.println("destination: " + destination);
            String title = json.get("title").asText();
            System.out.println("title: " + title);

            // DOUBLE
            Double sum = json.get("sum").asDouble();
            System.out.println("sum: " + sum);
            Double number = json.get("number").asDouble();
            System.out.println("number: " + number);

            TableAllEntity entity = new TableAllEntity();
            entity.setInputDate(inputDate);
            entity.setCopyDate(copyDate);
            entity.setOrigDate(origDate);
            entity.setDate(date);
            entity.setContractor(contractor);
            entity.setInitiator(initiator);
            entity.setMark(dropdownService.getMarkById(0L));
            entity.setStatus(status);
            entity.setDestination(destination);
            entity.setTitle(title);
            entity.setSum(sum);
            entity.setNumber(number);
            repo.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean edit(HttpSession session, String jsonString) {
        try {
            System.out.println(jsonString);
            // TableAllEntity entityNew = objectMapper.readValue(jsonString,
            // TableAllEntity.class);
            // TableAllEntity entity = getById(entityNew.getId());
            // if (allow(session))
            // BeanUtils.copyProperties(entityNew, entity, "id");
            // else
            // BeanUtils.copyProperties(entityNew, entity, "id",
            // "mark",
            // "deleted");
            // repo.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(HttpSession session, String jsonString) {
        try {
            getById(objectMapper.readTree(jsonString).get("id").asLong()).setDeleted(true);
            ;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
