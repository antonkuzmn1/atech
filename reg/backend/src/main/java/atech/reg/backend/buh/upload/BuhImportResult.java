package atech.reg.backend.buh.upload;

public class BuhImportResult {
    public boolean status = false;
    public int insertedRows = 0;
    public int totalRows = 0;

    public boolean getStatus() {
        return status;
    }

    public int getInsertedRows() {
        return insertedRows;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setInsertedRows(int insertedRows) {
        this.insertedRows = insertedRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
}
