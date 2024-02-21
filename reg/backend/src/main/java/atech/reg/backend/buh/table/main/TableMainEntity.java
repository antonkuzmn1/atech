package atech.reg.backend.buh.table.main;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import atech.reg.backend.buh.contractor.ContractorEntity;
import atech.reg.backend.buh.dropdown.data.BuhDropdownAboutEntity;
import atech.reg.backend.buh.dropdown.data.BuhDropdownMarkEntity;
import atech.reg.backend.buh.dropdown.data.BuhDropdownStatusEntity;
import atech.reg.backend.buh.initiator.InitiatorEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "buh_table_main")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableMainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @JsonProperty("inputDate")
    private Date inputDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonProperty("contractor")
    private ContractorEntity contractor;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonProperty("initiator")
    private InitiatorEntity initiator;

    @Column(length = 255, nullable = false)
    @JsonProperty("destination")
    private String destination;

    @Column(nullable = false)
    @JsonProperty("sum")
    private Double sum;

    @Column(nullable = false)
    @JsonProperty("sumClosing")
    private Double sumClosing;

    @Column(nullable = true)
    @JsonProperty("copyDate")
    private Date copyDate;

    @Column(nullable = true)
    @JsonProperty("origDate")
    Date origDate;

    @Column(nullable = false)
    @JsonProperty("title")
    private String title;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonProperty("about")
    private BuhDropdownAboutEntity about;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonProperty("mark")
    private BuhDropdownMarkEntity mark;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonProperty("status")
    private BuhDropdownStatusEntity status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public ContractorEntity getContractor() {
        return contractor;
    }

    public void setContractor(ContractorEntity contractor) {
        this.contractor = contractor;
    }

    public InitiatorEntity getInitiator() {
        return initiator;
    }

    public void setInitiator(InitiatorEntity initiator) {
        this.initiator = initiator;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getSumClosing() {
        return sumClosing;
    }

    public void setSumClosing(Double sumClosing) {
        this.sumClosing = sumClosing;
    }

    public Date getCopyDate() {
        return copyDate;
    }

    public void setCopyDate(Date copyDate) {
        this.copyDate = copyDate;
    }

    public Date getOrigDate() {
        return origDate;
    }

    public void setOrigDate(Date origDate) {
        this.origDate = origDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BuhDropdownAboutEntity getAbout() {
        return about;
    }

    public void setAbout(BuhDropdownAboutEntity about) {
        this.about = about;
    }

    public BuhDropdownMarkEntity getMark() {
        return mark;
    }

    public void setMark(BuhDropdownMarkEntity mark) {
        this.mark = mark;
    }

    public BuhDropdownStatusEntity getStatus() {
        return status;
    }

    public void setStatus(BuhDropdownStatusEntity status) {
        this.status = status;
    }

}
