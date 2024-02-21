package atech.reg.backend.buh.table.all;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import atech.reg.backend.buh.contractor.ContractorEntity;
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
@Table(name = "buh_table_all")
public class TableAllEntity {

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

    @Column(nullable = false, length = 255)
    @JsonProperty("destination")
    private String destination;

    @Column(nullable = false)
    @JsonProperty("sum")
    private Double sum;

    @Column(nullable = false)
    @JsonProperty("copyDate")
    private Date copyDate;

    @Column(nullable = false)
    @JsonProperty("origDate")
    private Date origDate;

    @Column(nullable = true, length = 255)
    @JsonProperty("title")
    private String title;

    @Column(nullable = false)
    @JsonProperty("date")
    private Date date;

    @Column(nullable = false)
    @JsonProperty("number")
    private int number;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonProperty("mark")
    private BuhDropdownMarkEntity mark;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonProperty("status")
    private BuhDropdownStatusEntity status;

    @Column(nullable = false, columnDefinition = "tinyint default 0")
    @JsonProperty("deleted")
    private boolean deleted;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}