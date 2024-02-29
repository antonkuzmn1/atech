package atech.reg.backend.buh.upload;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import atech.reg.backend.Tools;
import atech.reg.backend.buh.contractor.ContractorService;
import atech.reg.backend.buh.dropdown.BuhDropdownService;
import atech.reg.backend.buh.initiator.InitiatorService;
import atech.reg.backend.buh.table.main.TableMainEntity;
import atech.reg.backend.buh.table.main.TableMainService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/buh/upload")
public class BuhUploadController {

    @Autowired
    private Tools tools;
    @Autowired
    private TableMainService serviceMain;
    @Autowired
    private ContractorService contractorService;
    @Autowired
    private InitiatorService initiatorService;
    @Autowired
    private BuhDropdownService dropdownService;

    @PostMapping
    public BuhImportResult upload(HttpSession session, @RequestBody String jsonString) {
        BuhImportResult result = new BuhImportResult();
        @SuppressWarnings("unchecked")
        List<Long> groupIds = (List<Long>) session.getAttribute("groups");
        if (groupIds.contains(1L) || groupIds.contains(2L)) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode json = objectMapper.readTree(jsonString);

                for (JsonNode row : json) {
                    Date inputDate = tools.stringToDate(row.get("inputDate").asText());
                    String contrAgent = row.get("contrAgent").asText();
                    String paymentDestination = row.get("paymentDestination").asText();
                    String initiatorOfPayment = row.get("initiatorOfPayment").asText();
                    Double sum = row.get("sum").asDouble();

                    TableMainEntity entity = new TableMainEntity();
                    entity.setInputDate(inputDate);
                    entity.setContractor(contractorService.insert(contrAgent));
                    entity.setDestination(paymentDestination);
                    entity.setInitiator(initiatorService.insert(initiatorOfPayment));
                    entity.setSum(sum);
                    entity.setSumClosing(0D);
                    entity.setCopyDate(null);
                    entity.setOrigDate(null);
                    entity.setTitle("");
                    entity.setAbout(dropdownService.getAboutById(0L));
                    entity.setMark(dropdownService.getMarkById(0L));
                    entity.setStatus(dropdownService.getStatusById(0L));

                    result.totalRows++;
                    if (serviceMain.insert(entity))
                        result.insertedRows++;
                }
                result.status = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
