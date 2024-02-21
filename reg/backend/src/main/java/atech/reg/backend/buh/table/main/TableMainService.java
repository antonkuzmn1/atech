package atech.reg.backend.buh.table.main;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import atech.reg.backend.Tools;
import atech.reg.backend.buh.contractor.ContractorService;
import atech.reg.backend.buh.dropdown.BuhDropdownService;
import atech.reg.backend.buh.dropdown.data.BuhDropdownAboutEntity;
import atech.reg.backend.buh.dropdown.data.BuhDropdownMarkEntity;
import atech.reg.backend.buh.dropdown.data.BuhDropdownStatusEntity;
import atech.reg.backend.buh.initiator.InitiatorService;
import jakarta.servlet.http.HttpSession;

@Service
public class TableMainService {

    @Autowired
    private TableMainRepository repo;
    @Autowired
    private Tools tools;
    @Autowired
    private BuhDropdownService ddService;
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

    public List<TableMainEntity> getAll(String data) {
        try {
            System.out.println("\n\n\nINPUTARRAY:\n" + data);
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
            Double sumClosingFrom = json.get("sumClosing").get("from").asDouble();
            Double sumClosingTo = json.get("sumClosing").get("to").asDouble();
            Date copyDateFrom = tools.stringToDate(json.get("copyDate").get("from").asText());
            Date copyDateTo = tools.stringToDate(json.get("copyDate").get("to").asText());
            boolean copyDateNull = json.get("copyDate").get("null").asBoolean();
            Date origDateFrom = tools.stringToDate(json.get("origDate").get("from").asText());
            Date origDateTo = tools.stringToDate(json.get("origDate").get("to").asText());
            boolean origDateNull = json.get("origDate").get("null").asBoolean();
            String title = json.get("title").asText();
            List<Long> aboutIds = objectMapper.convertValue(json.get("about"), new TypeReference<List<Long>>() {
            });
            if (aboutIds.size() == 0)
                aboutIds = ddService.getAllIds("about");
            List<Long> markIds = objectMapper.convertValue(json.get("mark"), new TypeReference<List<Long>>() {
            });
            if (markIds.size() == 0)
                markIds = ddService.getAllIds("mark");
            List<Long> statusIds = objectMapper.convertValue(json.get("status"), new TypeReference<List<Long>>() {
            });
            if (statusIds.size() == 0)
                statusIds = ddService.getAllIds("status");

            StringBuilder sb = new StringBuilder("\n\n\nRECIEVED DATA:\n");
            sb.append("inputDateFrom: ").append(inputDateFrom).append("\n");
            sb.append("inputDateTo: ").append(inputDateTo).append("\n");
            sb.append("contractor: ").append(contractorIds).append("\n");
            sb.append("initiator: ").append(initiatorIds).append("\n");
            sb.append("destination: ").append(destination).append("\n");
            sb.append("sumFrom: ").append(sumFrom).append("\n");
            sb.append("sumTo: ").append(sumTo).append("\n");
            sb.append("sumClosingFrom: ").append(sumClosingFrom).append("\n");
            sb.append("sumClosingTo: ").append(sumClosingTo).append("\n");
            sb.append("copyDateFrom: ").append(copyDateFrom).append("\n");
            sb.append("copyDateTo: ").append(copyDateTo).append("\n");
            sb.append("copyDateNull: ").append(copyDateNull).append("\n");
            sb.append("origDateFrom: ").append(origDateFrom).append("\n");
            sb.append("origDateTo: ").append(origDateTo).append("\n");
            sb.append("origDateNull: ").append(origDateNull).append("\n");
            sb.append("title: ").append(title).append("\n");
            sb.append("about: ").append(aboutIds).append("\n");
            sb.append("mark: ").append(markIds).append("\n");
            sb.append("status: ").append(statusIds).append("\n");
            System.out.println(sb.toString());

            return repo.getFilteredTest(
                    inputDateFrom, inputDateTo,
                    contractorIds,
                    initiatorIds,
                    destination,
                    sumFrom, sumTo,
                    sumClosingFrom, sumClosingTo,
                    copyDateFrom, copyDateTo, copyDateNull,
                    origDateFrom, origDateTo, origDateNull,
                    title,
                    aboutIds, markIds, statusIds);
            // return repo.getFilteredAll(
            // inputDateFrom, inputDateTo,
            // contractor,
            // initiator,
            // destination,
            // sumFrom, sumTo,
            // sumClosingFrom, sumClosingTo,
            // copyDateFrom, copyDateTo, copyDateNull,
            // origDateFrom, origDateTo, origDateNull,
            // title,
            // about,
            // mark,
            // status);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert(TableMainEntity entity) {
        if (repo.findByInputDateAndContractorAndInitiatorAndDestinationAndSum(
                entity.getInputDate(),
                entity.getContractor(),
                entity.getInitiator(),
                entity.getDestination(),
                entity.getSum()) == null) {
            repo.save(entity);
            return true;
        } else
            return false;
    }

    public boolean edit(HttpSession session, String jsonString) {
        try {
            System.out.println(jsonString);
            JsonNode jsonList = objectMapper.readTree(jsonString);
            for (JsonNode json : jsonList) {
                Long id = json.get("id").asLong();
                Double sumClosing = json.get("sumClosing").asDouble();
                BuhDropdownAboutEntity about = ddService.getAboutById(json.get("about").get("id").asLong());
                BuhDropdownMarkEntity mark = ddService.getMarkById(json.get("mark").get("id").asLong());
                BuhDropdownStatusEntity status = ddService.getStatusById(json.get("status").get("id").asLong());
                Date copyDate = tools.stringToDate(json.get("copyDate").asText());
                Date origDate = tools.stringToDate(json.get("origDate").asText());
                String title = json.get("title").asText();

                System.out.println("sumC: " + sumClosing);
                System.out.println("about: " + about.getText());
                System.out.println("mark: " + mark.getText());
                System.out.println("status: " + status.getText());
                System.out.println("dcopy: " + copyDate);
                System.out.println("dorig: " + origDate);

                TableMainEntity entity = repo.findById(id).orElseThrow();
                entity.setSumClosing(sumClosing);
                entity.setAbout(about);
                entity.setStatus(status);
                entity.setTitle(title);
                if (allow(session)) {
                    entity.setMark(mark);
                    entity.setCopyDate(copyDate);
                    entity.setOrigDate(origDate);
                }
                repo.save(entity);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
